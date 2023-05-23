package es.plexus.service.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import es.plexus.entity.event.UserUpdatedEvent;
import es.plexus.entity.user.User;
import es.plexus.repository.user.query.UserQueryRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserUpdatedKafkaConsumer {
    @Autowired
    UserQueryRepository userQueryRepository;

    @KafkaListener(topics = {"user_updated"}, groupId = "user_consumer")
    public void swapRegister(ConsumerRecord<Integer,String> consumerRecord) throws JsonProcessingException {
        log.info("ConsumerRecord : {} ", consumerRecord );
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        UserUpdatedEvent userUpdatedEvent = mapper.readValue(consumerRecord.value(), UserUpdatedEvent.class);
        User userUpdated = userUpdatedEvent.getUser();

        userQueryRepository.updateUser(userUpdated);
    }
}
