package com.just_jump.coding_calculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.just_jump.coding_calculator.extensions.checkInteger
import org.mariuszgromada.math.mxparser.*
import kotlinx.android.synthetic.main.activity_calculator_new.*

class CalculatorNew : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator_new)
        expression_value.showSoftInputOnFocus = false

        /**
         * Event to control: button come back to the parent
         */
        button_back.setOnClickListener {
            finish()
        }

        number0.setOnClickListener {
            updateChar("0")
        }

        number1.setOnClickListener {
            updateChar("1")
        }

        number2.setOnClickListener {
            updateChar("2")
        }

        number3.setOnClickListener {
            updateChar("3")
        }

        number4.setOnClickListener {
            updateChar("4")
        }

        number5.setOnClickListener {
            updateChar("5")
        }

        number6.setOnClickListener {
            updateChar("6")
        }

        number7.setOnClickListener {
            updateChar("7")
        }

        number8.setOnClickListener {
            updateChar("8")
        }

        number9.setOnClickListener {
            updateChar("9")
        }

        numberPlus.setOnClickListener {
            updateChar("+")
        }

        numberLess.setOnClickListener {
            updateChar("-")
        }

        numberMultiply.setOnClickListener {
            updateChar("×")
        }

        numberDivide.setOnClickListener {
            updateChar("÷")
        }

        numberPercentage.setOnClickListener {
            updateChar("%")
        }

        numberPoint.setOnClickListener {
            updateChar(".")
        }

        numberOpenParenthesis.setOnClickListener {
            updateChar("(")
        }

        numberCloseParenthesis.setOnClickListener {
            updateChar(")")
        }

        numberAllClear.setOnClickListener {
            expression_value.setText("")
            Result_field.text = ""
        }

        numberBackSpace.setOnClickListener {
            backspaceBTN(it)
        }

        numberPlusLess.setOnClickListener {

        }

        numberResult.setOnClickListener {
            var userExp = expression_value.text.toString()

            userExp = userExp.replace('×', '*')
            userExp = userExp.replace('÷', '/')

            val expression = Expression(userExp)
            val result = expression.calculate().toString()
            Result_field.text = result

        }
    }

    private fun backspaceBTN(view: View) {
        val cursorPos = expression_value.selectionStart
        val textLen = expression_value.text.length

        if (cursorPos != 0 && textLen != 0) {
            val selection = expression_value.text as SpannableStringBuilder

            selection.replace(cursorPos - 1, cursorPos, "")
            expression_value.text = selection
            expression_value.setSelection(cursorPos - 1)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun updateChar(strToAdd: String) {
        val oldStr = expression_value.text.toString()
        val cursorPos = expression_value.selectionStart

        val leftStr = oldStr.substring(0, cursorPos)
        val rightStr = oldStr.substring(cursorPos)

        expression_value.setText("$leftStr$strToAdd$rightStr")
        expression_value.setSelection(cursorPos + 1)
    }
}