package justjump.coding_calculator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.chip.Chip
import justjump.coding_calculator.utilities.Functions
import kotlinx.android.synthetic.main.fragment__average.*
import kotlinx.android.synthetic.main.fragment__average.view.*
import java.text.DecimalFormat

class FragmentAverage : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle? ): View? {

        val view = inflater.inflate(R.layout.fragment__average, container, false)

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

        return view
    }
}