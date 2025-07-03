package com.cristiandpt.device_emitter

import java.time.Instant
import java.time.temporal.ChronoUnit
import kotlinx.coroutines.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.TaskScheduler

@SpringBootApplication
class DeviceEmitterApplication
@Autowired
constructor(
        private val taskScheduler: TaskScheduler,
        private val measurementTask: MeasurementTask,
        private val kafkaProducerService: KafkaProducerService
) : ApplicationRunner {

    override fun run(args: ApplicationArguments) {
        kafkaProducerService.sendMessage("Hello, ", "Cristian")
        taskScheduler.schedule(
                measurementTask.MeasureGeneration(),
                Instant.now().plus(1, ChronoUnit.SECONDS)
        )
        println("All work done!")
    }
}

fun main(args: Array<String>) {
    runApplication<DeviceEmitterApplication>(*args)
}
