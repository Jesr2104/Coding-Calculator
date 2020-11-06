package com.just_jump.coding_calculator

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.just_jump.coding_calculator.data.local.SRDataColors
import kotlinx.android.synthetic.main.activity_list_saved_colors.*

class ListSavedColors : AppCompatActivity(), RVSavedColorInt{

    private lateinit var adapter: RecycleAdapterSavedColors
    lateinit var data: ArrayList<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_saved_colors)

        data = SRDataColors.customPreference(this).getlist()

        recycleViewFields.layoutManager = LinearLayoutManager(this)
        adapter = RecycleAdapterSavedColors(data,this)
        recycleViewFields.adapter = adapter

        val gradientDrawable = GradientDrawable(
            GradientDrawable.Orientation.BOTTOM_TOP,
            intArrayOf(
                Color.parseColor("#212121"),
                Color.parseColor("#616161")
            )
        )
        mainLayoutSavedColor.background = gradientDrawable
    }

    override fun colorIntValue(colorInt: Int) {
        val resultIntent = Intent()
        resultIntent.putExtra("loadColor", colorInt)
        setResult(RESULT_OK,resultIntent)
        finish()
    }
}