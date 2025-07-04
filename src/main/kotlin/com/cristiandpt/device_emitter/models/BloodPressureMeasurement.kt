package com.cristiandpt.device_emitter.models

import java.math.BigDecimal
import java.time.LocalDateTime

data class BloodPressureMeasurement(
        val userId: BigDecimal,
        val systolic: BigDecimal,
        val diastolic: BigDecimal,
        val meanArterialPressure: BigDecimal,
        val pulseRate: BigDecimal,
        val measuredAt: LocalDateTime
)
