package com.cristiandpt.device_emitter

import com.cristiandpt.device_emitter.models.BloodPressureMeasurement
import com.cristiandpt.device_emitter.models.MeasurementType
import com.cristiandpt.device_emitter.repository.MeasurementRepository
import com.cristiandpt.device_emitter.utils.MeasurementGenerator
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.concurrent.ThreadLocalRandom
import org.slf4j.LoggerFactory
import org.springframework.scheduling.TaskScheduler
import org.springframework.stereotype.Component

@Component
class MeasurementTask
constructor(
        private val taskExecutor: TaskScheduler,
        private val repository: MeasurementRepository
) {

    private val log = LoggerFactory.getLogger(javaClass)

    inner class MeasureGeneration() : Runnable {
        override fun run() {
            val delaySeconds = ThreadLocalRandom.current().nextLong(1, 15)
            log.info("Random: $delaySeconds")
            val nextExecutionTime = Instant.now().plus(delaySeconds, ChronoUnit.SECONDS)
            try {
                val bloodMeasurements =
                        MeasurementGenerator.measurementFactory(MeasurementType.BloodPressure) as?
                                BloodPressureMeasurement
                bloodMeasurements?.let { repository.saveBloodMeasurement(it) }
                log.info("Generating the blood pressure ${bloodMeasurements}")
            } catch (e: Exception) {
                log.error("Error executing task: ${e.message}", e)
            } finally {
                taskExecutor.schedule(MeasureGeneration(), nextExecutionTime)
            }
        }
    }
}
