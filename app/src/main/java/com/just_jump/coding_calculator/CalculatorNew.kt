package com.just_jump.coding_calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_calculator_new.*

class CalculatorNew : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator_new)

        /**
         * Event to control: button come back to the parent
         */
        button_back.setOnClickListener {
            finish()
        }
    }
}