package com.just_jump.coding_calculator

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.just_jump.coding_calculator.data.local.ListColorsRange
import com.just_jump.coding_calculator.databinding.ActivityPaletteColorsBinding
import com.just_jump.coding_calculator.databinding.ItemPaletteColorBinding

class PaletteColors : AppCompatActivity() {

    private lateinit var binding: ActivityPaletteColorsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaletteColorsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val gradientDrawable = GradientDrawable(
            GradientDrawable.Orientation.BOTTOM_TOP,
            intArrayOf(
                Color.parseColor("#212121"),
                Color.parseColor("#616161")
            )
        )
        binding.mainLayoutPalette.background = gradientDrawable


        // call to the function to load the list colors
        loadListColors(ListColorsRange().getListColors())
    }

    @SuppressLint("SetTextI18n", "DefaultLocale", "InflateParams")
    private fun loadListColors(itemList: Array<Array<String>>) {
        var bindingItem: ItemPaletteColorBinding

        for (item in itemList) {

            bindingItem = ItemPaletteColorBinding.inflate(layoutInflater)
            binding.layoutColors.addView(bindingItem.root)

            bindingItem.NameColor.text = "  ${item[0]}".toUpperCase()
            bindingItem.ColorInfo.setColorFilter(Color.parseColor(item[1]))

            bindingItem.Button.setOnClickListener {
                val paletteColors = Intent(this, GammaColors::class.java)

                paletteColors.putExtra("COLOR", item[1])
                startActivity(paletteColors)
            }
        }
    }
}