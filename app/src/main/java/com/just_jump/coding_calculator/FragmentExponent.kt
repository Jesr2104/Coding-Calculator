package com.just_jump.coding_calculator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.just_jump.coding_calculator.extensions.checkInteger
import kotlinx.android.synthetic.main.fragment_exponent.*
import kotlinx.android.synthetic.main.fragment_exponent.view.*
import kotlin.math.pow

class FragmentExponent : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_exponent, container, false)

        view.calculatorButton.setOnClickListener {
            if (view.fieldNumberBase.text!!.isNotEmpty() && view.fieldNumberExponent.text!!.isNotEmpty()){

                val base = (fieldNumberBase.text.toString()).toDouble()
                val exponent = (fieldNumberExponent.text.toString()).toDouble()

                view.resultField.text = (base.pow(exponent)).toString().checkInteger()
            } else {
                //message of error to inform one field is empty
                Toast.makeText(view.context, "Some of the data is missing to insert", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }
}