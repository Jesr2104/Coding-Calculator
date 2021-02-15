package com.just_jump.coding_calculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.mariuszgromada.math.mxparser.*
import kotlinx.android.synthetic.main.activity_calculator_new.*

class CalculatorNew : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator_new)
        expression_value.showSoftInputOnFocus = false

        /***************************************************************************/
        // Events to come back to the main window
        /***************************************************************************/

        button_back.setOnClickListener { finish() }

        /***************************************************************************/
        // Events of Numbers
        /***************************************************************************/

        number1.setOnClickListener { insertNumber('1') }

        number2.setOnClickListener { insertNumber('2') }

        number3.setOnClickListener { insertNumber('3') }

        number4.setOnClickListener { insertNumber('4') }

        number5.setOnClickListener { insertNumber('5') }

        number6.setOnClickListener { insertNumber('6') }

        number7.setOnClickListener { insertNumber('7') }

        number8.setOnClickListener { insertNumber('8') }

        number9.setOnClickListener { insertNumber('9') }

        number0.setOnClickListener { insertNumberZero()}

        /***************************************************************************/
        // Events of Math signs
        /***************************************************************************/

        numberPlus.setOnClickListener { insertMathSign('+') }

        numberLess.setOnClickListener { insertMathSign('-') }

        numberMultiply.setOnClickListener { insertMathSign('×') }

        numberDivide.setOnClickListener { insertMathSign('÷') }

        numberPercentage.setOnClickListener { insertMathSign('%') }

        numberOpenParenthesis.setOnClickListener { insertParenthesis('(') }

        numberCloseParenthesis.setOnClickListener { insertParenthesis(')') }

        numberPlusLess.setOnClickListener { insertPlusLess() }

        numberPoint.setOnClickListener { insertPoint() }

        /***************************************************************************/
        // clear field
        /***************************************************************************/

        numberAllClear.setOnClickListener { allClear() }

        numberBackSpace.setOnClickListener { backspaceBTN() }

        /***************************************************************************/
        // Result expression
        /***************************************************************************/

        numberResult.setOnClickListener { calculateResult() }
    }

    // this function clear all the field
    /*   finished and checked   */
    private fun allClear() {
        expression_value.setText("")
        Result_field.text = ""
    }

    // this function delete the char on the left of the cursor
    // it's missing:
    // 1. when you delete a number need to check if the number have sense
    /*   finished and checked   */
    private fun backspaceBTN() {
        val cursorPos = expression_value.selectionStart
        val textLen = expression_value.text.length

        if (cursorPos != 0 && textLen != 0) {
            val selection = expression_value.text as SpannableStringBuilder

            selection.replace(cursorPos - 1, cursorPos, "")
            expression_value.text = selection
            expression_value.setSelection(cursorPos - 1)
        }
    }

    // function to control when we try to insert a math sign
    // this is necessary to check:
    // 1. we can't put two sign together
    // 2. we can't start a expression with a math sign
    // 3. when you press a new math sign and you find one previously you need to change for the new one
    /*   finished and checked   */
    @SuppressLint("SetTextI18n")
    private fun insertMathSign(mathSignToAdd: Char) {
        val oldStr = expression_value.text.toString()
        val cursorPos = expression_value.selectionStart

        val leftStr = oldStr.substring(0, cursorPos)
        val rightStr = oldStr.substring(cursorPos)

        if (leftStr.isEmpty()) {
            // this case is when you are on the first position and we don't have nothing
        } else {
            if (leftStr.isNotEmpty() && rightStr.isNotEmpty()) {
                // case 1: if you gonna put a math sign between two number like 21[cursor]04
                if (leftStr[leftStr.length - 1].isDigit() && rightStr[0].isDigit()) {
                    expression_value.setText(leftStr + mathSignToAdd + rightStr)
                    expression_value.setSelection(cursorPos + 1)
                    // case 2: if you press a math sign and you find one already on the left you change for the new one 8+[cursor]5
                } else if ("+-×÷%".contains(leftStr[leftStr.length - 1])) {
                    if(leftStr[leftStr.length - 2] == '(') {
                        if (leftStr[leftStr.length - 1] == '-' || leftStr[leftStr.length - 1] == '+'){
                            if (mathSignToAdd == '+') {
                                expression_value.setText(leftStr.substring(0, leftStr.length - 1) + mathSignToAdd + rightStr)
                                expression_value.setSelection(cursorPos)
                            } else if (mathSignToAdd == '-') {
                                expression_value.setText(leftStr.substring(0, leftStr.length - 1) + mathSignToAdd + rightStr)
                                expression_value.setSelection(cursorPos)
                            }
                        }
                    } else {
                        expression_value.setText(
                            leftStr.substring(
                                0,
                                leftStr.length - 1
                            ) + mathSignToAdd + rightStr
                        )
                        expression_value.setSelection(cursorPos)
                    }
                }   else if (leftStr[leftStr.length - 1].isDigit() && rightStr[0] == '(') {
                    expression_value.setText(leftStr + mathSignToAdd + rightStr)
                    expression_value.setSelection(cursorPos + 1)
                }
                // case 3: if you press a math sign and you find one already on the right you change for the new one 8[cursor]+5
                else if (leftStr[leftStr.length - 1].isDigit() && "+-×÷%".contains(rightStr[0])) {
                    expression_value.setText(leftStr + mathSignToAdd + rightStr.substring(1))
                    expression_value.setSelection(cursorPos)
                }
                // case 6: it's the same but for the case we find data on both side
                else if (")".contains(leftStr[leftStr.length - 1])) {
                    if (!("+-×÷%".contains(rightStr[0]))){
                        expression_value.setText(leftStr + mathSignToAdd + rightStr)
                        expression_value.setSelection(cursorPos + 1)
                    } else {
                        expression_value.setText(leftStr + mathSignToAdd + rightStr.substring(1))
                        expression_value.setSelection(cursorPos + 1)
                    }
                }
                // case 4: if you try to insert a math sign on the end of number like: 21[cursor]
            } else if (leftStr[leftStr.length - 1].isDigit() && rightStr.isEmpty()) {
                expression_value.setText(leftStr + mathSignToAdd)
                expression_value.setSelection(cursorPos + 1)
                // case 5: if you want to insert a math sign but is one on the right you change this for the new one
            } else if ("+-×÷%".contains(leftStr[leftStr.length - 1]) && rightStr.isEmpty()) {
                if(leftStr[leftStr.length - 2] == '(') {
                    if (leftStr[leftStr.length - 1] == '-' || leftStr[leftStr.length - 1] == '+'){
                        if (mathSignToAdd == '+') {
                            expression_value.setText(leftStr.substring(0, leftStr.length - 1) + mathSignToAdd)
                            expression_value.setSelection(cursorPos)
                        } else if (mathSignToAdd == '-') {
                            expression_value.setText(leftStr.substring(0, leftStr.length - 1) + mathSignToAdd)
                            expression_value.setSelection(cursorPos)
                        }
                    }
                } else {
                    expression_value.setText(leftStr.substring(0, leftStr.length - 1) + mathSignToAdd)
                    expression_value.setSelection(cursorPos)
                }
                // case 6: if you press a math sign but is a close parenthesis on the left
            } else if (")".contains(leftStr[leftStr.length - 1])) {
                expression_value.setText(leftStr + mathSignToAdd + rightStr)
                expression_value.setSelection(cursorPos + 1)
            }
        }
    }

    // function to insert number
    // this is necessary to check:
    // 1. if the user insert a number but if zero previously 0 need to change for the new number
    // 2. when you press a number and you find on the right close parenthesis we need to change for
    //    a multiply parenthesis like: (20+1)[cursor] and press 4 need to finish (20+1)×4[cursor]
    @SuppressLint("SetTextI18n")
    private fun insertNumber(numberToAdd: Char) {
        val oldStr = expression_value.text.toString()
        val cursorPos = expression_value.selectionStart

        val leftStr = oldStr.substring(0, cursorPos)
        val rightStr = oldStr.substring(cursorPos)



        expression_value.setText(leftStr + numberToAdd + rightStr)
        expression_value.setSelection(cursorPos + 1)
    }

    // function to insert number
    // this is necessary to check:
    // 1. the used can't be put two zero together in the integer part like 000
    // 2. the can can put all zero you need if you are on the decimal par of the number
    /*   finished and checked   */
    @SuppressLint("SetTextI18n")
    private fun insertNumberZero() {
        val oldStr = expression_value.text.toString()
        val cursorPos = expression_value.selectionStart

        val leftStr = oldStr.substring(0, cursorPos)
        val rightStr = oldStr.substring(cursorPos)

        if (oldStr.isNotEmpty()){
            // if you find data on both side of the cursor for example: 25[cursor]45
            if (leftStr.isNotEmpty() && rightStr.isNotEmpty()){
                if (leftStr[leftStr.length - 1].isDigit() && rightStr[0].isDigit()){
                    expression_value.setText(leftStr + '0' + rightStr)
                    expression_value.setSelection(cursorPos + 1)
                }

            // if you find data just on the left side of the cursor like: 25[cursor]
            } else if (leftStr.isNotEmpty() && rightStr.isEmpty()){
                var num = ""
                var cont = leftStr.length - 1

                while (cont > -1){
                    if (!"+-×÷%()".contains(leftStr[cont])){
                        num += leftStr[cont]
                    } else { cont = 0 }
                    cont --
                }
                num = num.reversed()

                if (num.isNotEmpty()){
                    if (num.toDouble() > 0 || num.contains('.')){
                        expression_value.setText(leftStr + '0' + rightStr)
                        expression_value.setSelection(cursorPos + 1)
                    }
                } else {
                    if ("+-×÷%()".contains(leftStr[leftStr.length - 1])){
                        expression_value.setText(leftStr + '0' + rightStr)
                        expression_value.setSelection(cursorPos + 1)
                    }
                }

            // if you find data just on the right side of the cursor for example [cursor]25
            }
        } else {
            // default case: if you find empty the expression
            expression_value.setText(leftStr + '0' + rightStr)
            expression_value.setSelection(cursorPos + 1)
        }
    }

    // function to control when we try to insert a parenthesis
    // this is necessary to check:
    // 1. if you press to insert open parenthesis and you find a number on the right we need to
    //    put [number]× and open parenthesis
    // 2. if you try to insert open parenthesis but you find a close parenthesis you need to insert
    //    the sign × between this two like (20+1)[cursor] result (20+1)*([cursor]
    // 3. we can't start expression with a close parenthesis is the expression is empty
    /*   finished and checked   */
    @SuppressLint("SetTextI18n")
    private fun insertParenthesis(parenthesisToAdd: Char) {
        val oldStr = expression_value.text.toString()
        val cursorPos = expression_value.selectionStart

        val leftStr = oldStr.substring(0, cursorPos)
        val rightStr = oldStr.substring(cursorPos)

        if (leftStr.isNotEmpty() && rightStr.isNotEmpty()){
            if(rightStr[0] == '(' && parenthesisToAdd == ')'){
                expression_value.setText("$leftStr$parenthesisToAdd×$rightStr")
                expression_value.setSelection(leftStr.length + 2)
            }
        } else if (leftStr.isNotEmpty()) {
            if ((leftStr[leftStr.length - 1] == ')' && parenthesisToAdd == '(') || (leftStr[leftStr.length - 1].isDigit() && parenthesisToAdd == '(')) {
                expression_value.setText("$leftStr×$parenthesisToAdd$rightStr")
                expression_value.setSelection(cursorPos + 2)
            } else {
                expression_value.setText(leftStr + parenthesisToAdd + rightStr)
                expression_value.setSelection(cursorPos + 1)
            }
        } else {
            if (rightStr.isEmpty() && parenthesisToAdd == ')') {
                // if the string leftStr and rightStr is empty
            } else {
                expression_value.setText(leftStr + parenthesisToAdd + rightStr)
                expression_value.setSelection(cursorPos + 1)
            }
        }
    }

    // function to control when we try to insert a parenthesis
    // this is necessary to check:
    // 1. just one point in the number
    // 2. is you can find number on the left you need to put 0.
    /*   finished and checked   */
    @SuppressLint("SetTextI18n")
    private fun insertPoint() {
        val oldStr = expression_value.text.toString()
        val cursorPos = expression_value.selectionStart
        val charPoint = "."

        val leftStr = oldStr.substring(0, cursorPos)
        val rightStr = oldStr.substring(cursorPos)

        if (oldStr.isEmpty()){
            expression_value.setText("0$charPoint")
            expression_value.setSelection(2)
        } else if (leftStr.isNotEmpty()) {
            var num = ""
            var cont = leftStr.length - 1

            while (cont > -1){
                if (!"+-×÷%()".contains(leftStr[cont])){
                    num += leftStr[cont]
                } else { cont = 0 }
                cont --
            }
            num = num.reversed()
            cont = 0

            if (rightStr.isNotEmpty()){
                while (cont < rightStr.length -1) {
                    if (!"+-×÷%()".contains(rightStr[cont])){
                        num += rightStr[cont]
                    } else { cont = rightStr.length }
                    cont++
                }
            }

            if (num.isNotEmpty()){

                when {
                    num.contains(charPoint) -> {
                        // if this number contain point already we don't put more just can be one
                    }
                    "+-×÷%(".contains(leftStr[leftStr.length - 1]) ->{
                        expression_value.setText("${leftStr}0$charPoint$rightStr")
                        expression_value.setSelection(leftStr.length + 2)
                    }
                    leftStr[leftStr.length - 1] == ')' ->{
                        expression_value.setText("${leftStr}×0$charPoint$rightStr")
                        expression_value.setSelection(leftStr.length + 3)
                    }
                    else -> {
                        expression_value.setText("$leftStr$charPoint$rightStr")
                        expression_value.setSelection(leftStr.length + 1)
                    }
                }
            } else {
                if ("+-×÷%(".contains(leftStr[leftStr.length - 1])){
                    expression_value.setText("${leftStr}0$charPoint$rightStr")
                    expression_value.setSelection(leftStr.length + 2)
                } else if (leftStr[leftStr.length - 1] == ')'){
                    expression_value.setText("${leftStr}×0$charPoint$rightStr")
                    expression_value.setSelection(leftStr.length + 3)
                }
            }
        }
    }

    // function to control when we try to insert a parenthesis
    // this is necessary to check:
    // 1. if you press and the button and expression is empty put this (-
    // 2. the rest of the requirements is on the function
    /*   finished and checked   */
    @SuppressLint("SetTextI18n")
    private fun insertPlusLess() {
        val oldStr = expression_value.text.toString()
        val cursorPos = expression_value.selectionStart
        val expressionPlusLess = "(-"

        var indexA = 0
        var indexB = 0
        var number = ""
        var removeSign = false

        // case 1: if you want to insert a negative number but the expression is totally empty
        if (oldStr.isEmpty()) {
            expression_value.setText(expressionPlusLess)
            expression_value.setSelection(cursorPos + 2)
        // case 2: when the expression have information
        } else {

            // this part  is to looking for the number and index where start and finish the number
            var cont = cursorPos - 1
            while (cont >= 0) {
                if ("+-×÷%()".contains(oldStr[cont])) {
                    if (oldStr[cont] == '-' && oldStr[cont - 1] == '(') {
                        removeSign = true
                    }
                    indexA = cont + 1
                    cont = 0
                } else {
                    number += oldStr[cont]
                }
                cont--
            }
            number = number.reversed()
            cont = cursorPos

            while (cont < oldStr.length) {
                if ("+-×÷%()".contains(oldStr[cont])) {
                    indexB = cont
                    cont = oldStr.length
                } else {
                    number += oldStr[cont]
                }
                cont++
            }

            if(indexB == 0){
                indexB = oldStr.length
            }

            // in this part we check if we need to remove or put sign
            if (removeSign) {
                var stringA = oldStr.substring( 0,indexA )
                var stringB = oldStr.substring( indexB )

                if (stringA.isNotEmpty()) {
                    if (stringA[stringA.length-1] == '-' && stringA[stringA.length -2] == '(') {
                        stringA = stringA.substring(0,  stringA.length -2)
                    }
                }

                if (stringB.isNotEmpty()) {
                    if (stringB[0] == ')') {
                        stringB = stringB.substring(1)
                        number = number.replace(")", "")
                    }
                }

                expression_value.setText(stringA + number + stringB)
                expression_value.setSelection(stringA.length + number.length)

            } else {
                val stringA = oldStr.substring( 0,indexA)
                val stringB = oldStr.substring( indexB, oldStr.length)

                 if (stringB.isEmpty() && number.isNotEmpty()){
                    // case 1: if you press the button and you got de last number like 21[cursor] or 6+21[cursor]
                    // the result is gonna be (-21[cursor] or 6+(-21[cursor] living the expression open to finish to write
                    number = "(-$number"
                    expression_value.setText(stringA + number + stringB)
                    expression_value.setSelection(stringA.length + number.length)
                } else if(stringA.isNotEmpty() && number.isEmpty()){
                    // case 2: in the case when you don't select a number you put gonna have open to write down the rest
                    // in this example like 21*[cursor] the result is: 21*(-[cursor]
                    // case 3: when you have a close parenthesis on the right

                    number = if (stringA[stringA.length -1] == ')'){ "×(-" } else { "(-" }

                    expression_value.setText(stringA + number + stringB)
                    expression_value.setSelection(stringA.length + number.length)

                     // case 4: to check when is the first position to insert a negative number
                } else if (stringA.isEmpty() && oldStr[cursorPos] == '('){
                     number = "(-$number"
                     expression_value.setText(stringA + number + oldStr.substring(cursorPos))
                     expression_value.setSelection(stringA.length + number.length)

                } else {
                    // case default: if you find a number on the middle of the expression you put this number negative like 21+4[cursor]-9
                    // the result of the expression be easy 21+(-4)-9
                    number = "(-$number)"
                    expression_value.setText(stringA + number + stringB)
                    expression_value.setSelection(stringA.length + number.length)
                }
            }
        }
    }

    // this is necessary to check:
    // 1. check the number of parenthesis
    // 2. check for the expression field is no empty
    // 3.
    private fun calculateResult() {
        var userExp = expression_value.text.toString()

        userExp = userExp.replace('×', '*')
        userExp = userExp.replace('÷', '/')

        val expression = Expression(userExp)
        val result = expression.calculate().toString()
        Result_field.text = result
    }
}