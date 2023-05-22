package es.plexus.service.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaConsumer {
/*

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
*/
}
