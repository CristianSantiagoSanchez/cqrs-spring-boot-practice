package es.plexus.service.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@Profile("dev")
public class KafkaConfig {
    @Bean
    public NewTopic userUpdated(){
        return TopicBuilder.name("user_updated")
                .partitions(3)
                .replicas(1)
                .build();
    }

}
