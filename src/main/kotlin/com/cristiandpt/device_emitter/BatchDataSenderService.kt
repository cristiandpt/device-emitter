package com.cristiandpt.device_emitter

import com.cristiandpt.device_emitter.dto.BloodPressureKafkaDto
import com.cristiandpt.device_emitter.event.BloodPressureMeasuredEvent
import com.cristiandpt.device_emitter.models.BloodPressureMeasurement
import com.cristiandpt.device_emitter.repository.MeasurementRepository
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

    @Async
    @Scheduled(cron = "0 0/2 * * * *")
    fun sendMeasuresBatch() {
        println("Sending measures to kafka cluster")
        val entities = repository.fetchTop10Measurements()
        if (entities.isEmpty()) return
        entities.forEach {
            conversionService.convert(it, BloodPressureMeasurement::class.java)?.let {
                val event = BloodPressureMeasuredEvent(this, it)
                eventPublisher.publishEvent(event)
            }
        }
        }
    }
}
