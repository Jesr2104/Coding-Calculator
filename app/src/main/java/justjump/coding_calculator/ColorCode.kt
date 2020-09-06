package justjump.coding_calculator

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.skydoves.colorpickerview.ColorPickerDialog
import com.skydoves.colorpickerview.listeners.ColorEnvelopeListener
import justjump.coding_calculator.utilities.ColorDesign
import justjump.coding_calculator.viewmodel.ColorCodeViewModel
import kotlinx.android.synthetic.main.activity_color_code.*


class ColorCode : AppCompatActivity() {

    lateinit var cViewModel: ColorCodeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color_code)

        cViewModel = ViewModelProviders.of(this).get(ColorCodeViewModel::class.java)

        /***************************************************************************/
        // observer
        /***************************************************************************/
        val myObserverColor = Observer<Int> {

            //  Primary color
            primaryColor1.setBackgroundColor(cViewModel.getRGBColor())
            primaryColor2.setBackgroundColor(cViewModel.getRGBColor())
            primaryColor3.setBackgroundColor(cViewModel.getRGBColor())
            primaryColor4.setBackgroundColor(cViewModel.getRGBColor())
            primaryColor5.setBackgroundColor(cViewModel.getRGBColor())
            barColor.setBackgroundColor(cViewModel.getRGBColor())

            loadDataColor(cViewModel.getRGBColor())

            // Complementary color viewer
            complementaryColor.setBackgroundColor(cViewModel.loadComplementaryColor())

            // Split Complementary color viewer
            val splitComplementaryColor = cViewModel.loadSplitComplementaryColor()
            splitComplementary1.setBackgroundColor(splitComplementaryColor[0])
            splitComplementary2.setBackgroundColor(splitComplementaryColor[1])

            // Analogous
            val analogousColor = cViewModel.loadAnalogous()
            analogous1.setBackgroundColor(analogousColor[0])
            analogous2.setBackgroundColor(analogousColor[1])
            analogous3.setBackgroundColor(analogousColor[2])
            analogous4.setBackgroundColor(analogousColor[3])
            analogous5.setBackgroundColor(analogousColor[4])

            // Triadic
            val triadicColor = cViewModel.loadTriadic()
            triadic1.setBackgroundColor(triadicColor[0])
            triadic2.setBackgroundColor(triadicColor[1])

            // Tetradic
            val tetradicColor = cViewModel.loadTetradicColor()
            tetradic1.setBackgroundColor(tetradicColor[0])
            tetradic2.setBackgroundColor(tetradicColor[1])
            tetradic3.setBackgroundColor(tetradicColor[2])
        }

        // this observer works when the expression change
        cViewModel.colorRGB.observe(this@ColorCode, myObserverColor)

        // this event control when you press the color select
        colorPicker.setOnClickListener(View.OnClickListener {
            ColorPickerDialog.Builder(this)
                .setTitle("ColorPicker Dialog")
                .setPreferenceName("MyColorPickerDialog")
                .setPositiveButton("confirm",
                    ColorEnvelopeListener {
                            envelope, fromUser ->
                                cViewModel.setRGBColor(envelope.color)
                    })
                .setNegativeButton("Cancel") {
                        dialogInterface, i -> dialogInterface.dismiss()
                }
                .attachAlphaSlideBar(false) // the default value is true.
                .attachBrightnessSlideBar(true) // the default value is true.
                .setBottomSpace(12) // set a bottom space between the last slidebar and buttons.
                .show()
        })


        configRedColor.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                redValue.text = p1.toString()
                val valueRed = Functions().convertToHex(p1).toString()
                cViewModel.setRGBColor(Color.parseColor("#$valueRed${cViewModel.getGreen()}${cViewModel.getBlue()}"))
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })

        configGreenColor.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                greenValue.text = p1.toString()
                val valueGreen = Functions().convertToHex(p1).toString()
                cViewModel.setRGBColor(Color.parseColor("#${cViewModel.getRed()}$valueGreen${cViewModel.getBlue()}"))
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })

        configBlueColor.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                blueValue.text = p1.toString()
                val valueBlue = Functions().convertToHex(p1).toString()
                cViewModel.setRGBColor(Color.parseColor("#${cViewModel.getRed()}${cViewModel.getGreen()}$valueBlue"))
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })
    }

    fun loadDataColor(rgbColor: Int) {
        val r = Color.red(rgbColor)
        val g = Color.green(rgbColor)
        val b = Color.blue(rgbColor)

        configRedColor.progress = r
        configGreenColor.progress = g
        configBlueColor.progress = b

        rgbfield_red.setText(r.toString())
        rgbfield_green.setText(g.toString())
        rgbfield_blue.setText(b.toString())

        hexfield_red.setText(Functions().convertToHex(r))
        hexfield_green.setText(Functions().convertToHex(g))
        hexfield_blue.setText(Functions().convertToHex(b))

        val colorHSL = ColorDesign().getHSLColorFromRGB(rgbColor)

        hslfield_hue.setText(((colorHSL[0] * 360).toInt()).toString())
        hslfield_saturation.setText(((colorHSL[1] * 100).toInt()).toString())
        hslfield_lightness.setText(((colorHSL[2] * 100).toInt()).toString())


    }
}