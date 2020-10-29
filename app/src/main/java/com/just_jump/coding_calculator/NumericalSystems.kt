package com.just_jump.coding_calculator

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.InputFilter.LengthFilter
import android.text.InputType
import android.text.TextWatcher
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.just_jump.coding_calculator.utilities.Functions
import com.just_jump.coding_calculator.viewmodel.ViewModelNumericalSystem
import kotlinx.android.synthetic.main.activity_numerical_systems.*

class NumericalSystems : AppCompatActivity() {

    lateinit var cViewModel: ViewModelNumericalSystem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_numerical_systems)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        cViewModel = ViewModelProviders.of(this).get(ViewModelNumericalSystem::class.java)

        /***************************************************************************/
        // observer
        /***************************************************************************/
        val myObserverColor = Observer<String> {

            val intSelectButton: Int = radioGroup!!.checkedRadioButtonId
            val radioButton = findViewById<RadioButton>(intSelectButton)

            when (radioButton.text) {
                "Dec" -> {
                    cViewModel.getSystemNumber(radioButton.text.toString())
                }
                "Oct" -> {
                    cViewModel.getSystemNumber(radioButton.text.toString())
                }
                "Bin" -> {
                    cViewModel.getSystemNumber(radioButton.text.toString())
                }
                "Hex" -> {
                    cViewModel.getSystemNumber(radioButton.text.toString())
                }
            }

            resultBinary.text = cViewModel.resultBinary.value
            resultOctal.text = cViewModel.resultOctal.value
            resultHex.text = cViewModel.resultHex.value
            resultDecimal.text = cViewModel.resultDecimal.value
        }

        // this observer works when the expression change
        cViewModel.dataNumber.observe(this@NumericalSystems, myObserverColor)

        //------------------------------------------------------------------------------------------
        // event to control when the user change the type of the numerical system.
        //------------------------------------------------------------------------------------------
        radioGroup.setOnCheckedChangeListener { group, checkedId ->

            // checkedId is the RadioButton selected
            val intSelectButton: Int = radioGroup!!.checkedRadioButtonId
            val radioButton = findViewById<RadioButton>(intSelectButton)

            when(radioButton.text){
                "Dec" -> {
                    val maxLength = 15
                    val filterArray = arrayOfNulls<InputFilter>(1)
                    filterArray[0] = LengthFilter(maxLength)

                    TextValueA.filters = filterArray

                    // change keyboard for number
                    TextValueA.inputType = InputType.TYPE_CLASS_NUMBER

                    if (TextValueA.text.toString().isNotEmpty()) {
                        if (TextValueA.text.toString().length >= 15) {
                            fieldFor_Number.error = "The number is much larger than I allow"
                            TextValueA.setText("")
                        } else if (!Functions().validateDecimalNumber(TextValueA.text.toString())) {
                            fieldFor_Number.error = "The Number is not correct in this system"
                            TextValueA.setText("")
                        }
                    }

                    titleDecimal.visibility = View.GONE
                    resultDecimal.visibility = View.GONE

                    titleOctal.visibility = View.VISIBLE
                    resultOctal.visibility = View.VISIBLE
                    titleBinary.visibility = View.VISIBLE
                    resultBinary.visibility = View.VISIBLE
                    titleHex.visibility = View.VISIBLE
                    resultHex.visibility = View.VISIBLE
                    Toast.makeText(this, "Decimal", Toast.LENGTH_SHORT).show()
                }

                "Bin" -> {
                    val maxLength = 30
                    val filterArray = arrayOfNulls<InputFilter>(1)
                    filterArray[0] = LengthFilter(maxLength)

                    TextValueA.filters = filterArray

                    // change keyboard for number
                    TextValueA.inputType = InputType.TYPE_CLASS_NUMBER

                    if (TextValueA.text.toString().isNotEmpty()) {
                        if (!Functions().validateBinaryNumber(TextValueA.text.toString())) {
                            fieldFor_Number.error = "Number is not correct in this system"
                            TextValueA.setText("")
                        }
                    }

                    titleBinary.visibility = View.GONE
                    resultBinary.visibility = View.GONE

                    titleDecimal.visibility = View.VISIBLE
                    resultDecimal.visibility = View.VISIBLE
                    titleOctal.visibility = View.VISIBLE
                    resultOctal.visibility = View.VISIBLE
                    titleHex.visibility = View.VISIBLE
                    resultHex.visibility = View.VISIBLE
                    Toast.makeText(this, "Binary", Toast.LENGTH_SHORT).show()
                }

                "Oct" -> {
                    val maxLength = 15
                    val filterArray = arrayOfNulls<InputFilter>(1)
                    filterArray[0] = LengthFilter(maxLength)

                    TextValueA.filters = filterArray

                    // change keyboard for number
                    TextValueA.inputType = InputType.TYPE_CLASS_NUMBER

                    if (TextValueA.text.toString().isNotEmpty()) {
                        if (TextValueA.text.toString().length >= 15) {
                            fieldFor_Number.error = "The number is much larger than I allow"
                            TextValueA.setText("")
                        } else if (!Functions().validateDecimalNumber(TextValueA.text.toString())) {
                            fieldFor_Number.error = "The Number is not correct in this system"
                            TextValueA.setText("")
                        }
                    }

                    titleOctal.visibility = View.GONE
                    resultOctal.visibility = View.GONE

                    titleDecimal.visibility = View.VISIBLE
                    resultDecimal.visibility = View.VISIBLE
                    titleBinary.visibility = View.VISIBLE
                    resultBinary.visibility = View.VISIBLE
                    titleHex.visibility = View.VISIBLE
                    resultHex.visibility = View.VISIBLE
                    Toast.makeText(this, "Octal", Toast.LENGTH_SHORT).show()
                }

                "Hex" -> {
                    val maxLength = 14
                    val filterArray = arrayOfNulls<InputFilter>(1)
                    filterArray[0] = LengthFilter(maxLength)

                    TextValueA.filters = filterArray

                    // change keyboard for text to include letter
                    TextValueA.inputType = InputType.TYPE_CLASS_TEXT

                    if (TextValueA.text.toString().isNotEmpty()) {
                        if (TextValueA.text.toString().length >= 15) {
                            fieldFor_Number.error = "The number is much larger than I allow"
                            TextValueA.setText("")
                        } else if (!Functions().validateDecimalNumber(TextValueA.text.toString())) {
                            fieldFor_Number.error = "The Number is not correct in this system"
                            TextValueA.setText("")
                        }
                    }

                    titleHex.visibility = View.GONE
                    resultHex.visibility = View.GONE

                    titleDecimal.visibility = View.VISIBLE
                    resultDecimal.visibility = View.VISIBLE
                    titleOctal.visibility = View.VISIBLE
                    resultOctal.visibility = View.VISIBLE
                    titleBinary.visibility = View.VISIBLE
                    resultBinary.visibility = View.VISIBLE
                    Toast.makeText(this, "Hexadecimal", Toast.LENGTH_SHORT).show()
                }
            }

            // update of number when you selected a different numerical system
            cViewModel.dataNumber.value = cViewModel.dataNumber.value
        }

        //------------------------------------------------------------------------------------------
        // event to control when the field change the value tu check if this is valid.
        //------------------------------------------------------------------------------------------
        TextValueA.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {

                val intSelectButton: Int = radioGroup!!.checkedRadioButtonId
                val radioButton = findViewById<RadioButton>(intSelectButton)

                when (radioButton.text) {
                    "Dec" -> {
                        val text: String = p0.toString()
                        val length: Int = text.length

                        if (!Functions().validateDecimalNumber(text) && length > 0) {
                            p0!!.delete(length - 1, length)
                        } else {
                            cViewModel.dataNumber.value = TextValueA.text.toString()

                            if (cViewModel.dataNumber.value!!.isNotEmpty()) {
                                fieldFor_Number.isErrorEnabled = false
                            }
                        }
                    }
                    "Oct" -> {
                        val text: String = p0.toString()
                        val length: Int = text.length

                        if (!Functions().validateOctalNumber(text) && length > 0) {
                            p0!!.delete(length - 1, length)
                        } else {
                            cViewModel.dataNumber.value = TextValueA.text.toString()

                            if (cViewModel.dataNumber.value!!.isNotEmpty()) {
                                fieldFor_Number.isErrorEnabled = false
                            }
                        }
                    }
                    "Bin" -> {
                        val text: String = p0.toString()
                        val length: Int = text.length

                        if (!Functions().validateBinaryNumber(text) && length > 0) {
                            p0!!.delete(length - 1, length)
                        } else {
                            cViewModel.dataNumber.value = TextValueA.text.toString()

                            if (cViewModel.dataNumber.value!!.isNotEmpty()) {
                                fieldFor_Number.isErrorEnabled = false
                            }
                        }
                    }
                    "Hex" -> {
                        val text: String = p0.toString()
                        val length: Int = text.length

                        if (!Functions().validateHexNumber(text) && length > 0) {
                            p0!!.delete(length - 1, length)
                        } else {
                            cViewModel.dataNumber.value = TextValueA.text.toString()

                            if (cViewModel.dataNumber.value!!.isNotEmpty()) {
                                fieldFor_Number.isErrorEnabled = false
                            }
                        }
                    }
                }
            }
        })
    }
}