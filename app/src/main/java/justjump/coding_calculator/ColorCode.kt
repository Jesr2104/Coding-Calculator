package justjump.coding_calculator

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.skydoves.colorpickerview.ColorPickerDialog
import com.skydoves.colorpickerview.listeners.ColorEnvelopeListener
import justjump.coding_calculator.data.local.SRDataColors
import justjump.coding_calculator.utilities.ColorDesign
import justjump.coding_calculator.utilities.Functions
import justjump.coding_calculator.viewmodel.ColorCodeViewModel
import kotlinx.android.synthetic.main.activity_color_code.*

class ColorCode : AppCompatActivity() {

    lateinit var cViewModel: ColorCodeViewModel
    private var complementaryColorStore: Int = 0
    private lateinit var splitComplementaryColorStore: Array<Int>
    private lateinit var analogousColorStore: Array<Int>
    private lateinit var triadicColorStore: Array<Int>
    private lateinit var tetradicColorStore: Array<Int>

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
            complementaryColorStore = cViewModel.loadComplementaryColor()
            complementaryColor.setBackgroundColor(complementaryColorStore)

            // Split Complementary color viewer
            splitComplementaryColorStore = cViewModel.loadSplitComplementaryColor()
            splitComplementary1.setBackgroundColor(splitComplementaryColorStore[0])
            splitComplementary2.setBackgroundColor(splitComplementaryColorStore[1])

            // Analogous
            analogousColorStore = cViewModel.loadAnalogous()
            analogous1.setBackgroundColor(analogousColorStore[0])
            analogous2.setBackgroundColor(analogousColorStore[1])
            analogous3.setBackgroundColor(analogousColorStore[2])
            analogous4.setBackgroundColor(analogousColorStore[3])
            analogous5.setBackgroundColor(analogousColorStore[4])

            // Triadic
            triadicColorStore = cViewModel.loadTriadic()
            triadic1.setBackgroundColor(triadicColorStore[0])
            triadic2.setBackgroundColor(triadicColorStore[1])

            // Tetradic
            tetradicColorStore = cViewModel.loadTetradicColor()
            tetradic1.setBackgroundColor(tetradicColorStore[0])
            tetradic2.setBackgroundColor(tetradicColorStore[1])
            tetradic3.setBackgroundColor(tetradicColorStore[2])

            // this function need to be update and implement more clear
            updateColor()
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

        // Dialog with the color information
        //------------------------------------------------------------------------------------------
        barColor.setOnClickListener(View.OnClickListener {
            val newFragment = InfoColorDialog(this.applicationContext,cViewModel.getRGBColor(),cViewModel)
            newFragment.show(supportFragmentManager, "infoColor")
        })

        primaryColor1.setOnClickListener(View.OnClickListener {
            val newFragment = InfoColorDialog(this.applicationContext, cViewModel.getRGBColor(), cViewModel)
            newFragment.show(supportFragmentManager, "infoColor")
        })

        primaryColor2.setOnClickListener(View.OnClickListener {
            val newFragment = InfoColorDialog(this.applicationContext, cViewModel.getRGBColor(), cViewModel)
            newFragment.show(supportFragmentManager, "infoColor")
        })

        primaryColor3.setOnClickListener(View.OnClickListener {
            val newFragment = InfoColorDialog(this.applicationContext, cViewModel.getRGBColor(), cViewModel)
            newFragment.show(supportFragmentManager, "infoColor")
        })

        primaryColor4.setOnClickListener(View.OnClickListener {
            val newFragment = InfoColorDialog(this.applicationContext, cViewModel.getRGBColor(), cViewModel)
            newFragment.show(supportFragmentManager, "infoColor")
        })

        primaryColor5.setOnClickListener(View.OnClickListener {
            val newFragment = InfoColorDialog(this.applicationContext, cViewModel.getRGBColor(), cViewModel)
            newFragment.show(supportFragmentManager, "infoColor")
        })

        complementaryColor.setOnClickListener(View.OnClickListener {
            val newFragment = InfoColorDialog(this.applicationContext, complementaryColorStore, cViewModel)
            newFragment.show(supportFragmentManager, "infoColor")
        })

        splitComplementary1.setOnClickListener(View.OnClickListener {
            val newFragment = InfoColorDialog(this.applicationContext, splitComplementaryColorStore[0], cViewModel)
            newFragment.show(supportFragmentManager, "infoColor")
        })

