package justjump.coding_calculator

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.dialog_palette_colors.view.*
import java.lang.IllegalStateException

class PaletteColorsDialog : AppCompatDialogFragment() {

    var nombre = mutableListOf<String>()

    @Override
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val itemList: Array<kotlin.String> = arrayOf(
            "Red",
            "Pink",
            "Purple",
            "Deep Purple",
            "Indigo",
            "Blue",
            "Light Blue",
            "Cyan",
            "Teal",
            "Green",
            "Light Green",
            "Lime",
            "Yellow",
            "Amber",
            "Orange",
            "Deep Orange",
            "Brown",
            "Grey",
            "Blue Grey"
        )

        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater;

            val layout = inflater.inflate(R.layout.dialog_palette_colors, null)
            layout.colorList.layoutManager = LinearLayoutManager(context)
            layout.colorList.adapter = RecyclerAdapter(itemList)

            // Inflate and set the layout for the dialog
            builder.setView(layout)
            builder.create()

        }?: throw IllegalStateException("Activity cannot be null")
    }
}