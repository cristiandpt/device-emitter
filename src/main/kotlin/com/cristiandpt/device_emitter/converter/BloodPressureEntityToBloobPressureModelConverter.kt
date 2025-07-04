package com.cristiandpt.devive_emitter.converter

import com.cristiandpt.device_emitter.entity.BloodPressureEntity
import com.cristiandpt.device_emitter.models.BloodPressureMeasurement
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class BloodPressureEntityToBloodPressureModelConverter() :
        Converter<BloodPressureEntity, BloodPressureMeasurement> {

    override fun convert(entity: BloodPressureEntity): BloodPressureMeasurement =
            BloodPressureMeasurement(
                    userId = entity.userId,
                    diastolic = entity.diastolic,
                    systolic = entity.systolic,
                    meanArterialPressure = entity.meanArterialPressure,
                    pulseRate = entity.pulseRate,
                    measuredAt = entity.createdAt
            )
}
