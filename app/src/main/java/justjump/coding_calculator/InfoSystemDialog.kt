package justjump.coding_calculator

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatDialogFragment

class InfoSystemDialog(var InfoColorfor: String) : AppCompatDialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        return activity?.let {
            val builder = AlertDialog.Builder(it)
            // Get the layout inflater
            val inflater = requireActivity().layoutInflater;
            val viewDialog = inflater.inflate(R.layout.info_system_color, null)
            val webView = viewDialog.findViewById<ImageView>(R.id.myWebView)

            when(InfoColorfor){
                "IC" -> {
                    webView.setImageResource(R.drawable.info_complementary_color)
                }
                "ISC" -> {
                    webView.setImageResource(R.drawable.info_split_complementary_color)
                }
                "IA" -> {
                    webView.setImageResource(R.drawable.info_analogous_color)
                }
                "ITR" -> {
                    webView.setImageResource(R.drawable.info_triadic_color)
                }
                "ITE" -> {
                    webView.setImageResource(R.drawable.info_tetradic_color)
                }
            }

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
