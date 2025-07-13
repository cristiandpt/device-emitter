package com.cristiandpt.device_emitter

import com.cristiandpt.device_emitter.entity.BloodPressureEntity
import com.cristiandpt.device_emitter.event.BloodPressureMeasuredEvent
import com.cristiandpt.device_emitter.models.BloodPressureMeasurement
import com.cristiandpt.device_emitter.repository.MeasurementRepository
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import org.springframework.context.ApplicationEventPublisher
import org.springframework.core.convert.ConversionService
import java.math.BigDecimal
import java.time.LocalDateTime

@ExtendWith(MockitoExtension::class)
class BatchDataSenderServiceTest {

    @Mock
    private lateinit var repository: MeasurementRepository

    @Mock
    private lateinit var kafkaProducer: KafkaProducerService<Any>

    @Mock
    private lateinit var conversionService: ConversionService

    @Mock
    private lateinit var eventPublisher: ApplicationEventPublisher

    @InjectMocks
    private lateinit var batchDataSenderService: BatchDataSenderService

    @Test
    fun `should send measures and delete them`() {
        // Given
        val now = LocalDateTime.now()
        val entity = BloodPressureEntity(
            id = 1L,
            userId = BigDecimal(1),
            systolic = BigDecimal(120),
            diastolic = BigDecimal(80),
            meanArterialPressure = BigDecimal(93),
            pulseRate = BigDecimal(60),
            createdAt = now
        )
        val measurement = BloodPressureMeasurement(
            userId = BigDecimal(1),
            systolic = BigDecimal(120),
            diastolic = BigDecimal(80),
            meanArterialPressure = BigDecimal(93),
            pulseRate = BigDecimal(60),
            measuredAt = now
        )
        whenever(repository.fetchTop10Measurements()).thenReturn(listOf(entity))
        whenever(conversionService.convert(entity, BloodPressureMeasurement::class.java)).thenReturn(measurement)

        // When
        batchDataSenderService.sendMeasuresBatch()

        // Then
        verify(repository, times(1)).fetchTop10Measurements()
        verify(eventPublisher, times(1)).publishEvent(any<BloodPressureMeasuredEvent>())
        verify(repository, times(1)).deleteById(1L)
    }
}
