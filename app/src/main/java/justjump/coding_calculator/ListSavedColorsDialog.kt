package justjump.coding_calculator

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import justjump.coding_calculator.data.local.SRDataColors
import kotlinx.android.synthetic.main.list_saved_colors.view.*

class ListSavedColorsDialog(contextA: Context, colorRGB: MutableLiveData<Int>): AppCompatDialogFragment() {
    private val newContext = contextA
    private val newColorRGB = colorRGB

    @Override
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater;
            val layout = inflater.inflate(R.layout.list_saved_colors, null)

            val data = SRDataColors.customPreference(newContext).getlist()

            layout.recycleViewFields.layoutManager = LinearLayoutManager(context)
            layout.recycleViewFields.adapter = RecycleAdapterSavedColors(data,newColorRGB)

            // Inflate and set the layout for the dialog
            builder.setView(layout)

             //Add action buttons
            .setNeutralButton("Ok") { dialog, id ->
                dialog.dismiss()
            }

            builder.create()

        }?: throw IllegalStateException("Activity cannot be null")
    }
}