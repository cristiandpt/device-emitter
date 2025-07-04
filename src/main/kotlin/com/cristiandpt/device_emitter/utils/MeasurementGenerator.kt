package com.cristiandpt.device_emitter.utils

import com.cristiandpt.device_emitter.models.BloodPressureMeasurement
import com.cristiandpt.device_emitter.models.MeasurementType
import com.cristiandpt.device_emitter.models.MeasurementType.*
import java.math.BigDecimal
import java.time.Instant
import java.time.ZoneId

class MeasurementGenerator() {

    companion object {

        fun measurementFactory(measureType: MeasurementType): Any =
                when (measureType) {
                    BodyComposition -> Unit
                    BloodPressure -> generateBloodPressureData()
                    Weight -> Unit
                }

        fun generateBloodPressureData(): BloodPressureMeasurement {
            val min = BigDecimal("50.00")
            val max = BigDecimal("200.00")
            val zonedDateTime = Instant.now().run { atZone(ZoneId.systemDefault()) }

            return BloodPressureMeasurement(
                    measuredAt = zonedDateTime.toLocalDateTime(),
                    userId = BigDecimal("1"),
                    diastolic = generateRandomBigDecimal(min, max),
                    systolic = generateRandomBigDecimal(min, max),
                    meanArterialPressure = generateRandomBigDecimal(min, max),
                    pulseRate = generateRandomBigDecimal(min, max)
            )
        }
    }
}
