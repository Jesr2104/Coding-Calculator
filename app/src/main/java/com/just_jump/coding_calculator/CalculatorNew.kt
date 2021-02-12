package com.just_jump.coding_calculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.SpannableStringBuilder
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

        number0.setOnClickListener { }

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

    // function to control when we try to insert a math sign
    // this is necessary to check:
    // 1. we can't put two sign together
    // 2. we can't start a expression with a math sign
    // 3. when you press a new math sign and you find one previously you need to change for the new one
    /*   finished and checked   */
    @SuppressLint("SetTextI18n")
    private fun insertMathSign(mathSignToAdd: Char){
        val oldStr = expression_value.text.toString()
        val cursorPos = expression_value.selectionStart

        val leftStr = oldStr.substring(0, cursorPos)
        val rightStr = oldStr.substring(cursorPos)

        if (leftStr.isEmpty()){
            // this case is when you are on the first position and we don't have nothing
        } else{
            if(leftStr.isNotEmpty() && rightStr.isNotEmpty()){
                // case 1: if you gonna put a math sign between two number like 21[cursor]04
                if (leftStr[leftStr.length -1].isDigit() && rightStr[0].isDigit()){
                    expression_value.setText(leftStr + mathSignToAdd + rightStr)
                    expression_value.setSelection(cursorPos + 1)
                    // case 2: if you press a math sign and you find one already on the left you change for the new one 8+[cursor]5
                } else if ("+-×÷%".contains(leftStr[leftStr.length -1])){
                    expression_value.setText(leftStr.substring(0,leftStr.length-1) + mathSignToAdd + rightStr)
                    expression_value.setSelection(cursorPos)
                }
                // case 3: if you press a math sign and you find one already on the right you change for the new one 8[cursor]+5
                else if (leftStr[leftStr.length -1].isDigit() && "+-×÷%".contains(rightStr[0])){
                    expression_value.setText(leftStr + mathSignToAdd + rightStr.substring(1))
                    expression_value.setSelection(cursorPos)
                }
                // case 4: if you try to insert a math sign on the end of number like: 21[cursor]
            } else if (leftStr[leftStr.length -1].isDigit() && rightStr.isEmpty()){
                expression_value.setText(leftStr + mathSignToAdd)
                expression_value.setSelection(cursorPos + 1)
                // case 5:
            } else if ("+-×÷%".contains(leftStr[leftStr.length -1]) && rightStr.isEmpty()){
                expression_value.setText(leftStr.substring(0,leftStr.length -1) + mathSignToAdd)
                expression_value.setSelection(cursorPos)
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


    // function to control when we try to insert a parenthesis
    // this is necessary to check:
    // 1. if you press to insert open parenthesis and you find a number on the right we need to
    //    put [number]× and open parenthesis
    // 2. if you try to insert open parenthesis but you find a close parenthesis you need to insert
    //    the sign × between this two like (20+1)[cursor] result (20+1)*([cursor]
    // 3. we can't start expression with a close parenthesis is the expression is empty
    /*   finished and checked   */
    @SuppressLint("SetTextI18n")
    private fun insertParenthesis(parenthesisToAdd: Char){
        val oldStr = expression_value.text.toString()
        val cursorPos = expression_value.selectionStart

        val leftStr = oldStr.substring(0, cursorPos)
        val rightStr = oldStr.substring(cursorPos)

        if (leftStr.isNotEmpty()){
            if ((leftStr[leftStr.length - 1] == ')' && parenthesisToAdd == '(') || (leftStr[leftStr.length - 1].isDigit() && parenthesisToAdd == '(')) {
                expression_value.setText("$leftStr*$parenthesisToAdd$rightStr")
                expression_value.setSelection(cursorPos + 2)
            }
            else {
                expression_value.setText(leftStr + parenthesisToAdd + rightStr)
                expression_value.setSelection(cursorPos + 1)
            }
        } else {
            if(rightStr.isEmpty() && parenthesisToAdd == ')'){
                // if the string leftStr and rightStr is empty
            } else {
                expression_value.setText(leftStr + parenthesisToAdd + rightStr)
                expression_value.setSelection(cursorPos + 1)
            }
        }
    }

    private fun insertPoint(){

    }

    private fun insertPlusLess(){

    }
}