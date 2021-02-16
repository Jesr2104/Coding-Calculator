package com.just_jump.coding_calculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.just_jump.coding_calculator.utilities.Functions
import com.just_jump.coding_calculator.utilities.HideKeyboard
import com.just_jump.coding_calculator.utilities.ReturnMainActivity
import kotlinx.android.synthetic.main.fragment__rule_of_three_new.*
import kotlinx.android.synthetic.main.fragment__rule_of_three_new.view.*
import java.text.DecimalFormat

class FragmentRuleOfThree(private val myInterface: ReturnMainActivity) : Fragment() {

    private var proportionsTypeSelected = true

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle? ): View? {

        val view = inflater.inflate(R.layout.fragment__rule_of_three_new, container, false)

        view.TextValueA.hint = getString(R.string.valueA)
        view.TextValueA.setHintTextColor(resources.getColor(R.color.grey_hint))

        view.TextValueB.hint = getString(R.string.valueB)
        view.TextValueB.setHintTextColor(resources.getColor(R.color.grey_hint))

        view.TextValueC.hint = getString(R.string.valueC)
        view.TextValueC.setHintTextColor(resources.getColor(R.color.grey_hint))

        /**
         * Event to control: button come back to the parent
         */
        view.button_back.setOnClickListener {
            myInterface.returnMainActivity()
        }

        /**
         *  Event to control: when the new field lost the focus
         */
        view.TextValueA.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus){
                TextValueA.hint = ""
            } else {
                if (TextValueA.text!!.isEmpty()){
                    TextValueA.hint = getString(R.string.valueA)
                }
            }
        }

        view.TextValueB.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus){
                TextValueB.hint = ""
            } else {
                if (TextValueB.text!!.isEmpty()){
                    TextValueB.hint = getString(R.string.valueB)
                }
            }
        }

        view.TextValueC.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus){
                TextValueC.hint = ""
            } else {
                if (TextValueC.text!!.isEmpty()){
                    TextValueC.hint = getString(R.string.valueC)
                }
            }
        }

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
            if (view.TextValueA.text.toString().isNotEmpty() &&
                view.TextValueB.text.toString().isNotEmpty() &&
                view.TextValueC.text.toString().isNotEmpty()
            ) {

                val calculatorResult: Double =
                    if (proportionsTypeSelected) {
                        Functions().ruleOfThreeDirect(
                            view.TextValueA.text.toString().toDouble(),
                            view.TextValueB.text.toString().toDouble(),
                            view.TextValueC.text.toString().toDouble()
                        )
                    } else {
                        Functions().ruleOfThreeInverse(
                            view.TextValueA.text.toString().toDouble(),
                            view.TextValueB.text.toString().toDouble(),
                            view.TextValueC.text.toString().toDouble()
                        )
                    }

                val format = DecimalFormat()
                format.maximumFractionDigits = 6

                view.result.text = format.format(calculatorResult)

                view.fieldValue_resultA.text = "A: ${TextValueA.text}"
                view.fieldValue_resultB.text = "B: ${TextValueB.text}"
                view.fieldValue_resultC.text = "C: ${TextValueC.text}"

                view.TextValueA.setText("")
                view.TextValueA.clearFocus()
                view.TextValueA.hint = getString(R.string.valueA)

                view.TextValueB.setText("")
                view.TextValueB.clearFocus()
                view.TextValueB.hint = getString(R.string.valueB)

                view.TextValueC.setText("")
                view.TextValueC.clearFocus()
                view.TextValueC.hint = getString(R.string.valueB)

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