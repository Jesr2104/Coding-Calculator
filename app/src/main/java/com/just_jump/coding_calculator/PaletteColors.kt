package com.just_jump.coding_calculator

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.just_jump.coding_calculator.data.local.ListColorsRange
import kotlinx.android.synthetic.main.item_palette_color.view.*
import kotlinx.android.synthetic.main.activity_palette_colors.*

class PaletteColors : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_palette_colors)

        val gradientDrawable = GradientDrawable(
            GradientDrawable.Orientation.BOTTOM_TOP,
            intArrayOf(
                Color.parseColor("#212121"),
                Color.parseColor("#616161")
            )
        )
        mainLayoutPalette.background = gradientDrawable


        // call to the function to load the list colors
        loadListColors(ListColorsRange().getListColors())
    }

    @SuppressLint("SetTextI18n", "DefaultLocale", "InflateParams")
    private fun loadListColors(itemList: Array<Array<String>>) {
        for (item in itemList) {

            val view = LayoutInflater.from(this).inflate(R.layout.item_palette_color, null, false)

            layoutColors.addView(view)

            view.NameColor.text = "  ${item[0]}".toUpperCase()
            view.ColorInfo.setColorFilter(Color.parseColor(item[1]))

            view.Button.setOnClickListener {
                val paletteColors = Intent(this, GammaColors::class.java)

                paletteColors.putExtra("COLOR", item[1])
                startActivity(paletteColors)
            }
        }
    }
}