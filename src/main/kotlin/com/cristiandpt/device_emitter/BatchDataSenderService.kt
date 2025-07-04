package com.cristiandpt.device_emitter

import com.cristiandpt.device_emitter.dto.BloodPressureKafkaDto
import com.cristiandpt.device_emitter.models.BloodPressureMeasurement
import com.cristiandpt.device_emitter.repository.MeasurementRepository
import org.springframework.core.convert.ConversionService
import org.springframework.scheduling.annotation.Async
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class BatchDataSenderService
constructor(
        private val repository: MeasurementRepository,
        private val kafkaProducer: KafkaProducerService<Any>,
        private val conversionService: ConversionService
) {

    @Async
    @Scheduled(cron = "0 0/2 * * * *")
    fun sendMeasuresBatch() {
        println("Sending measures to kafka cluster")
        val entities = repository.fetchTop10Measurements()
        if (entities.isEmpty()) return
        entities.forEach {
            val measurementDto =
                    conversionService.convert(it, BloodPressureMeasurement::class.java)?.let {
                        conversionService.convert(it, BloodPressureKafkaDto::class.java)
                    }
            measurementDto?.also { dto -> kafkaProducer.sendMessage(dto) }
        }
    }
}
