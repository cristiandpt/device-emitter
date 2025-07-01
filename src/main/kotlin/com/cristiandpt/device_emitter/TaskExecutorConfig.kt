package com.cristiandpt.device_emitter

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.task.TaskExecutor
import org.springframework.scheduling.TaskScheduler
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler

@Configuration
@EnableScheduling
class TaksExecutorConfig {

    @Bean
    fun taskExecutor(): TaskExecutor =
            ThreadPoolTaskExecutor().apply {
                corePoolSize = 5
                maxPoolSize = 10
                queueCapacity = 10
                initialize()
            }

    @Bean
    fun taskScheduler(): TaskScheduler =
            ThreadPoolTaskScheduler().apply {
                poolSize = 10
                initialize()
            }
}
