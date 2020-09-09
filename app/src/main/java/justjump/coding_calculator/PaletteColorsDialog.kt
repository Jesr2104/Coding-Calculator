package justjump.coding_calculator

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.dialog_palette_colors.view.*
import java.lang.IllegalStateException

class PaletteColorsDialog : AppCompatDialogFragment() {

    @Override
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val itemList: Array<Array<String>> = arrayOf(
            arrayOf("Red","#f44336"),
            arrayOf("Pink","#e91e63"),
            arrayOf("Purple","#9c27b0"),
            arrayOf("Deep Purple","#673ab7"),
            arrayOf("Indigo","#3f51b5"),
            arrayOf("Blue","#2196f3"),
            arrayOf("Light Blue","#03a9f4"),
            arrayOf("Cyan","#00bcd4"),
            arrayOf("Teal","#009688"),
            arrayOf("Green","#4caf50"),
            arrayOf("Light Green","#8bc34a"),
            arrayOf("Lime","#cddc39"),
            arrayOf("Yellow","#ffeb3b"),
            arrayOf("Amber","#ffc107"),
            arrayOf("Orange","#ff9800"),
            arrayOf("Deep Orange","#ff5722"),
            arrayOf("Brown","#795548"),
            arrayOf("Grey","#9e9e9e"),
            arrayOf("Blue Grey","#607d8b")
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