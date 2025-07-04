package com.cristiandpt.device_emitter.repository

import BloodPressureMeasurement
import com.cristiandpt.device_emitter.entity.BloodPressureEntity
import jakarta.validation.Valid
import org.springframework.core.convert.ConversionService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.validation.annotation.Validated

@Service
@Validated
class MeasurementService(
        private val bloodPressure: BloodPressureRepository,
        private val conversionService: ConversionService
) {

    @Transactional
    fun saveBloodMeasurement(@Valid bloodPressureMeasurement: BloodPressureMeasurement) {
        val entity =
                conversionService.convert(bloodPressureMeasurement, BloodPressureEntity::class.java)
        entity?.let { bloodPressure.save(it) }
    }
}
