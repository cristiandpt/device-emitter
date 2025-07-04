package com.cristiandpt.device_emitter

import BloodPressureMeasurement
import com.cristiandpt.device_emitter.models.MeasurementType
import com.cristiandpt.device_emitter.repository.MeasurementRepository
import com.cristiandpt.device_emitter.utils.MeasurementGenerator
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.concurrent.ThreadLocalRandom
import org.springframework.scheduling.TaskScheduler
import org.springframework.stereotype.Component

@Component
class MeasurementTask
constructor(
        private val taskExecutor: TaskScheduler,
        private val repository: MeasurementRepository
) {

    inner class MeasureGeneration() : Runnable {
        override fun run() {
            val delaySeconds = ThreadLocalRandom.current().nextLong(1, 15)
            println("Random: $delaySeconds")
            val nextExecutionTime = Instant.now().plus(delaySeconds, ChronoUnit.SECONDS)
            try {
                val bloodMeasurements =
                        MeasurementGenerator.measurementFactory(MeasurementType.BloodPressure) as?
                                BloodPressureMeasurement
                bloodMeasurements?.let { repository.saveBloodMeasurement(it) }
                println("Generating the blood pressure ${bloodMeasurements}")
            } catch (e: Exception) {
                println("Error executing task: ${e.message}")
            } finally {
                taskExecutor.schedule(MeasureGeneration(), nextExecutionTime)
            }
        }
    }
}
