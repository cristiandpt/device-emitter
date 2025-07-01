package com.cristiandpt.device_emitter

import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.concurrent.ThreadLocalRandom
import org.springframework.scheduling.TaskScheduler
import org.springframework.stereotype.Component

@Component
class MeasurementTask constructor(private val taskExecutor: TaskScheduler) {

    inner class MeasureGeneration() : Runnable {
        override fun run() {
            val delaySeconds = ThreadLocalRandom.current().nextLong(5, 20)
            println("Random: $delaySeconds")
            val nextExecutionTime = Instant.now().plus(delaySeconds, ChronoUnit.SECONDS)
            try {
                println("asdf")
            } catch (e: Exception) {
                println("Error executing task: ${e.message}")
            } finally {
                taskExecutor.schedule(MeasureGeneration(), nextExecutionTime)
            }
        }
    }
}
