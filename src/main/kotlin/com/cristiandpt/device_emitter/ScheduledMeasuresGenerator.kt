package com.cristiandpt.device_emitter

import org.springframework.scheduling.annotation.Async
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class ScheduledMeasuresGenerator {

    @Async
    @Scheduled(cron = "0 * * * * *")
    fun generateMeasures() {
        println("Generating measures each minute")
    }

    @Async
    @Scheduled(cron = "0 0/2 * * * *")
    fun pushMeasuresBatch() {
        println("Pushing each 2 minutes")
    }
}
