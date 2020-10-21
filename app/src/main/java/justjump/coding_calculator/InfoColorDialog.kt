package justjump.coding_calculator

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialogFragment
import justjump.coding_calculator.data.local.SRDataColors
import justjump.coding_calculator.utilities.ColorDesign
import justjump.coding_calculator.utilities.Functions
import justjump.coding_calculator.viewmodel.ViewModelColorCode
import kotlinx.android.synthetic.main.dialog_info_color.view.*

class InfoColorDialog(view: Context, rgbColor: Int, private val cViewModel: ViewModelColorCode) : AppCompatDialogFragment() {

    private val r = Color.red(rgbColor)
    private val g = Color.green(rgbColor)
    private val b = Color.blue(rgbColor)
    private val rgbColorInt = rgbColor
    private val viewContext = view

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
                .setNegativeButton("Load",
                    DialogInterface.OnClickListener { dialog, id ->
                        // sign in the user ...
                        cViewModel.setRGBColor(rgbColorInt)
                    })

                // Add action buttons
                .setPositiveButton("Save",
                    DialogInterface.OnClickListener { dialog, id ->
                        // sign in the user ...

                        var dataSavedList = SRDataColors.getlist()
                        var isAlreadySaved = true

                        dataSavedList.forEach { item ->
                            if (item == rgbColorInt) {
                                isAlreadySaved = false
                            }
                        }

                        if (isAlreadySaved) {
                            val check =
                                SRDataColors.customPreference(viewContext).setList(rgbColorInt)
                            if (check) {
                                Toast.makeText(viewContext, "Saved Successful", Toast.LENGTH_SHORT)
                                    .show()
                                cViewModel.setRGBColor(cViewModel.getRGBColor())
                            } else {
                                Toast.makeText(
                                    viewContext,
                                    "List Colors Saved Is Full",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        } else {
                            Toast.makeText(
                                viewContext,
                                "the color has already been previously saved",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    })

            // Inflate and set the layout for the dialog
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}