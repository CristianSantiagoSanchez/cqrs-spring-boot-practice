package es.plexus.service.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import es.plexus.entity.event.SwapPatchedEvent;
import es.plexus.entity.event.SwapRegisterEvent;
import es.plexus.entity.swap.Swap;
import es.plexus.service.email.EmailSender;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaConsumer {

    @Autowired
    private EmailSender emailSender;

    @KafkaListener(topics = {"swap_register"}, groupId = "swap_consumer")
    public void swapRegister(ConsumerRecord<Integer,String> consumerRecord) throws JsonProcessingException {
        log.info("ConsumerRecord : {} ", consumerRecord );
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        SwapRegisterEvent swapRegisterEvent = mapper.readValue(consumerRecord.value(), SwapRegisterEvent.class);
        Swap swapCreated = swapRegisterEvent.getSwap();

        this.emailSender.sendNotification("You have a pending swap request 🚀", swapCreated.getUserTarget().getEmail(), swapCreated);
    }
    @KafkaListener(topics = {"swap_patched"}, groupId = "swap_consumer")
    public void swapPatched(ConsumerRecord<Integer,String> consumerRecord) throws JsonProcessingException {
        log.info("ConsumerRecord : {} ", consumerRecord );
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        SwapPatchedEvent swapPatchedEvent = mapper.readValue(consumerRecord.value(), SwapPatchedEvent.class);
        Swap swapUpdated = swapPatchedEvent.getSwap();

        this.emailSender.sendNotification("Your swap has been " + swapUpdated.getSwapState() +" 🎈", swapUpdated.getUserOrigin().getEmail(),swapUpdated);
    }
}
