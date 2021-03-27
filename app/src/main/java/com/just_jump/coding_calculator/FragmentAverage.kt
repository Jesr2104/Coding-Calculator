package com.just_jump.coding_calculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.chip.Chip
import com.just_jump.coding_calculator.databinding.FragmentAverageNewBinding
import com.just_jump.coding_calculator.extensions.checkInteger
import com.just_jump.coding_calculator.utilities.Functions
import com.just_jump.coding_calculator.utilities.ReturnMainActivity
import java.text.DecimalFormat

class FragmentAverage(private val myInterface: ReturnMainActivity) : Fragment() {

    private lateinit var binding: FragmentAverageNewBinding

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle? ): View {

        // Inflate the layout for this fragment
        binding = FragmentAverageNewBinding.inflate(layoutInflater)
        binding.clearButton.isEnabled = false

        binding.newValue.hint = getString(R.string.insert_new_value)
        binding.newValue.setHintTextColor(ContextCompat.getColor(requireContext(), R.color.grey_hint))

        /**
         * Event to control: button come back to the parent
         */
        binding.buttonBack.setOnClickListener {
            myInterface.returnMainActivity()
        }

        /**
         *  Event to control: when the new field lost the focus
         */
        binding.newValue.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus){
                binding.newValue.hint = ""
            } else {
                if (binding.newValue.text!!.isEmpty()){
                    binding.newValue.hint = getString(R.string.base_x)
                }
            }
        }

        /**
         * Event to control: insert new value
         */
        binding.InsertNewValue.setOnClickListener {

            if (binding.newValue.text!!.isNotEmpty()) {

                if (binding.newValue.text.toString() != "." && binding.newValue.text.toString() != "-"){

                    val chip = Chip(this.context)
                    chip.text = binding.newValue.text.toString()
                    chip.isCloseIconVisible = true
                    chip.setOnCloseIconClickListener {
                        binding.chipGroup.removeView(chip)
                        getResult()
                    }
                    binding.chipGroup.addView(chip)

                    binding.linearLayout.fullScroll(View.FOCUS_DOWN)

                    // clean the field
                    binding.newValue.setText("")

                    binding.clearButton.isEnabled = true

                    // get to values
                    getResult()

                } else {
                    // clean the field
                    binding.newValue.setText("")
                    Toast.makeText(context, getString(R.string.message_3),Toast.LENGTH_SHORT).show()
                }
            }
        }

        /**
         *  Event to control: button clear
         */
        binding.clearButton.setOnClickListener {
            val count = binding.chipGroup.childCount
            var i = 0

            while(i < count) {
                binding.chipGroup.removeView(binding.chipGroup.getChildAt(0))
                i++
            }
            binding.clearButton.isEnabled = false
            binding.resultField.text = getString(R.string.mean)
            binding.totalValueAdd.text = getString(R.string.total)
            binding.numValues.text = getString(R.string.n_values)

            binding.newValue.setText("")
            binding.newValue.clearFocus()
            binding.newValue.hint = getString(R.string.insert_new_value)

            binding.labelTotal.visibility = View.INVISIBLE
            binding.labelNValues.visibility = View.INVISIBLE
            binding.labelResult.visibility = View.INVISIBLE
        }

        return binding.root
    }

    private fun getResult(){
        val format = DecimalFormat()
        val dataArray: ArrayList<String> = arrayListOf()
        val count = binding.chipGroup.childCount
        var i = 0

        format.maximumFractionDigits = 6

        while(i < count) {

            val chip = binding.chipGroup.getChildAt(i) as Chip
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

            binding.resultField.text = format.format(result).toString().checkInteger()
            binding.totalValueAdd.text = format.format(Functions().resultAdd(dataArray)).toString().checkInteger()
            binding.numValues.text = count.toString()

            binding.labelTotal.visibility = View.VISIBLE
            binding.labelNValues.visibility = View.VISIBLE
            binding.labelResult.visibility = View.VISIBLE

        } else{
            binding.clearButton.isEnabled = false
            binding.resultField.text = getString(R.string.mean)
            binding.totalValueAdd.text = getString(R.string.total)
            binding.numValues.text = getString(R.string.n_values)
        }
    }
}