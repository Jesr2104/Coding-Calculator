package com.just_jump.coding_calculator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.just_jump.coding_calculator.utilities.ReturnMainActivity
import kotlinx.android.synthetic.main.fragment__average_new.view.*

class FragmentSquareRoot(private val myInterface: ReturnMainActivity) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle? ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_square_root_new, container, false)

        /**
         * Event to control: button come back to the parent
         */
        view.button_back.setOnClickListener {
            myInterface.returnMainActivity()
        }

        return view
    }
}