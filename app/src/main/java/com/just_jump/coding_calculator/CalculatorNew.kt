package com.just_jump.coding_calculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.view.MotionEvent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import com.github.hamzaahmedkhan.spinnerdialog.callbacks.OnSpinnerOKPressedListener
import com.github.hamzaahmedkhan.spinnerdialog.enums.SpinnerSelectionType
import com.github.hamzaahmedkhan.spinnerdialog.models.SpinnerModel
import com.github.hamzaahmedkhan.spinnerdialog.ui.SpinnerDialogFragment
import com.just_jump.coding_calculator.data.local.SRDataExpression
import com.just_jump.coding_calculator.databinding.ActivityCalculatorNewBinding
import com.just_jump.coding_calculator.extensions.checkInteger
import com.just_jump.coding_calculator.extensions.checkParenthesis
import com.just_jump.coding_calculator.extensions.paintString
import org.mariuszgromada.math.mxparser.Expression
import java.math.BigInteger

class CalculatorNew : AppCompatActivity() {
    private var stateResult = false
    private var mainHandler: Handler? = null
    private var decimalSeparator = '.'
    private var numberOfParenthesis = 0
    lateinit var binding: ActivityCalculatorNewBinding

    private val action = object : Runnable {
        override fun run() {
            backspaceBTN()
            mainHandler?.postDelayed(this, 150)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculatorNewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.expressionValue.showSoftInputOnFocus = false        

        /***************************************************************************/
        // Events to come back to the main window
        /***************************************************************************/

        binding.buttonBack.setOnClickListener { finish() }

        /***************************************************************************/
        // Events of Numbers
        /***************************************************************************/

        binding.number1.setOnClickListener { insertNumber('1') }

        binding.number2.setOnClickListener { insertNumber('2') }

        binding.number3.setOnClickListener { insertNumber('3') }

        binding.number4.setOnClickListener { insertNumber('4') }

        binding.number5.setOnClickListener { insertNumber('5') }

        binding.number6.setOnClickListener { insertNumber('6') }

        binding.number7.setOnClickListener { insertNumber('7') }

        binding.number8.setOnClickListener { insertNumber('8') }

        binding.number9.setOnClickListener { insertNumber('9') }

        binding.number0.setOnClickListener { insertNumberZero()}

        /***************************************************************************/
        // Events of Math signs
        /***************************************************************************/

        binding.numberPlus.setOnClickListener { insertMathSign('+') }

        binding.numberLess.setOnClickListener { insertMathSign('-') }

        binding.numberMultiply.setOnClickListener { insertMathSign('×') }

        binding.numberDivide.setOnClickListener { insertMathSign('÷') }

        binding.numberPercentage.setOnClickListener { insertPercentage() }

        binding.numberOpenParenthesis.setOnClickListener { insertParenthesis('(') }

        binding.numberCloseParenthesis.setOnClickListener { insertParenthesis(')') }

        binding.numberPlusLess.setOnClickListener { insertPlusLess() }

        binding.numberPoint.setOnClickListener { insertPoint() }

        /***************************************************************************/
        // clear field
        /***************************************************************************/

        binding.numberAllClear.setOnClickListener { allClear() }

        binding.numberBackSpace.setOnClickListener { backspaceBTN() }

        binding.numberBackSpace.setOnTouchListener { _, motionEvent ->
            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {
                    if (mainHandler != null)
                        true
                    mainHandler = Handler(Looper.getMainLooper())
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

        binding.numberResult.setOnClickListener { calculateResult() }

        /***************************************************************************/
        // History
        /***************************************************************************/

        binding.historic.setOnClickListener { showHistory() }
    }

    // this function clear all the field
    /*   finished and checked   */
    private fun allClear() {
        binding.expressionValue.setText("")
        binding.ResultField.text = ""
        numberOfParenthesis = 0
    }

    // this function delete the char on the left of the cursor
    // this is necessary to check:
    // 1. when you delete a number need to check if the number have sense
    /*   finished and checked   */
    private fun backspaceBTN() {
        // clear the expression is the state of the result is already shown
        clearExpression()

        val cursorPos = binding.expressionValue.selectionStart

        // case: if you delete number 9000 for example need to check for the rest o the number if
        // this number is 0000 need you put just one 0 on the expression

        // case: if you have 5+(9-4) and you delete number 5 need to delete the math sign before
        // 5 to don't leave any math orphan sign

        if (binding.expressionValue.text.isNotEmpty()){
            val selection = binding.expressionValue.text as SpannableStringBuilder

            when {
                selection[cursorPos - 1].isDigit() -> {
                    selection.replace(cursorPos - 1, cursorPos, "")
                    binding.expressionValue.setText(formatColor(checkNumbers(selection.toString())))  //#formatColor
                    binding.expressionValue.setSelection(cursorPos - 1)
                }
                "+-×÷()%".contains(selection[cursorPos - 1]) -> {

                    if (selection[cursorPos - 1] == '('){
                        numberOfParenthesis--
                    }

                    selection.replace(cursorPos - 1, cursorPos, "")
                    binding.expressionValue.text = formatColor(selection) //#formatColor
                    binding.expressionValue.setSelection(cursorPos - 1)
                }
                selection[cursorPos - 1] == decimalSeparator -> {
                    selection.replace(cursorPos - 1, cursorPos, "")
                    binding.expressionValue.setText(formatColor(checkNumbers(selection.toString())))  //#formatColor
                    binding.expressionValue.setSelection(cursorPos - 1)
                }
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
            val resultData = binding.ResultField.text.toString()

            binding.expressionValue.setText(formatColor(resultData)) //#formatColor
            binding.expressionValue.setSelection(resultData.length)
            binding.ResultField.text = ""
            stateResult = false
        }

        val oldStr = binding.expressionValue.text.toString()
        val cursorPos = binding.expressionValue.selectionStart

        val leftStr = oldStr.substring(0, cursorPos)
        val rightStr = oldStr.substring(cursorPos)

        if (leftStr.isEmpty()) {
            // this case is when you are on the first position and we don't have nothing
        } else {
            if (leftStr.isNotEmpty() && rightStr.isNotEmpty()) {
                // case 1: if you gonna put a math sign between two number like 21[cursor]04
                if (leftStr[leftStr.length - 1].isDigit() && rightStr[0].isDigit()) {
                    binding.expressionValue.setText(formatColor(leftStr + mathSignToAdd + rightStr)) //#formatColor
                    binding.expressionValue.setSelection(cursorPos + 1)
                    // case 2: if you press a math sign and you find one already on the left you change for the new one 8+[cursor]5
                } else if ("+-×÷".contains(leftStr[leftStr.length - 1])) {
                    if(leftStr[leftStr.length - 2] == '(') {
                        if (leftStr[leftStr.length - 1] == '-' || leftStr[leftStr.length - 1] == '+'){
                            if (mathSignToAdd == '+') {
                                binding.expressionValue.setText(formatColor(leftStr.substring(0, leftStr.length - 1) + mathSignToAdd + rightStr)) //#formatColor
                                binding.expressionValue.setSelection(cursorPos)
                            } else if (mathSignToAdd == '-') {
                                binding.expressionValue.setText(formatColor(leftStr.substring(0, leftStr.length - 1) + mathSignToAdd + rightStr)) //#formatColor
                                binding.expressionValue.setSelection(cursorPos)
                            }
                        }
                    } else {
                        binding.expressionValue.setText(formatColor(leftStr.substring(0,leftStr.length - 1) + mathSignToAdd + rightStr)) //#formatColor
                        binding.expressionValue.setSelection(cursorPos)
                    }
                }   else if (leftStr[leftStr.length - 1].isDigit() && rightStr[0] == '(') {
                    binding.expressionValue.setText(formatColor(leftStr + mathSignToAdd + rightStr)) //#formatColor
                    binding.expressionValue.setSelection(cursorPos + 1)
                }
                // case 3: if you press a math sign and you find one already on the right you change for the new one 8[cursor]+5
                else if (leftStr[leftStr.length - 1].isDigit() && "+-×÷".contains(rightStr[0])) {
                    binding.expressionValue.setText(formatColor(leftStr + mathSignToAdd + rightStr.substring(1))) //#formatColor
                    binding.expressionValue.setSelection(cursorPos)
                }
                // case 6: it's the same but for the case we find data on both side
                else if (")".contains(leftStr[leftStr.length - 1])) {
                    if (!("+-×÷".contains(rightStr[0]))){
                        binding.expressionValue.setText(formatColor(leftStr + mathSignToAdd + rightStr)) //#formatColor
                        binding.expressionValue.setSelection(cursorPos + 1)
                    } else {
                        binding.expressionValue.setText(formatColor(leftStr + mathSignToAdd + rightStr.substring(1))) //#formatColor
                        binding.expressionValue.setSelection(cursorPos + 1)
                    }
                } else if (leftStr[leftStr.length - 1] != '('){
                    binding.expressionValue.setText(formatColor(leftStr + mathSignToAdd + rightStr)) //#formatColor
                    binding.expressionValue.setSelection(cursorPos + 1)
                }
                // case 4: if you try to insert a math sign on the end of number like: 21[cursor]
            } else if (leftStr[leftStr.length - 1].isDigit() && rightStr.isEmpty()) {
                binding.expressionValue.setText(formatColor(leftStr + mathSignToAdd)) //#formatColor
                binding.expressionValue.setSelection(cursorPos + 1)
                // case 5: if you want to insert a math sign but is one on the right you change this for the new one
            } else if ("+-×÷".contains(leftStr[leftStr.length - 1]) && rightStr.isEmpty()) {
                if(leftStr[leftStr.length - 2] == '(') {
                    if (leftStr[leftStr.length - 1] == '-' || leftStr[leftStr.length - 1] == '+'){
                        if (mathSignToAdd == '+') {
                            binding.expressionValue.setText(formatColor(leftStr.substring(0, leftStr.length - 1) + mathSignToAdd)) //#formatColor
                            binding.expressionValue.setSelection(cursorPos)
                        } else if (mathSignToAdd == '-') {
                            binding.expressionValue.setText(formatColor(leftStr.substring(0, leftStr.length - 1) + mathSignToAdd)) //#formatColor
                            binding.expressionValue.setSelection(cursorPos)
                        }
                    }
                } else {
                    binding.expressionValue.setText(formatColor(leftStr.substring(0, leftStr.length - 1) + mathSignToAdd)) //#formatColor
                    binding.expressionValue.setSelection(cursorPos)
                }
                // case 6: if you press a math sign but is a close parenthesis on the left
            } else if (")".contains(leftStr[leftStr.length - 1])) {
                binding.expressionValue.setText(formatColor(leftStr + mathSignToAdd + rightStr)) //#formatColor
                binding.expressionValue.setSelection(cursorPos + 1)
                // when we got percentage sign and we want to insert math sign
            } else if (leftStr[leftStr.length - 1] == '%'){
                binding.expressionValue.setText(formatColor(leftStr + mathSignToAdd + rightStr)) //#formatColor
                binding.expressionValue.setSelection(cursorPos + 1)
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

        val oldStr = binding.expressionValue.text.toString()
        val cursorPos = binding.expressionValue.selectionStart

        // string on the left and on the right of the cursor
        val leftStr = oldStr.substring(0, cursorPos)
        val rightStr = oldStr.substring(cursorPos)

        if (oldStr.isEmpty()){
            // case: if you find empty the expression
            binding.expressionValue.setText(formatColor(leftStr + numberToAdd + rightStr)) //#formatColor
            binding.expressionValue.setSelection(cursorPos + 1)
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
                    binding.expressionValue.setText(formatColor(leftStr + numberToAdd + rightStr)) //#formatColor
                    binding.expressionValue.setSelection(cursorPos + 1)
                }
                else if ("+-×÷$decimalSeparator".contains(leftStr[leftStr.length -1]) && rightStr[0].isDigit()) {
                    //Case: Number[cursor]Number: "+-×÷."[cursor]6
                    binding.expressionValue.setText(formatColor(leftStr + numberToAdd + rightStr)) //#formatColor
                    binding.expressionValue.setSelection(cursorPos + 1)
                }
                else if (leftStr[leftStr.length - 1].isDigit() && "+-×÷%$decimalSeparator".contains(rightStr[0])){
                    //Case: Number[cursor]MathSign: 6[cursor]"+-×÷"
                    if (leftStr[leftStr.length -1] == '0'){
                        // case: 0[cursor]
                        if (checkReplaceZero(oldStr, cursorPos)){
                            binding.expressionValue.setText(formatColor("${leftStr.substring(0, leftStr.length - 1)}$numberToAdd$rightStr")) //#formatColor
                            binding.expressionValue.setSelection(cursorPos)
                        } else {
                            // if the number if different of zero we can add the new number
                            binding.expressionValue.setText(formatColor("$leftStr$numberToAdd$rightStr")) //#formatColor
                            binding.expressionValue.setSelection(cursorPos + 1)
                        }
                    } else {
                        // if the number if different of zero we can add the new number
                        binding.expressionValue.setText(formatColor("$leftStr$numberToAdd$rightStr")) //#formatColor
                        binding.expressionValue.setSelection(cursorPos + 1)
                    }
                }
                else if ("+-×÷$decimalSeparator".contains(leftStr[leftStr.length -1]) && "+-×÷%$decimalSeparator".contains(rightStr[0])){
                    //Case: MathSign[cursor]MathSign: "+-×÷"[cursor]"+-×÷"
                    binding.expressionValue.setText(formatColor(leftStr + numberToAdd + rightStr)) //#formatColor
                    binding.expressionValue.setSelection(cursorPos + 1)
                }
                else if (leftStr[leftStr.length -1] == ')' && rightStr[0].isDigit()){
                    //Case: closeParenthesis[cursor]Number: )[cursor]6
                    binding.expressionValue.setText(formatColor("$leftStr×$numberToAdd$rightStr")) //#formatColor
                    binding.expressionValue.setSelection(cursorPos + 1)
                }
                else if (leftStr[leftStr.length - 1].isDigit() && rightStr[0] == '('){
                    //Case: Number[cursor]openParenthesis: 6[cursor](
                    if (leftStr[leftStr.length -1] == '0'){
                        // case: 0[cursor]
                        if (checkReplaceZero(oldStr, cursorPos)){
                            binding.expressionValue.setText(formatColor("${leftStr.substring(0, leftStr.length - 1)}$numberToAdd×$rightStr")) //#formatColor
                            binding.expressionValue.setSelection(cursorPos)
                        } else {
                            // if the number if different of zero we can add the new number
                            binding.expressionValue.setText(formatColor("$leftStr$numberToAdd×$rightStr")) //#formatColor
                            binding.expressionValue.setSelection(cursorPos + 1)
                        }
                    } else {
                        // if the number if different of zero we can add the new number
                        binding.expressionValue.setText(formatColor("$leftStr$numberToAdd×$rightStr")) //#formatColor
                        binding.expressionValue.setSelection(cursorPos + 1)
                    }
                }
                else if (leftStr[leftStr.length -1] == ')' && rightStr[0] == '('){
                    //Case: closeParenthesis[cursor]openParenthesis: )[cursor](
                    binding.expressionValue.setText(formatColor("$leftStr×$numberToAdd×$rightStr")) //#formatColor
                    binding.expressionValue.setSelection(cursorPos + 1)
                }
                else if (leftStr[leftStr.length -1] == '(' && "+-×÷".contains(rightStr[0])) {
                    //Case: openParenthesis[cursor]"+-×÷": ([cursor]"+-×÷"
                    binding.expressionValue.setText(formatColor("$leftStr$numberToAdd$rightStr")) //#formatColor
                    binding.expressionValue.setSelection(cursorPos + 1)
                }
                else if ("+-×÷$decimalSeparator".contains(leftStr[leftStr.length -1]) && rightStr[0] == ')'){
                    //Case: "+-×÷."[cursor]closeParenthesis: "+-×÷."[cursor])
                    binding.expressionValue.setText(formatColor("$leftStr$numberToAdd$rightStr")) //#formatColor
                    binding.expressionValue.setSelection(cursorPos + 1)
                }
                else if (leftStr[leftStr.length -1].isDigit() && rightStr[0] == ')'){
                    //Case: Number[cursor]closeParenthesis: 6[cursor])
                    if (leftStr[leftStr.length -1] == '0'){
                        // case: 0[cursor]
                        if (checkReplaceZero(oldStr, cursorPos)){
                            binding.expressionValue.setText(formatColor("${leftStr.substring(0, leftStr.length - 1)}$numberToAdd$rightStr")) //#formatColor
                            binding.expressionValue.setSelection(cursorPos)
                        } else {
                            // if the number if different of zero we can add the new number
                            binding.expressionValue.setText(formatColor("$leftStr$numberToAdd$rightStr")) //#formatColor
                            binding.expressionValue.setSelection(cursorPos + 1)
                        }
                    } else {
                        // if the number if different of zero we can add the new number
                        binding.expressionValue.setText(formatColor("$leftStr$numberToAdd$rightStr")) //#formatColor
                        binding.expressionValue.setSelection(cursorPos + 1)
                    }
                }
                else if (leftStr[leftStr.length -1] == '(' && rightStr[0].isDigit()){
                    //Case: openParenthesis[cursor]Number ([cursor]Number
                    binding.expressionValue.setText(formatColor(leftStr + numberToAdd + rightStr)) //#formatColor
                    binding.expressionValue.setSelection(cursorPos + 1)
                }
                else if (leftStr[leftStr.length -1] == '(' && rightStr[0] == ')') {
                    //Case: openParenthesis[cursor]closeParenthesis: ([cursor])
                    binding.expressionValue.setText(formatColor(leftStr + numberToAdd + rightStr)) //#formatColor
                    binding.expressionValue.setSelection(cursorPos + 1)
                }
                else if (leftStr[leftStr.length -1] == '(' && rightStr[0] == '(') {
                    //Case: openParenthesis[cursor]openParenthesis: ([cursor](
                    binding.expressionValue.setText(formatColor("$leftStr$numberToAdd×$rightStr")) //#formatColor
                    binding.expressionValue.setSelection(cursorPos + 1)
                }
                else if (leftStr[leftStr.length -1] == ')' && rightStr[0] == ')') {
                    //Case: closeParenthesis[cursor]closeParenthesis: ([cursor](
                    binding.expressionValue.setText(formatColor("$leftStr×$numberToAdd$rightStr")) //#formatColor
                    binding.expressionValue.setSelection(cursorPos + 1)
                }
                else if ("+-×÷$decimalSeparator".contains(leftStr[leftStr.length -1]) && rightStr[0] == '(') {
                    //Case: MathSign[cursor]openParenthesis: "+-×÷."[cursor](
                    binding.expressionValue.setText(formatColor("$leftStr$numberToAdd×$rightStr")) //#formatColor
                    binding.expressionValue.setSelection(cursorPos + 1)
                }
                else if (leftStr[leftStr.length -1] == ')' && "+-×÷".contains(rightStr[0])) {
                    //Case: closeParenthesis[cursor]MathSign: ([cursor]"+-×÷"
                    binding.expressionValue.setText(formatColor("$leftStr×$numberToAdd$rightStr")) //#formatColor
                    binding.expressionValue.setSelection(cursorPos + 1)
                }
            } else {
                if (leftStr.isEmpty() && rightStr.isNotEmpty()){
                    when {
                        "+-×÷.".contains(rightStr[0]) -> {
                            binding.expressionValue.setText(formatColor("$numberToAdd$rightStr")) //#formatColor
                            binding.expressionValue.setSelection(cursorPos + 1)
                        }
                        rightStr[0] == '(' -> {
                            binding.expressionValue.setText(formatColor("$numberToAdd×$rightStr")) //#formatColor
                            binding.expressionValue.setSelection(cursorPos + 1)
                        }
                        rightStr[0] == ')' -> {

                        }
                        rightStr[0].isDigit() -> {
                            binding.expressionValue.setText(formatColor("$numberToAdd$rightStr")) //#formatColor
                            binding.expressionValue.setSelection(cursorPos + 1)
                        }
                    }
                } else if (leftStr.isNotEmpty() && rightStr.isEmpty()){
                    if ("+-×÷$decimalSeparator".contains(leftStr[leftStr.length -1])){
                        // case: MathSign[cursor]
                        binding.expressionValue.setText(formatColor("$leftStr$numberToAdd")) //#formatColor
                        binding.expressionValue.setSelection(cursorPos + 1)
                    } else if (leftStr[leftStr.length -1] == '(') {
                        // case: openParenthesis[cursor]
                        binding.expressionValue.setText(formatColor("$leftStr$numberToAdd")) //#formatColor
                        binding.expressionValue.setSelection(cursorPos + 1)
                    } else if (leftStr[leftStr.length -1] == ')') {
                        // case: closeParenthesis[cursor]
                        binding.expressionValue.setText(formatColor("$leftStr×$numberToAdd")) //#formatColor
                        binding.expressionValue.setSelection(cursorPos + 2)
                    } else if (leftStr[leftStr.length -1] == '.') {
                        // case: .[cursor]
                        binding.expressionValue.setText(formatColor("$leftStr$numberToAdd")) //#formatColor
                        binding.expressionValue.setSelection(cursorPos + 1)
                    } else if (leftStr[leftStr.length -1] == '%') {
                        // case: %[cursor]
                        binding.expressionValue.setText(formatColor("$leftStr×$numberToAdd")) //#formatColor
                        binding.expressionValue.setSelection(cursorPos + 2)
                    } else if (leftStr[leftStr.length -1].isDigit()) {
                        // case: Number[cursor]
                        if (leftStr[leftStr.length -1] == '0'){
                            // case: 0[cursor]
                            if (checkReplaceZero(oldStr, cursorPos)){
                                binding.expressionValue.setText(formatColor("${leftStr.substring(0, leftStr.length - 1)}$numberToAdd")) //#formatColor
                                binding.expressionValue.setSelection(cursorPos)
                            } else {
                                binding.expressionValue.setText(formatColor("$leftStr$numberToAdd")) //#formatColor
                                binding.expressionValue.setSelection(cursorPos + 1)
                            }
                        } else {
                            // case: Number!=0[cursor]
                            binding.expressionValue.setText(formatColor("$leftStr$numberToAdd")) //#formatColor
                            binding.expressionValue.setSelection(cursorPos + 1)
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

        val oldStr = binding.expressionValue.text.toString()
        val cursorPos = binding.expressionValue.selectionStart
        var number = getNumberOnExpression()

        if (number.contains('%')){
            number = number.replace("%","")
        }

        // string on the left and on the right of the cursor
        val leftStr = oldStr.substring(0, cursorPos)
        val rightStr = oldStr.substring(cursorPos)

        if (oldStr.isEmpty()){
            // case: if you find empty the expression
            binding.expressionValue.setText(formatColor(leftStr + '0' + rightStr)) //#formatColor
            binding.expressionValue.setSelection(cursorPos + 1)
        } else {
            if (leftStr.isNotEmpty() && rightStr.isNotEmpty()){
                // if you find data on both side of the cursor for example: 25[cursor]45
                if (leftStr[leftStr.length - 1].isDigit() && rightStr[0].isDigit()){
                    //Case: Number[cursor]Number: 6[cursor]6
                    if (number.toDouble() > 0 || number.contains(decimalSeparator) || (number.contains('-') && number != "-0")){
                        binding.expressionValue.setText(formatColor(leftStr + '0' + rightStr)) //#formatColor
                        binding.expressionValue.setSelection(cursorPos + 1)
                    }
                }
                else if ("+-×÷".contains(leftStr[leftStr.length -1]) && rightStr[0].isDigit()){
                    //Case: MathSign[cursor]Number: "+-×÷"[cursor]6
                    if (number.toDouble() > 0 || number.contains(decimalSeparator) || (number.contains('-') && number != "-0")){
                        binding.expressionValue.setText(formatColor(leftStr + rightStr)) //#formatColor
                        binding.expressionValue.setSelection(cursorPos)
                    }
                }
                else if (leftStr[leftStr.length - 1].isDigit() && "+-×÷$decimalSeparator".contains(rightStr[0])){
                    if (rightStr[0] == decimalSeparator && number.toDouble().toInt() == 0){
                        // this if don't need to do nothing
                    }
                    //Case: Number[cursor]MathSign: 6[cursor]"+-×÷."
                    else if (number.toDouble() > 0 || number.contains(decimalSeparator) || (number.contains('-') && number != "-0")){
                        binding.expressionValue.setText(formatColor(leftStr + "0" + rightStr)) //#formatColor
                        binding.expressionValue.setSelection(cursorPos + 1)
                    }
                }
                else if (leftStr[leftStr.length -1]== decimalSeparator){
                    //Case: .[cursor]Number: 0.[cursor]wherever
                    if (number.toDouble() > 0 || number.contains(decimalSeparator) || (number.contains('-') && number != "-0")){
                        binding.expressionValue.setText(formatColor(leftStr + "0" + rightStr)) //#formatColor
                        binding.expressionValue.setSelection(cursorPos + 1)
                    }
                }
                else if ("+-×÷$decimalSeparator".contains(leftStr[leftStr.length -1]) && "+-×÷$decimalSeparator".contains(rightStr[0])){
                    //Case: MathSign[cursor]MathSign: "+-×÷"[cursor]"+-×÷"
                    binding.expressionValue.setText(formatColor("${leftStr}0$rightStr")) //#formatColor
                    binding.expressionValue.setSelection(cursorPos + 1)
                }
                else if (leftStr[leftStr.length -1] == ')' && rightStr[0].isDigit()) {
                    //Case: closeParenthesis[cursor]MathSign: )[cursor]"+-×÷"
                    binding.expressionValue.setText(formatColor("${leftStr}×$rightStr")) //#formatColor
                    binding.expressionValue.setSelection(cursorPos + 1)
                }
                else if (leftStr[leftStr.length - 1].isDigit() && rightStr[0] == '('){
                    //Case: Number[cursor]openParenthesis 6[cursor](
                    if (number.toDouble() > 0 || number.contains(decimalSeparator) || (number.contains('-') && number != "-0")){
                        binding.expressionValue.setText(formatColor("${leftStr}0×$rightStr")) //#formatColor
                        binding.expressionValue.setSelection(cursorPos + 1)
                    }
                }
                else if (leftStr[leftStr.length -1] == ')' && rightStr[0] == '('){
                    //Case: closeParenthesis[cursor]openParenthesis )[cursor](
                    binding.expressionValue.setText(formatColor("${leftStr}×0×$rightStr")) //#formatColor
                    binding.expressionValue.setSelection(cursorPos + 2)
                }
                else if (leftStr[leftStr.length -1] == '(' && "+-×÷".contains(rightStr[0])){
                    //Case: openParenthesis[cursor]MathSign ([cursor]"+-×÷"
                    binding.expressionValue.setText(formatColor("${leftStr}0$rightStr")) //#formatColor
                    binding.expressionValue.setSelection(cursorPos + 1)
                }
                else if ("+-×÷".contains(leftStr[leftStr.length -1]) && rightStr[0] == '('){
                    //Case: MathSign[cursor]openParenthesis "+-×÷"[cursor](
                    binding.expressionValue.setText(formatColor("${leftStr}0×$rightStr")) //#formatColor
                    binding.expressionValue.setSelection(cursorPos + 1)
                }
                else if (leftStr[leftStr.length -1] == ')' && "+-×÷".contains(rightStr[0])){
                    //Case: closeParenthesis[cursor]MathSign )[cursor]"+-×÷"
                    binding.expressionValue.setText(formatColor("${leftStr}×0$rightStr")) //#formatColor
                    binding.expressionValue.setSelection(cursorPos + 1)
                }
                else if ("+-×÷".contains(leftStr[leftStr.length -1]) && rightStr[0] == ')'){
                    //Case: MathSign[cursor]closeParenthesis "+-×÷"[cursor])
                    binding.expressionValue.setText(formatColor("${leftStr}0$rightStr")) //#formatColor
                    binding.expressionValue.setSelection(cursorPos + 1)
                }
                else if (leftStr[leftStr.length - 1].isDigit() && rightStr[0] == ')') {
                    //Case: Number[cursor]closeParenthesis Number[cursor])
                    if (number.toDouble() > 0 || number.contains(decimalSeparator) || (number.contains('-') && number != "-0")){
                        binding.expressionValue.setText(formatColor("${leftStr}0$rightStr")) //#formatColor
                        binding.expressionValue.setSelection(cursorPos + 1)
                    }
                }
                else if (leftStr[leftStr.length -1] == ')' && rightStr[0].isDigit()){
                    //Case: openParenthesis[cursor]Number ([cursor]Number
                    binding.expressionValue.setText(formatColor("$leftStr×$rightStr")) //#formatColor
                    binding.expressionValue.setSelection(cursorPos)
                }
                else if (leftStr[leftStr.length -1] == '(' && rightStr[0] == ')'){
                    //Case: openParenthesis[cursor]closeParenthesis ([cursor])
                    binding.expressionValue.setText(formatColor("${leftStr}0$rightStr")) //#formatColor
                    binding.expressionValue.setSelection(cursorPos + 1)
                }
                else if (leftStr[leftStr.length -1] == '%' && "+-×÷".contains(rightStr[0])){
                    //Case: %[cursor]MathSign %[cursor]"+-×÷"
                    binding.expressionValue.setText(formatColor("${leftStr}×0$rightStr")) //#formatColor
                    binding.expressionValue.setSelection(cursorPos + 1)
                }
                else if (leftStr[leftStr.length -1] == '%' && rightStr[0] == '('){
                    //Case: %[cursor]openParenthesis %[cursor](
                    binding.expressionValue.setText(formatColor("${leftStr}×0×$rightStr")) //#formatColor
                    binding.expressionValue.setSelection(cursorPos + 2)
                }
                else if (leftStr[leftStr.length -1] == '(' && rightStr[0] == '('){
                    //Case: closeParenthesis[cursor]openParenthesis ([cursor](
                    binding.expressionValue.setText(formatColor("${leftStr}0×$rightStr")) //#formatColor
                    binding.expressionValue.setSelection(cursorPos + 2)
                }
                else if (leftStr[leftStr.length -1] == ')' && rightStr[0] == ')'){
                    //Case: closeParenthesis[cursor]openParenthesis )[cursor])
                    binding.expressionValue.setText(formatColor("${leftStr}×0$rightStr")) //#formatColor
                    binding.expressionValue.setSelection(cursorPos + 2)
                }
                else if (number.toDouble() > 0 && rightStr[0] == '%') {
                    binding.expressionValue.setText(formatColor("${leftStr}0$rightStr")) //#formatColor
                    binding.expressionValue.setSelection(cursorPos + 1)
                }
            } else if (leftStr.isNotEmpty() && rightStr.isEmpty()){
                // if the number is empty can be something like: (-, (
                if (number.isEmpty()){
                    if (leftStr[leftStr.length -1] == '(' || leftStr[leftStr.length -1] == decimalSeparator || "+-×÷".contains(leftStr[leftStr.length -1])){
                        binding.expressionValue.setText(formatColor(leftStr + "0")) //#formatColor
                        binding.expressionValue.setSelection(cursorPos + 1)
                    }
                    else if (leftStr[leftStr.length -1] == ')' || leftStr[leftStr.length -1] == '%'){
                        binding.expressionValue.setText(formatColor("$leftStr×0")) //#formatColor
                        binding.expressionValue.setSelection(cursorPos + 2)
                    }
                } else if (leftStr[leftStr.length -1].isDigit() ||  leftStr[leftStr.length -1] == '.') {
                    if (number.toDouble() > 0 || number.contains(decimalSeparator) || (number.contains('-') && number != "-0")){
                        binding.expressionValue.setText(formatColor(leftStr + "0")) //#formatColor
                        binding.expressionValue.setSelection(cursorPos + 1)
                    }
                }
            } /*else if (leftStr.isEmpty() && rightStr.isNotEmpty()){
                // if you find data just on the right side of the cursor for example [cursor]25
            }*/
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

        val oldStr = binding.expressionValue.text.toString()
        val cursorPos = binding.expressionValue.selectionStart

        val leftStr = oldStr.substring(0, cursorPos)
        val rightStr = oldStr.substring(cursorPos)

        // with this conditions we gonna check the number of parenthesis
        // if you want to open parenthesis always you can write down one new one
        // or if you want to close one open we gonna check the number of the parenthesis you open before
        if (parenthesisToAdd == '(' || numberOfParenthesis > 0){

            if (leftStr.isNotEmpty() && rightStr.isNotEmpty()){
                if(rightStr[0] == '(' && parenthesisToAdd == ')'){
                    binding.expressionValue.setText(formatColor("$leftStr$parenthesisToAdd×$rightStr")) //#formatColor
                    binding.expressionValue.setSelection(leftStr.length + 2)
                    if (parenthesisToAdd == '('){
                        numberOfParenthesis++
                    } else {
                        numberOfParenthesis--
                    }

                } else {
                    if (leftStr[leftStr.length - 1].isDigit() && parenthesisToAdd == '(') {
                        binding.expressionValue.setText(formatColor("$leftStr×$parenthesisToAdd$rightStr")) //#formatColor
                        binding.expressionValue.setSelection(cursorPos + 2)
                        if (parenthesisToAdd == '('){
                            numberOfParenthesis++
                        } else {
                            numberOfParenthesis--
                        }
                    } else {
                        binding.expressionValue.setText(formatColor(leftStr + parenthesisToAdd + rightStr)) //#formatColor
                        binding.expressionValue.setSelection(cursorPos + 1)
                        if (parenthesisToAdd == '('){
                            numberOfParenthesis++
                        } else {
                            numberOfParenthesis--
                        }
                    }
                }
            } else if (leftStr.isNotEmpty()) {
                if ((leftStr[leftStr.length - 1] == '%' && parenthesisToAdd == '(') || (leftStr[leftStr.length - 1] == ')' && parenthesisToAdd == '(') || (leftStr[leftStr.length - 1].isDigit() && parenthesisToAdd == '(')) {
                    binding.expressionValue.setText(formatColor("$leftStr×$parenthesisToAdd$rightStr")) //#formatColor
                    binding.expressionValue.setSelection(cursorPos + 2)
                    if (parenthesisToAdd == '('){
                        numberOfParenthesis++
                    } else {
                        numberOfParenthesis--
                    }
                } else {
                    binding.expressionValue.setText(formatColor(leftStr + parenthesisToAdd + rightStr)) //#formatColor
                    binding.expressionValue.setSelection(cursorPos + 1)
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
                    binding.expressionValue.setText(formatColor(leftStr + parenthesisToAdd + rightStr)) //#formatColor
                    binding.expressionValue.setSelection(cursorPos + 1)
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

        val oldStr = binding.expressionValue.text.toString()
        val cursorPos = binding.expressionValue.selectionStart
        val charPoint = decimalSeparator

        val leftStr = oldStr.substring(0, cursorPos)
        val rightStr = oldStr.substring(cursorPos)

        if (oldStr.isEmpty()){
            binding.expressionValue.setText(formatColor("0$charPoint")) //#formatColor
            binding.expressionValue.setSelection(2)
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
                        binding.expressionValue.setText(formatColor("${leftStr}0$charPoint$rightStr")) //#formatColor
                        binding.expressionValue.setSelection(leftStr.length + 2)
                    }
                    leftStr[leftStr.length - 1] == ')' ->{
                        binding.expressionValue.setText(formatColor("${leftStr}×0$charPoint$rightStr")) //#formatColor
                        binding.expressionValue.setSelection(leftStr.length + 3)
                    }
                    else -> {
                        binding.expressionValue.setText(formatColor("$leftStr$charPoint$rightStr")) //#formatColor
                        binding.expressionValue.setSelection(leftStr.length + 1)
                    }
                }
            } else {
                when {
                    "+-×÷(".contains(leftStr[leftStr.length - 1]) -> {
                        binding.expressionValue.setText(formatColor("${leftStr}0$charPoint$rightStr")) //#formatColor
                        binding.expressionValue.setSelection(leftStr.length + 2)
                    }
                    leftStr[leftStr.length - 1] == ')' -> {
                        binding.expressionValue.setText(formatColor("${leftStr}×0$charPoint$rightStr")) //#formatColor
                        binding.expressionValue.setSelection(leftStr.length + 3)
                    }
                    leftStr[leftStr.length - 1] == '%' -> {
                        binding.expressionValue.setText(formatColor("${leftStr}×0$charPoint$rightStr")) //#formatColor
                        binding.expressionValue.setSelection(leftStr.length + 3)
                    }
                }
            }
        } else if (rightStr.isNotEmpty()) {
            // if you want to add 0. on the start of the number
            if (rightStr[0].isDigit()){
                binding.expressionValue.setText(formatColor("${leftStr}0$charPoint$rightStr")) //#formatColor
                binding.expressionValue.setSelection(leftStr.length + 2)
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

        val oldStr = binding.expressionValue.text.toString()
        val cursorPos = binding.expressionValue.selectionStart
        val expressionPlusLess = "(-"

        var indexA = 0
        var indexB = 0
        var number = ""
        var removeSign = false

        // case 1: if you want to insert a negative number but the expression is totally empty
        if (oldStr.isEmpty()) {
            binding.expressionValue.setText(formatColor(expressionPlusLess)) //#formatColor
            binding.expressionValue.setSelection(cursorPos + 2)
            numberOfParenthesis++
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
                        numberOfParenthesis--
                    }
                }

                if (stringB.isNotEmpty()) {
                    if (stringB[0] == ')') {
                        stringB = stringB.substring(1)
                        number = number.replace(")", "")
                        numberOfParenthesis++
                    }
                }

                binding.expressionValue.setText(formatColor(stringA + number + stringB)) //#formatColor
                binding.expressionValue.setSelection(stringA.length + number.length)

            } else {
                val stringA = oldStr.substring( 0,indexA)
                val stringB = oldStr.substring( indexB, oldStr.length)

                 if (stringB.isEmpty() && number.isNotEmpty()){
                    // case 1: if you press the button and you got de last number like 21[cursor] or 6+21[cursor]
                    // the result is gonna be (-21[cursor] or 6+(-21[cursor] living the expression open to finish to write
                    number = "(-$number"
                    binding.expressionValue.setText(formatColor(stringA + number + stringB)) //#formatColor
                    binding.expressionValue.setSelection(stringA.length + number.length)
                    numberOfParenthesis++
                } else if(stringA.isNotEmpty() && number.isEmpty()){
                    // case 2: in the case when you don't select a number you put gonna have open to write down the rest
                    // in this example like 21*[cursor] the result is: 21*(-[cursor]
                    // case 3: when you have a close parenthesis on the right

                    number = if (stringA[stringA.length -1] == ')' || stringA[stringA.length -1] == '%'){ "×(-" } else { "(-" }

                    binding.expressionValue.setText(formatColor(stringA + number + stringB)) //#formatColor
                    binding.expressionValue.setSelection(stringA.length + number.length)
                    numberOfParenthesis++

                     // case 4: to check when is the first position to insert a negative number
                } else if (stringA.isEmpty() && oldStr[cursorPos] == '('){
                     number = "(-$number"
                     binding.expressionValue.setText(formatColor(stringA + number + oldStr.substring(cursorPos))) //#formatColor
                     binding.expressionValue.setSelection(stringA.length + number.length)
                     numberOfParenthesis++
                } else {
                    // case default: if you find a number on the middle of the expression you put this number negative like 21+4[cursor]-9
                    // the result of the expression be easy 21+(-4)-9
                    number = "(-$number)"
                    binding.expressionValue.setText(formatColor(stringA + number + stringB)) //#formatColor
                    binding.expressionValue.setSelection(stringA.length + number.length)
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

        val oldStr = binding.expressionValue.text.toString()
        val cursorPos = binding.expressionValue.selectionStart

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
            if (leftStr.isNotEmpty()){
                if (rightStr.isNotEmpty()){
                    if (leftStr[leftStr.length - 1 ].isDigit() && rightStr[0].isDigit()){
                        binding.expressionValue.setText(formatColor("$leftStr%×$rightStr")) //#formatColor
                        binding.expressionValue.setSelection(cursorPos + 2)
                    } else if (leftStr[leftStr.length - 1 ].isDigit() || leftStr[leftStr.length - 1 ] == ')') {
                        binding.expressionValue.setText(formatColor("$leftStr%$rightStr")) //#formatColor
                        binding.expressionValue.setSelection(cursorPos + 1)
                    }

                } else if (leftStr[leftStr.length - 1 ].isDigit() || leftStr[leftStr.length - 1 ] == ')') {
                    binding.expressionValue.setText(formatColor("$leftStr%$rightStr")) //#formatColor
                    binding.expressionValue.setSelection(cursorPos + 1)
                }

            }
        }
    }

    // this is necessary to check:
    // 1. check the number of parenthesis
    // 2. check for the expression field is no empty
    private fun calculateResult() {
        val rowExpression = binding.expressionValue.text.toString()

        var userExp: String = rowExpression.replace('×', '*')
        userExp = userExp.replace('÷', '/')

        if (checkExpression()){
            // function to calculate result external library
            // from: MathParser.org
            val expression = Expression(userExp)
            val result = expression.calculate().toString().checkInteger()

            if (result != "NaN"){
                binding.ResultField.text = result
                stateResult = true
                saveHistoryExpression(rowExpression, result)
                numberOfParenthesis = 0
            } else {
                //message of error to inform field is wrong
                Toast.makeText(this, getString(R.string.message_3), Toast.LENGTH_SHORT).show()
            }

        } else {
            //message of error to inform field is wrong
            Toast.makeText(this, getString(R.string.message_3), Toast.LENGTH_SHORT).show()
        }
    }

    private fun saveHistoryExpression(expression: String, result: String) {
        val srData = SRDataExpression.customPreference(this)
        srData.setList(expression)
        srData.setList("Result: $result")
    }

    private fun checkExpression(): Boolean {
        if (binding.expressionValue.text.isNotEmpty()){
            // this function check if the number of the parenthesis open and close is the same quantity
            if (binding.expressionValue.text.toString().checkParenthesis()) {
                 return true
            } else {
                //message of error to inform field is wrong
                Toast.makeText(this, getString(R.string.message_3), Toast.LENGTH_SHORT).show()
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
                                        binding.expressionValue.text.toString() + data.text.substring(8)
                                    }
                            } else -> {
                                dataResult =
                                    if (stateResult) {
                                        data.text
                                    } else {
                                        binding.expressionValue.text.toString() + data.text
                                    }
                            }
                        }
                        clearExpression()
                        binding.expressionValue.setText(formatColor(dataResult))
                        binding.expressionValue.setSelection(dataResult.length)
                    }

                    override fun onMultiSelection(data: List<SpinnerModel>,selectedPosition: Int) {
                        /* It will never send Multi selection data in SINGLE_SELECTION Mode*/
                    }
                }, 0
            )
        spinnerSingleSelectDialogFragment.showSearchBar = false
        spinnerSingleSelectDialogFragment.buttonText = "Load Expression"
        spinnerSingleSelectDialogFragment.themeColorResId = ContextCompat.getColor(this, R.color.color_text_contrast)
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
            binding.expressionValue.setText("")
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
        val oldStr = binding.expressionValue.text.toString()
        // position of the cursor
        val cursorPos = binding.expressionValue.selectionStart
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