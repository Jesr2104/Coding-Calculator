package justjump.coding_calculator

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.lifecycle.MutableLiveData
import justjump.coding_calculator.utilities.Functions
import kotlinx.android.synthetic.main.dialog_change_value_color.*
import kotlinx.android.synthetic.main.dialog_change_value_color.view.*

class InfoChangeValueColorDialog(var Value: String, var TypeColor: String, var colorRGB: MutableLiveData<Int>): AppCompatDialogFragment() {

    private var rangeA: Int = 0
    private var rangeB: Int = 0
    private var newValue: Int = 0
    private var hexValue: Int = 0
    var mainHandler: Handler? = null

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {

            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            val viewDialog = inflater.inflate(R.layout.dialog_change_value_color, null)

            // insert the value previous in the field to change
            viewDialog.editTextReal.setText(Value)

            when(TypeColor){
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

            // action to do when you keeping press the button
            val actionToIncrease = object : Runnable {
                override fun run() {
                    if (TypeColor != "HEXR" && TypeColor != "HEXG" && TypeColor != "HEXB"){
                        newValue = viewDialog.editTextReal.text.toString().toInt()
                        if (newValue > rangeA){
                            newValue -= 1
                        }

                        viewDialog.editTextReal.setText(newValue.toString())

                    } else{
                        hexValue = Functions().convertHexToDecimal(viewDialog.editTextReal.text.toString()).toInt()
                        if ( hexValue > rangeA){
                            hexValue -= 1
                        }

                        viewDialog.editTextReal.setText(Functions().convertDecToHex(hexValue))
                    }
                    mainHandler?.postDelayed(this, 70)
                }
            }

            // action to do when you keeping press the button
            val actionToDecrease = object : Runnable {
                override fun run() {
                    if (TypeColor != "HEXR" && TypeColor != "HEXG" && TypeColor != "HEXB"){
                        newValue = viewDialog.editTextReal.text.toString().toInt()

                        if (newValue < rangeB){
                            newValue += 1
                        }

                        viewDialog.editTextReal.setText(newValue.toString())

                    } else{
                        hexValue = Functions().convertHexToDecimal(viewDialog.editTextReal.text.toString()).toInt()
                        if ( hexValue < rangeB){
                            hexValue += 1
                        }

                        viewDialog.editTextReal.setText(Functions().convertDecToHex(hexValue))
                    }
                    mainHandler?.postDelayed(this, 70)
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
                    hexValue = Functions().convertHexToDecimal(viewDialog.editTextReal.text.toString()).toInt()
                    if ( hexValue > rangeA){
                        hexValue -= 1
                    }

                    viewDialog.editTextReal.setText(Functions().convertDecToHex(hexValue))
                }
            }

            // event to control the increase the value fast
            viewDialog.decrease.setOnTouchListener { view, motionEvent ->
                when (motionEvent.action) {
                    MotionEvent.ACTION_DOWN -> {
                        if (mainHandler != null)
                            true
                        mainHandler = Handler()
                        mainHandler?.postDelayed(actionToIncrease, 200)
                        false
                    }
                    MotionEvent.ACTION_UP -> {
                        if (mainHandler == null)
                            true
                        mainHandler?.removeCallbacks(actionToIncrease)
                        mainHandler = null
                        false
                    }
                    else -> false
                }
            }

            // event to control the increase the value one by one
            viewDialog.increase.setOnClickListener {

                if (TypeColor != "HEXR" && TypeColor != "HEXG" && TypeColor != "HEXB"){
                    newValue = viewDialog.editTextReal.text.toString().toInt()

                    if (newValue < rangeB){
                        newValue += 1
                    }

                    viewDialog.editTextReal.setText(newValue.toString())

                } else{
                    hexValue = Functions().convertHexToDecimal(viewDialog.editTextReal.text.toString()).toInt()
                    if ( hexValue < rangeB){
                        hexValue += 1
                    }

                    viewDialog.editTextReal.setText(Functions().convertDecToHex(hexValue))
                }
            }

            // event to control the increase the value fast
            viewDialog.increase.setOnTouchListener { view, motionEvent ->
                when (motionEvent.action) {
                    MotionEvent.ACTION_DOWN -> {
                        if (mainHandler != null)
                            true
                        mainHandler = Handler()
                        mainHandler?.postDelayed(actionToDecrease, 200)
                        false
                    }
                    MotionEvent.ACTION_UP -> {
                        if (mainHandler == null)
                            true
                        mainHandler?.removeCallbacks(actionToDecrease)
                        mainHandler = null
                        false
                    }
                    else -> false
                }
            }

            // to control user typed text
            viewDialog.editTextReal.addTextChangedListener(object: TextWatcher{

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    // en esta parte tengo que solucionar que nose inserten numero
                    // mayores nu menores y que si se insertan cambialo por el mayor y por el menor
                }
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun afterTextChanged(p0: Editable?) {}

            })

            builder.setView(viewDialog)

                // Add action buttons
                .setNegativeButton("Cancel",
                    DialogInterface.OnClickListener { dialog, id ->
                    })

                // Add action buttons
                .setPositiveButton("Ok",
                    DialogInterface.OnClickListener { dialog, id ->

                        when (TypeColor){
                            "HEXR" ->{
                                if(viewDialog.editTextReal.text.toString().isNotEmpty()){
                                    val r = Functions().convertHexToDecimal(viewDialog.editTextReal.text.toString()).toInt()
                                    val g = Color.green(colorRGB.value!!)
                                    val b = Color.blue(colorRGB.value!!)

                                    colorRGB.value = Color.rgb(r,g,b)
                                }
                            }
                            "HEXG" ->{
                                if(viewDialog.editTextReal.text.toString().isNotEmpty()){
                                    val r = Color.red(colorRGB.value!!)
                                    val g = Functions().convertHexToDecimal(viewDialog.editTextReal.text.toString()).toInt()
                                    val b = Color.blue(colorRGB.value!!)

                                    colorRGB.value = Color.rgb(r,g,b)
                                }
                            }
                            "HEXB" ->{
                                if(viewDialog.editTextReal.text.toString().isNotEmpty()){
                                    val r = Color.red(colorRGB.value!!)
                                    val g = Color.green(colorRGB.value!!)
                                    val b = Functions().convertHexToDecimal(viewDialog.editTextReal.text.toString()).toInt()

                                    colorRGB.value = Color.rgb(r,g,b)
                                }
                            }
                            "RGBR" ->{
                                if(viewDialog.editTextReal.text.toString().isNotEmpty()){
                                    val g = Color.green(colorRGB.value!!)
                                    val b = Color.blue(colorRGB.value!!)

                                    colorRGB.value = Color.rgb(viewDialog.editTextReal.text.toString().toInt(),g,b)
                                }
                            }
                            "RGBG" ->{
                                if(viewDialog.editTextReal.text.toString().isNotEmpty()){
                                    val r = Color.red(colorRGB.value!!)
                                    val b = Color.blue(colorRGB.value!!)

                                    colorRGB.value = Color.rgb(r,viewDialog.editTextReal.text.toString().toInt(),b)
                                }
                            }
                            "RGBB" ->{
                                if(viewDialog.editTextReal.text.toString().isNotEmpty()){
                                    val r = Color.red(colorRGB.value!!)
                                    val g = Color.green(colorRGB.value!!)

                                    colorRGB.value = Color.rgb(r,g,viewDialog.editTextReal.text.toString().toInt())
                                }
                            }
                        }
                    })

            builder.create()
            
        }?: throw IllegalStateException("Activity cannot be null")
    }
}