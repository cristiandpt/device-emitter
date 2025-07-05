package com.cristiandpt.device_emitter.event

import com.cristiandpt.device_emitter.models.BloodPressureMeasurement // Events should typically
// extend ApplicationEvent
import org.springframework.context.ApplicationEvent

class BloodPressureMeasuredEvent(source: Any, val data: BloodPressureMeasurement) :
        ApplicationEvent(source) {
    // You can add more data or context here
    override fun toString(): String {
        return "BloodPressureMeasuredEvent(data=$data)"
    }
}
