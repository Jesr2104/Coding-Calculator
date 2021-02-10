package com.just_jump.coding_calculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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

    private fun updateNumber(strToAdd: String) {

        // to control the number [1-9]
        // 1. when you press a number and you find 0 alone we need to change for the number
        if (strToAdd == "1" ||
            strToAdd == "2" ||
            strToAdd == "3" ||
            strToAdd == "4" ||
            strToAdd == "5" ||
            strToAdd == "6" ||
            strToAdd == "7" ||
            strToAdd == "8" ||
            strToAdd == "9"
        ){

        }
    }

    @SuppressLint("SetTextI18n")
    private fun updateChar(strToAdd: String) {
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
        if (strToAdd == "0") {
            val num = numberPartA(leftStr)

            if (num.isNotEmpty()) {
                if (num.toDouble() <= 0) {
                    printChar = false
                }
            }
        }

        // when the value is the first press
        if ((getString(R.string.insert_expression) == expression_value.text.toString()) || expression_value.text.isEmpty()) {
            if (strToAdd == "+"||strToAdd == "-"||strToAdd == "×"||strToAdd == "÷"||strToAdd == "%") {
                // no print noting because you can put a sign to start expression

            } else if (strToAdd == ".") {
                expression_value.setText("0$strToAdd")
                expression_value.setSelection(cursorPos + 2)
            } else {
                expression_value.setText(strToAdd)
                expression_value.setSelection(cursorPos + 1)
            }
        // when the user try to insert new value but us already something inserted
        } else {
            if (strToAdd == "1" || strToAdd == "2" || strToAdd == "3" || strToAdd == "4" || strToAdd == "5" || strToAdd == "6" || strToAdd == "7" || strToAdd == "8" || strToAdd == "9") {
                expression_value.setText("$leftStr$strToAdd$rightStr")
                expression_value.setSelection(cursorPos + 1)
            } else if (strToAdd == "+"||strToAdd == "-"||strToAdd == "×"||strToAdd == "÷"||strToAdd == "%"){
                // if to check the case of math sign is press "+ - × ÷ %"

                // case: to check when you insert a math sign between two number  36[Cursor]58
                if (leftStr.isNotEmpty() && rightStr.isNotEmpty()){
                    if (leftStr[leftStr.length - 1].isDigit() && rightStr[0].isDigit()){
                        expression_value.setText("$leftStr$strToAdd$rightStr")
                        expression_value.setSelection(cursorPos + 1)
                    } else {
                        var newA = ""
                        var newB = ""

                        // if the math sign is on the middle and we need to change for the new one
                        if (!leftStr[leftStr.length - 1].isDigit()){
                            newA = leftStr.substring(0, leftStr.length - 1)
                        }
                        if (!rightStr[0].isDigit()){
                            newB = rightStr.substring(1,rightStr.length)
                        }

                        if (newA.isEmpty()){ newA = leftStr }
                        if (newB.isEmpty()){ newB = rightStr }

                        expression_value.setText("$newA$strToAdd$newB")
                        expression_value.setSelection(cursorPos)
                    }
                // case: to check when you gonna insert number at the end of the expression 3658[cursor]
                } else if (leftStr.isNotEmpty() && rightStr.isEmpty()){
                    if (leftStr[leftStr.length - 1].isDigit()){
                        expression_value.setText("$leftStr$strToAdd")
                        expression_value.setSelection(cursorPos + 1)
                    } else {
                        expression_value.setText("${leftStr.substring(0, leftStr.length - 1)}$strToAdd")
                        expression_value.setSelection(cursorPos)
                    }
                }
            // this option is to check print ['.' '(' ')']
            } else {
                expression_value.setText("$leftStr$strToAdd$rightStr")
                expression_value.setSelection(cursorPos + 1)
            }
        }
    }

    private fun getNumber(leftStr: String, rightStr: String): String {
        return numberPartA(leftStr) + numberPartB(rightStr)
    }

    private fun numberPartA(leftStr: String): String {
        var valueA = ""
        var indexA = leftStr.length

        if (leftStr.isNotEmpty()) {
            while (indexA > 0) {
                if (leftStr[indexA - 1] != '(' &&
                    leftStr[indexA - 1] != ')' &&
                    leftStr[indexA - 1] != '+' &&
                    leftStr[indexA - 1] != '-' &&
                    leftStr[indexA - 1] != '÷' &&
                    leftStr[indexA - 1] != '×' &&
                    leftStr[indexA - 1] != '%'
                ) {
                    valueA += leftStr[indexA - 1]
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

        if (rightStr.isNotEmpty()) {
            while ((indexB < rightStr.length)) {
                if (rightStr[indexB] != '(' &&
                    rightStr[indexB] != ')' &&
                    rightStr[indexB] != '+' &&
                    rightStr[indexB] != '-' &&
                    rightStr[indexB] != '÷' &&
                    rightStr[indexB] != '×' &&
                    rightStr[indexB] != '%'
                ) {
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