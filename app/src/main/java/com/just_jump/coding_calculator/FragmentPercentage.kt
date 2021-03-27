package com.just_jump.coding_calculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.just_jump.coding_calculator.databinding.FragmentPercentageNewBinding
import com.just_jump.coding_calculator.utilities.Functions
import com.just_jump.coding_calculator.utilities.HideKeyboard
import com.just_jump.coding_calculator.utilities.ReturnMainActivity
import java.text.DecimalFormat

class FragmentPercentage(private val myInterface: ReturnMainActivity) : Fragment() {

    private lateinit var binding: FragmentPercentageNewBinding

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {

        binding = FragmentPercentageNewBinding.inflate(layoutInflater)

        binding.fieldValue.hint = getString(R.string.value)
        binding.fieldValue.setHintTextColor(ContextCompat.getColor(requireContext(), R.color.grey_hint))

        binding.fieldPercentage.hint = getString(R.string.percentage)
        binding.fieldPercentage.setHintTextColor(ContextCompat.getColor(requireContext().applicationContext, R.color.grey_hint))

        /**
         *  Event to control: when the new field lost the focus
         */
        binding.fieldValue.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus){
                binding.fieldValue.hint = ""
            } else {
                if (binding.fieldValue.text!!.isEmpty()){
                    binding.fieldValue.hint = getString(R.string.value)
                }
            }
        }

        /**
         *  Event to control: when the new field lost the focus
         */
        binding.fieldPercentage.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus){
                binding.fieldPercentage.hint = ""
            } else {
                if (binding.fieldPercentage.text!!.isEmpty()){
                    binding.fieldPercentage.hint = getString(R.string.percentage)
                }
            }
        }

        /**
         * Event to control: button come back to the parent
         */
        binding.buttonBack.setOnClickListener {
            myInterface.returnMainActivity()
        }

        binding.calculatorButton.setOnClickListener{

            if(binding.fieldPercentage.text.toString().isNotEmpty() &&
                binding.fieldValue.text.toString().isNotEmpty()){

                if (binding.fieldPercentage.text.toString() != "." && binding.fieldValue.text.toString() != "."&&
                    binding.fieldPercentage.text.toString() != "-" && binding.fieldValue.text.toString() != "-"){

                    val format = DecimalFormat()
                    format.maximumFractionDigits = 2

                    binding.fieldValue.setText(format.format(binding.fieldValue.text.toString().toDouble()))
                    binding.valuePercentage.setText("${binding.fieldPercentage.text} %")

                    val resultTemp = Functions().percentageCalculator(binding.fieldValue.text.toString().toDouble(),binding.fieldPercentage.text.toString().toDouble())
                    binding.resultPercentage.text = format.format(resultTemp)

                    val resultAddition = binding.fieldValue.text.toString().replace(",", "").toDouble() + resultTemp
                    binding.additionResult.text = format.format(resultAddition)

                    val resultSubtraction = binding.fieldValue.text.toString().replace(",", "").toDouble() - resultTemp
                    binding.subtractionResult.text = format.format(resultSubtraction)

                    // we hide the keyboard to show the result of the percentage calculations
                    this.activity?.let { it1 -> HideKeyboard(it1) }

                    binding.fieldValue.setText("")
                    binding.fieldValue.clearFocus()
                    binding.fieldValue.hint = getString(R.string.value)

                    binding.fieldPercentage.setText("")
                    binding.fieldPercentage.clearFocus()
                    binding.fieldPercentage.hint = getString(R.string.percentage)

                    binding.labelValuePercentage.visibility = View.VISIBLE
                    binding.labelPercentage.visibility = View.VISIBLE

                } else {
                    //message of error to inform field is wrong
                    Toast.makeText(context, getString(R.string.message_3), Toast.LENGTH_SHORT).show()
                }
            }
            else
            {
                //message of error to inform one field is empty
                Toast.makeText(context, getString(R.string.message_2), Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }
}