        splitComplementary2.setOnClickListener(View.OnClickListener {
            val newFragment = InfoColorDialog(this.applicationContext, splitComplementaryColorStore[1], cViewModel)
            newFragment.show(supportFragmentManager, "infoColor")
        })

        analogous1.setOnClickListener(View.OnClickListener {
            val newFragment = InfoColorDialog(this.applicationContext, analogousColorStore[0], cViewModel)
            newFragment.show(supportFragmentManager, "infoColor")
        })

        analogous2.setOnClickListener(View.OnClickListener {
            val newFragment = InfoColorDialog(this.applicationContext, analogousColorStore[1], cViewModel)
            newFragment.show(supportFragmentManager, "infoColor")
        })

        analogous3.setOnClickListener(View.OnClickListener {
            val newFragment = InfoColorDialog(this.applicationContext, analogousColorStore[2], cViewModel)
            newFragment.show(supportFragmentManager, "infoColor")
        })

        analogous4.setOnClickListener(View.OnClickListener {
            val newFragment = InfoColorDialog(this.applicationContext, analogousColorStore[3], cViewModel)
            newFragment.show(supportFragmentManager, "infoColor")
        })

        analogous5.setOnClickListener(View.OnClickListener {
            val newFragment = InfoColorDialog(this.applicationContext, analogousColorStore[4], cViewModel)
            newFragment.show(supportFragmentManager, "infoColor")
        })

        triadic1.setOnClickListener(View.OnClickListener {
            val newFragment = InfoColorDialog(this.applicationContext, triadicColorStore[0], cViewModel)
            newFragment.show(supportFragmentManager, "infoColor")
        })

        triadic2.setOnClickListener(View.OnClickListener {
            val newFragment = InfoColorDialog(this.applicationContext, triadicColorStore[1], cViewModel)
            newFragment.show(supportFragmentManager, "infoColor")
        })

        tetradic1.setOnClickListener(View.OnClickListener {
            val newFragment = InfoColorDialog(this.applicationContext, tetradicColorStore[0], cViewModel)
            newFragment.show(supportFragmentManager, "infoColor")
        })

        tetradic2.setOnClickListener(View.OnClickListener {
            val newFragment = InfoColorDialog(this.applicationContext, tetradicColorStore[1], cViewModel)
            newFragment.show(supportFragmentManager, "infoColor")
        })

        tetradic3.setOnClickListener(View.OnClickListener {
            val newFragment = InfoColorDialog(this.applicationContext, tetradicColorStore[2], cViewModel)
            newFragment.show(supportFragmentManager, "infoColor")
        })
        //------------------------------------------------------------------------------------------

        // Dialog with the saved color information
        //------------------------------------------------------------------------------------------
        savedcolor1.setOnClickListener(View.OnClickListener {
            val newFragment = InfoColorSavedDialog(this.applicationContext, SRDataColors.getItem(0), cViewModel)
            newFragment.show(supportFragmentManager, "infoColor")
        })

        savedcolor2.setOnClickListener(View.OnClickListener {
            val newFragment = InfoColorSavedDialog(this.applicationContext, SRDataColors.getItem(1), cViewModel)
            newFragment.show(supportFragmentManager, "infoColor")
        })

        savedcolor3.setOnClickListener(View.OnClickListener {
            val newFragment = InfoColorSavedDialog(this.applicationContext, SRDataColors.getItem(2), cViewModel)
            newFragment.show(supportFragmentManager, "infoColor")
        })

        savedcolor4.setOnClickListener(View.OnClickListener {
            val newFragment = InfoColorSavedDialog(this.applicationContext, SRDataColors.getItem(3), cViewModel)
            newFragment.show(supportFragmentManager, "infoColor")
        })

        savedcolor5.setOnClickListener(View.OnClickListener {
            val newFragment = InfoColorSavedDialog(this.applicationContext, SRDataColors.getItem(4), cViewModel)
            newFragment.show(supportFragmentManager, "infoColor")
        })

        savedcolor6.setOnClickListener(View.OnClickListener {
            val newFragment = InfoColorSavedDialog(this.applicationContext, SRDataColors.getItem(5), cViewModel)
            newFragment.show(supportFragmentManager, "infoColor")
        })

        savedcolor7.setOnClickListener(View.OnClickListener {
            val newFragment = InfoColorSavedDialog(this.applicationContext, SRDataColors.getItem(6), cViewModel)
            newFragment.show(supportFragmentManager, "infoColor")
        })

