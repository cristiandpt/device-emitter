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
import org.springframework.transaction.annotation.EnableTransactionManagement

@SpringBootApplication
@EnableTransactionManagement
class DeviceEmitterApplication
@Autowired
constructor(
        private val taskScheduler: TaskScheduler,
        private val measurementTask: MeasurementTask
) : ApplicationRunner {

    override fun run(args: ApplicationArguments) {
        taskScheduler.schedule(
                measurementTask.MeasureGeneration(),
                Instant.now().plus(1, ChronoUnit.SECONDS)
        )
        println("Start generating measurements!")
    }
}

fun main(args: Array<String>) {
    runApplication<DeviceEmitterApplication>(*args)
}
