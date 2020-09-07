package justjump.coding_calculator

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatDialogFragment
import justjump.coding_calculator.utilities.ColorDesign
import justjump.coding_calculator.utilities.Functions
import kotlinx.android.synthetic.main.dialog_info_color.view.*

class InfoColorDialog(rgbColor: Int) : AppCompatDialogFragment() {

    private val r = Color.red(rgbColor)
    private val g = Color.green(rgbColor)
    private val b = Color.blue(rgbColor)
    private val rgbColorInt = rgbColor

    @Override
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        return activity?.let {
            val builder = AlertDialog.Builder(it)
            // Get the layout inflater
            val inflater = requireActivity().layoutInflater;

            val viewDialog = inflater.inflate(R.layout.dialog_info_color, null)

            // RGB color
            viewDialog.rgb_red_part.text = r.toString() + " "
            viewDialog.rgb_green_part.text = g.toString() + " "
            viewDialog.rgb_blue_part.text = b.toString()

            // change the color on the background to show the color
            viewDialog.field_forcolor.setBackgroundColor(rgbColorInt)

            // calculate the HSL Color
            val colorHSL = ColorDesign().getHSLColorFromRGB(rgbColorInt)
            viewDialog.hsl_part.text =
                (((colorHSL[0] * 360).toInt()).toString() + " " + ((colorHSL[1] * 100).toInt()).toString() + " " + ((colorHSL[2] * 100).toInt()).toString())

            // calculate the Hex color
            viewDialog.hex_part.text = "#${Functions().convertToHex(r)}${Functions().convertToHex(g)}${Functions().convertToHex(b)}"

            // Inflate and set the layout for the dialog
            builder.setView(viewDialog)

                // Add action buttons
                .setPositiveButton("OK",
                    DialogInterface.OnClickListener { dialog, id ->
                        // sign in the user ...
                    })

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}