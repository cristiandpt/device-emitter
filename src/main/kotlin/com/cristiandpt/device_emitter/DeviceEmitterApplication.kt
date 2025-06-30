package com.cristiandpt.device_emitter

import kotlinx.coroutines.*
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DeviceEmitterApplication : ApplicationRunner {

    override fun run(args: ApplicationArguments) {
        runBlocking {
            coroutineScope {
                val greenThreadsNumber = 1_000
                val jobs =
                        List(greenThreadsNumber) { index ->
                            launch {
                                delay(700)
                                println("Corountine $index Completed!")
                            }
                        }
                jobs.forEach { it.join() }
            }
        }

        println("All work done!")
    }
}

fun main(args: Array<String>) {
    runApplication<DeviceEmitterApplication>(*args)
}
