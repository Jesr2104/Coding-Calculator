package com.just_jump.coding_calculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.view.MotionEvent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import com.github.hamzaahmedkhan.spinnerdialog.callbacks.OnSpinnerOKPressedListener
import com.github.hamzaahmedkhan.spinnerdialog.enums.SpinnerSelectionType
import com.github.hamzaahmedkhan.spinnerdialog.models.SpinnerModel
import com.github.hamzaahmedkhan.spinnerdialog.ui.SpinnerDialogFragment
import com.just_jump.coding_calculator.data.local.SRDataExpression
import com.just_jump.coding_calculator.extensions.checkInteger
import com.just_jump.coding_calculator.extensions.checkParenthesis
import com.just_jump.coding_calculator.extensions.paintString
import kotlinx.android.synthetic.main.activity_calculator_new.*
import kotlinx.android.synthetic.main.activity_calculator_new.historic
import kotlinx.android.synthetic.main.activity_calculator_new.number0
import kotlinx.android.synthetic.main.activity_calculator_new.number1
import kotlinx.android.synthetic.main.activity_calculator_new.number2
import kotlinx.android.synthetic.main.activity_calculator_new.number3
import kotlinx.android.synthetic.main.activity_calculator_new.number4
import kotlinx.android.synthetic.main.activity_calculator_new.number5
import kotlinx.android.synthetic.main.activity_calculator_new.number6
import kotlinx.android.synthetic.main.activity_calculator_new.number7
import kotlinx.android.synthetic.main.activity_calculator_new.number8
import kotlinx.android.synthetic.main.activity_calculator_new.number9
import kotlinx.android.synthetic.main.activity_calculator_new.numberAllClear
import kotlinx.android.synthetic.main.activity_calculator_new.numberBackSpace
import kotlinx.android.synthetic.main.activity_calculator_new.numberCloseParenthesis
import kotlinx.android.synthetic.main.activity_calculator_new.numberDivide
import kotlinx.android.synthetic.main.activity_calculator_new.numberLess
import kotlinx.android.synthetic.main.activity_calculator_new.numberMultiply
import kotlinx.android.synthetic.main.activity_calculator_new.numberOpenParenthesis
import kotlinx.android.synthetic.main.activity_calculator_new.numberPercentage
import kotlinx.android.synthetic.main.activity_calculator_new.numberPlus
import kotlinx.android.synthetic.main.activity_calculator_new.numberPlusLess
import kotlinx.android.synthetic.main.activity_calculator_new.numberPoint
import kotlinx.android.synthetic.main.activity_calculator_new.numberResult
import org.mariuszgromada.math.mxparser.Expression
import java.math.BigInteger

class CalculatorNew : AppCompatActivity() {
    private var stateResult = false
    private var mainHandler: Handler? = null
    private var decimalSeparator = '.'
    private var numberOfParenthesis = 0

    private val action = object : Runnable {
        override fun run() {
            backspaceBTN()
            mainHandler?.postDelayed(this, 150)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
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

        numberPercentage.setOnClickListener { insertPercentage() }

        numberOpenParenthesis.setOnClickListener { insertParenthesis('(') }

        numberCloseParenthesis.setOnClickListener { insertParenthesis(')') }

        numberPlusLess.setOnClickListener { insertPlusLess() }

        numberPoint.setOnClickListener { insertPoint() }

        /***************************************************************************/
        // clear field
        /***************************************************************************/

        numberAllClear.setOnClickListener { allClear() }

        numberBackSpace.setOnClickListener { backspaceBTN() }

        numberBackSpace.setOnTouchListener { _, motionEvent ->
            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {
                    if (mainHandler != null)
                        true
                    mainHandler = Handler()
                    mainHandler?.postDelayed(action, 150)
                    false
                }
                MotionEvent.ACTION_UP -> {
                    if (mainHandler == null)
                        true
                    mainHandler?.removeCallbacks(action)
                    mainHandler = null
                    false
                }
                else -> false
            }
        }

        /***************************************************************************/
        // Result expression
        /***************************************************************************/

        numberResult.setOnClickListener { calculateResult() }

        /***************************************************************************/
        // History
        /***************************************************************************/

        historic.setOnClickListener { showHistory() }
    }

    // this function clear all the field
    /*   finished and checked   */
    private fun allClear() {
        expression_value.setText("")
        Result_field.text = ""
        numberOfParenthesis = 0
    }

