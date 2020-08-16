package justjump.coding_calculator

import com.github.zieiony.calc.Calc

class Calculator_functions
{
    /**********************************************************************/
    // Function to return integer number
    /**********************************************************************/
    fun checkInt(value: Double): Boolean {
        return value % 1 == 0.0
    }

    /**********************************************************************/
    // Function to Add Integer number and float
    /**********************************************************************/
    fun basicEquations(expression: String) : Double
    {
        return Calc().evaluate(expression)
    }

    /**********************************************************************/
    // Function to calculate the media of the number of the array
    /**********************************************************************/
    fun average(values: Array<Double>) : Double
    {
        var result:Double = 0.0
        var i: Int = 0
        for(item in values){
            result += item
            i++
        }
        result /= i
        return result
    }

    /**********************************************************************/
    // Function to do rule of three
    /**********************************************************************/
    fun ruleOfThree(a: Double, b: Double, c: Double) : Double
    {
        return ((b*c)/a)
    }

    /**********************************************************************/
    // Function to convert integer to binary
    /**********************************************************************/
    fun convertToBinary(numero: Int): String?
    {
        var numero = numero
        var binario: String = ""
        var resto: Int

        do
        {
            resto = numero % 2
            numero /= 2
            binario = "$binario $resto"
        }
        while (numero >= 2)

        binario = "$binario $numero"

        return binario.reversed()
    }

    /**********************************************************************/
    // Function to convert integer to hexadecimal
    /**********************************************************************/
    fun convertToHex(numero: Int): String?
    {
        var numero = numero
        var binario: String = ""
        var resto: Int

        do
        {
            resto = numero % 16
            numero /= 16

            when(resto)
            {
                10 -> {binario = "$binario"+"A"}
                11 -> {binario = "$binario"+"B"}
                12 -> {binario = "$binario"+"C"}
                13 -> {binario = "$binario"+"D"}
                14 -> {binario = "$binario"+"E"}
                15 -> {binario = "$binario"+"F"}
                else -> {binario = "$binario$resto"}
            }
        }
        while (numero >= 16)

        when(numero)
        {
            10 -> {binario = "$binario"+"A"}
            11 -> {binario = "$binario"+"B"}
            12 -> {binario = "$binario"+"C"}
            13 -> {binario = "$binario"+"D"}
            14 -> {binario = "$binario"+"E"}
            15 -> {binario = "$binario"+"F"}
            else -> {binario = "$binario$numero"}
        }
        return binario.reversed()
    }

    /**********************************************************************/
    // Function to discount calculator
    /**********************************************************************/
    fun discountCalculator(quantity: Double ,discount: Double): Array<String>
    {
        var n1:Double = quantity-((discount/100)*quantity)
        var n2: Double = (discount/100)*quantity

        var result: Array<String> = arrayOf(n1.toString(),n2.toString())

        return result
    }

    /**********************************************************************/
    // Function to calculate color in RGB to hex
    /**********************************************************************/
    fun ColorGeneralte(R: Int,G: Int, B:Int) : String
    {
        var hexColor:String = "#"+convertToHex(R) + convertToHex(G) + convertToHex(B)
        return hexColor
    }

    /**********************************************************************/
    // Function to calculate color in hex to RGB
    /**********************************************************************/
    fun ColorGeneralte(colorHex: String) : String
    {
        var contador: Int = 1
        var R: Int = 0
        var G: Int = 0
        var B: Int = 0
        var result: Int = 0

        for(item in colorHex.toCharArray())
        {
            result=0
            if(contador == 2 || contador == 3)
            {
                println("grupo 1 " + item)

                when(item)
                {
                    'A' -> {result = (10*16)}
                    'B' -> {result = (11*16)}
                    'C' -> {result = (12*16)}
                    'D' -> {result = (13*16)}
                    'E' -> {result = (14*16)}
                    'F' -> {result = (15*16)}
                    else ->
                    {
                        println("KKKKKKKKKKKKKKKKKKKK->    "+item)
                        result = item.toInt()*16
                    }
                }
                println("grupo 1 " + result)
            }
            if(contador == 4 || contador == 5)
            {
                println("grupo 2 " + item)
            }
            if(contador == 6 || contador == 7)
            {
                println("grupo 3 " + item)
            }
            contador++
        }
        return "Hola"
    }
}