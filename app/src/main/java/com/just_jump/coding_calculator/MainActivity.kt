package com.just_jump.coding_calculator

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.just_jump.coding_calculator.data.remote.checkCurrentVersion
import com.just_jump.coding_calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        // set the theme again since it had been changed by the splash
        setTheme(R.style.CodingCalculatorTheme)

        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // call to check update of the app
        checkCurrentVersion(this)

        binding.cardCalculator.setOnClickListener {
            val calculator = Intent(this, CalculatorNew::class.java)
            startActivity(calculator)
        }

        binding.cardConverter.setOnClickListener {
            val converter = Intent(this, Converter::class.java)
            startActivity(converter)
        }

        binding.cardNumericalSystems.setOnClickListener {
            val numericalSystems = Intent(this, NumericalSystems::class.java)
            startActivity(numericalSystems)
        }

        binding.cardHarmonicColors.setOnClickListener {
            val colorCode = Intent(this, ColorCode::class.java)
            startActivity(colorCode)
        }

        binding.cardExtraCalculator.setOnClickListener {
            val extrasCalculators = Intent(this, ExtraCalculations::class.java)
            startActivity(extrasCalculators)
        }
    }
}