package com.cristiandpt.device_emitter

import org.springframework.boot.task.ThreadPoolTaskExecutorBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.task.TaskExecutor
import org.springframework.scheduling.annotation.EnableScheduling

@Configuration
@EnableScheduling
class TaksExecutorConfig {

    @Bean
    fun taskExecutor(): TaskExecutor =
            ThreadPoolTaskExecutorBuilder()
                    .corePoolSize(5)
                    .maxPoolSize(10)
                    .threadNamePrefix("measurement-")
                    .queueCapacity(10)
                    .build()
}
