package justjump.coding_calculator.utilities

import com.github.zieiony.calc.Calc
import java.util.regex.Pattern

class Functions {

    /**********************************************************************/
    // Function to return integer number
    /**********************************************************************/
    fun checkInt(value: Double): Boolean {
        return value % 1 == 0.0
    }

    /**********************************************************************/
    // Function to Add Integer number and float
    /**********************************************************************/
    fun basicEquations(expression: String): String {
        if(validateExpression(expression))
        {
            return Calc().evaluate(expression).toString()
        }
        return "#2104"
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

    /**********************************************************************/
    // Function to calculate color in RGB to hex
    /**********************************************************************/
    fun colorGenerator(R: Int, G: Int, B: Int): String {
        return "#" + convertToHex(R) + convertToHex(G) + convertToHex(B)
    }

    /**********************************************************************/
    // Function to convert integer to binary
    /**********************************************************************/
    fun convertToBinary(dataNumber: Int): String? {
        var number = dataNumber
        var binary = ""
        var rest: Int

        do {
            rest = number % 2
            number /= 2
            binary = "$binary $rest"
        } while (number >= 2)

        binary = "$binary $number"

        return binary.reversed()
    }

    /**********************************************************************/
    // Function to convert integer to binary
    /**********************************************************************/
    fun convertToOctal(dataNumber: Int): String? {

        return Integer.toOctalString(dataNumber)
    }

    /**********************************************************************/
    // Function to convert integer to hexadecimal
    /**********************************************************************/
    fun convertToHex(dataNumber: Int): String? {
        var number = dataNumber
        var binario = ""
        var rest: Int

        do {
            rest = number % 16
            number /= 16

            when (rest) {
                10 -> {
                    binario += "A"
                }
                11 -> {
                    binario += "B"
                }
                12 -> {
                    binario += "C"
                }
                13 -> {
                    binario += "D"
                }
                14 -> {
                    binario += "E"
                }
                15 -> {
                    binario += "F"
                }
                else -> {
                    binario = "$binario$rest"
                }
            }
        } while (number >= 16)

        when (number) {
            10 -> {
                binario += "A"
            }
            11 -> {
                binario += "B"
            }
            12 -> {
                binario += "C"
            }
            13 -> {
                binario += "D"
            }
            14 -> {
                binario += "E"
            }
            15 -> {
                binario += "F"
            }
            else -> {
                binario = "$binario$number"
            }
        }
        return binario.reversed()
    }

    /**********************************************************************/
    // Function to convert integer to hexadecimal
    /**********************************************************************/
    fun convertHexToDecimal(dataNumber: String): Int {
        return Integer.parseInt(dataNumber, 16)
    }

    /**********************************************************************/
    // Function to convert octal to decimal
    /**********************************************************************/
    fun convertOctalToDecimal(value: String): Int {
        return Integer.parseInt(value, 8)
    }

    /**********************************************************************/
    // Function to convert binary to decimal
    /**********************************************************************/
    fun convertBinaryToDecimal(value: String): Int {
        return Integer.parseInt(value, 2)
    }
}