package com.just_jump.coding_calculator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.chip.Chip
import com.just_jump.coding_calculator.utilities.Functions
import com.just_jump.coding_calculator.utilities.HideKeyboard
import kotlinx.android.synthetic.main.fragment__average.*
import kotlinx.android.synthetic.main.fragment__average.view.*
import java.text.DecimalFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.schedule

class FragmentAverage : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment__average, container, false)
        view.clear_button.isEnabled = false

        view.InsertNewValue.setOnClickListener {

            if (newChipNumber.text!!.isNotEmpty()) {

                val chip = Chip(this.context)
                chip.text = newChipNumber.text.toString()
                chip.isCloseIconVisible = true
                chip.setOnCloseIconClickListener { chipGroup.removeView(chip) }
                //chip.setTextAppearanceResource();
                chipGroup.addView(chip)

                // clean the field
                newChipNumber.setText("")

                view.clear_button.isEnabled = true

                // hide the keyboard when we insert a new element because could be the last
                // this.activity?.let { it1 -> HideKeyboard(it1) }
            }
        }

        view.check_result.setOnClickListener {
            val format = DecimalFormat()
            format.maximumFractionDigits = 4
            val dataArray: ArrayList<String> = arrayListOf()
            val count = this.chipGroup.childCount
            var i = 1

            while(i < count) {

                val chip = chipGroup.getChildAt(i) as Chip
                dataArray.add(chip.text.toString())
                i++
            }

            // calculates the percentage of the inserted values
            val result = Functions().average(dataArray)
            view.resultField.text = format.format(result).toString()
        }

        view.clear_button.setOnClickListener {
            val count = this.chipGroup.childCount
            var i = 1

            while(i < count) {
                chipGroup.removeView(chipGroup.getChildAt(1))
                i++
            }

            view.clear_button.isEnabled = false
        }

        return view
    }

}