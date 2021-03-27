package com.just_jump.coding_calculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.just_jump.coding_calculator.databinding.FragmentRuleOfThreeNewBinding
import com.just_jump.coding_calculator.utilities.Functions
import com.just_jump.coding_calculator.utilities.HideKeyboard
import com.just_jump.coding_calculator.utilities.ReturnMainActivity
import java.text.DecimalFormat

class FragmentRuleOfThree(private val myInterface: ReturnMainActivity) : Fragment() {

    private var proportionsTypeSelected = true
    private lateinit var binding: FragmentRuleOfThreeNewBinding

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle? ): View {
        
        binding = FragmentRuleOfThreeNewBinding.inflate(layoutInflater)

        binding.TextValueA.hint = getString(R.string.valueA)
        binding.TextValueA.setHintTextColor(ContextCompat.getColor(requireContext(), R.color.grey_hint))

        binding.TextValueB.hint = getString(R.string.valueB)
        binding.TextValueB.setHintTextColor(ContextCompat.getColor(requireContext(), R.color.grey_hint))

        binding.TextValueC.hint = getString(R.string.valueC)
        binding.TextValueC.setHintTextColor(ContextCompat.getColor(requireContext(), R.color.grey_hint))

        /**
         * Event to control: button come back to the parent
         */
        binding.buttonBack.setOnClickListener {
            myInterface.returnMainActivity()
        }

        /**
         *  Event to control: when the new field lost the focus
         */
        binding.TextValueA.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus){
                binding.TextValueA.hint = ""
            } else {
                if (binding.TextValueA.text!!.isEmpty()){
                    binding.TextValueA.hint = getString(R.string.valueA)
                }
            }
        }

        binding.TextValueB.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus){
                binding.TextValueB.hint = ""
            } else {
                if (binding.TextValueB.text!!.isEmpty()){
                    binding.TextValueB.hint = getString(R.string.valueB)
                }
            }
        }

        binding.TextValueC.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus){
                binding.TextValueC.hint = ""
            } else {
                if (binding.TextValueC.text!!.isEmpty()){
                    binding.TextValueC.hint = getString(R.string.valueC)
                }
            }
        }

        //------------------------------------------------------------------------
        // when you press to change the type
        //------------------------------------------------------------------------
        binding.direct.setOnClickListener {
            proportionsTypeSelected = true
            binding.equation.setImageResource(R.drawable.equation_direct)
        }

        binding.inverse.setOnClickListener {
            proportionsTypeSelected = false
            binding.equation.setImageResource(R.drawable.equation_inverse)
        }
        //------------------------------------------------------------------------

        binding.ruleOfThreeInfo.setOnClickListener {

            if (proportionsTypeSelected) {
                val newFragment = InfoRuleOfThreeDirectDialog()
                newFragment.show(requireActivity().supportFragmentManager, "changeColorValue")
            } else {
                val newFragment = InfoRuleOfThreeInverseDialog()
                newFragment.show(requireActivity().supportFragmentManager, "changeColorValue")
            }
        }

        binding.calculatorButton.setOnClickListener {
            if (binding.TextValueA.text.toString().isNotEmpty() &&
                binding.TextValueB.text.toString().isNotEmpty() &&
                binding.TextValueC.text.toString().isNotEmpty()
            ) {

                if (binding.TextValueA.text.toString() != "." && binding.TextValueA.text.toString() != "-"&&
                    binding.TextValueB.text.toString() != "." && binding.TextValueB.text.toString() != "-"&&
                    binding.TextValueC.text.toString() != "." && binding.TextValueC.text.toString() != "-"){

                    val calculatorResult: Double =
                        if (proportionsTypeSelected) {
                            Functions().ruleOfThreeDirect(
                                binding.TextValueA.text.toString().toDouble(),
                                binding.TextValueB.text.toString().toDouble(),
                                binding.TextValueC.text.toString().toDouble()
                            )
                        } else {
                            Functions().ruleOfThreeInverse(
                                binding.TextValueA.text.toString().toDouble(),
                                binding.TextValueB.text.toString().toDouble(),
                                binding.TextValueC.text.toString().toDouble()
                            )
                        }

                    val format = DecimalFormat()
                    format.maximumFractionDigits = 6

                    binding.result.text = format.format(calculatorResult)

                    binding.fieldValueResultA.text = "A: ${binding.TextValueA.text}"
                    binding.fieldValueResultB.text = "B: ${binding.TextValueB.text}"
                    binding.fieldValueResultC.text = "C: ${binding.TextValueC.text}"

                    binding.TextValueA.setText("")
                    binding.TextValueA.clearFocus()
                    binding.TextValueA.hint = getString(R.string.valueA)

                    binding.TextValueB.setText("")
                    binding.TextValueB.clearFocus()
                    binding.TextValueB.hint = getString(R.string.valueB)

                    binding.TextValueC.setText("")
                    binding.TextValueC.clearFocus()
                    binding.TextValueC.hint = getString(R.string.valueB)

                    // we hide the keyboard to show the result of the rule of three calculations
                    this.activity?.let { it1 -> HideKeyboard(it1) }

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