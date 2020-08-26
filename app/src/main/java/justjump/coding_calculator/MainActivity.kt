package justjump.coding_calculator

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        calculator.setOnClickListener(){
            val calculator = Intent(this, Calculator::class.java)
            calculator.putExtra("TAG", 0)
            startActivity(calculator)
        }

        colorCode.setOnClickListener(){
            val colorCode = Intent(this, ColorCode::class.java)
            colorCode.putExtra("TAG", 1)
            startActivity(colorCode)
        }

        decimaltobinary.setOnClickListener(){
            val decimalToBinary = Intent(this, DecimalToHex::class.java)
            decimalToBinary.putExtra("TAG", 2)
            startActivity(decimalToBinary)
        }
    }
}