package justjump.coding_calculator

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_palette_colors.*


class PaletteColors : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_palette_colors)

        val itemList: Array<Array<String>> = arrayOf(
            arrayOf("Red", "#f44336"),
            arrayOf("Pink", "#e91e63"),
            arrayOf("Purple", "#9c27b0"),
            arrayOf("Deep Purple", "#673ab7"),
            arrayOf("Indigo", "#3f51b5"),
            arrayOf("Blue", "#2196f3"),
            arrayOf("Light Blue", "#03a9f4"),
            arrayOf("Cyan", "#00bcd4"),
            arrayOf("Teal", "#009688"),
            arrayOf("Green", "#4caf50"),
            arrayOf("Light Green", "#8bc34a"),
            arrayOf("Lime", "#cddc39"),
            arrayOf("Yellow", "#ffeb3b"),
            arrayOf("Amber", "#ffc107"),
            arrayOf("Orange", "#ff9800"),
            arrayOf("Deep Orange", "#ff5722"),
            arrayOf("Brown", "#795548"),
            arrayOf("Grey", "#9e9e9e"),
            arrayOf("Blue Grey", "#607d8b")
        )

        // call to the function to load the list colors
        loadListColors(itemList)
    }

    private fun loadListColors(itemList: Array<Array<String>>)
    {
        for (item in itemList) {

            val buttonColors = Button(this)
            val id: Int = buttonColors.id

            buttonColors.text = "  ${item[0]}"
            buttonColors.textSize = 17f
            buttonColors.setTypeface(null, Typeface.BOLD);
            buttonColors.setBackgroundColor(Color.parseColor(item[1]))
            layoutColors.addView(buttonColors)

            buttonColors.setOnClickListener {
                Toast.makeText(this, "Id:${id} Color:${item[0]}", Toast.LENGTH_LONG).show()
            }
        }
    }
}