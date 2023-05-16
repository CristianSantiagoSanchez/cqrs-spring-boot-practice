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
    public NewTopic swapRegister(){
        return TopicBuilder.name("swap_register")
                .partitions(3)
                .replicas(1)
                .build();
    }
    @Bean
    public NewTopic swapPatched(){
        return TopicBuilder.name("swap_patched")
                .partitions(3)
                .replicas(1)
                .build();
    }
}
