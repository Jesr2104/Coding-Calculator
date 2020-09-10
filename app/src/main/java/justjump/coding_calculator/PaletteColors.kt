package justjump.coding_calculator

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.blue
import androidx.core.graphics.green
import androidx.core.graphics.red
import androidx.recyclerview.widget.LinearLayoutManager
import justjump.coding_calculator.data.local.ListColorsRange
import kotlinx.android.synthetic.main.activity_gamma_colors.*
import kotlinx.android.synthetic.main.activity_palette_colors.*

class PaletteColors : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_palette_colors)

        // call to the function to load the list colors
        loadListColors(ListColorsRange().getListColors())
    }

    private fun loadListColors(itemList: Array<Array<String>>)
    {
        for (item in itemList) {

            val buttonColors = Button(this)

            buttonColors.text = "  ${item[0]}"
            buttonColors.textSize = 17f
            buttonColors.setTypeface(null, Typeface.BOLD);
            buttonColors.setBackgroundColor(Color.parseColor(item[1]))
            layoutColors.addView(buttonColors)

            buttonColors.setOnClickListener {
                val paletteColors = Intent(this, GammaColors::class.java)

                paletteColors.putExtra("COLOR", item[1])
                startActivity(paletteColors)
            }
        }
    }
}