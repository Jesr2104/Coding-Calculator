package com.just_jump.coding_calculator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.just_jump.coding_calculator.extensions.checkInteger
import com.just_jump.coding_calculator.utilities.Functions
import com.just_jump.coding_calculator.utilities.ReturnMainActivity
import kotlinx.android.synthetic.main.fragment_nth_root_new.*
import kotlinx.android.synthetic.main.fragment_nth_root_new.view.*
import java.text.DecimalFormat

class FragmentSquareRoot(private val myInterface: ReturnMainActivity) : Fragment() {

    private val format = DecimalFormat()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle? ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_nth_root_new, container, false)

        view.field_n.hint = getString(R.string.insertN)
        view.field_n.setHintTextColor(resources.getColor(R.color.grey_hint))

        view.field_x.hint = getString(R.string.insertX)
        view.field_x.setHintTextColor(resources.getColor(R.color.grey_hint))

        format.maximumFractionDigits = 7

        /**
         * Event to control: button come back to the parent
         */
        view.button_back.setOnClickListener {
            myInterface.returnMainActivity()
        }

        /**
         *  Event to control: when the new field lost the focus
         */
        view.field_n.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                field_n.hint = ""
            } else {
                if (field_n.text!!.isEmpty()) {
                    field_n.hint = getString(R.string.insertN)
                }
            }
        }

        /**
         *  Event to control: when the new field lost the focus
         */
        view.field_x.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                field_x.hint = ""
            } else {
                if (field_x.text!!.isEmpty()) {
                    field_x.hint = getString(R.string.insertX)
                }
            }
        }

        view.calculatorButton.setOnClickListener {

            if (field_n.text.toString().isNotEmpty() &&
                field_x.text.toString().isNotEmpty()
            ) {

                val valueN = field_n.text.toString().toDouble()
                val valueX = field_x.text.toString().toDouble()

                if (valueN > 0) {

                    view.value_n.text = "n: ${valueN.toString().checkInteger()}"
                    view.value_x.text = "x: ${valueX.toString().checkInteger()}"

                    view.field_result.text = format.format(Functions().isNthRoot(valueX, valueN))

                    field_n.setText("")
                    field_x.setText("")

                } else {
                    Toast.makeText(
                        view.context,
                        getString(R.string.message_1),
                        Toast.LENGTH_SHORT
                    ).show()
                    field_n.setText("")
                }

            } else {
                //message of error to inform one field is empty
                Toast.makeText(
                    view.context,
                    getString(R.string.message_2),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        return view
    }
}