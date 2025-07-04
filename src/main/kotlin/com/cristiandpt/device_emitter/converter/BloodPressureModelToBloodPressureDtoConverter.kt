package com.cristiandpt.device_emitter.converter

import com.cristiandpt.device_emitter.dto.BloodPressureKafkaDto
import com.cristiandpt.device_emitter.models.BloodPressureMeasurement
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class BloodPressureModelToBloodPressureDtoConverter() :
        Converter<BloodPressureMeasurement, BloodPressureKafkaDto> {

    override fun convert(model: BloodPressureMeasurement): BloodPressureKafkaDto {
        return BloodPressureKafkaDto(
                id = null,
                userId = model.userId,
                diastolic = model.diastolic,
                systolic = model.systolic,
                pulseRate = model.pulseRate,
                timestamp = model.measuredAt
        )
    }
}
