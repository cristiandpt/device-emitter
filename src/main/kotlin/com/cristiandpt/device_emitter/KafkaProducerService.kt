package com.cristiandpt.device_emitter

import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class KafkaProducerService constructor(val kafkaTemplate: KafkaTemplate<String, String>) {

    // Inject the topic name from application.properties or define it directly
    @Value("\${kafka.topic.name:measurement-topic}") // Default to "measurement-topic" if not set
    lateinit var topicName: String

    /**
     * Sends a message to the configured Kafka topic.
     * @param message The message to send.
     */
    fun sendMessage(message: String) {
        kafkaTemplate.send(topicName, message)

        // For more robust error handling and callbacks:
        // ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topicName,
        // message);
        // future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
        //     @Override
        //     public void onSuccess(SendResult<String, String> result) {
        //         log.info("Message sent successfully to topic '{}' with offset {}",
        //                  result.getRecordMetadata().topic(),
        // result.getRecordMetadata().offset());
        //     }
        //
        //     @Override
        //     public void onFailure(Throwable ex) {
        //         log.error("Failed to send message to topic '{}': {}", topicName,
        // ex.getMessage());
        //     }
        // });
    }

    /**
     * Sends a message with a specific key to the configured Kafka topic. Keys are used for
     * partitioning messages, ensuring messages with the same key go to the same partition.
     * @param key The key for the message.
     * @param message The message to send.
     */
    fun sendMessage(key: String, message: String) {
        kafkaTemplate.send(topicName, key, message)
    }
}
