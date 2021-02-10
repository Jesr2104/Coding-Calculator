package com.just_jump.coding_calculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.just_jump.coding_calculator.utilities.Functions
import org.mariuszgromada.math.mxparser.*
import kotlinx.android.synthetic.main.activity_calculator_new.*

class CalculatorNew : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator_new)

        expression_value.showSoftInputOnFocus = false
        expression_value.setText(getString(R.string.insert_expression))

        expression_value.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                if (getString(R.string.insert_expression) == expression_value.text.toString()) {
                    expression_value.setText("")
                }
            }
        }

        number0.setOnClickListener {
            updateText("0")
        }

        number1.setOnClickListener {
            updateText("1")
        }

        number2.setOnClickListener {
            updateText("2")
        }

        number3.setOnClickListener {
            updateText("3")
        }

        number4.setOnClickListener {
            updateText("4")
        }

        number5.setOnClickListener {
            updateText("5")
        }

        number6.setOnClickListener {
            updateText("6")
        }

        number7.setOnClickListener {
            updateText("7")
        }

        number8.setOnClickListener {
            updateText("8")
        }

        number9.setOnClickListener {
            updateText("9")
        }

        numberPlus.setOnClickListener {
            updateText("+")
        }

        numberLess.setOnClickListener {
            updateText("-")
        }

        numberMultiply.setOnClickListener {
            updateText("×")
        }

        numberDivide.setOnClickListener {
            updateText("÷")
        }

        numberPercentage.setOnClickListener {
            updateText("%")
        }

        numberPoint.setOnClickListener {
            updateText(".")
        }

        numberOpenParenthesis.setOnClickListener {
            updateText("(")
        }

        numberCloseParenthesis.setOnClickListener {
            updateText(")")
        }

        numberAllClear.setOnClickListener {
            expression_value.setText(getString(R.string.insert_expression))
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
            var result = expression.calculate().toString()

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
    private fun updateText(strToAdd: String) {
        val oldStr = expression_value.text.toString()
        val cursorPos = expression_value.selectionStart
        var printChar = true

        val leftStr = oldStr.substring(0, cursorPos)
        val rightStr = oldStr.substring(cursorPos)

        // to control the character .
        // 1. when you user try to add point but you have already one " 2 + 5.'Press a new point'"
        // 2. when you user try to add point but the number have already point "1 + 3.586 'Press a new point'"
        if (strToAdd == ".") {
            val num = getNumber(leftStr, rightStr)

            if (num.contains('.')) {
                printChar = false
            }
        }

        // to control the character 0
        // 1. the user can't be press two zero in a row like "00"
        // 2. when the user press a number is the number before is zero change zero for the new number
        if (strToAdd == "0"){

        }

        if (getString(R.string.insert_expression) == expression_value.text.toString()) {
            if (printChar && strToAdd != ".") {
                expression_value.setText(strToAdd)
                expression_value.setSelection(cursorPos + 1)
            } else if(strToAdd == ".") {
                expression_value.setText("0$strToAdd")
                expression_value.setSelection(cursorPos + 2)
            }
        } else {
            if (printChar) {
                expression_value.setText("$leftStr$strToAdd$rightStr")
                expression_value.setSelection(cursorPos + 1)
            }
        }
    }

    private fun getNumber(leftStr: String, rightStr: String): String {
        return numberPartA(leftStr)+numberPartB(rightStr)
    }

    private fun numberPartA(leftStr: String): String {
        var valueA = ""
        var indexA = leftStr.length - 1

        if (leftStr.isNotEmpty()) {
            while (indexA > 0) {
                if (leftStr[indexA] != '(' &&
                    leftStr[indexA] != ')' &&
                    leftStr[indexA] != '+' &&
                    leftStr[indexA] != '-' &&
                    leftStr[indexA] != '÷' &&
                    leftStr[indexA] != '×' &&
                    leftStr[indexA] != '%'
                ) {
                    valueA += leftStr[indexA]
                } else {
                    indexA = 0
                }
                indexA--
            }
            valueA = valueA.reversed()
        }

        return valueA
    }

    private fun numberPartB(rightStr: String): String {
        var valueB = ""
        var indexB = 0

        if (rightStr.isNotEmpty()){
            while ((indexB < rightStr.length)) {
                if (rightStr[indexB] != '('&&
                    rightStr[indexB] != ')'&&
                    rightStr[indexB] != '+'&&
                    rightStr[indexB] != '-'&&
                    rightStr[indexB] != '÷'&&
                    rightStr[indexB] != '×'&&
                    rightStr[indexB] != '%'){
                    valueB += rightStr[indexB]
                } else {
                    indexB = rightStr.length
                }
                indexB++
            }
        }

        return valueB
    }
}