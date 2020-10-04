package justjump.coding_calculator

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        calculator.setOnClickListener() {
            val calculator = Intent(this, Calculator::class.java)
            startActivity(calculator)
        }

        harmonicColors.setOnClickListener() {
            val colorCode = Intent(this, ColorCode::class.java)
            startActivity(colorCode)
        }

        numericalSystem.setOnClickListener() {
            val numericalSystems = Intent(this, NumericalSystems::class.java)
            startActivity(numericalSystems)
        }

        extraCalculators.setOnClickListener() {
            val extrasCalculators = Intent(this, ExtraCalculations::class.java)
            startActivity(extrasCalculators)
        }
    }
}