package com.cristiandpt.device_emitter

import com.cristiandpt.device_emitter.event.BloodPressureMeasuredEvent
import com.cristiandpt.device_emitter.models.BloodPressureMeasurement
import com.cristiandpt.device_emitter.repository.MeasurementRepository
import org.slf4j.LoggerFactory
import org.springframework.context.ApplicationEventPublisher
import org.springframework.core.convert.ConversionService
import org.springframework.scheduling.annotation.Async
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class BatchDataSenderService
constructor(
        private val repository: MeasurementRepository,
        private val kafkaProducer: KafkaProducerService<Any>,
        private val conversionService: ConversionService,
        private val eventPublisher: ApplicationEventPublisher
) {

    private val log = LoggerFactory.getLogger(javaClass)

    @Async
    @Scheduled(cron = "\${batch.sender.cron}")
    fun sendMeasuresBatch() {
        log.info("Sending measures to kafka cluster")
        val entities = repository.fetchTop10Measurements()
        if (entities.isEmpty()) return
        entities.forEach { entity ->
            try {
                conversionService.convert(entity, BloodPressureMeasurement::class.java)?.let {
                    val event = BloodPressureMeasuredEvent(this, it)
                    eventPublisher.publishEvent(event)
                    repository.deleteById(entity.id)
                }
            } catch (e: Exception) {
                log.error("Error processing measurement with id {}: {}", entity.id, e.message, e)
            }
        }
    }
}
