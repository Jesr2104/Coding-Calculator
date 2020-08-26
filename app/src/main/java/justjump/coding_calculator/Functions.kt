package justjump.coding_calculator

import com.github.zieiony.calc.Calc

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
    fun basicEquations(expression: String): Double {
        return Calc().evaluate(expression)
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
    // Function to convert integer to binary
    /**********************************************************************/
    fun convertToBinary(dataNumber: Int): String? {
        var number = dataNumber
        var binary = ""
        var resto: Int

        do {
            resto = number % 2
            number /= 2
            binary = "$binary $resto"
        } while (number >= 2)

        binary = "$binary $number"

        return binary.reversed()
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
    // Function to calculate color in hex to RGB
    // function incomplete
    // function incomplete
    // function incomplete
    // function incomplete
    /**********************************************************************/
    fun colorGenerator(colorHex: String): String {
        /*var cont: Int = 1
        var R = 0
        var G = 0
        var B = 0
        var result: Int = 0


        for (item in colorHex.toCharArray()) {
            result = 0
            if (cont == 2 || cont == 3) {
                println("grupo 1 " + item)

                when (item) {
                    'A' -> {
                        result = (10 * 16)
                    }
                    'B' -> {
                        result = (11 * 16)
                    }
                    'C' -> {
                        result = (12 * 16)
                    }
                    'D' -> {
                        result = (13 * 16)
                    }
                    'E' -> {
                        result = (14 * 16)
                    }
                    'F' -> {
                        result = (15 * 16)
                    }
                    else -> {
                        println("KKKKKKKKKKKKKKKKKKKK->    " + item)
                        result = item.toInt() * 16
                    }
                }
                println("grupo 1 " + result)
            }
            if (cont == 4 || cont == 5) {
                println("grupo 2 " + item)
            }
            if (cont == 6 || cont == 7) {
                println("grupo 3 " + item)
            }
            cont++
        }*/
        return "#ffffff"
    }
}