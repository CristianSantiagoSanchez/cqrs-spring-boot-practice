package es.plexus.service.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import es.plexus.entity.event.BookSwapEvent;
import es.plexus.service.event.EventPublisher;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
@Slf4j
public class KafkaProducer implements EventPublisher {
    @Autowired
    KafkaTemplate<Integer, String> kafkaTemplate;



    public void publish(BookSwapEvent event) {

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        Integer key = 1;

        String value = null;

        try {
            value = mapper.writeValueAsString(event);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        ProducerRecord<Integer, String> producerRecord = buildProducerRecord(event.getTopic(), key, value);


        CompletableFuture<SendResult<Integer, String>> completableFuture = kafkaTemplate.send(producerRecord);
        final String valueFinal = value;
        completableFuture.handleAsync((result, ex) -> {
            if (ex != null) {
                handleFailure(key, valueFinal, ex);
                return null;
            } else {
                handleSuccess(key, valueFinal, result);
                return result;
            }
        });

    }

    private ProducerRecord<Integer, String> buildProducerRecord(String topic, Integer key, String value) {
        return new ProducerRecord<>(topic, key, value);
    }

    private void handleFailure(Integer key, String value, Throwable ex) {
        log.error("Error Sending the Message and the exception is {}", ex.getMessage());
        try {
            throw ex;
        } catch (Throwable throwable) {
            log.error("Error in OnFailure: {}", throwable.getMessage());
        }


    }

    private void handleSuccess(Integer key, String value, SendResult<Integer, String> result) {
        log.info("Message Sent SuccessFully for the key : {} and the value is {} , partition is {}", key, value, result.getRecordMetadata().partition());
    }


}
