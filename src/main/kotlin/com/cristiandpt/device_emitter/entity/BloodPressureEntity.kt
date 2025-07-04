package com.cristiandpt.device_emitter.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.validation.constraints.*
import java.math.BigDecimal

@Entity
@Table(name = "blood_pressure")
data class BloodPressureEntity(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0L,
        @field:NotNull(message = "User ID is mandatory")
        @field:Digits(
                integer = 20,
                fraction = 0,
                message = "User ID must be a valid number with no decimal places"
        )
        @Column(name = "user_id", precision = 20, scale = 0)
        val userId: BigDecimal,
        @field:NotNull(message = "Systolic pressure is mandatory")
        @field:DecimalMin(value = "50.00", message = "Systolic pressure must be at least 50.00")
        @field:DecimalMax(value = "250.00", message = "Systolic pressure must be at most 250.00")
        @Column(name = "systolic", precision = 5, scale = 2)
        val systolic: BigDecimal,
        @field:NotNull(message = "Diastolic pressure is mandatory")
        @field:DecimalMin(value = "30.00", message = "Diastolic pressure must be at least 30.00")
        @field:DecimalMax(value = "200.00", message = "Diastolic pressure must be at most 200.00")
        @Column(name = "diastolic", precision = 5, scale = 2)
        val diastolic: BigDecimal,
        @field:NotNull(message = "Mean arterial pressure is mandatory")
        @field:DecimalMin(
                value = "40.00",
                message = "Mean arterial pressure must be at least 40.00"
        )
        @field:DecimalMax(
                value = "220.00",
                message = "Mean arterial pressure must be at most 220.00"
        )
        @Column(name = "mean_arterial_pressure", precision = 5, scale = 2)
        val meanArterialPressure: BigDecimal,
        @field:NotNull(message = "Pulse rate is mandatory")
        @field:DecimalMin(value = "30.00", message = "Pulse rate must be at least 30.00")
        @field:DecimalMax(value = "200.00", message = "Pulse rate must be at most 200.00")
        @Column(name = "pulse_rate", precision = 5, scale = 2)
        val pulseRate: BigDecimal
)
