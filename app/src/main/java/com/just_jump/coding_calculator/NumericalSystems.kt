package com.just_jump.coding_calculator

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
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.just_jump.coding_calculator.databinding.ActivityNumericalSystemsNewBinding
import com.just_jump.coding_calculator.utilities.Functions
import com.just_jump.coding_calculator.viewmodel.ViewModelNumericalSystem

class NumericalSystems : AppCompatActivity() {

    lateinit var cViewModel: ViewModelNumericalSystem
    private lateinit var binding: ActivityNumericalSystemsNewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNumericalSystemsNewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cViewModel = ViewModelProvider(this).get(ViewModelNumericalSystem::class.java)

        binding.fieldNumber.hint = getString(R.string.insert_number)
        binding.fieldNumber.setHintTextColor(ContextCompat.getColor(this, R.color.grey_hint))

        binding.titleOctal.textSize = 15F

        /***************************************************************************/
        // observer
        /***************************************************************************/
        val myObserverColor = Observer<String> {

            val intSelectButton: Int = binding.radioGroup.checkedRadioButtonId
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

            binding.resultBinary.text = cViewModel.resultBinary.value
            binding.resultOctal.text = cViewModel.resultOctal.value
            binding.resultHex.text = cViewModel.resultHex.value
            binding.resultDecimal.text = cViewModel.resultDecimal.value
        }

        // this observer works when the expression change
        cViewModel.dataNumber.observe(this@NumericalSystems, myObserverColor)

        //------------------------------------------------------------------------------------------
        // event to control when the user change the type of the numerical system.
        //------------------------------------------------------------------------------------------
        binding.radioGroup.setOnCheckedChangeListener { _, _ ->

            // checkedId is the RadioButton selected
            val intSelectButton: Int = binding.radioGroup.checkedRadioButtonId
            val radioButton = findViewById<RadioButton>(intSelectButton)

            when(radioButton.text){
                "Dec" -> {
                    val maxLength = 14
                    val filterArray = arrayOfNulls<InputFilter>(1)
                    filterArray[0] = LengthFilter(maxLength)

                    binding.fieldNumber.filters = filterArray

                    // change keyboard for number
                    binding.fieldNumber.inputType = InputType.TYPE_CLASS_NUMBER

                    if (binding.fieldNumber.text.toString().isNotEmpty()) {
                        if (binding.fieldNumber.text.toString().length >= 15) {
                            binding.fieldNumberLayout.error = "The number is much larger than I allow"
                            binding.fieldNumber.setText("")
                        } else if (!Functions().validateDecimalNumber(binding.fieldNumber.text.toString())) {
                            binding.fieldNumberLayout.error = "The Number is not correct in this system"
                            binding.fieldNumber.setText("")
                        }
                    }

                    binding.titleDecimal.visibility = View.GONE
                    binding.resultDecimal.visibility = View.GONE

                    binding.titleOctal.visibility = View.VISIBLE
                    binding.resultOctal.visibility = View.VISIBLE
                    binding.titleBinary.visibility = View.VISIBLE
                    binding.resultBinary.visibility = View.VISIBLE
                    binding.titleHex.visibility = View.VISIBLE
                    binding.resultHex.visibility = View.VISIBLE
                    Toast.makeText(this, "Decimal", Toast.LENGTH_SHORT).show()
                }

                "Bin" -> {
                    val maxLength = 30
                    val filterArray = arrayOfNulls<InputFilter>(1)
                    filterArray[0] = LengthFilter(maxLength)

                    binding.fieldNumber.filters = filterArray

                    // change keyboard for number
                    binding.fieldNumber.inputType = InputType.TYPE_CLASS_NUMBER

                    if (binding.fieldNumber.text.toString().isNotEmpty()) {
                        if (!Functions().validateBinaryNumber(binding.fieldNumber.text.toString())) {
                            binding.fieldNumberLayout.error = "Number is not correct in this system"
                            binding.fieldNumber.setText("")
                        }
                    }

                    binding.titleBinary.visibility = View.GONE
                    binding.resultBinary.visibility = View.GONE

                    binding.titleDecimal.visibility = View.VISIBLE
                    binding.resultDecimal.visibility = View.VISIBLE
                    binding.titleOctal.visibility = View.VISIBLE
                    binding.resultOctal.visibility = View.VISIBLE
                    binding.titleHex.visibility = View.VISIBLE
                    binding.resultHex.visibility = View.VISIBLE
                    Toast.makeText(this, "Binary", Toast.LENGTH_SHORT).show()
                }

                "Oct" -> {
                    val maxLength = 14
                    val filterArray = arrayOfNulls<InputFilter>(1)
                    filterArray[0] = LengthFilter(maxLength)

                    binding.fieldNumber.filters = filterArray

                    // change keyboard for number
                    binding.fieldNumber.inputType = InputType.TYPE_CLASS_NUMBER

                    if (binding.fieldNumber.text.toString().isNotEmpty()) {
                        if (binding.fieldNumber.text.toString().length >= 15) {
                            binding.fieldNumberLayout.error = "The number is much larger than I allow"
                            binding.fieldNumber.setText("")
                        } else if (!Functions().validateDecimalNumber(binding.fieldNumber.text.toString())) {
                            binding.fieldNumberLayout.error = "The Number is not correct in this system"
                            binding.fieldNumber.setText("")
                        }
                    }

                    binding.titleOctal.visibility = View.GONE
                    binding.resultOctal.visibility = View.GONE

                    binding.titleDecimal.visibility = View.VISIBLE
                    binding.resultDecimal.visibility = View.VISIBLE
                    binding.titleBinary.visibility = View.VISIBLE
                    binding.resultBinary.visibility = View.VISIBLE
                    binding.titleHex.visibility = View.VISIBLE
                    binding.resultHex.visibility = View.VISIBLE
                    Toast.makeText(this, "Octal", Toast.LENGTH_SHORT).show()
                }

                "Hex" -> {

                    val maxLength = 14
                    val filterArray = arrayOfNulls<InputFilter>(1)
                    filterArray[0] = LengthFilter(maxLength)

                    binding.fieldNumber.filters = filterArray

                    // change keyboard for text to include letter
                    binding.fieldNumber.inputType = InputType.TYPE_CLASS_TEXT

                    if (binding.fieldNumber.text.toString().isNotEmpty()) {
                        if (binding.fieldNumber.text.toString().length >= 15) {
                            binding.fieldNumberLayout.error = "The number is much larger than I allow"
                            binding.fieldNumber.setText("")
                        } else if (!Functions().validateDecimalNumber(binding.fieldNumber.text.toString())) {
                            binding.fieldNumberLayout.error = "The Number is not correct in this system"
                            binding.fieldNumber.setText("")
                        }
                    }

                    binding.titleHex.visibility = View.GONE
                    binding.resultHex.visibility = View.GONE

                    binding.titleDecimal.visibility = View.VISIBLE
                    binding.resultDecimal.visibility = View.VISIBLE
                    binding.titleOctal.visibility = View.VISIBLE
                    binding.resultOctal.visibility = View.VISIBLE
                    binding.titleBinary.visibility = View.VISIBLE
                    binding.resultBinary.visibility = View.VISIBLE
                    Toast.makeText(this, "Hexadecimal", Toast.LENGTH_SHORT).show()
                }
            }

            // update of number when you selected a different numerical system
            cViewModel.dataNumber.value = cViewModel.dataNumber.value
        }

