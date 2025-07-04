package com.cristiandpt.device_emitter.repository

import BloodPressureMeasurement
import jakarta.validation.Valid
import org.springframework.core.convert.ConversionService
import org.springframework.stereotype.Service
import org.springframework.validation.annotation.Validated

@Service
@Validated
class MeasurementService(
        private val bloodPressure: BloodPressureRepository,
        private val conversionService: ConversionService
) {

    fun saveBloodMeasurement(@Valid bloodPressureMeasurement: BloodPressureMeasurement) {
        bloodPressure.save()
    }
}
