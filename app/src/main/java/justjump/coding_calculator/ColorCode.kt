package justjump.coding_calculator

import android.graphics.Color
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import justjump.coding_calculator.utilities.ColorDesign
import kotlinx.android.synthetic.main.activity_color_code.*

class ColorCode : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color_code)

        var valueRed = "00"
        var valueGreen = "00"
        var valueBlue = "00"

        var valueRedInteger = 0
        var valueGreenInteger = 0
        var valueBlueInteger = 0

        var paint = Paint()
        paint.color = Color.parseColor("#FFDD00")
        println("Color 1 Enviado => #FFDD00")
        println("Resultado => "+ ColorDesign().getComplementary(paint))

        paint.color = Color.parseColor("#186276")
        println("Color 2 Enviado => #186276")
        println("Resultado => "+ ColorDesign().getComplementary(paint))

        paint.color = Color.parseColor("#000000")
        println("Color 3 Enviado => #000000")
        println("Resultado => "+ ColorDesign().getComplementary(paint))

        paint.color = Color.parseColor("#FFFFFF")
        println("Color 4 Enviado => #FFFFFF")
        println("Resultado => "+ ColorDesign().getComplementary(paint))

        paint.color = Color.parseColor("#FFF000")
        println("Color 5 Enviado => #FFF000")
        println("Resultado => "+ ColorDesign().getComplementary(paint))

        paint.color = Color.parseColor("#F0F0F0")
        println("Color 6 Enviado => #F0F0F0")
        println("Resultado => "+ ColorDesign().getComplementary(paint))




        configRedColor.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {

                redValue.text = p1.toString()
                valueRed = Functions().convertToHex(p1).toString()
                valueRedInteger = p1
                barColor.setBackgroundColor(Color.parseColor("#$valueRed$valueGreen$valueBlue"))

                resultRGB.setText("RGB($valueRedInteger,$valueGreenInteger,$valueBlueInteger)")
                resultHEX.setText("#$valueRed$valueGreen$valueBlue")
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })

        configGreenColor.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {

                greenValue.text = p1.toString()
                valueGreen = Functions().convertToHex(p1).toString()
                valueGreenInteger = p1
                barColor.setBackgroundColor(Color.parseColor("#$valueRed$valueGreen$valueBlue"))

                resultRGB.setText("RGB($valueRedInteger,$valueGreenInteger,$valueBlueInteger)")
                resultHEX.setText("#$valueRed$valueGreen$valueBlue")
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })

        configBlueColor.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {

                blueValue.text = p1.toString()
                valueBlue = Functions().convertToHex(p1).toString()
                valueBlueInteger = p1
                barColor.setBackgroundColor(Color.parseColor("#$valueRed$valueGreen$valueBlue"))

                resultRGB.setText("RGB($valueRedInteger,$valueGreenInteger,$valueBlueInteger)")
                resultHEX.setText("#$valueRed$valueGreen$valueBlue")
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })
    }
}