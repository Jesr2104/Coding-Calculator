package com.just_jump.coding_calculator

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.just_jump.coding_calculator.data.local.ListColorsRange
import com.just_jump.coding_calculator.databinding.ActivityGammaColorsBinding


class GammaColors : AppCompatActivity() {

    private lateinit var binding:ActivityGammaColorsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGammaColorsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val colorValue = intent.getStringExtra("COLOR")

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = RecyclerAdapterGammaColors(ListColorsRange().getRangeColors(colorValue!!))

        val gradientDrawable = GradientDrawable(
            GradientDrawable.Orientation.BOTTOM_TOP,
            intArrayOf(
                Color.parseColor("#212121"),
                Color.parseColor("#616161")
            )
        )
        binding.mainLayoutGamma.background = gradientDrawable
    }
}