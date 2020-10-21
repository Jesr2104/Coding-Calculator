package justjump.coding_calculator

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        card_calculator.setOnClickListener() {
            val calculator = Intent(this, Calculator::class.java)
            startActivity(calculator)
        }

        card_converter.setOnClickListener() {
            val converter = Intent(this, Converter::class.java)
            startActivity(converter)
        }

        card_numerical_systems.setOnClickListener() {
            val numericalSystems = Intent(this, NumericalSystems::class.java)
            startActivity(numericalSystems)
        }

        card_harmonic_colors.setOnClickListener() {
            val colorCode = Intent(this, ColorCode::class.java)
            startActivity(colorCode)
        }

        card_extra_calculator.setOnClickListener() {
            val extrasCalculators = Intent(this, ExtraCalculations::class.java)
            startActivity(extrasCalculators)
        }
    }
}