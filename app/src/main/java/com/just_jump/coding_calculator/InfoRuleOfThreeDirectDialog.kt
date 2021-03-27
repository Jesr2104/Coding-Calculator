package com.just_jump.coding_calculator

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatDialogFragment
import com.just_jump.coding_calculator.databinding.DialogInfoRuleOfThreeDirectBinding

class InfoRuleOfThreeDirectDialog : AppCompatDialogFragment() {

    private lateinit var binding: DialogInfoRuleOfThreeDirectBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        return activity?.let {
            val builder = AlertDialog.Builder(it)

            // Get the layout inflater
            binding = DialogInfoRuleOfThreeDirectBinding.inflate(layoutInflater)

            builder.setView(binding.root)

                // Add action buttons
                .setNeutralButton("Ok") { dialog, _ ->
                    dialog.dismiss()
                }

            // Inflate and set the layout for the dialog
            builder.create()

        } ?: throw IllegalStateException("Activity cannot be null")
    }
}