        /**
         *  Event to control: when the new field lost the focus
         */
        binding.fieldNumber.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus){
                binding.fieldNumber.hint = ""
            } else {
                if (binding.fieldNumber.text!!.isEmpty()){
                    binding.fieldNumber.hint = getString(R.string.base_x)
                }
            }
        }

        //------------------------------------------------------------------------------------------
        // event to control when the field change the value tu check if this is valid.
        //------------------------------------------------------------------------------------------
        binding.fieldNumber.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {

                val intSelectButton: Int = binding.radioGroup.checkedRadioButtonId
                val radioButton = findViewById<RadioButton>(intSelectButton)

                when (radioButton.text) {
                    "Dec" -> {
                        val text: String = p0.toString()
                        val length: Int = text.length

                        if (!Functions().validateDecimalNumber(text) && length > 0) {
                            p0!!.delete(length - 1, length)
                        } else {
                            cViewModel.dataNumber.value = binding.fieldNumber.text.toString()

                            if (cViewModel.dataNumber.value!!.isNotEmpty()) {
                                binding.fieldNumberLayout.isErrorEnabled = false
                            }
                        }
                    }
                    "Oct" -> {
                        val text: String = p0.toString()
                        val length: Int = text.length

                        if (!Functions().validateOctalNumber(text) && length > 0) {
                            p0!!.delete(length - 1, length)
                        } else {
                            cViewModel.dataNumber.value = binding.fieldNumber.text.toString()

                            if (cViewModel.dataNumber.value!!.isNotEmpty()) {
                                binding.fieldNumberLayout.isErrorEnabled = false
                            }
                        }
                    }
                    "Bin" -> {
                        val text: String = p0.toString()
                        val length: Int = text.length

                        if (!Functions().validateBinaryNumber(text) && length > 0) {
                            p0!!.delete(length - 1, length)
                        } else {
                            cViewModel.dataNumber.value = binding.fieldNumber.text.toString()

                            if (cViewModel.dataNumber.value!!.isNotEmpty()) {
                                binding.fieldNumberLayout.isErrorEnabled = false
                            }
                        }
                    }
                    "Hex" -> {

                        binding.fieldNumber.inputType = InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS
                        val text: String = p0.toString()
                        val length: Int = text.length

                        if (!Functions().validateHexNumber(text) && length > 0) {
                            p0!!.delete(length - 1, length)
                        } else {
                            cViewModel.dataNumber.value = binding.fieldNumber.text.toString()

                            if (cViewModel.dataNumber.value!!.isNotEmpty()) {
                                binding.fieldNumberLayout.isErrorEnabled = false
                            }
                        }
                    }
                }
            }
        })

        /**
         * Event to control: button come back to the parent
         */
        binding.buttonBack.setOnClickListener {
            finish()
        }
    }
}