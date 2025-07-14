/*
package com.cristiandpt.device_emitter

import com.cristiandpt.device_emitter.dto.BloodPressureKafkaDto
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.concurrent.BlockingQueue
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.TimeUnit
import kotlin.test.assertEquals
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory
import org.springframework.kafka.test.context.EmbeddedKafka
import org.springframework.stereotype.Component
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.TestPropertySource

@SpringBootTest
@DirtiesContext
@EmbeddedKafka(partitions = 1)
@TestPropertySource(properties = ["spring.kafka.bootstrap-servers=\${spring.embedded.kafka.brokers}"])
class KafkaProducerServiceTest {

    @Autowired
    private lateinit var producer: BloodPressureKafkaDtoProducer

    @Autowired
    private lateinit var consumer: TestConsumer

    @Test
    fun `should send message to kafka topic`() {
        // Given
        val now = LocalDateTime.now()
        val dto =
                BloodPressureKafkaDto(
                        id = 1L,
                        timestamp = now,
                        pulseRate = BigDecimal(60),
                        userId = BigDecimal(1),
                        systolic = BigDecimal(120),
                        diastolic = BigDecimal(80)
                )

        // When
        producer.sendMessage(dto)

        // Then
        val singleRecord = consumer.records.poll(10, TimeUnit.SECONDS)
        assertEquals(dto.id, singleRecord.value().id)
        assertEquals(dto.timestamp, singleRecord.value().timestamp)
        assertEquals(dto.pulseRate, singleRecord.value().pulseRate)
        assertEquals(dto.userId, singleRecord.value().userId)
        assertEquals(dto.systolic, singleRecord.value().systolic)
        assertEquals(dto.diastolic, singleRecord.value().diastolic)
    }

    @Component
    class TestConsumer {
        val records: BlockingQueue<ConsumerRecord<String, BloodPressureKafkaDto>> = LinkedBlockingQueue()

        @KafkaListener(topics = ["measurement-topic"], groupId = "test-group")
        fun receive(record: ConsumerRecord<String, BloodPressureKafkaDto>) {
            records.add(record)
        }
    }

    @TestConfiguration
    class KafkaTestConfig {
        @Bean
        fun kafkaTemplate(producerFactory: ProducerFactory<String, BloodPressureKafkaDto>): KafkaTemplate<String, BloodPressureKafkaDto> {
            return KafkaTemplate(producerFactory)
        }
    }
}
*/
