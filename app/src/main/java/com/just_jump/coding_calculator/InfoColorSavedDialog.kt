package com.just_jump.coding_calculator

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatDialogFragment
import com.just_jump.coding_calculator.data.local.SRDataColors
import com.just_jump.coding_calculator.utilities.ColorDesign
import com.just_jump.coding_calculator.utilities.Functions
import com.just_jump.coding_calculator.viewmodel.ViewModelColorCode
import kotlinx.android.synthetic.main.dialog_info_color.view.*

class InfoColorSavedDialog(view: Context, rgbColor: Int, private val cViewModel: ViewModelColorCode) : AppCompatDialogFragment() {

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
            viewDialog.rgb_red_part.text = "$r "
            viewDialog.rgb_green_part.text = "$g "
            viewDialog.rgb_blue_part.text = b.toString()

            // change the color on the background to show the color
            viewDialog.field_forcolor.setBackgroundColor(rgbColorInt)

            // calculate the HSL Color
            val colorHSL = ColorDesign().getHSLColorFromRGB(rgbColorInt)

            viewDialog.hsl_partH.text = ((colorHSL[0] * 360).toInt()).toString()
            viewDialog.hsl_partS.text = ((colorHSL[1] * 100).toInt()).toString()
            viewDialog.hsl_partL.text = ((colorHSL[2] * 100).toInt()).toString()

            // calculate the Hex color
            viewDialog.hex_part.text = "#${Functions().convertDecToHex(r)}${Functions().convertDecToHex(g)}${Functions().convertDecToHex(b)}"

            builder.setView(viewDialog)

                // Add action buttons
                .setNeutralButton("Ok",
                    DialogInterface.OnClickListener { dialog, id ->
                        // sign in the user ...
                        dialog.dismiss()
                    })

                // Add action buttons
                .setNegativeButton("Delete",
                    DialogInterface.OnClickListener { dialog, id ->
                        // sign in the user ...
                        SRDataColors.deleteItem(rgbColorInt)
                        cViewModel.setRGBColor(cViewModel.getRGBColor())
                    })

                // Add action buttons
                .setPositiveButton("Load",
                    DialogInterface.OnClickListener { dialog, id ->
                        // sign in the user ...
                        cViewModel.setRGBColor(rgbColorInt)
                    })

            // Inflate and set the layout for the dialog
            builder.create();

        } ?: throw IllegalStateException("Activity cannot be null")
    }
}