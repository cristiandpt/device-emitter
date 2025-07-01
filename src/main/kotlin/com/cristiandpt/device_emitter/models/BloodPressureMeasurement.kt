import java.math.BigDecimal

data class BloodPressureMeasurement(
        val timeStamp: String,
        val userId: BigDecimal,
        val systolic: BigDecimal,
        val diastolic: BigDecimal,
        val meanArterialPressure: BigDecimal,
        val pulseRate: BigDecimal
)
