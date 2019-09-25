import java.lang.Math.sqrt
import kotlin.math.pow

class Complex(r: Double, i:Double) {
    private val real = r
    private val imaginary = i

    fun square(): Complex {
        return this * this
    }

    fun abs(): Double {
        return sqrt(real.pow(2) + imaginary.pow(2))
    }

    operator fun plus(increment: Complex): Complex {
        return Complex(real + increment.real, imaginary + increment.imaginary)
    }

    operator fun times(multiplier: Complex): Complex {
        val r = real * multiplier.real - imaginary * multiplier.imaginary
        val i = real * multiplier.imaginary + imaginary * multiplier.real

        return Complex(r, i)
    }
}