        savedcolor8.setOnClickListener(View.OnClickListener {
            val newFragment = InfoColorSavedDialog(this.applicationContext, SRDataColors.getItem(7), cViewModel)
            newFragment.show(supportFragmentManager, "infoColor")
        })

        savedcolor9.setOnClickListener(View.OnClickListener {
            val newFragment = InfoColorSavedDialog(this.applicationContext, SRDataColors.getItem(8), cViewModel)
            newFragment.show(supportFragmentManager, "infoColor")
        })

        savedcolor10.setOnClickListener(View.OnClickListener {
            val newFragment = InfoColorSavedDialog(this.applicationContext, SRDataColors.getItem(9), cViewModel)
            newFragment.show(supportFragmentManager, "infoColor")
        })

        savedcolor11.setOnClickListener(View.OnClickListener {
            val newFragment = InfoColorSavedDialog(this.applicationContext, SRDataColors.getItem(10), cViewModel)
            newFragment.show(supportFragmentManager, "infoColor")
        })

        savedcolor12.setOnClickListener(View.OnClickListener {
            val newFragment = InfoColorSavedDialog(this.applicationContext, SRDataColors.getItem(11), cViewModel)
            newFragment.show(supportFragmentManager, "infoColor")
        })
        //------------------------------------------------------------------------------------------

        // event to control the new palette colors list
        paletteColorsNew.setOnClickListener(View.OnClickListener {
            val paletteColors = Intent(this, PaletteColors::class.java)
            paletteColors.putExtra("TAG", 1)
            startActivity(paletteColors)
        })

        // Event of the seekbar of the RGB controls
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

    private fun loadDataColor(rgbColor: Int) {
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

    private fun updateColor() {
        val data = SRDataColors.customPreference(this).getlist()

        if (data.size != 0) {
            if (data.size + 1 > 1) {
                savedcolor1.visibility = View.VISIBLE
                savedcolor1.setBackgroundColor(data[0])
            }
            else
                savedcolor1.visibility = View.INVISIBLE

            if (data.size + 1 > 2){
                savedcolor2.visibility = View.VISIBLE
                savedcolor2.setBackgroundColor(data[1])
            }
            else
                savedcolor2.visibility = View.INVISIBLE

            if (data.size + 1 > 3){
                savedcolor3.visibility = View.VISIBLE
                savedcolor3.setBackgroundColor(data[2])
            }
            else
                savedcolor3.visibility = View.INVISIBLE

            if (data.size + 1 > 4){
                savedcolor4.visibility = View.VISIBLE
                savedcolor4.setBackgroundColor(data[3])
            }
            else
                savedcolor4.visibility = View.INVISIBLE

            if (data.size + 1 > 5){
                savedcolor5.visibility = View.VISIBLE
                savedcolor5.setBackgroundColor(data[4])
            }
            else
                savedcolor5.visibility = View.INVISIBLE

            if (data.size + 1 > 6){
                savedcolor6.visibility = View.VISIBLE
                savedcolor6.setBackgroundColor(data[5])
            }
            else
                savedcolor6.visibility = View.INVISIBLE

            if (data.size + 1 > 7){
                savedcolor7.visibility = View.VISIBLE
                savedcolor7.setBackgroundColor(data[6])
            }
            else
                savedcolor7.visibility = View.INVISIBLE

            if (data.size + 1 > 8){
                savedcolor8.visibility = View.VISIBLE
                savedcolor8.setBackgroundColor(data[7])
            }
            else
                savedcolor8.visibility = View.INVISIBLE

            if (data.size + 1 > 9){
                savedcolor9.visibility = View.VISIBLE
                savedcolor9.setBackgroundColor(data[8])
            }
            else
                savedcolor9.visibility = View.INVISIBLE

            if (data.size + 1 > 10){
                savedcolor10.visibility = View.VISIBLE
                savedcolor10.setBackgroundColor(data[9])
            }
            else
                savedcolor10.visibility = View.INVISIBLE

            if (data.size + 1 > 11){
                savedcolor11.visibility = View.VISIBLE
                savedcolor11.setBackgroundColor(data[10])
            }
            else
                savedcolor11.visibility = View.INVISIBLE

            if (data.size + 1 > 12){
                savedcolor12.visibility = View.VISIBLE
                savedcolor12.setBackgroundColor(data[11])
            }
            else
                savedcolor12.visibility = View.INVISIBLE
        }else{
            savedcolor1.visibility = View.INVISIBLE
        }
    }
}