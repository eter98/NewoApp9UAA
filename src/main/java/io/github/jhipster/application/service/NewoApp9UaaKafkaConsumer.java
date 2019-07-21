package io.github.jhipster.application.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class NewoApp9UaaKafkaConsumer {

    private final Logger log = LoggerFactory.getLogger(NewoApp9UaaKafkaConsumer.class);
    private static final String TOPIC = "topic_newoapp9uaa";

    @KafkaListener(topics = "topic_newoapp9uaa", groupId = "group_id")
    public void consume(String message) throws IOException {
        log.info("Consumed message in {} : {}", TOPIC, message);
    }
}
