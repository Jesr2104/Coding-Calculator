package com.just_jump.coding_calculator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.just_jump.coding_calculator.utilities.Functions
import kotlinx.android.synthetic.main.fragment__rule_of_three.*
import kotlinx.android.synthetic.main.fragment__rule_of_three.view.*
import java.text.DecimalFormat

class FragmentRuleOfThree : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment__rule_of_three, container, false)

        view.ruleOfThreeInfo.setOnClickListener{
            val newFragment = InfoRuleOfThreeDialog()
            newFragment.show(requireActivity().supportFragmentManager, "changeColorValue")
        }

        view.calculatorButton.setOnClickListener{
            if(TextValueA.text.toString().isNotEmpty() &&
               TextValueB.text.toString().isNotEmpty() &&
               TextValueC.text.toString().isNotEmpty()) {

                val calculatorResult = Functions().ruleOfThree(
                    TextValueA.text.toString().toDouble(),
                    TextValueB.text.toString().toDouble(),
                    TextValueC.text.toString().toDouble()
                )

                val format = DecimalFormat()
                format.maximumFractionDigits = 6

                result.text = format.format(calculatorResult)
            }
            else
            {
                //message of error to inform one field is empty
                Toast.makeText(view.context, "Some of the data is missing to insert", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }
}