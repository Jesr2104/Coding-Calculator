package com.just_jump.coding_calculator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.just_jump.coding_calculator.utilities.Functions
import kotlinx.android.synthetic.main.fragment__percentage.*
import kotlinx.android.synthetic.main.fragment__percentage.view.*
import java.text.DecimalFormat

class FragmentPercentage : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment__percentage, container, false)

        view.calculatorButton.setOnClickListener{

            if(fieldNumberValue.text.toString().isNotEmpty() &&
               fieldNumberPercentage.text.toString().isNotEmpty()){

                val format = DecimalFormat()
                format.maximumFractionDigits = 3

                view.textResultValue.text = format.format(view.fieldNumberValue.text.toString().toDouble())
                view.textResultPercentage.text = "${view.fieldNumberPercentage.text} %"
                val resultTemp = Functions().percentageCalculator(fieldNumberValue.text.toString().toDouble(),fieldNumberPercentage.text.toString().toDouble())
                view.textResultNumber.text = format.format(resultTemp)

                val resultAddition = view.fieldNumberValue.text.toString().toDouble() + resultTemp
                view.additionResult.text = format.format(resultAddition)

                val resultSubtration = view.fieldNumberValue.text.toString().toDouble() - resultTemp
                view.subtrationResult.text = format.format(resultSubtration)
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