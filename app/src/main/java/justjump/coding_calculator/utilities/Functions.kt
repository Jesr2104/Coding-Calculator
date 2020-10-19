package justjump.coding_calculator.utilities

import com.github.zieiony.calc.Calc
import java.util.regex.Pattern

class Functions {

    /**********************************************************************/
    // Function to solve the basic mathematical expressions
    /**********************************************************************/
    fun basicEquations(expression: String): String {
        if(validateExpression(expression))
        {
            return Calc().evaluate(expression).toString()
        }
        return "0"
    }

    /**********************************************************************/
    // Function check the the expression with a regex pattern
    /**********************************************************************/
    private fun validateExpression(data: String): Boolean {

        val p = Pattern.compile("((\\()*(-?\\d+(\\.\\d+)?)(\\))*[+/*-])*((\\()*-?\\d+(\\.\\d+)?(\\))*)")
        val m = p.matcher(data)
        val b = m.matches()

        if (b) {
            return true
        }
        return false
    }

    /**********************************************************************/
    // Function to validate hexadecimal number
    /**********************************************************************/
    fun validateHexNumber(hexNumber: String): Boolean {
        val p = Pattern.compile("[0-9a-fA-F]+")
        val m = p.matcher(hexNumber)
        val b = m.matches()

        if (b) {
            return true
        }
        return false
    }

    /**********************************************************************/
    // Function to validate binary number
    /**********************************************************************/
    fun validateBinaryNumber(binary: String): Boolean {
        val p = Pattern.compile("[01]+")
        val m = p.matcher(binary)
        val b = m.matches()

        if (b) {
            return true
        }
        return false
    }

    /**********************************************************************/
    // Function to validate octal number
    /**********************************************************************/
    fun validateOctalNumber(octal: String): Boolean {
        val p = Pattern.compile("[0-7]+")
        val m = p.matcher(octal)
        val b = m.matches()

        if (b) {
            return true
        }
        return false
    }

    /**********************************************************************/
    // Function to validate decimal number
    /**********************************************************************/
    fun validateDecimalNumber(octal: String): Boolean {
        val p = Pattern.compile("[0-9]+")
        val m = p.matcher(octal)
        val b = m.matches()

        if (b) {
            return true
        }
        return false
    }

    /**********************************************************************/
    // Function to convert decimal to hexadecimal
    /**********************************************************************/
    fun convertDecToHex(dataNumber: Int): String {
        var number = dataNumber
        var binario = ""
        var rest: Int

        do {
            rest = number % 16
            number /= 16

            when (rest) {
                10 -> binario += "A"
                11 -> binario += "B"
                12 -> binario += "C"
                13 -> binario += "D"
                14 -> binario += "E"
                15 -> binario += "F"
                else -> binario = "$binario$rest"
            }
        } while (number >= 16)

        when (number) {
            10 -> binario += "A"
            11 -> binario += "B"
            12 -> binario += "C"
            13 -> binario += "D"
            14 -> binario += "E"
            15 -> binario += "F"
            else -> binario = "$binario$number"
        }
        return binario.reversed()
    }

    /**********************************************************************/
    // Function to convert decimal to hexadecimal (Long Integer) override
    /**********************************************************************/
    fun convertDecToHex(dataNumber: Long): String {
        var number = dataNumber
        var binario = ""
        var rest: Long

        do {
            rest = number % 16
            number /= 16

            when (rest) {
                10L -> binario += "A"
                11L -> binario += "B"
                12L -> binario += "C"
                13L -> binario += "D"
                14L -> binario += "E"
                15L -> binario += "F"
                else -> binario = "$binario$rest"
            }
        } while (number >= 16)

        when (number) {
            10L -> binario += "A"
            11L -> binario += "B"
            12L -> binario += "C"
            13L -> binario += "D"
            14L -> binario += "E"
            15L -> binario += "F"
            else -> binario = "$binario$number"
        }
        return binario.reversed()
    }

    /**********************************************************************/
    // Function to convert decimal to binary
    /**********************************************************************/
    fun convertDecToBin(dataNumber: Long): String {
        var number = dataNumber
        var binary = ""
        var rest: Long

        do {
            rest = number % 2
            number /= 2
            binary = "$binary $rest"
        } while (number >= 2)

        binary = "$binary $number"

        return binary.reversed()
    }

    /**********************************************************************/
    // Function to convert decimal to octal
    /**********************************************************************/
    fun convertDecToOct(dataNumber: Long): String {
        var decimal = dataNumber
        var octalNumber = 0L
        var i = 1L
        while (decimal != 0L) {
            octalNumber += decimal % 8L * i
            decimal /= 8L
            i *= 10
        }
        return octalNumber.toString()
    }

    /**********************************************************************/
    // Function to convert binary to decimal
    /**********************************************************************/
    fun convertBinaryToDecimal(value: String): String {
        return Integer.parseInt(value, 2).toString()
    }










    /**********************************************************************/
    // Function to convert binary to decimal
    /**********************************************************************/
    fun convertBinaryToDecimal(value: Long): String {
        var decValue = 0L
        var base = 1L
        var temp = value

        while (temp > 0) {
            val lastDigit = temp % 10L
            temp /= 10L
            decValue += lastDigit * base
            base *= 2L
        }
        return decValue.toString()
    }

    fun convertBinaryToDecimal1(value: Long): String {
        var decValue:Long = 0
        var base:Long = 1
        var temp:Long = value

        while (temp > 0) {
            val lastDigit: Long = temp % 10
            temp /= 10
            decValue += lastDigit * base
            base *= 2
        }
        return decValue.toString()
    }








    /**********************************************************************/
    // Function to convert integer to hexadecimal
    /**********************************************************************/
    fun convertHexToDecimal(dataNumber: String): Int {
        return Integer.parseInt(dataNumber, 16)
        // falta por cambiar por que no es la manera correcta de cuncionamiento ademas se
        // se esta usando un numero entero y tiene un rango demasiado corto de numeros.
    }

    /**********************************************************************/
    // Function to convert octal to decimal
    /**********************************************************************/
//    fun convertOctalToDecimal(value: String): Int {
//        return Integer.parseInt(value, 8)
//    }








    /**********************************************************************/
    // Function to calculate color in RGB to hex
    /**********************************************************************/
    fun colorGenerator(R: Int, G: Int, B: Int): String {
        return "#" + convertDecToHex(R) + convertDecToHex(G) + convertDecToHex(B)
    }

    /**********************************************************************/
    // Function to calculate the media of the number of the array
    /**********************************************************************/
    fun average(values: Array<Double>): Double {
        var result = 0.0
        var i = 0
        for (item in values) {
            result += item
            i++
        }
        result /= i
        return result
    }

    /**********************************************************************/
    // Function to do rule of three
    /**********************************************************************/
    fun ruleOfThree(a: Double, b: Double, c: Double): Double {
        return ((b * c) / a)
    }

    /**********************************************************************/
    // Function to discount calculator
    /**********************************************************************/
    fun discountCalculator(quantity: Double, discount: Double): Array<String> {
        val n1: Double = quantity - ((discount / 100) * quantity)
        val n2: Double = (discount / 100) * quantity

        return arrayOf(n1.toString(), n2.toString())
    }
}