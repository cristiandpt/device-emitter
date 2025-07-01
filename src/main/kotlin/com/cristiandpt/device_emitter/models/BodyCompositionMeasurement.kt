package com.cristiandpt.device_emitter.models

import java.math.BigDecimal

data class BodyCompositionMeasurement(
        val timeStamp: String,
        val userId: BigDecimal,
        val basalMetabolism: BigDecimal,
        val musclePercentage: BigDecimal,
        val muscleMass: BigDecimal,
        val fatFreeMass: BigDecimal,
        val sftLeanMass: BigDecimal,
        val bodyWaterMass: BigDecimal,
        val weight: BigDecimal,
        val height: BigDecimal,
        val impedance: BigDecimal
)
