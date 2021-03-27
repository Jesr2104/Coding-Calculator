package com.just_jump.coding_calculator

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatDialogFragment
import com.just_jump.coding_calculator.data.local.SRDataColors
import com.just_jump.coding_calculator.databinding.DialogInfoColorBinding
import com.just_jump.coding_calculator.utilities.ColorDesign
import com.just_jump.coding_calculator.utilities.Functions
import com.just_jump.coding_calculator.viewmodel.ViewModelColorCode

class InfoColorSavedDialog(rgbColor: Int, private val cViewModel: ViewModelColorCode) : AppCompatDialogFragment() {

    private val r = Color.red(rgbColor)
    private val g = Color.green(rgbColor)
    private val b = Color.blue(rgbColor)
    private val rgbColorInt = rgbColor
    private lateinit var binding: DialogInfoColorBinding

    @SuppressLint("SetTextI18n")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        return activity?.let {
            val builder = AlertDialog.Builder(it)
            // Get the layout inflater
            binding = DialogInfoColorBinding.inflate(layoutInflater)

            // RGB color
            binding.rgbRedPart.text = "$r "
            binding.rgbGreenPart.text = "$g "
            binding.rgbBluePart.text = b.toString()

            // change the color on the background to show the color
            binding.fieldColor.setBackgroundColor(rgbColorInt)

            // calculate the HSL Color
            val colorHSL = ColorDesign().getHSLColorFromRGB(rgbColorInt)

            binding.hslPartH.text = ((colorHSL[0] * 360).toInt()).toString()
            binding.hslPartS.text = ((colorHSL[1] * 100).toInt()).toString()
            binding.hslPartL.text = ((colorHSL[2] * 100).toInt()).toString()

            // calculate the Hex color
            binding.hexPart.text = "#${Functions().convertDecToHex(r)}${Functions().convertDecToHex(g)}${Functions().convertDecToHex(b)}"

            builder.setView(binding.root)

                // Add action buttons
                .setNeutralButton("Ok") { dialog, _ ->
                    // sign in the user ...
                    dialog.dismiss()
                }

                // Add action buttons
                .setNegativeButton("Delete") { _, _ ->
                    // sign in the user ...
                    SRDataColors.deleteItem(rgbColorInt)
                    cViewModel.setRGBColor(cViewModel.getRGBColor())
                }

                // Add action buttons
                .setPositiveButton("Load") { _, _ ->
                    // sign in the user ...
                    cViewModel.setRGBColor(rgbColorInt)
                }

            // Inflate and set the layout for the dialog
            builder.create()

        } ?: throw IllegalStateException("Activity cannot be null")
    }
}