// Notice the ease of using Java libraries
import java.lang.Math.sqrt
import kotlin.math.pow

// Declare a class with its constructor all in one line
// Notice
class Complex(r: Double, i:Double) {

    // Initialize the class variable, a val is a constant, once assigned does not change
    // Also notice the constant variable take on the type of r and i
    private val real = r
    private val imaginary = i

    // Method definition, return the square of this object
    fun square(): Complex {
        return this * this
    }

    // Method absolute, return the square root of the sum of the squares
    fun abs(): Double {
        return sqrt(real.pow(2) + imaginary.pow(2))
    }

    // Operator overload for +, return real + real, and imaginary + imaginary
    operator fun plus(increment: Complex): Complex {
        return Complex(real + increment.real, imaginary + increment.imaginary)
    }

    // Operator overload for *, you can see what it does
    operator fun times(multiplier: Complex): Complex {
        val r = real * multiplier.real - imaginary * multiplier.imaginary
        val i = real * multiplier.imaginary + imaginary * multiplier.real

        return Complex(r, i)
    }
}