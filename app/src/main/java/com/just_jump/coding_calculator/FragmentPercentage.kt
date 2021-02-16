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
import kotlinx.android.synthetic.main.fragment__percentage_new.*
import kotlinx.android.synthetic.main.fragment__percentage_new.view.*
import java.text.DecimalFormat

class FragmentPercentage(private val myInterface: ReturnMainActivity) : Fragment() {

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment__percentage_new, container, false)

        view.field_value.hint = getString(R.string.value)
        view.field_value.setHintTextColor(resources.getColor(R.color.grey_hint))

        view.field_percentage.hint = getString(R.string.percentage)
        view.field_percentage.setHintTextColor(resources.getColor(R.color.grey_hint))

        /**
         *  Event to control: when the new field lost the focus
         */
        view.field_value.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus){
                field_value.hint = ""
            } else {
                if (field_value.text!!.isEmpty()){
                    field_value.hint = getString(R.string.value)
                }
            }
        }

        /**
         *  Event to control: when the new field lost the focus
         */
        view.field_percentage.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus){
                field_percentage.hint = ""
            } else {
                if (field_percentage.text!!.isEmpty()){
                    field_percentage.hint = getString(R.string.percentage)
                }
            }
        }

        /**
         * Event to control: button come back to the parent
         */
        view.button_back.setOnClickListener {
            myInterface.returnMainActivity()
        }

        view.calculatorButton.setOnClickListener{

            if(field_percentage.text.toString().isNotEmpty() &&
                field_value.text.toString().isNotEmpty()){

                val format = DecimalFormat()
                format.maximumFractionDigits = 2

                view.fieldValue.text = format.format(view.field_value.text.toString().toDouble())
                view.value_percentage.text = "${view.field_percentage.text} %"

                val resultTemp = Functions().percentageCalculator(field_value.text.toString().toDouble(),field_percentage.text.toString().toDouble())
                view.result_percentage.text = format.format(resultTemp)

                val resultAddition = view.fieldValue.text.toString().replace(",", "").toDouble() + resultTemp
                view.additionResult.text = format.format(resultAddition)

                val resultSubtraction = view.fieldValue.text.toString().replace(",", "").toDouble() - resultTemp
                view.subtractionResult.text = format.format(resultSubtraction)

                // we hide the keyboard to show the result of the percentage calculations
                this.activity?.let { it1 -> HideKeyboard(it1) }

                view.field_value.setText("")
                view.field_value.clearFocus()
                view.field_value.hint = getString(R.string.value)

                view.field_percentage.setText("")
                view.field_percentage.clearFocus()
                view.field_percentage.hint = getString(R.string.percentage)

                view.label_value_percentage.visibility = View.VISIBLE
                view.label_percentage.visibility = View.VISIBLE
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