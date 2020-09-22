package justjump.coding_calculator

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.lifecycle.MutableLiveData
import justjump.coding_calculator.utilities.ColorDesign
import justjump.coding_calculator.utilities.Functions
import kotlinx.android.synthetic.main.change_value_color.view.*

class InfoChangeValueColorDialog(var Value: String, var TypeColor: String, var colorRGB: MutableLiveData<Int>): AppCompatDialogFragment() {

    private var rangeA: Int = 0
    private var rangeB: Int = 0
    private var newValue: Int = 0
    private var hexValue: Int = 0

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {

            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            val viewDialog = inflater.inflate(R.layout.change_value_color, null)

            // insert the value previous in the field to change
            viewDialog.editTextReal.setText(Value)

            when(TypeColor){
                "HUE" -> {
                    rangeA = 0
                    rangeB = 359
                    viewDialog.editText.helperText = "Range [0 - 359]"
                }
                "SAT", "LUM" -> {
                    rangeA = 0
                    rangeB = 100
                    viewDialog.editText.helperText = "Range [0 - 100]"
                }
                "HEXR","HEXG","HEXB" ->{
                    rangeA = 0
                    rangeB = 255
                    viewDialog.editText.helperText = "Range [00 - FF]"
                }
                else ->{
                    rangeA = 0
                    rangeB = 255
                    viewDialog.editText.helperText = "Range [0 - 255]"
                }
            }

            // event to control the button to increase the value
            viewDialog.decrease.setOnClickListener {

                if (TypeColor != "HEXR" && TypeColor != "HEXG" && TypeColor != "HEXB"){
                    newValue = viewDialog.editTextReal.text.toString().toInt()
                    if (newValue > rangeA){
                        newValue -= 1
                    }

                    viewDialog.editTextReal.setText(newValue.toString())

                } else{
                    hexValue = Functions().convertHexToDecimal(viewDialog.editTextReal.text.toString())
                    if ( hexValue > rangeA){
                        hexValue -= 1
                    }

                    viewDialog.editTextReal.setText(Functions().convertToHex(hexValue).toString())
                }
            }

            // event to control the button to decrease the value
            viewDialog.increase.setOnClickListener {

                if (TypeColor != "HEXR" && TypeColor != "HEXG" && TypeColor != "HEXB"){
                    newValue = viewDialog.editTextReal.text.toString().toInt()

                    if (newValue < rangeB){
                        newValue += 1
                    }

                    viewDialog.editTextReal.setText(newValue.toString())

                } else{
                    hexValue = Functions().convertHexToDecimal(viewDialog.editTextReal.text.toString())
                    if ( hexValue < rangeB){
                        hexValue += 1
                    }

                    viewDialog.editTextReal.setText(Functions().convertToHex(hexValue).toString())
                }
            }

            builder.setView(viewDialog)

                // Add action buttons
                .setNegativeButton("Cancel",
                    DialogInterface.OnClickListener { dialog, id ->
                    })

                // Add action buttons
                .setPositiveButton("Ok",
                    DialogInterface.OnClickListener { dialog, id ->

                        when (TypeColor){
                            "HUE" ->{
                                val hslFromRGB = ColorDesign().getHSLColorFromRGB(colorRGB.value!!)
                                val newHslColor = FloatArray(3)
                                newHslColor[0] = viewDialog.editTextReal.text.toString().toFloat() / 360
                                newHslColor[1] = hslFromRGB[1]
                                newHslColor[2] = hslFromRGB[2]

                                val rgbFromHSL = ColorDesign().getRGBColorFromHSL(newHslColor)

                                val r = Color.red(rgbFromHSL)
                                val g = Color.green(rgbFromHSL)
                                val b = Color.blue(rgbFromHSL)

                                colorRGB.value = Color.rgb(r,g,b)
                            }
                            "SAT" ->{

                            }
                            "LUM" ->{

                            }
                            "HEXR" ->{
                                val r = Functions().convertHexToDecimal(viewDialog.editTextReal.text.toString())
                                val g = Color.green(colorRGB.value!!)
                                val b = Color.blue(colorRGB.value!!)

                                colorRGB.value = Color.rgb(r,g,b)
                            }
                            "HEXG" ->{
                                val r = Color.red(colorRGB.value!!)
                                val g = Functions().convertHexToDecimal(viewDialog.editTextReal.text.toString())
                                val b = Color.blue(colorRGB.value!!)

                                colorRGB.value = Color.rgb(r,g,b)
                            }
                            "HEXB" ->{
                                val r = Color.red(colorRGB.value!!)
                                val g = Color.green(colorRGB.value!!)
                                val b = Functions().convertHexToDecimal(viewDialog.editTextReal.text.toString())

                                colorRGB.value = Color.rgb(r,g,b)
                            }
                            "RGBR" ->{
                                val g = Color.green(colorRGB.value!!)
                                val b = Color.blue(colorRGB.value!!)

                                colorRGB.value = Color.rgb(viewDialog.editTextReal.text.toString().toInt(),g,b)
                            }
                            "RGBG" ->{
                                val r = Color.red(colorRGB.value!!)
                                val b = Color.blue(colorRGB.value!!)

                                colorRGB.value = Color.rgb(r,viewDialog.editTextReal.text.toString().toInt(),b)
                            }
                            "RGBB" ->{
                                val r = Color.red(colorRGB.value!!)
                                val g = Color.green(colorRGB.value!!)

                                colorRGB.value = Color.rgb(r,g,viewDialog.editTextReal.text.toString().toInt())
                            }
                        }
                    })

            builder.create()
            
        }?: throw IllegalStateException("Activity cannot be null")
    }
}