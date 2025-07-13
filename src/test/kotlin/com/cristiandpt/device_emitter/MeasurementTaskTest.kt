package com.cristiandpt.device_emitter

import com.cristiandpt.device_emitter.models.BloodPressureMeasurement
import com.cristiandpt.device_emitter.repository.MeasurementRepository
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import org.mockito.kotlin.verify
import org.springframework.scheduling.TaskScheduler
import java.time.Instant

@ExtendWith(MockitoExtension::class)
class MeasurementTaskTest {

    @Mock
    private lateinit var taskScheduler: TaskScheduler

    @Mock
    private lateinit var repository: MeasurementRepository

    @InjectMocks
    private lateinit var measurementTask: MeasurementTask

    @Captor
    private lateinit var runnableCaptor: ArgumentCaptor<Runnable>

    @Captor
    private lateinit var instantCaptor: ArgumentCaptor<Instant>

    @Test
    fun `should generate measurement and schedule next execution`() {
        // Given
        val measureGeneration = measurementTask.MeasureGeneration()

        // When
        measureGeneration.run()

        // Then
        verify(repository).saveBloodMeasurement(any<BloodPressureMeasurement>())
        verify(taskScheduler).schedule(runnableCaptor.capture(), instantCaptor.capture())
    }
}
