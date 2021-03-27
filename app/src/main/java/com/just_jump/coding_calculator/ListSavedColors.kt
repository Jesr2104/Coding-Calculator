package com.just_jump.coding_calculator

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.just_jump.coding_calculator.data.local.SRDataColors
import com.just_jump.coding_calculator.databinding.ActivityListSavedColorsBinding

class ListSavedColors : AppCompatActivity(), RVSavedColorInt {

    private lateinit var adapter: RecycleAdapterSavedColors
    lateinit var data: ArrayList<Int>
    private lateinit var binding: ActivityListSavedColorsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListSavedColorsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        data = SRDataColors.customPreference(this).getlist()

        binding.recycleViewFields.layoutManager = LinearLayoutManager(this)
        adapter = RecycleAdapterSavedColors(data,this)
        binding.recycleViewFields.adapter = adapter

        val gradientDrawable = GradientDrawable(
            GradientDrawable.Orientation.BOTTOM_TOP,
            intArrayOf(
                Color.parseColor("#212121"),
                Color.parseColor("#616161")
            )
        )
        binding.mainLayoutSavedColor.background = gradientDrawable
    }

    override fun colorIntValue(colorInt: Int) {
        val resultIntent = Intent()
        resultIntent.putExtra("loadColor", colorInt)
        setResult(RESULT_OK,resultIntent)
        finish()
    }
}