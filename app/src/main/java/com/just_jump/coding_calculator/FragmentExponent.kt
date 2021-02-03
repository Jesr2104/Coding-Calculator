package com.just_jump.coding_calculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.just_jump.coding_calculator.extensions.checkInteger
import kotlinx.android.synthetic.main.fragment_exponent_new.*
import kotlinx.android.synthetic.main.fragment_exponent_new.view.*
import java.text.DecimalFormat
import kotlin.math.pow

class FragmentExponent : Fragment() {

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle? ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_exponent_new, container, false)

        view.field_base.hint = getString(R.string.base_x)
        view.field_base.setHintTextColor(resources.getColor(R.color.grey_hint))

        view.field_exponent.hint = getString(R.string.exponent_n)
        view.field_exponent.setHintTextColor(resources.getColor(R.color.grey_hint))

        view.calculatorButton.setOnClickListener {
            if (view.field_base.text!!.isNotEmpty() && view.field_exponent.text!!.isNotEmpty()){

                val format = DecimalFormat()
                format.maximumFractionDigits = 6

                val base = (field_base.text.toString()).toDouble()
                val exponent = (field_exponent.text.toString()).toDouble()

                val result = format.format(base.pow(exponent))

                view.resultField.text = result.toString().checkInteger()

                view.base_value.text = "${getText(R.string.base)} ${base.toString().checkInteger()}"
                view.exponent_value.text = "${getText(R.string.exponent_dot)} ${base.toString().checkInteger()}"

                view.field_base.setText("")
                view.field_base.clearFocus()
                view.field_base.hint = getString(R.string.base_x)

                view.field_exponent.setText("")
                view.field_exponent.clearFocus()
                view.field_exponent.hint = getString(R.string.exponent_n)

                view.label_result.visibility = View.VISIBLE

            } else {
                //message of error to inform one field is empty
                Toast.makeText(view.context, "Some of the data is missing to insert", Toast.LENGTH_SHORT).show()
            }
        }

        view.field_base.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus){
                view.field_base.hint = ""
            } else {
                if (view.field_base.text!!.isEmpty()){
                    view.field_base.hint = getString(R.string.base_x)
                }
            }
        }

        view.field_exponent.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus){
                view.field_exponent.hint = ""
            } else {
                if (view.field_exponent.text!!.isEmpty()){
                    view.field_exponent.hint = getString(R.string.exponent_n)
                }
            }
        }

        return view
    }
}