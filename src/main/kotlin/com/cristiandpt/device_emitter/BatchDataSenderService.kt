package com.cristiandpt.device_emitter

import com.cristiandpt.device_emitter.repository.MeasurementRepository
import org.springframework.scheduling.annotation.Async
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class BatchDataSenderService
constructor(
        private val repository: MeasurementRepository,
        private val kafkaProducer: KafkaProducerService
) {

    @Async
    @Scheduled(cron = "0 0/2 * * * *")
    fun sendMeasuresBatch() {
        println("Sending measures to kafka cluster")
        val entities = repository.fetchTop10Measurements()
        if (entities.isEmpty()) return
        entities.forEach { kafkaProducer.sendMessage(it) }
    }
}
