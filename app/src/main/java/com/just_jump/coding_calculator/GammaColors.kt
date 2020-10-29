package com.just_jump.coding_calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.just_jump.coding_calculator.data.local.ListColorsRange
import kotlinx.android.synthetic.main.activity_gamma_colors.*

class GammaColors : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gamma_colors)

        val colorValue = intent.getStringExtra("COLOR")

        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = RecyclerAdapterGammaColors(ListColorsRange().getRangeColors(colorValue!!))
    }
}