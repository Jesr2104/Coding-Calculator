package com.just_jump.coding_calculator.utilities

import android.annotation.SuppressLint
import com.github.zieiony.calc.Calc
import java.util.regex.Pattern

class Functions {

    /**********************************************************************/
    // Function to solve the basic mathematical expressions
    /**********************************************************************/
    fun basicEquations(expression: String): String {
        if (validateExpression(expression)) {
            return Calc().evaluate(expression).toString()
        }
        return "0"
    }

    /**********************************************************************/
    // Function check the the expression with a regex pattern
    /**********************************************************************/
    private fun validateExpression(data: String): Boolean {

        val p =
            Pattern.compile("((\\()*(-?\\d+(\\.\\d+)?)(\\))*[+/*-])*((\\()*-?\\d+(\\.\\d+)?(\\))*)")
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
        var resultHexadecimal = ""
        var rest: Int

        do {
            rest = number % 16
            number /= 16

            when (rest) {
                10 -> resultHexadecimal += "A"
                11 -> resultHexadecimal += "B"
                12 -> resultHexadecimal += "C"
                13 -> resultHexadecimal += "D"
                14 -> resultHexadecimal += "E"
                15 -> resultHexadecimal += "F"
                else -> resultHexadecimal = "$resultHexadecimal$rest"
            }
        } while (number >= 16)

        when (number) {
            10 -> resultHexadecimal += "A"
            11 -> resultHexadecimal += "B"
            12 -> resultHexadecimal += "C"
            13 -> resultHexadecimal += "D"
            14 -> resultHexadecimal += "E"
            15 -> resultHexadecimal += "F"
            else -> resultHexadecimal = "$resultHexadecimal$number"
        }
        return resultHexadecimal.reversed()
    }

    /**********************************************************************/
    // Function to convert decimal to hexadecimal (Long Integer) override
    /**********************************************************************/
    fun convertDecToHex(dataNumber: Long): String {
        var number = dataNumber
        var resultHexadecimal = ""
        var rest: Long

        do {
            rest = number % 16
            number /= 16

            when (rest) {
                10L -> resultHexadecimal += "A"
                11L -> resultHexadecimal += "B"
                12L -> resultHexadecimal += "C"
                13L -> resultHexadecimal += "D"
                14L -> resultHexadecimal += "E"
                15L -> resultHexadecimal += "F"
                else -> resultHexadecimal = "$resultHexadecimal$rest"
            }
        } while (number >= 16)

        when (number) {
            10L -> resultHexadecimal += "A"
            11L -> resultHexadecimal += "B"
            12L -> resultHexadecimal += "C"
            13L -> resultHexadecimal += "D"
            14L -> resultHexadecimal += "E"
            15L -> resultHexadecimal += "F"
            else -> {
                resultHexadecimal = "$resultHexadecimal$number"
            }
        }
        val result = resultHexadecimal.reversed()

        if ( result[0] == '0') {
            return result.substring(1,result.length)

        }

        return result
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
    // Function to convert octal to decimal
    /**********************************************************************/
    fun convertOctalToDecimal(value: Long): String {
        var resultDecimalValue = 0L
        var base = 1L
        var temp = value

        while (temp > 0) {
            val lastDigit = temp % 10L
            temp /= 10L

            resultDecimalValue += lastDigit * base
            base *= 8L
        }
        return resultDecimalValue.toString()
    }

    /**********************************************************************/
    // Function to convert hexadecimal to decimal
    /**********************************************************************/
    @SuppressLint("DefaultLocale")
    fun convertHexToDecimal(value: String): String {
        val hexNumber = value.toUpperCase()
        val hexString = "0123456789ABCDEF"

        var num = 0L
        for (element in hexNumber) {
            val index = hexString.indexOf(element).toLong()
            num = 16 * num + index
        }
        return num.toString()
    }

    /**********************************************************************/
    // Function to do rule of three
    /**********************************************************************/
    fun ruleOfThree(a: Double, b: Double, c: Double): Double {
        return ((b * c) / a)
    }

    /**********************************************************************/
    // Function to percentage calculator
    /**********************************************************************/
    fun percentageCalculator(quantity: Double, percentage: Double): Double {
        return (quantity * percentage) / 100
    }

    /**********************************************************************/
    // Function to calculate the media of the number of the array
    /**********************************************************************/
    fun average(values: ArrayList<String>): Double {
        var result = 0.0

        for (item in values) {
            result += item.toDouble()
        }
        result /= values.size
        return result
    }
}