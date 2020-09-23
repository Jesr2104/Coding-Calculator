package justjump.coding_calculator

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.webkit.WebView
import androidx.appcompat.app.AppCompatDialogFragment

class InfoSystemDialog() : AppCompatDialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        return activity?.let {
            val builder = AlertDialog.Builder(it)
            // Get the layout inflater
            val inflater = requireActivity().layoutInflater;
            val viewDialog = inflater.inflate(R.layout.info_system_color, null)


            val webView = viewDialog.findViewById<WebView>(R.id.myWebView)
            val webSettings = webView.settings
            webSettings.javaScriptEnabled = true
            webView.loadUrl("file:///android_asset/complementary.html")


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