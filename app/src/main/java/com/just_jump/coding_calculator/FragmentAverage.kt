package com.just_jump.coding_calculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.chip.Chip
import com.just_jump.coding_calculator.extensions.checkInteger
import com.just_jump.coding_calculator.utilities.Functions
import kotlinx.android.synthetic.main.fragment__average.*
import kotlinx.android.synthetic.main.fragment__average.view.InsertNewValue
import kotlinx.android.synthetic.main.fragment__average.view.clear_button
import kotlinx.android.synthetic.main.fragment__average.view.resultField
import kotlinx.android.synthetic.main.fragment__average_new.view.*
import java.text.DecimalFormat

class FragmentAverage : Fragment() {

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment__average_new, container, false)
        view.clear_button.isEnabled = false

        /**
         * Event to control: button come back to the parent
         */
        view.button_back.setOnClickListener {
            Toast.makeText(context, "Back..", Toast.LENGTH_SHORT).show()
        }

        /**
         * Event to control: insert new value
         */
        view.InsertNewValue.setOnClickListener {

            if (newChipNumber.text!!.isNotEmpty()) {

                if (newChipNumber.text.toString() != "."){

                    val chip = Chip(this.context)
                    chip.text = newChipNumber.text.toString()
                    chip.isCloseIconVisible = true
                    chip.setOnCloseIconClickListener {
                        chipGroup.removeView(chip)
                        getResult(view)
                    }
                    chipGroup.addView(chip)

                    view.linearLayout.fullScroll(View.FOCUS_DOWN)

                    // clean the field
                    newChipNumber.setText("")

                    view.clear_button.isEnabled = true

                    // get to values
                    getResult(view)

                } else {
                    // clean the field
                    newChipNumber.setText("")
                    Toast.makeText(context,"Invalid format used.",Toast.LENGTH_SHORT).show()
                }
            }
        }

        /**
         *  Event to control: button clear
         */
        view.clear_button.setOnClickListener {
            val count = this.chipGroup.childCount
            var i = 0

            while(i < count) {
                chipGroup.removeView(chipGroup.getChildAt(0))
                i++
            }
            view.clear_button.isEnabled = false
            view.resultField.text = getString(R.string.mean)
            view.total_values.text = getString(R.string.total)
            view.number_of_values.text = getString(R.string.n_values)
        }

        return view
    }

    private fun getResult(view: View){
        val format = DecimalFormat()
        val dataArray: ArrayList<String> = arrayListOf()
        val count = this.chipGroup.childCount
        var i = 0

        format.maximumFractionDigits = 4

        while(i < count) {

            val chip = chipGroup.getChildAt(i) as Chip
            dataArray.add(chip.text.toString())
            i++
        }

        if (dataArray.size != 0){
            val result =
                if (dataArray.size > 1) {
                    Functions().average(dataArray)
                }  else {
                    dataArray[0].toDouble()
                }

            view.resultField.text = format.format(result).toString().checkInteger()
            view.total_values.text = Functions().resultAdd(dataArray).toString().checkInteger()
            view.number_of_values.text = count.toString()

        } else{
            view.clear_button.isEnabled = false
            view.resultField.text = getString(R.string.mean)
            view.total_values.text = getString(R.string.total)
            view.number_of_values.text = getString(R.string.n_values)
        }


    }
}