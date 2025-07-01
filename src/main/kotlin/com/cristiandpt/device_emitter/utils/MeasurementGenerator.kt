package com.cristiandpt.device_emitter.utils

import BloodPressureMeasurement
import com.cristiandpt.device_emitter.models.MeasurementType
import com.cristiandpt.device_emitter.models.MeasurementType.*
import java.math.BigDecimal
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class MeasurementGenerator() {

    companion object {

        fun measurementFactory(measureType: MeasurementType): Any =
                when (measureType) {
                    BodyComposition -> Unit
                    BloodPressure -> generateBloodPressureData()
                    Weight -> Unit
                }

        fun generateBloodPressureData(): BloodPressureMeasurement {
            val min = BigDecimal("10.00")
            val max = BigDecimal("20.00")
            val zonedDateTime = Instant.now().run { atZone(ZoneId.systemDefault()) }

            val formattedString: String =
                    zonedDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
            return BloodPressureMeasurement(
                    timeStamp = formattedString,
                    userId = BigDecimal("1.0"),
                    diastolic = generateRandomBigDecimal(min, max),
                    systolic = generateRandomBigDecimal(min, max),
                    meanArterialPressure = generateRandomBigDecimal(min, max),
                    pulseRate = generateRandomBigDecimal(min, max)
            )
        }
    }
}
