package com.cristiandpt.device_emitter.utils

import java.math.BigDecimal
import java.math.RoundingMode
import java.util.concurrent.ThreadLocalRandom

/**
 * Generates a random BigDecimal within the specified range.
 *
 * @param min The minimum value of the range (inclusive).
 * @param max The maximum value of the range (inclusive).
 * @param scale The number of decimal places for the generated BigDecimal. Default is 2.
 * @return A random BigDecimal within the specified range.
 */
fun generateRandomBigDecimal(min: BigDecimal, max: BigDecimal, scale: Int = 2): BigDecimal {
    require(min <= max) { "Minimum value must be less than or equal to maximum value" }

    val scaledMin = min.movePointRight(scale).toLong()
    val scaledMax = max.movePointRight(scale).toLong()

    val randomLong = ThreadLocalRandom.current().nextLong(scaledMin, scaledMax + 1)

    return BigDecimal(randomLong).movePointLeft(scale).setScale(scale, RoundingMode.HALF_UP)
}
