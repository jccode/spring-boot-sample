package com.github.jccode.springbootkafkademo.jsondemo2;


import com.github.jccode.springbootkafkademo.jsondemo2.event.EventProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate(ProducerFactory<String, String> producerFactory) {
        KafkaTemplate<String, String> template = new KafkaTemplate<>(producerFactory);
        template.setMessageConverter(new StringJsonMessageConverter());
        template.setDefaultTopic("SPRING_MESSAGEING_TOPIC");
        return template;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> jsonKafkaListenerContainerFactory(ConsumerFactory<String, String> consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, String> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        factory.setMessageConverter(new StringJsonMessageConverter());
        return factory;
    }

    @Autowired
    private EventProducer producer;

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            producer.produceEvents1();
            producer.produceEvents2();
        };
    }
}
