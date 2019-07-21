package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.NewoApp9UaaKafkaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/newo-app-9-uaa-kafka")
public class NewoApp9UaaKafkaResource {

    private final Logger log = LoggerFactory.getLogger(NewoApp9UaaKafkaResource.class);

    private NewoApp9UaaKafkaProducer kafkaProducer;

    public NewoApp9UaaKafkaResource(NewoApp9UaaKafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @PostMapping(value = "/publish")
    public void sendMessageToKafkaTopic(@RequestParam("message") String message) {
        log.debug("REST request to send to Kafka topic the message : {}", message);
        this.kafkaProducer.sendMessage(message);
    }
}
