package justjump.coding_calculator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.fragment__average.*
import kotlinx.android.synthetic.main.fragment__average.view.*

class FragmentAverage : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment__average, container, false)

        view.InsertNewValue.setOnClickListener {

            if (newChipNumber.text!!.isNotEmpty()) {

                val chip = Chip(this.context)
                chip.text = newChipNumber.text.toString()
                chip.isCloseIconVisible = true
                chip.setOnCloseIconClickListener { chipGroup.removeView(chip) }
                //chip.elevation = 15F
                //chip.setTextAppearanceResource();
                chipGroup.addView(chip)

                // clean the field
                newChipNumber.setText("")
            }
        }

        view.check_result.setOnClickListener {

//            val count = this.chipGroup.childCount
//            var i = 0
//
//            while(i < count) {
//
//                val chip = (this.chipGroup.getChildAt(i) as Chip).text.toString()
//                Toast.makeText(this.context, "${chip.toString()}", Toast.LENGTH_SHORT).show()
//
//
//            }









//            val count = this.chipGroup.childCount
//            var i = 0
//
//            while(i < count) {
//
//                val new = chipGroup.getChildAt(i)
//                new.
//
//
//                Toast.makeText(this.context, "${i}", Toast.LENGTH_SHORT).show()
//                i++
//            }
        }

        return view
    }
}