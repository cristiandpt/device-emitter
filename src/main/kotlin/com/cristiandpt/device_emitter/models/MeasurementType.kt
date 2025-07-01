package com.cristiandpt.device_emitter.models

sealed class MeasurementType {
    object BodyComposition : MeasurementType()
    object BloodPressure : MeasurementType()
    object Weight : MeasurementType()
}
