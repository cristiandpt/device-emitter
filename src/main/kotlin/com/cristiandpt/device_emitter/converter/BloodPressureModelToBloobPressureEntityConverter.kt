package com.cristiandpt.devive_emitter.converter

import com.cristiandpt.device_emitter.entity.BloodPressureEntity
import com.cristiandpt.device_emitter.models.BloodPressureMeasurement
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class BloodPressureModelToBloodPressureEntityConverter() :
        Converter<BloodPressureMeasurement, BloodPressureEntity> {

    override fun convert(model: BloodPressureMeasurement): BloodPressureEntity =
            BloodPressureEntity(
                    userId = model.userId,
                    diastolic = model.diastolic,
                    systolic = model.systolic,
                    meanArterialPressure = model.meanArterialPressure,
                    pulseRate = model.pulseRate,
                    createdAt = model.measuredAt
            )
}
