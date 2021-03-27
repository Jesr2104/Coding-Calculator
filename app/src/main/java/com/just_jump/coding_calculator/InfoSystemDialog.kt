package com.just_jump.coding_calculator

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatDialogFragment
import com.just_jump.coding_calculator.databinding.DialogInfoSystemColorBinding

class InfoSystemDialog(private var InfoColorFor: String) : AppCompatDialogFragment() {

    private lateinit var binding: DialogInfoSystemColorBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        return activity?.let {
            val builder = AlertDialog.Builder(it)

            // Get the layout inflater
            binding = DialogInfoSystemColorBinding.inflate(layoutInflater)
            val imageInformation = binding.myInfoImage

            when(InfoColorFor){
                "IC" -> {
                    imageInformation.setImageResource(R.drawable.info_complementary_color)
                }
                "ISC" -> {
                    imageInformation.setImageResource(R.drawable.info_split_complementary_color)
                }
                "IA" -> {
                    imageInformation.setImageResource(R.drawable.info_analogous_color)
                }
                "ITR" -> {
                    imageInformation.setImageResource(R.drawable.info_triadic_color)
                }
                "ITE" -> {
                    imageInformation.setImageResource(R.drawable.info_tetradic_color)
                }
            }

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
