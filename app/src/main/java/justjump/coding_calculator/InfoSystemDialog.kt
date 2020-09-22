package justjump.coding_calculator

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AppCompatDialogFragment


class InfoSystemDialog() : AppCompatDialogFragment() {

    @Override
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        return activity?.let {
            val builder = AlertDialog.Builder(it)
            // Get the layout inflater
            val inflater = requireActivity().layoutInflater;
            val viewDialog = inflater.inflate(R.layout.info_system_color, null)


            builder.setView(viewDialog)

                // Add action buttons
                .setNeutralButton("Ok",
                    DialogInterface.OnClickListener { dialog, id ->
                        // sign in the user ...
                        dialog.dismiss()
                    })

            // Inflate and set the layout for the dialog
            builder.create();

        } ?: throw IllegalStateException("Activity cannot be null")
    }
}