    // this function delete the char on the left of the cursor
    // this is necessary to check:
    // 1. when you delete a number need to check if the number have sense
    /*   finished and checked   */
    private fun backspaceBTN() {
        // clear the expression is the state of the result is already shown
        clearExpression()

        val cursorPos = expression_value.selectionStart

        // case: if you delete number 9000 for example need to check for the rest o the number if
        // this number is 0000 need you put just one 0 on the expression

        // case: if you have 5+(9-4) and you delete number 5 need to delete the math sign before
        // 5 to don't leave any math orphan sign

        if (expression_value.text.isNotEmpty()){
            val selection = expression_value.text as SpannableStringBuilder

            if (selection[cursorPos - 1].isDigit()){
                selection.replace(cursorPos - 1, cursorPos, "")
                expression_value.setText(formatColor(checkNumbers(selection.toString())))  //#formatColor
                expression_value.setSelection(cursorPos - 1)
            } else if ("+-×÷()%$decimalSeparator".contains(selection[cursorPos - 1])){

                if (selection[cursorPos - 1] == '('){
                    numberOfParenthesis--
                }

                selection.replace(cursorPos - 1, cursorPos, "")
                expression_value.text = formatColor(selection) //#formatColor
                expression_value.setSelection(cursorPos - 1)
            }
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
        if (stateResult){
            val resultData = Result_field.text.toString()

            expression_value.setText(formatColor(resultData)) //#formatColor
            expression_value.setSelection(resultData.length)
            Result_field.text = ""
            stateResult = false
        }

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
                    expression_value.setText(formatColor(leftStr + mathSignToAdd + rightStr)) //#formatColor
                    expression_value.setSelection(cursorPos + 1)
                    // case 2: if you press a math sign and you find one already on the left you change for the new one 8+[cursor]5
                } else if ("+-×÷".contains(leftStr[leftStr.length - 1])) {
                    if(leftStr[leftStr.length - 2] == '(') {
                        if (leftStr[leftStr.length - 1] == '-' || leftStr[leftStr.length - 1] == '+'){
                            if (mathSignToAdd == '+') {
                                expression_value.setText(formatColor(leftStr.substring(0, leftStr.length - 1) + mathSignToAdd + rightStr)) //#formatColor
                                expression_value.setSelection(cursorPos)
                            } else if (mathSignToAdd == '-') {
                                expression_value.setText(formatColor(leftStr.substring(0, leftStr.length - 1) + mathSignToAdd + rightStr)) //#formatColor
                                expression_value.setSelection(cursorPos)
                            }
                        }
                    } else {
                        expression_value.setText(formatColor(leftStr.substring(0,leftStr.length - 1) + mathSignToAdd + rightStr)) //#formatColor
                        expression_value.setSelection(cursorPos)
                    }
                }   else if (leftStr[leftStr.length - 1].isDigit() && rightStr[0] == '(') {
                    expression_value.setText(formatColor(leftStr + mathSignToAdd + rightStr)) //#formatColor
                    expression_value.setSelection(cursorPos + 1)
                }
                // case 3: if you press a math sign and you find one already on the right you change for the new one 8[cursor]+5
                else if (leftStr[leftStr.length - 1].isDigit() && "+-×÷".contains(rightStr[0])) {
                    expression_value.setText(formatColor(leftStr + mathSignToAdd + rightStr.substring(1))) //#formatColor
                    expression_value.setSelection(cursorPos)
                }
                // case 6: it's the same but for the case we find data on both side
                else if (")".contains(leftStr[leftStr.length - 1])) {
                    if (!("+-×÷".contains(rightStr[0]))){
                        expression_value.setText(formatColor(leftStr + mathSignToAdd + rightStr)) //#formatColor
                        expression_value.setSelection(cursorPos + 1)
                    } else {
                        expression_value.setText(formatColor(leftStr + mathSignToAdd + rightStr.substring(1))) //#formatColor
                        expression_value.setSelection(cursorPos + 1)
                    }
                } else if (leftStr[leftStr.length - 1] != '('){
                    expression_value.setText(formatColor(leftStr + mathSignToAdd + rightStr)) //#formatColor
                    expression_value.setSelection(cursorPos + 1)
                }
                // case 4: if you try to insert a math sign on the end of number like: 21[cursor]
            } else if (leftStr[leftStr.length - 1].isDigit() && rightStr.isEmpty()) {
                expression_value.setText(formatColor(leftStr + mathSignToAdd)) //#formatColor
                expression_value.setSelection(cursorPos + 1)
                // case 5: if you want to insert a math sign but is one on the right you change this for the new one
            } else if ("+-×÷".contains(leftStr[leftStr.length - 1]) && rightStr.isEmpty()) {
                if(leftStr[leftStr.length - 2] == '(') {
                    if (leftStr[leftStr.length - 1] == '-' || leftStr[leftStr.length - 1] == '+'){
                        if (mathSignToAdd == '+') {
                            expression_value.setText(formatColor(leftStr.substring(0, leftStr.length - 1) + mathSignToAdd)) //#formatColor
                            expression_value.setSelection(cursorPos)
                        } else if (mathSignToAdd == '-') {
                            expression_value.setText(formatColor(leftStr.substring(0, leftStr.length - 1) + mathSignToAdd)) //#formatColor
                            expression_value.setSelection(cursorPos)
                        }
                    }
                } else {
                    expression_value.setText(formatColor(leftStr.substring(0, leftStr.length - 1) + mathSignToAdd)) //#formatColor
                    expression_value.setSelection(cursorPos)
                }
                // case 6: if you press a math sign but is a close parenthesis on the left
            } else if (")".contains(leftStr[leftStr.length - 1])) {
                expression_value.setText(formatColor(leftStr + mathSignToAdd + rightStr)) //#formatColor
                expression_value.setSelection(cursorPos + 1)
                // when we got percentage sign and we want to insert math sign
            } else if (leftStr[leftStr.length - 1] == '%'){
                expression_value.setText(formatColor(leftStr + mathSignToAdd + rightStr)) //#formatColor
                expression_value.setSelection(cursorPos + 1)
            }
        }
    }

    // function to insert number
    // this is necessary to check:
    // 1. if the user insert a number but if zero previously 0 need to change for the new number
    // 2. when you press a number and you find on the right close parenthesis we need to change for
    //    a multiply parenthesis like: (20+1)[cursor] and press 4 need to finish (20+1)×4[cursor]
    /*   finished and checked   */
    @SuppressLint("SetTextI18n")
    private fun insertNumber(numberToAdd: Char) {
        // clear the expression is the state of the result is already shown
        clearExpression()

        val oldStr = expression_value.text.toString()
        val cursorPos = expression_value.selectionStart

        // string on the left and on the right of the cursor
        val leftStr = oldStr.substring(0, cursorPos)
        val rightStr = oldStr.substring(cursorPos)

        if (oldStr.isEmpty()){
            // case: if you find empty the expression
            expression_value.setText(formatColor(leftStr + numberToAdd + rightStr)) //#formatColor
            expression_value.setSelection(cursorPos + 1)
        } else {
            // the case if you have expression in both side
            if (leftStr.isNotEmpty() && rightStr.isNotEmpty()){
                // if you find number in both side of the cursor like: 3[cursor]3
                if (leftStr[leftStr.length - 1].isDigit() && rightStr[0].isDigit()){
                    //Case: Number[cursor]Number: 6[cursor]6
                    /*  It's missing to set the condition to put this number
                    *   3[cursor]3 -> result: 3[Number][cursor]3
                    *   0[cursor]3 -> result: 0[Number][cursor]3 "in this case you delete number zero because can bee before a number like 30[cursor]3"
                    * */
                    expression_value.setText(formatColor(leftStr + numberToAdd + rightStr)) //#formatColor
                    expression_value.setSelection(cursorPos + 1)
                }
                else if ("+-×÷$decimalSeparator".contains(leftStr[leftStr.length -1]) && rightStr[0].isDigit()) {
                    //Case: Number[cursor]Number: "+-×÷."[cursor]6
                    expression_value.setText(formatColor(leftStr + numberToAdd + rightStr)) //#formatColor
                    expression_value.setSelection(cursorPos + 1)
                }
                else if (leftStr[leftStr.length - 1].isDigit() && "+-×÷$decimalSeparator".contains(rightStr[0])){
                    //Case: Number[cursor]MathSign: 6[cursor]"+-×÷"
                    if (leftStr[leftStr.length -1] == '0'){
                        // case: 0[cursor]
                        if (checkReplaceZero(oldStr, cursorPos)){
                            expression_value.setText(formatColor("${leftStr.substring(0, leftStr.length - 1)}$numberToAdd$rightStr")) //#formatColor
                            expression_value.setSelection(cursorPos)
                        } else {
                            // if the number if different of zero we can add the new number
                            expression_value.setText(formatColor("$leftStr$numberToAdd$rightStr")) //#formatColor
                            expression_value.setSelection(cursorPos + 1)
                        }
                    } else {
                        // if the number if different of zero we can add the new number
                        expression_value.setText(formatColor("$leftStr$numberToAdd$rightStr")) //#formatColor
                        expression_value.setSelection(cursorPos + 1)
                    }
                }
                else if ("+-×÷$decimalSeparator".contains(leftStr[leftStr.length -1]) && "+-×÷$decimalSeparator".contains(rightStr[0])){
                    //Case: MathSign[cursor]MathSign: "+-×÷"[cursor]"+-×÷"
                    expression_value.setText(formatColor(leftStr + numberToAdd + rightStr)) //#formatColor
                    expression_value.setSelection(cursorPos + 1)
                }
                else if (leftStr[leftStr.length -1] == ')' && rightStr[0].isDigit()){
                    //Case: closeParenthesis[cursor]Number: )[cursor]6
                    expression_value.setText(formatColor("$leftStr×$numberToAdd$rightStr")) //#formatColor
                    expression_value.setSelection(cursorPos + 1)
                }
                else if (leftStr[leftStr.length - 1].isDigit() && rightStr[0] == '('){
                    //Case: Number[cursor]openParenthesis: 6[cursor](
                    if (leftStr[leftStr.length -1] == '0'){
                        // case: 0[cursor]
                        if (checkReplaceZero(oldStr, cursorPos)){
                            expression_value.setText(formatColor("${leftStr.substring(0, leftStr.length - 1)}$numberToAdd×$rightStr")) //#formatColor
                            expression_value.setSelection(cursorPos)
                        } else {
                            // if the number if different of zero we can add the new number
                            expression_value.setText(formatColor("$leftStr$numberToAdd×$rightStr")) //#formatColor
                            expression_value.setSelection(cursorPos + 1)
                        }
                    } else {
                        // if the number if different of zero we can add the new number
                        expression_value.setText(formatColor("$leftStr$numberToAdd×$rightStr")) //#formatColor
                        expression_value.setSelection(cursorPos + 1)
                    }
                }
                else if (leftStr[leftStr.length -1] == ')' && rightStr[0] == '('){
                    //Case: closeParenthesis[cursor]openParenthesis: )[cursor](
                    expression_value.setText(formatColor("$leftStr×$numberToAdd×$rightStr")) //#formatColor
                    expression_value.setSelection(cursorPos + 1)
                }
                else if (leftStr[leftStr.length -1] == '(' && "+-×÷".contains(rightStr[0])) {
                    //Case: openParenthesis[cursor]"+-×÷": ([cursor]"+-×÷"
                    expression_value.setText(formatColor("$leftStr$numberToAdd$rightStr")) //#formatColor
                    expression_value.setSelection(cursorPos + 1)
                }
                else if ("+-×÷$decimalSeparator".contains(leftStr[leftStr.length -1]) && rightStr[0] == ')'){
                    //Case: "+-×÷."[cursor]closeParenthesis: "+-×÷."[cursor])
                    expression_value.setText(formatColor("$leftStr$numberToAdd$rightStr")) //#formatColor
                    expression_value.setSelection(cursorPos + 1)
                }
                else if (leftStr[leftStr.length -1].isDigit() && rightStr[0] == ')'){
                    //Case: Number[cursor]closeParenthesis: 6[cursor])
                    if (leftStr[leftStr.length -1] == '0'){
                        // case: 0[cursor]
                        if (checkReplaceZero(oldStr, cursorPos)){
                            expression_value.setText(formatColor("${leftStr.substring(0, leftStr.length - 1)}$numberToAdd$rightStr")) //#formatColor
                            expression_value.setSelection(cursorPos)
                        } else {
                            // if the number if different of zero we can add the new number
                            expression_value.setText(formatColor("$leftStr$numberToAdd$rightStr")) //#formatColor
                            expression_value.setSelection(cursorPos + 1)
                        }
                    } else {
                        // if the number if different of zero we can add the new number
                        expression_value.setText(formatColor("$leftStr$numberToAdd$rightStr")) //#formatColor
                        expression_value.setSelection(cursorPos + 1)
                    }
                }
                else if (leftStr[leftStr.length -1] == '(' && rightStr[0].isDigit()){
                    //Case: openParenthesis[cursor]Number ([cursor]Number
                    expression_value.setText(formatColor(leftStr + numberToAdd + rightStr)) //#formatColor
                    expression_value.setSelection(cursorPos + 1)
                }
                else if (leftStr[leftStr.length -1] == '(' && rightStr[0] == ')') {
                    //Case: openParenthesis[cursor]closeParenthesis: ([cursor])
                    expression_value.setText(formatColor(leftStr + numberToAdd + rightStr)) //#formatColor
                    expression_value.setSelection(cursorPos + 1)
                }
                else if (leftStr[leftStr.length -1] == '(' && rightStr[0] == '(') {
                    //Case: openParenthesis[cursor]openParenthesis: ([cursor](
                    expression_value.setText(formatColor("$leftStr$numberToAdd×$rightStr")) //#formatColor
                    expression_value.setSelection(cursorPos + 1)
                }
                else if (leftStr[leftStr.length -1] == ')' && rightStr[0] == ')') {
                    //Case: closeParenthesis[cursor]closeParenthesis: ([cursor](
                    expression_value.setText(formatColor("$leftStr×$numberToAdd$rightStr")) //#formatColor
                    expression_value.setSelection(cursorPos + 1)
                }
                else if ("+-×÷$decimalSeparator".contains(leftStr[leftStr.length -1]) && rightStr[0] == '(') {
                    //Case: MathSign[cursor]openParenthesis: "+-×÷."[cursor](
                    expression_value.setText(formatColor("$leftStr$numberToAdd×$rightStr")) //#formatColor
                    expression_value.setSelection(cursorPos + 1)
                }
                else if (leftStr[leftStr.length -1] == ')' && "+-×÷".contains(rightStr[0])) {
                    //Case: closeParenthesis[cursor]MathSign: ([cursor]"+-×÷"
                    expression_value.setText(formatColor("$leftStr×$numberToAdd$rightStr")) //#formatColor
                    expression_value.setSelection(cursorPos + 1)
                }
            } else {
                if (leftStr.isEmpty() && rightStr.isNotEmpty()){
                    when {
                        "+-×÷.".contains(rightStr[0]) -> {
                            expression_value.setText(formatColor("$numberToAdd$rightStr")) //#formatColor
                            expression_value.setSelection(cursorPos + 1)
                        }
                        rightStr[0] == '(' -> {
                            expression_value.setText(formatColor("$numberToAdd×$rightStr")) //#formatColor
                            expression_value.setSelection(cursorPos + 1)
                        }
                        rightStr[0] == ')' -> {

                        }
                        rightStr[0].isDigit() -> {
                            expression_value.setText(formatColor("$numberToAdd$rightStr")) //#formatColor
                            expression_value.setSelection(cursorPos + 1)
                        }
                    }
                } else if (leftStr.isNotEmpty() && rightStr.isEmpty()){
                    if ("+-×÷$decimalSeparator".contains(leftStr[leftStr.length -1])){
                        // case: MathSign[cursor]
                        expression_value.setText(formatColor("$leftStr$numberToAdd")) //#formatColor
                        expression_value.setSelection(cursorPos + 1)
                    } else if (leftStr[leftStr.length -1] == '(') {
                        // case: openParenthesis[cursor]
                        expression_value.setText(formatColor("$leftStr$numberToAdd")) //#formatColor
                        expression_value.setSelection(cursorPos + 1)
                    } else if (leftStr[leftStr.length -1] == ')') {
                        // case: closeParenthesis[cursor]
                        expression_value.setText(formatColor("$leftStr×$numberToAdd")) //#formatColor
                        expression_value.setSelection(cursorPos + 2)
                    } else if (leftStr[leftStr.length -1] == '.') {
                        // case: .[cursor]
                        expression_value.setText(formatColor("$leftStr$numberToAdd")) //#formatColor
                        expression_value.setSelection(cursorPos + 1)
                    } else if (leftStr[leftStr.length -1] == '%') {
                        // case: %[cursor]
                        expression_value.setText(formatColor("$leftStr×$numberToAdd")) //#formatColor
                        expression_value.setSelection(cursorPos + 2)
                    } else if (leftStr[leftStr.length -1].isDigit()) {
                        // case: Number[cursor]
                        if (leftStr[leftStr.length -1] == '0'){
                            // case: 0[cursor]
                            if (checkReplaceZero(oldStr, cursorPos)){
                                expression_value.setText(formatColor("${leftStr.substring(0, leftStr.length - 1)}$numberToAdd")) //#formatColor
                                expression_value.setSelection(cursorPos)
                            } else {
                                expression_value.setText(formatColor("$leftStr$numberToAdd")) //#formatColor
                                expression_value.setSelection(cursorPos + 1)
                            }
                        } else {
                            // case: Number!=0[cursor]
                            expression_value.setText(formatColor("$leftStr$numberToAdd")) //#formatColor
                            expression_value.setSelection(cursorPos + 1)
                        }
                    }
                }
            }
        }
    }

    // function to insert number
    // this is necessary to check:
    // 1. the used can't be put two zero together in the integer part like 000
    // 2. the can can put all zero you need if you are on the decimal par of the number
    /*   finished and checked   */
    @SuppressLint("SetTextI18n")
    private fun insertNumberZero() {
        // clear the expression is the state of the result is already shown
        clearExpression()

        val oldStr = expression_value.text.toString()
        val cursorPos = expression_value.selectionStart
        val number = getNumberOnExpression()

        // string on the left and on the right of the cursor
        val leftStr = oldStr.substring(0, cursorPos)
        val rightStr = oldStr.substring(cursorPos)

        if (oldStr.isEmpty()){
            // case: if you find empty the expression
            expression_value.setText(formatColor(leftStr + '0' + rightStr)) //#formatColor
            expression_value.setSelection(cursorPos + 1)
        } else {
            if (leftStr.isNotEmpty() && rightStr.isNotEmpty()){
                // if you find data on both side of the cursor for example: 25[cursor]45
                if (leftStr[leftStr.length - 1].isDigit() && rightStr[0].isDigit()){
                    //Case: Number[cursor]Number: 6[cursor]6
                    if (number.toDouble() > 0 || number.contains(decimalSeparator) || (number.contains('-') && number != "-0")){
                        expression_value.setText(formatColor(leftStr + '0' + rightStr)) //#formatColor
                        expression_value.setSelection(cursorPos + 1)
                    }
                }
                else if ("+-×÷".contains(leftStr[leftStr.length -1]) && rightStr[0].isDigit()){
                    //Case: MathSign[cursor]Number: "+-×÷"[cursor]6
                    if (number.toDouble() > 0 || number.contains(decimalSeparator) || (number.contains('-') && number != "-0")){
                        expression_value.setText(formatColor(leftStr + rightStr)) //#formatColor
                        expression_value.setSelection(cursorPos)
                    }
                }
                else if (leftStr[leftStr.length - 1].isDigit() && "+-×÷$decimalSeparator".contains(rightStr[0])){
                    if (rightStr[0] == decimalSeparator && number.toDouble().toInt() == 0){
                        // this if don't need to do nothing
                    }
                    //Case: Number[cursor]MathSign: 6[cursor]"+-×÷."
                    else if (number.toDouble() > 0 || number.contains(decimalSeparator) || (number.contains('-') && number != "-0")){
                        expression_value.setText(formatColor(leftStr + "0" + rightStr)) //#formatColor
                        expression_value.setSelection(cursorPos + 1)
                    }
                }
                else if (leftStr[leftStr.length -1]== decimalSeparator){
                    //Case: .[cursor]Number: 0.[cursor]wherever
                    if (number.toDouble() > 0 || number.contains(decimalSeparator) || (number.contains('-') && number != "-0")){
                        expression_value.setText(formatColor(leftStr + "0" + rightStr)) //#formatColor
                        expression_value.setSelection(cursorPos + 1)
                    }
                }
                else if ("+-×÷$decimalSeparator".contains(leftStr[leftStr.length -1]) && "+-×÷$decimalSeparator".contains(rightStr[0])){
                    //Case: MathSign[cursor]MathSign: "+-×÷"[cursor]"+-×÷"
                    expression_value.setText(formatColor("${leftStr}0$rightStr")) //#formatColor
                    expression_value.setSelection(cursorPos + 1)
                }
                else if (leftStr[leftStr.length -1] == ')' && rightStr[0].isDigit()) {
                    //Case: closeParenthesis[cursor]MathSign: )[cursor]"+-×÷"
                    expression_value.setText(formatColor("${leftStr}×$rightStr")) //#formatColor
                    expression_value.setSelection(cursorPos + 1)
                }
                else if (leftStr[leftStr.length - 1].isDigit() && rightStr[0] == '('){
                    //Case: Number[cursor]openParenthesis 6[cursor](
                    if (number.toDouble() > 0 || number.contains(decimalSeparator) || (number.contains('-') && number != "-0")){
                        expression_value.setText(formatColor("${leftStr}0×$rightStr")) //#formatColor
                        expression_value.setSelection(cursorPos + 1)
                    }
                }
                else if (leftStr[leftStr.length -1] == ')' && rightStr[0] == '('){
                    //Case: closeParenthesis[cursor]openParenthesis )[cursor](
                    expression_value.setText(formatColor("${leftStr}×0×$rightStr")) //#formatColor
                    expression_value.setSelection(cursorPos + 2)
                }
                else if (leftStr[leftStr.length -1] == '(' && "+-×÷".contains(rightStr[0])){
                    //Case: openParenthesis[cursor]MathSign ([cursor]"+-×÷"
                    expression_value.setText(formatColor("${leftStr}0$rightStr")) //#formatColor
                    expression_value.setSelection(cursorPos + 1)
                }
                else if ("+-×÷".contains(leftStr[leftStr.length -1]) && rightStr[0] == '('){
                    //Case: MathSign[cursor]openParenthesis "+-×÷"[cursor](
                    expression_value.setText(formatColor("${leftStr}0×$rightStr")) //#formatColor
                    expression_value.setSelection(cursorPos + 1)
                }
                else if (leftStr[leftStr.length -1] == ')' && "+-×÷".contains(rightStr[0])){
                    //Case: closeParenthesis[cursor]MathSign )[cursor]"+-×÷"
                    expression_value.setText(formatColor("${leftStr}×0$rightStr")) //#formatColor
                    expression_value.setSelection(cursorPos + 1)
                }
                else if ("+-×÷".contains(leftStr[leftStr.length -1]) && rightStr[0] == ')'){
                    //Case: MathSign[cursor]closeParenthesis "+-×÷"[cursor])
                    expression_value.setText(formatColor("${leftStr}0$rightStr")) //#formatColor
                    expression_value.setSelection(cursorPos + 1)
                }
                else if (leftStr[leftStr.length - 1].isDigit() && rightStr[0] == ')') {
                    //Case: Number[cursor]closeParenthesis Number[cursor])
                    if (number.toDouble() > 0 || number.contains(decimalSeparator) || (number.contains('-') && number != "-0")){
                        expression_value.setText(formatColor("${leftStr}0$rightStr")) //#formatColor
                        expression_value.setSelection(cursorPos + 1)
                    }
                }
                else if (leftStr[leftStr.length -1] == ')' && rightStr[0].isDigit()){
                    //Case: openParenthesis[cursor]Number ([cursor]Number
                    expression_value.setText(formatColor("$leftStr×$rightStr")) //#formatColor
                    expression_value.setSelection(cursorPos)
                }
                else if (leftStr[leftStr.length -1] == '(' && rightStr[0] == ')'){
                    //Case: openParenthesis[cursor]closeParenthesis ([cursor])
                    expression_value.setText(formatColor("${leftStr}0$rightStr")) //#formatColor
                    expression_value.setSelection(cursorPos + 1)
                }
                else if (leftStr[leftStr.length -1] == '%' && "+-×÷".contains(rightStr[0])){
                    //Case: %[cursor]MathSign %[cursor]"+-×÷"
                    expression_value.setText(formatColor("${leftStr}×0$rightStr")) //#formatColor
                    expression_value.setSelection(cursorPos + 1)
                }
                else if (leftStr[leftStr.length -1] == '%' && rightStr[0] == '('){
                    //Case: %[cursor]openParenthesis %[cursor](
                    expression_value.setText(formatColor("${leftStr}×0×$rightStr")) //#formatColor
                    expression_value.setSelection(cursorPos + 2)
                }
                else if (leftStr[leftStr.length -1] == '(' && rightStr[0] == '('){
                    //Case: closeParenthesis[cursor]openParenthesis ([cursor](
                    expression_value.setText(formatColor("${leftStr}0×$rightStr")) //#formatColor
                    expression_value.setSelection(cursorPos + 2)
                }
                else if (leftStr[leftStr.length -1] == ')' && rightStr[0] == ')'){
                    //Case: closeParenthesis[cursor]openParenthesis )[cursor])
                    expression_value.setText(formatColor("${leftStr}×0$rightStr")) //#formatColor
                    expression_value.setSelection(cursorPos + 2)
                }
            } else if (leftStr.isNotEmpty() && rightStr.isEmpty()){
                // if the number is empty can be something like: (-, (
                if (number.isEmpty()){
                    if (leftStr[leftStr.length -1] == '(' || leftStr[leftStr.length -1] == decimalSeparator || "+-×÷".contains(leftStr[leftStr.length -1])){
                        expression_value.setText(formatColor(leftStr + "0")) //#formatColor
                        expression_value.setSelection(cursorPos + 1)
                    }
                    else if (leftStr[leftStr.length -1] == ')' || leftStr[leftStr.length -1] == '%'){
                        expression_value.setText(formatColor("$leftStr×0")) //#formatColor
                        expression_value.setSelection(cursorPos + 2)
                    }
                } else if (leftStr[leftStr.length -1].isDigit() ||  leftStr[leftStr.length -1] == '.') {
                    if (number.toDouble() > 0 || number.contains(decimalSeparator) || (number.contains('-') && number != "-0")){
                        expression_value.setText(formatColor(leftStr + "0")) //#formatColor
                        expression_value.setSelection(cursorPos + 1)
                    }
                }
            } else if (leftStr.isEmpty() && rightStr.isNotEmpty()){
                // if you find data just on the right side of the cursor for example [cursor]25
            }
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
        // clear the expression is the state of the result is already shown
        clearExpression()

        val oldStr = expression_value.text.toString()
        val cursorPos = expression_value.selectionStart

        val leftStr = oldStr.substring(0, cursorPos)
        val rightStr = oldStr.substring(cursorPos)

        // with this conditions we gonna check the number of parenthesis
        // if you want to open parenthesis always you can write down one new one
        // or if you want to close one open we gonna check the number of the parenthesis you open before
        if (parenthesisToAdd == '(' || numberOfParenthesis > 0){

            if (leftStr.isNotEmpty() && rightStr.isNotEmpty()){
                if(rightStr[0] == '(' && parenthesisToAdd == ')'){
                    expression_value.setText(formatColor("$leftStr$parenthesisToAdd×$rightStr")) //#formatColor
                    expression_value.setSelection(leftStr.length + 2)
                    if (parenthesisToAdd == '('){
                        numberOfParenthesis++
                    } else {
                        numberOfParenthesis--
                    }

                } else {
                    if (leftStr[leftStr.length - 1].isDigit() && parenthesisToAdd == '(') {
                        expression_value.setText(formatColor("$leftStr×$parenthesisToAdd$rightStr")) //#formatColor
                        expression_value.setSelection(cursorPos + 2)
                        if (parenthesisToAdd == '('){
                            numberOfParenthesis++
                        } else {
                            numberOfParenthesis--
                        }
                    } else {
                        expression_value.setText(formatColor(leftStr + parenthesisToAdd + rightStr)) //#formatColor
                        expression_value.setSelection(cursorPos + 1)
                        if (parenthesisToAdd == '('){
                            numberOfParenthesis++
                        } else {
                            numberOfParenthesis--
                        }
                    }
                }
            } else if (leftStr.isNotEmpty()) {
                if ((leftStr[leftStr.length - 1] == '%' && parenthesisToAdd == '(') || (leftStr[leftStr.length - 1] == ')' && parenthesisToAdd == '(') || (leftStr[leftStr.length - 1].isDigit() && parenthesisToAdd == '(')) {
                    expression_value.setText(formatColor("$leftStr×$parenthesisToAdd$rightStr")) //#formatColor
                    expression_value.setSelection(cursorPos + 2)
                    if (parenthesisToAdd == '('){
                        numberOfParenthesis++
                    } else {
                        numberOfParenthesis--
                    }
                } else {
                    expression_value.setText(formatColor(leftStr + parenthesisToAdd + rightStr)) //#formatColor
                    expression_value.setSelection(cursorPos + 1)
                    if (parenthesisToAdd == '('){
                        numberOfParenthesis++
                    } else {
                        numberOfParenthesis--
                    }
                }
            } else {
                if (rightStr.isEmpty() && parenthesisToAdd == ')') {
                    // if the string leftStr and rightStr is empty
                } else {
                    expression_value.setText(formatColor(leftStr + parenthesisToAdd + rightStr)) //#formatColor
                    expression_value.setSelection(cursorPos + 1)
                    if (parenthesisToAdd == '('){
                        numberOfParenthesis++
                    } else {
                        numberOfParenthesis--
                    }
                }
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
        // clear the expression is the state of the result is already shown
        clearExpression()

        val oldStr = expression_value.text.toString()
        val cursorPos = expression_value.selectionStart
        val charPoint = decimalSeparator

        val leftStr = oldStr.substring(0, cursorPos)
        val rightStr = oldStr.substring(cursorPos)

        if (oldStr.isEmpty()){
            expression_value.setText(formatColor("0$charPoint")) //#formatColor
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
                        expression_value.setText(formatColor("${leftStr}0$charPoint$rightStr")) //#formatColor
                        expression_value.setSelection(leftStr.length + 2)
                    }
                    leftStr[leftStr.length - 1] == ')' ->{
                        expression_value.setText(formatColor("${leftStr}×0$charPoint$rightStr")) //#formatColor
                        expression_value.setSelection(leftStr.length + 3)
                    }
                    else -> {
                        expression_value.setText(formatColor("$leftStr$charPoint$rightStr")) //#formatColor
                        expression_value.setSelection(leftStr.length + 1)
                    }
                }
            } else {
                if ("+-×÷%(".contains(leftStr[leftStr.length - 1])){
                    expression_value.setText(formatColor("${leftStr}0$charPoint$rightStr")) //#formatColor
                    expression_value.setSelection(leftStr.length + 2)
                } else if (leftStr[leftStr.length - 1] == ')'){
                    expression_value.setText(formatColor("${leftStr}×0$charPoint$rightStr")) //#formatColor
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
        // clear the expression is the state of the result is already shown
        clearExpression()

        val oldStr = expression_value.text.toString()
        val cursorPos = expression_value.selectionStart
        val expressionPlusLess = "(-"

        var indexA = 0
        var indexB = 0
        var number = ""
        var removeSign = false

        // case 1: if you want to insert a negative number but the expression is totally empty
        if (oldStr.isEmpty()) {
            expression_value.setText(formatColor(expressionPlusLess)) //#formatColor
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

                expression_value.setText(formatColor(stringA + number + stringB)) //#formatColor
                expression_value.setSelection(stringA.length + number.length)

            } else {
                val stringA = oldStr.substring( 0,indexA)
                val stringB = oldStr.substring( indexB, oldStr.length)

                 if (stringB.isEmpty() && number.isNotEmpty()){
                    // case 1: if you press the button and you got de last number like 21[cursor] or 6+21[cursor]
                    // the result is gonna be (-21[cursor] or 6+(-21[cursor] living the expression open to finish to write
                    number = "(-$number"
                    expression_value.setText(formatColor(stringA + number + stringB)) //#formatColor
                    expression_value.setSelection(stringA.length + number.length)
                } else if(stringA.isNotEmpty() && number.isEmpty()){
                    // case 2: in the case when you don't select a number you put gonna have open to write down the rest
                    // in this example like 21*[cursor] the result is: 21*(-[cursor]
                    // case 3: when you have a close parenthesis on the right

                    number = if (stringA[stringA.length -1] == ')' || stringA[stringA.length -1] == '%'){ "×(-" } else { "(-" }

                    expression_value.setText(formatColor(stringA + number + stringB)) //#formatColor
                    expression_value.setSelection(stringA.length + number.length)

                     // case 4: to check when is the first position to insert a negative number
                } else if (stringA.isEmpty() && oldStr[cursorPos] == '('){
                     number = "(-$number"
                     expression_value.setText(formatColor(stringA + number + oldStr.substring(cursorPos))) //#formatColor
                     expression_value.setSelection(stringA.length + number.length)

                } else {
                    // case default: if you find a number on the middle of the expression you put this number negative like 21+4[cursor]-9
                    // the result of the expression be easy 21+(-4)-9
                    number = "(-$number)"
                    expression_value.setText(formatColor(stringA + number + stringB)) //#formatColor
                    expression_value.setSelection(stringA.length + number.length)
                }
            }
        }
    }

    // function to control when we try to insert a parenthesis
    // this is necessary to check:
    // 1. no put percentage on the left of the number
    // 2. no put two percentage on the same number
    /*   finished and checked   */
    @SuppressLint("SetTextI18n")
    private fun insertPercentage(){
        // clear the expression is the state of the result is already shown
        clearExpression()

        val oldStr = expression_value.text.toString()
        val cursorPos = expression_value.selectionStart

        val leftStr = oldStr.substring(0, cursorPos)
        val rightStr = oldStr.substring(cursorPos)

        var num = ""
        var cont = leftStr.length - 1

        while (cont > -1){
            if (!"+-×÷()".contains(leftStr[cont])){
                num += leftStr[cont]
            } else { cont = 0 }
            cont --
        }
        num = num.reversed()
        cont = 0

        if (rightStr.isNotEmpty()){
            while (cont < rightStr.length) {
                if (!"+-×÷()".contains(rightStr[cont])){
                    num += rightStr[cont]
                } else { cont = rightStr.length }
                cont++
            }
        }

        if (num.contains('%')){
            // if the number contain already percentage % you don't need put more
        } else {
            if (leftStr.isNotEmpty()) {
                if (leftStr[leftStr.length - 1 ].isDigit() || leftStr[leftStr.length - 1 ] == ')') {
                    expression_value.setText(formatColor("$leftStr%$rightStr")) //#formatColor
                    expression_value.setSelection(cursorPos + 1)
                }
            }
        }
    }

    // this is necessary to check:
    // 1. check the number of parenthesis
    // 2. check for the expression field is no empty
    private fun calculateResult() {
        val rowExpression = expression_value.text.toString()

        var userExp: String = rowExpression.replace('×', '*')
        userExp = userExp.replace('÷', '/')

        if (checkExpression()){
            // function to calculate result external library
            // from: MathParser.org
            val expression = Expression(userExp)
            val result = expression.calculate().toString().checkInteger()

            if (result != "NaN"){
                Result_field.text = result
                stateResult = true
                saveHistoryExpression(rowExpression, result)
                numberOfParenthesis = 0
            } else {
                val toast = Toast.makeText(applicationContext,"Invalid format used.", Toast.LENGTH_SHORT)
                toast.show()
            }

        } else {
            val toast = Toast.makeText(applicationContext,"Invalid format used.", Toast.LENGTH_SHORT)
            toast.show()
        }
    }

    private fun saveHistoryExpression(expression: String, result: String) {
        val srData = SRDataExpression.customPreference(this)
        srData.setList(expression)
        srData.setList("Result: $result")
    }

    private fun checkExpression(): Boolean {
        if (expression_value.text.isNotEmpty()){
            // this function check if the number of the parenthesis open and close is the same quantity
            if (expression_value.text.toString().checkParenthesis()) {
                 return true
            } else {
                Toast.makeText(this, "It's missing close a parenthesis", Toast.LENGTH_SHORT).show()
            }
        }
        return false
    }

    private fun showHistory(){
        val arraySpinnerModel: ArrayList<SpinnerModel> = ArrayList()
        val myList: ArrayList<String> = SRDataExpression.customPreference(this).getlist()
        var dataResult: String

        var cont: Int = myList.size - 1

        while (cont >= 0) {
            arraySpinnerModel.add(SpinnerModel(myList[cont]))
            cont--
        }

        val spinnerSingleSelectDialogFragment = SpinnerDialogFragment.newInstance(
            SpinnerSelectionType.SINGLE_SELECTION, getString(R.string.History), arraySpinnerModel, object :
                OnSpinnerOKPressedListener {
                    override fun onSingleSelection(data: SpinnerModel, selectedPosition: Int) {
                        when {
                            data.text[0] == 'R' ->{
                                dataResult =
                                    if (stateResult){
                                        data.text.substring(8)
                                    } else {
                                        expression_value.text.toString() + data.text.substring(8)
                                    }
                            } else -> {
                                dataResult =
                                    if (stateResult) {
                                        data.text
                                    } else {
                                        expression_value.text.toString() + data.text
                                    }
                            }
                        }
                        clearExpression()
                        expression_value.setText(formatColor(dataResult))
                        expression_value.setSelection(dataResult.length)
                    }

                    override fun onMultiSelection(data: List<SpinnerModel>,selectedPosition: Int) {
                        /* It will never send Multi selection data in SINGLE_SELECTION Mode*/
                    }
                }, 0
            )
        spinnerSingleSelectDialogFragment.showSearchBar = false
        spinnerSingleSelectDialogFragment.buttonText = "Load Expression"
        spinnerSingleSelectDialogFragment.themeColorResId = resources.getColor(R.color.color_text_contrast)
        spinnerSingleSelectDialogFragment.show(
            supportFragmentManager,
            "SpinnerDialogFragmentSingle"
        )
    }

    // this is necessary to check:
    // this function check the state of the result if already show the result just change the result
    // to used like expression
    private fun clearExpression(){
        if (stateResult){
            expression_value.setText("")
            stateResult = false
        }
    }

    /*   finished and checked   */
    private fun formatColor(stringToFormat: String): Spanned {
        return HtmlCompat.fromHtml(stringToFormat.paintString(), HtmlCompat.FROM_HTML_MODE_LEGACY)
    }

    /*   finished and checked   */
    private fun formatColor(stringToFormat: SpannableStringBuilder): Editable {
        return HtmlCompat.fromHtml(stringToFormat.toString().paintString(), HtmlCompat.FROM_HTML_MODE_LEGACY) as Editable
    }

    /***************************************************************************/
    // functions to get number, index A and index B
    /***************************************************************************/

    // this function return the number select with the cursor
    private fun getNumberOnExpression(): String {
        // complete string expression
        val oldStr = expression_value.text.toString()
        // position of the cursor
        val cursorPos = expression_value.selectionStart
        // string on the left and on the right of the cursor
        val leftStr = oldStr.substring(0, cursorPos)
        val rightStr = oldStr.substring(cursorPos)

        var num = ""
        var cont = leftStr.length - 1

        while (cont > -1){
            if (!"+-×÷()".contains(leftStr[cont])){
                num += leftStr[cont]
            } else {
                if (leftStr[cont] == '-'){
                    if (cont > 0){
                        if (leftStr[cont - 1] == '('){
                            num += "-"
                        }
                    }
                }
                cont = 0
            }
            cont --
        }
        num = num.reversed()
        cont = 0

        if (rightStr.isNotEmpty()){
            while (cont < rightStr.length) {
                if (!"+-×÷()".contains(rightStr[cont])){
                    num += rightStr[cont]
                } else {
                    if (rightStr[cont] == '-' && cont == 0){
                        if (leftStr[leftStr.length - 1] == '('){
                            num += rightStr[cont]
                        } else {
                            cont = rightStr.length
                        }
                    } else {
                        cont = rightStr.length
                    }
                }
                cont++
            }
        }

        return num
    }

    /*   finished and checked   */
    private fun checkNumbers(expression: String): String {
        var cont = 0
        var temp = ""
        var rest: String
        var expressionNew = expression

        if (expressionNew.isNotEmpty()){
            if ("+-×÷%".contains(expressionNew[0])) {
                expressionNew = expression.substring(1)
            }
        }

        while (cont < expressionNew.length){
            if (expressionNew[cont].isDigit()){
                var separatorCont = 0
                var checkDecimal = false
                var tempNumber = ""
                var decimalPart = ""
                var integerPart = ""
                var numberFinish = false
                rest = ""

                while (cont < expressionNew.length && !numberFinish){
                    if (expressionNew[cont].isDigit() || expressionNew[cont] == '.'){
                        tempNumber += expressionNew[cont]
                    } else {
                        numberFinish = true
                        if (expressionNew.length > cont){
                            rest = expressionNew[cont].toString()
                            cont--
                        }
                    }
                    cont++
                }

                if (tempNumber.contains('.')){
                    while (separatorCont < tempNumber.length){
                        if (tempNumber[separatorCont] != decimalSeparator && !checkDecimal){
                            integerPart += tempNumber[separatorCont]
                        } else {
                            checkDecimal = true
                            decimalPart += tempNumber[separatorCont]
                        }
                        separatorCont ++
                    }

                    temp +=
                        if (checkDecimal){ "${integerPart.toBigInteger()}$decimalPart$rest" }
                        else { "${integerPart.toBigInteger()}$rest" }
                } else {
                    temp += tempNumber.toBigInteger().toString() + rest
                }

            } else {
                temp += expressionNew[cont]
            }

            cont++
        }
        return temp
    }

    // this function check if you need to add o replace 0 on the left of the expression
    // Like: 0.00[cursor] Result -> add one more zero
    // Like: 0[cursor] Result -> replace the 0 for the new number
    /*   finished and checked   */
    private fun checkReplaceZero(expression: String, cursorPos: Int): Boolean {
        var cont = cursorPos
        var tempNumber = ""

        while(cont > 0){
            if ("+-×÷()".contains(expression[cont - 1])){
                cont = 0
            } else {
                tempNumber += expression[cont - 1]
            }
            cont --
        }
        tempNumber = tempNumber.reversed()

        if (tempNumber.contains('.')){
            return false
        }

        return tempNumber.toBigInteger() == BigInteger.ZERO
    }
}