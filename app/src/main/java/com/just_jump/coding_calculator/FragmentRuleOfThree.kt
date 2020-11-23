package com.just_jump.coding_calculator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.just_jump.coding_calculator.utilities.Functions
import com.just_jump.coding_calculator.utilities.HideKeyboard
import kotlinx.android.synthetic.main.fragment__rule_of_three.*
import kotlinx.android.synthetic.main.fragment__rule_of_three.view.*
import java.text.DecimalFormat

class FragmentRuleOfThree : Fragment() {

    var proportionsTypeSelected = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment__rule_of_three, container, false)

        //------------------------------------------------------------------------
        // when you press to change the type
        //------------------------------------------------------------------------
        view.direct.setOnClickListener {
            proportionsTypeSelected = true
            equation.setImageResource(R.drawable.equation_direct)
        }

        view.inverse.setOnClickListener {
            proportionsTypeSelected = false
            equation.setImageResource(R.drawable.equation_inverse)
        }
        //------------------------------------------------------------------------

        view.ruleOfThreeInfo.setOnClickListener {

            if (proportionsTypeSelected) {
                val newFragment = InfoRuleOfThreeDirectDialog()
                newFragment.show(requireActivity().supportFragmentManager, "changeColorValue")
            } else {
                val newFragment = InfoRuleOfThreeInverseDialog()
                newFragment.show(requireActivity().supportFragmentManager, "changeColorValue")
            }
        }

        view.calculatorButton.setOnClickListener {
            if (TextValueA.text.toString().isNotEmpty() &&
                TextValueB.text.toString().isNotEmpty() &&
                TextValueC.text.toString().isNotEmpty()
            ) {

                val calculatorResult: Double

                if (proportionsTypeSelected) {
                    calculatorResult = Functions().ruleOfThreeDirect(
                        TextValueA.text.toString().toDouble(),
                        TextValueB.text.toString().toDouble(),
                        TextValueC.text.toString().toDouble()
                    )
                } else {
                    calculatorResult = Functions().ruleOfThreeInverse(
                        TextValueA.text.toString().toDouble(),
                        TextValueB.text.toString().toDouble(),
                        TextValueC.text.toString().toDouble()
                    )
                }

                val format = DecimalFormat()
                format.maximumFractionDigits = 6

                result.text = format.format(calculatorResult)

                // we hide the keyboard to show the result of the rule of three calculations
                this.activity?.let { it1 -> HideKeyboard(it1) }
            } else {
                //message of error to inform one field is empty
                Toast.makeText(
                    view.context,
                    "Some of the data is missing to insert",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        return view
    }
}