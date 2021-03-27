package com.just_jump.coding_calculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.just_jump.coding_calculator.databinding.FragmentNthRootNewBinding
import com.just_jump.coding_calculator.extensions.checkInteger
import com.just_jump.coding_calculator.utilities.Functions
import com.just_jump.coding_calculator.utilities.ReturnMainActivity
import java.text.DecimalFormat

class FragmentSquareRoot(private val myInterface: ReturnMainActivity) : Fragment() {

    private val format = DecimalFormat()
    private lateinit var binding: FragmentNthRootNewBinding

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle? ): View {
        
        binding = FragmentNthRootNewBinding.inflate(layoutInflater)

        binding.fieldN.hint = getString(R.string.insertN)
        binding.fieldN.setHintTextColor(ContextCompat.getColor(requireContext(), R.color.grey_hint))

        binding.fieldX.hint = getString(R.string.insertX)
        binding.fieldX.setHintTextColor(ContextCompat.getColor(requireContext(), R.color.grey_hint))

        format.maximumFractionDigits = 7

        /**
         * Event to control: button come back to the parent
         */
        binding.buttonBack.setOnClickListener {
            myInterface.returnMainActivity()
        }

        /**
         *  Event to control: when the new field lost the focus
         */
        binding.fieldN.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.fieldN.hint = ""
            } else {
                if (binding.fieldN.text!!.isEmpty()) {
                    binding.fieldN.hint = getString(R.string.insertN)
                }
            }
        }

        /**
         *  Event to control: when the new field lost the focus
         */
        binding.fieldX.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.fieldX.hint = ""
            } else {
                if (binding.fieldX.text!!.isEmpty()) {
                    binding.fieldX.hint = getString(R.string.insertX)
                }
            }
        }

        binding.calculatorButton.setOnClickListener {

            if (binding.fieldN.text.toString().isNotEmpty() &&
                binding.fieldX.text.toString().isNotEmpty()
            ) {

                if (binding.fieldN.text.toString() != "." && binding.fieldX.text.toString() != "."&&
                    binding.fieldN.text.toString() != "-" && binding.fieldX.text.toString() != "-"){

                    val valueN = binding.fieldX.text.toString().toDouble()
                    val valueX = binding.fieldX.text.toString().toDouble()

                    if (valueN >= 0) {

                        binding.valueN.text = "n: ${valueN.toString().checkInteger()}"
                        binding.valueX.text = "x: ${valueX.toString().checkInteger()}"

                        binding.fieldResult.text = format.format(Functions().isNthRoot(valueX, valueN))

                        binding.fieldN.setText("")
                        binding.fieldX.setText("")

                    } else {
                        Toast.makeText(
                            context,
                            getString(R.string.message_1),
                            Toast.LENGTH_SHORT
                        ).show()
                        binding.fieldN.setText("")
                    }

                } else {
                    //message of error to inform field is wrong
                    Toast.makeText(context, getString(R.string.message_3), Toast.LENGTH_SHORT).show()
                }

            } else {
                //message of error to inform one field is empty
                Toast.makeText(
                    context,
                    getString(R.string.message_2),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        return binding.root
    }
}