package com.cristiandpt.device_emitter.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal
import java.time.LocalDateTime

class BloodPressureKafkaDto(
        @JsonProperty("record_id") val id: Long?,
        @JsonProperty("measurement_timestamp") val timestamp: LocalDateTime,
        @JsonProperty("pulse_rate") val pulseRate: BigDecimal,
        @JsonProperty("user_id") val userId: BigDecimal,
        val systolic: BigDecimal,
        val diastolic: BigDecimal
)
