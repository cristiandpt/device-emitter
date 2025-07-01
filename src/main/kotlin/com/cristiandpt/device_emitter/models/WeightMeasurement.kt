import java.math.BigDecimal

data class WeightMeasurement(
        private val timeStamp: String,
        private val weight: BigDecimal,
        private val userId: BigDecimal,
        private val bmi: BigDecimal,
        private val height: BigDecimal
)
