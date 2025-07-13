package com.cristiandpt.device_emitter

import com.cristiandpt.device_emitter.dto.BloodPressureKafkaDto
import java.math.BigDecimal
import java.time.LocalDateTime
import kotlin.test.assertEquals
import org.apache.kafka.clients.consumer.Consumer
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.test.EmbeddedKafkaBroker
import org.springframework.kafka.test.context.EmbeddedKafka
import org.springframework.kafka.test.utils.KafkaTestUtils
import org.springframework.test.annotation.DirtiesContext

@SpringBootTest
@DirtiesContext
@EmbeddedKafka(
        partitions = 1,
        brokerProperties =
                [
                        "listeners=PLAINTEXT://localhost:\${spring.kafka.bootstrap-servers.port}",
                        "port=\${spring.kafka.bootstrap-servers.port}"]
)
class KafkaProducerServiceTest {

    @Autowired private lateinit var producer: KafkaProducerService<BloodPressureKafkaDto>

    @Autowired private lateinit var embeddedKafkaBroker: EmbeddedKafkaBroker

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
        val consumer = setupConsumer()

        // When
        producer.sendMessage(dto)

        // Then
        val singleRecord = KafkaTestUtils.getSingleRecord(consumer, "measurement-topic")
        assertEquals(dto.id, singleRecord.value().id)
        assertEquals(dto.timestamp, singleRecord.value().timestamp)
        assertEquals(dto.pulseRate, singleRecord.value().pulseRate)
        assertEquals(dto.userId, singleRecord.value().userId)
        assertEquals(dto.systolic, singleRecord.value().systolic)
        assertEquals(dto.diastolic, singleRecord.value().diastolic)
        consumer.close()
    }

    private fun setupConsumer(): Consumer<String, BloodPressureKafkaDto> {
        val consumerProps = KafkaTestUtils.consumerProps("test-group", "true", embeddedKafkaBroker)
        val consumerFactory =
                DefaultKafkaConsumerFactory<String, BloodPressureKafkaDto>(consumerProps)
        val consumer = consumerFactory.createConsumer()
        embeddedKafkaBroker.consumeFromAnEmbeddedTopic(consumer, "measurement-topic")
        return consumer
    }
}
