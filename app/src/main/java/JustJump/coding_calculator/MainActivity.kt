package JustJump.coding_calculator

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        calculator.setOnClickListener()
        {
            var inten: Intent = Intent(this, Calculator::class.java)
            inten.putExtra("TAG",0)
            startActivity(inten)
        }

//        println("Prueba Basic Equations -> " + Calculator_functions().basicEquations("1+1*(9*3)"))

//        println("Prueba Basic Equations -> " + Calculator_functions().basicEquations("3.5+2"))
//
//        //println("Prueba -> " + Calculator_funcions().basicEquations("50%"))
//        println("Prueba Basic Equations -> " + Calculator_functions().basicEquations("2.5+2.5"))
//
//        println("Prueba Basic Equations -> " + Calculator_functions().basicEquations("2.5+2.5"))
//
//        println("Prueba Basic Equations -> " + Calculator_functions().basicEquations("3.9*3.9"))
//
//        println("Prueba Basic Equations -> " + Calculator_functions().basicEquations("0*8"))
//
//        println("Prueba Basic Equations -> " + Calculator_functions().basicEquations("8/4"))
//
//        println("Prueba Basic Equations -> " + Calculator_functions().basicEquations("20/20"))
//
//        println("Prueba Basic Equations negativos-> " + Calculator_functions().basicEquations("-5+5"))
//
//        println("Prueba Basic Equations8888888888888888888888888888888888 -> " + Calculator_functions().basicEquations("3 + 1"))
//
//        val num = arrayOf<Double>(1.0, 2.0, 3.0,4.0,5.0)
//
//        println("Prueba Average -> " + Calculator_functions().average(num))
//
//        println("Prueba Rule Of Three -> " + Calculator_functions().ruleOfThree("5".toDouble(),"600".toDouble(),"8".toDouble()))
//
//        println("Prueba Binario -> " + Calculator_functions().convertToBinary(5))
//        println("Prueba Binario -> " + Calculator_functions().convertToBinary(13))
//        println("Prueba Binario -> " + Calculator_functions().convertToBinary(20))
//        println("Prueba Binario -> " + Calculator_functions().convertToBinary(68))
//
//        println("Prueba Hexadecimal-> " + Calculator_functions().convertToHex(200))
//        println("Prueba Hexadecimal-> " + Calculator_functions().convertToHex(7000))
//        println("Prueba Hexadecimal-> " + Calculator_functions().convertToHex(16))
//        println("Prueba Hexadecimal-> " + Calculator_functions().convertToHex(255))
//        println("Prueba Hexadecimal-> " + Calculator_functions().convertToHex(59))
//
//        var valor = Calculator_functions().discountCalculator(100.0,20.0)
//        println("Prueba Descuento Valor final -> ${valor[0]}")
//        println("Prueba Descuento Cantidad descontada  -> ${valor[1]}")
//
//        var valor1 = Calculator_functions().discountCalculator(24.99,40.0)
//        println("Prueba Descuento Valor final -> ${valor1[0]}")
//        println("Prueba Descuento Cantidad descontada  -> ${valor1[1]}")
//
//        println("Prueba general color hexadecimal-> " + Calculator_functions().ColorGeneralte(75,166,65))
//
//        println("Prueba -> " + Calculator_functions().ColorGeneralte("#B3B3B3"))

    }
}