package com.just_jump.coding_calculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.just_jump.coding_calculator.databinding.FragmentExponentNewBinding
import com.just_jump.coding_calculator.extensions.checkInteger
import com.just_jump.coding_calculator.utilities.ReturnMainActivity
import java.text.DecimalFormat
import kotlin.math.pow

class FragmentExponent(private val myInterface: ReturnMainActivity) : Fragment() {

    private lateinit var binding: FragmentExponentNewBinding

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle? ): View {

        // Inflate the layout for this fragment
        binding = FragmentExponentNewBinding.inflate(layoutInflater)

        binding.fieldBase.hint = getString(R.string.base_x)
        binding.fieldBase.setHintTextColor(ContextCompat.getColor(requireContext().applicationContext, R.color.grey_hint))

        binding.fieldExponent.hint = getString(R.string.exponent_n)
        binding.fieldExponent.setHintTextColor(ContextCompat.getColor(requireContext(), R.color.grey_hint))

        /**
         * Event to control: button come back to the parent
         */
        binding.buttonBack.setOnClickListener {
            myInterface.returnMainActivity()
        }

        binding.calculatorButton.setOnClickListener {
            if (binding.fieldBase.text!!.isNotEmpty() && binding.fieldExponent.text!!.isNotEmpty()){

                if (binding.fieldBase.text.toString() != "." && binding.fieldExponent.text.toString() != "."&&
                    binding.fieldBase.text.toString() != "-" && binding.fieldExponent.text.toString() != "-"){

                    val format = DecimalFormat()
                    format.maximumFractionDigits = 6

                    val base = (binding.fieldBase.text.toString()).toDouble()
                    val exponent = (binding.fieldExponent.text.toString()).toDouble()

                    val result =
                        if (base == 0.0 && exponent == 0.0) {
                            "NaN"
                        } else {
                            format.format(base.pow(exponent))
                        }

                    binding.resultField.text = result.checkInteger()

                    binding.baseValue.text = "${getText(R.string.base)} ${base.toString().checkInteger()}"
                    binding.exponentValue.text = "${getText(R.string.exponent_dot)} ${exponent.toString().checkInteger()}"

                    binding.fieldBase.setText("")
                    binding.fieldBase.clearFocus()
                    binding.fieldBase.hint = getString(R.string.base_x)

                    binding.fieldExponent.setText("")
                    binding.fieldExponent.clearFocus()
                    binding.fieldExponent.hint = getString(R.string.exponent_n)

                    binding.labelResult.visibility = View.VISIBLE

                } else {
                    //message of error to inform field is wrong
                    Toast.makeText(context, getString(R.string.message_3), Toast.LENGTH_SHORT).show()
                }
            } else {
                //message of error to inform one field is empty
                Toast.makeText(context, getString(R.string.message_2), Toast.LENGTH_SHORT).show()
            }
        }

        binding.fieldBase.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus){
                binding.fieldBase.hint = ""
            } else {
                if (binding.fieldBase.text!!.isEmpty()){
                    binding.fieldBase.hint = getString(R.string.base_x)
                }
            }
        }

        binding.fieldExponent.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus){
                binding.fieldExponent.hint = ""
            } else {
                if (binding.fieldExponent.text!!.isEmpty()){
                    binding.fieldExponent.hint = getString(R.string.exponent_n)
                }
            }
        }

        return binding.root
    }
}