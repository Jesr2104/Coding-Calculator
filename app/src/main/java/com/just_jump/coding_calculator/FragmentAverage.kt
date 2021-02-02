package com.just_jump.coding_calculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.chip.Chip
import com.just_jump.coding_calculator.R.layout.fragment__average_new
import com.just_jump.coding_calculator.extensions.checkInteger
import com.just_jump.coding_calculator.utilities.Functions
import kotlinx.android.synthetic.main.fragment__average_new.*
import kotlinx.android.synthetic.main.fragment__average_new.view.*
import java.text.DecimalFormat

class FragmentAverage : Fragment() {

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle? ): View {

        val view = inflater.inflate(fragment__average_new, container, false)
        view.clear_button.isEnabled = false

        view.new_value.hint = getString(R.string.insert_new_value)
        view.new_value.setHintTextColor(resources.getColor(R.color.grey_hint))

        /**
         * Event to control: button come back to the parent
         */
        view.button_back.setOnClickListener {
            Toast.makeText(context, "Back..", Toast.LENGTH_SHORT).show()
        }

        /**
         *  Event to control: when the new field lost the focus
         */
        view.new_value.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus){
                new_value.hint = ""
            } else {
                if (new_value.text!!.isEmpty()){
                    new_value.hint = getString(R.string.base_x)
                }
            }
        }

        /**
         * Event to control: insert new value
         */
        view.InsertNewValue.setOnClickListener {

            if (new_value.text!!.isNotEmpty()) {

                if (new_value.text.toString() != "."){

                    val chip = Chip(this.context)
                    chip.text = new_value.text.toString()
                    chip.isCloseIconVisible = true
                    chip.setOnCloseIconClickListener {
                        chipGroup.removeView(chip)
                        getResult(view)
                    }
                    chipGroup.addView(chip)

                    view.linearLayout.fullScroll(View.FOCUS_DOWN)

                    // clean the field
                    new_value.setText("")

                    view.clear_button.isEnabled = true

                    // get to values
                    getResult(view)

                } else {
                    // clean the field
                    new_value.setText("")
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
            view.total_value_add.text = getString(R.string.total)
            view.num_values.text = getString(R.string.n_values)

            view.new_value.setText("")
            view.new_value.clearFocus()
            view.new_value.hint = getString(R.string.insert_new_value)
        }

        return view
    }

    private fun getResult(view: View){
        val format = DecimalFormat()
        val dataArray: ArrayList<String> = arrayListOf()
        val count = this.chipGroup.childCount
        var i = 0

        format.maximumFractionDigits = 6

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
            view.total_value_add.text = format.format(Functions().resultAdd(dataArray)).toString().checkInteger()
            view.num_values.text = count.toString()

        } else{
            view.clear_button.isEnabled = false
            view.resultField.text = getString(R.string.mean)
            view.total_value_add.text = getString(R.string.total)
            view.num_values.text = getString(R.string.n_values)
        }
    }
}