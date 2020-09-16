package justjump.coding_calculator

import android.content.Intent
import android.content.res.ColorStateList
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
import kotlinx.android.synthetic.main.activity_color_code_new.*
import kotlinx.android.synthetic.main.rgb_block.*
import kotlinx.android.synthetic.main.save_colors.*
import kotlinx.android.synthetic.main.sc_analogous_colors.*
import kotlinx.android.synthetic.main.sc_complementary_color.*
import kotlinx.android.synthetic.main.sc_split_complementary_color.*
import kotlinx.android.synthetic.main.sc_tetradic_color.*
import kotlinx.android.synthetic.main.sc_triadic_color.*

class ColorCode : AppCompatActivity() {

    lateinit var cViewModel: ColorCodeViewModel
    private var complementaryColorStore: Int = 0
    private lateinit var splitComplementaryColorStore: Array<Int>
    private lateinit var analogousColorStore: Array<Int>
    private lateinit var triadicColorStore: Array<Int>
    private lateinit var tetradicColorStore: Array<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color_code_new)

        cViewModel = ViewModelProviders.of(this).get(ColorCodeViewModel::class.java)

        /***************************************************************************/
        // observer
        /***************************************************************************/
        val myObserverColor = Observer<Int> {

            //  Primary color
//            primaryColor1.setBackgroundColor(cViewModel.getRGBColor())
//            primaryColor2.setBackgroundColor(cViewModel.getRGBColor())
//            primaryColor3.setBackgroundColor(cViewModel.getRGBColor())
//            primaryColor4.setBackgroundColor(cViewModel.getRGBColor())
//            primaryColor5.setBackgroundColor(cViewModel.getRGBColor())
            layoutHSLText.setBackgroundColor(cViewModel.getRGBColor())

            loadDataColor(cViewModel.getRGBColor())

            // Complementary color viewer
            complementaryColorStore = cViewModel.loadComplementaryColor()
            Color1_Complementary.setBackgroundColor(complementaryColorStore)

            // Split Complementary color viewer
            splitComplementaryColorStore = cViewModel.loadSplitComplementaryColor()
            SplitCC_Color1.setBackgroundColor(splitComplementaryColorStore[0])
            SplitCC_Color2.setBackgroundColor(splitComplementaryColorStore[1])

            // Analogous
            analogousColorStore = cViewModel.loadAnalogous()
            Analogous_Color1.setBackgroundColor(analogousColorStore[0])
            Analogous_Color2.setBackgroundColor(analogousColorStore[1])
            Analogous_Color3.setBackgroundColor(analogousColorStore[2])
            Analogous_Color4.setBackgroundColor(analogousColorStore[3])
            Analogous_Color5.setBackgroundColor(analogousColorStore[4])

            // Triadic
            triadicColorStore = cViewModel.loadTriadic()
            triadic_Color1.setBackgroundColor(triadicColorStore[0])
            triadic_Color2.setBackgroundColor(triadicColorStore[1])

            // Tetradic
            tetradicColorStore = cViewModel.loadTetradicColor()
            tetradic_Color1.setBackgroundColor(tetradicColorStore[0])
            tetradic_Color2.setBackgroundColor(tetradicColorStore[1])
            tetradic_Color3.setBackgroundColor(tetradicColorStore[2])

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
        layoutHSLText.setOnClickListener(View.OnClickListener {
            val newFragment = InfoColorDialog(this.applicationContext,cViewModel.getRGBColor(),cViewModel)
            newFragment.show(supportFragmentManager, "infoColor")
        })

//        primaryColor1.setOnClickListener(View.OnClickListener {
//            val newFragment = InfoColorDialog(this.applicationContext, cViewModel.getRGBColor(), cViewModel)
//            newFragment.show(supportFragmentManager, "infoColor")
//        })
//
//        primaryColor2.setOnClickListener(View.OnClickListener {
//            val newFragment = InfoColorDialog(this.applicationContext, cViewModel.getRGBColor(), cViewModel)
//            newFragment.show(supportFragmentManager, "infoColor")
//        })
//
//        primaryColor3.setOnClickListener(View.OnClickListener {
//            val newFragment = InfoColorDialog(this.applicationContext, cViewModel.getRGBColor(), cViewModel)
//            newFragment.show(supportFragmentManager, "infoColor")
//        })
//
//        primaryColor4.setOnClickListener(View.OnClickListener {
//            val newFragment = InfoColorDialog(this.applicationContext, cViewModel.getRGBColor(), cViewModel)
//            newFragment.show(supportFragmentManager, "infoColor")
//        })
//
//        primaryColor5.setOnClickListener(View.OnClickListener {
//            val newFragment = InfoColorDialog(this.applicationContext, cViewModel.getRGBColor(), cViewModel)
//            newFragment.show(supportFragmentManager, "infoColor")
//        })

        Color1_Complementary.setOnClickListener(View.OnClickListener {
            val newFragment = InfoColorDialog(this.applicationContext, complementaryColorStore, cViewModel)
            newFragment.show(supportFragmentManager, "infoColor")
        })

        SplitCC_Color1.setOnClickListener(View.OnClickListener {
            val newFragment = InfoColorDialog(this.applicationContext, splitComplementaryColorStore[0], cViewModel)
            newFragment.show(supportFragmentManager, "infoColor")
        })

        SplitCC_Color2.setOnClickListener(View.OnClickListener {
            val newFragment = InfoColorDialog(this.applicationContext, splitComplementaryColorStore[1], cViewModel)
            newFragment.show(supportFragmentManager, "infoColor")
        })

        Analogous_Color1.setOnClickListener(View.OnClickListener {
            val newFragment = InfoColorDialog(this.applicationContext, analogousColorStore[0], cViewModel)
            newFragment.show(supportFragmentManager, "infoColor")
        })

        Analogous_Color2.setOnClickListener(View.OnClickListener {
            val newFragment = InfoColorDialog(this.applicationContext, analogousColorStore[1], cViewModel)
            newFragment.show(supportFragmentManager, "infoColor")
        })

        Analogous_Color3.setOnClickListener(View.OnClickListener {
            val newFragment = InfoColorDialog(this.applicationContext, analogousColorStore[2], cViewModel)
            newFragment.show(supportFragmentManager, "infoColor")
        })

        Analogous_Color4.setOnClickListener(View.OnClickListener {
            val newFragment = InfoColorDialog(this.applicationContext, analogousColorStore[3], cViewModel)
            newFragment.show(supportFragmentManager, "infoColor")
        })

        Analogous_Color5.setOnClickListener(View.OnClickListener {
            val newFragment = InfoColorDialog(this.applicationContext, analogousColorStore[4], cViewModel)
            newFragment.show(supportFragmentManager, "infoColor")
        })

        triadic_Color1.setOnClickListener(View.OnClickListener {
            val newFragment = InfoColorDialog(this.applicationContext, triadicColorStore[0], cViewModel)
            newFragment.show(supportFragmentManager, "infoColor")
        })

        triadic_Color2.setOnClickListener(View.OnClickListener {
            val newFragment = InfoColorDialog(this.applicationContext, triadicColorStore[1], cViewModel)
            newFragment.show(supportFragmentManager, "infoColor")
        })

        tetradic_Color1.setOnClickListener(View.OnClickListener {
            val newFragment = InfoColorDialog(this.applicationContext, tetradicColorStore[0], cViewModel)
            newFragment.show(supportFragmentManager, "infoColor")
        })

        tetradic_Color2.setOnClickListener(View.OnClickListener {
            val newFragment = InfoColorDialog(this.applicationContext, tetradicColorStore[1], cViewModel)
            newFragment.show(supportFragmentManager, "infoColor")
        })

        tetradic_Color3.setOnClickListener(View.OnClickListener {
            val newFragment = InfoColorDialog(this.applicationContext, tetradicColorStore[2], cViewModel)
            newFragment.show(supportFragmentManager, "infoColor")
        })
        //------------------------------------------------------------------------------------------

        // Dialog with the saved color information
        //------------------------------------------------------------------------------------------
        buttonColor1.setOnClickListener(View.OnClickListener {
            val newFragment = InfoColorSavedDialog(this.applicationContext, SRDataColors.getItem(0), cViewModel)
            newFragment.show(supportFragmentManager, "infoColor")
        })

        buttonColor2.setOnClickListener(View.OnClickListener {
            val newFragment = InfoColorSavedDialog(this.applicationContext, SRDataColors.getItem(1), cViewModel)
            newFragment.show(supportFragmentManager, "infoColor")
        })

        buttonColor3.setOnClickListener(View.OnClickListener {
            val newFragment = InfoColorSavedDialog(this.applicationContext, SRDataColors.getItem(2), cViewModel)
            newFragment.show(supportFragmentManager, "infoColor")
        })

//        savedcolor4.setOnClickListener(View.OnClickListener {
//            val newFragment = InfoColorSavedDialog(this.applicationContext, SRDataColors.getItem(3), cViewModel)
//            newFragment.show(supportFragmentManager, "infoColor")
//        })
//
//        savedcolor5.setOnClickListener(View.OnClickListener {
//            val newFragment = InfoColorSavedDialog(this.applicationContext, SRDataColors.getItem(4), cViewModel)
//            newFragment.show(supportFragmentManager, "infoColor")
//        })
//
//        savedcolor6.setOnClickListener(View.OnClickListener {
//            val newFragment = InfoColorSavedDialog(this.applicationContext, SRDataColors.getItem(5), cViewModel)
//            newFragment.show(supportFragmentManager, "infoColor")
//        })
//
//        savedcolor7.setOnClickListener(View.OnClickListener {
//            val newFragment = InfoColorSavedDialog(this.applicationContext, SRDataColors.getItem(6), cViewModel)
//            newFragment.show(supportFragmentManager, "infoColor")
//        })
//
//        savedcolor8.setOnClickListener(View.OnClickListener {
//            val newFragment = InfoColorSavedDialog(this.applicationContext, SRDataColors.getItem(7), cViewModel)
//            newFragment.show(supportFragmentManager, "infoColor")
//        })
//
//        savedcolor9.setOnClickListener(View.OnClickListener {
//            val newFragment = InfoColorSavedDialog(this.applicationContext, SRDataColors.getItem(8), cViewModel)
//            newFragment.show(supportFragmentManager, "infoColor")
//        })
//
//        savedcolor10.setOnClickListener(View.OnClickListener {
//            val newFragment = InfoColorSavedDialog(this.applicationContext, SRDataColors.getItem(9), cViewModel)
//            newFragment.show(supportFragmentManager, "infoColor")
//        })
//
//        savedcolor11.setOnClickListener(View.OnClickListener {
//            val newFragment = InfoColorSavedDialog(this.applicationContext, SRDataColors.getItem(10), cViewModel)
//            newFragment.show(supportFragmentManager, "infoColor")
//        })
//
//        savedcolor12.setOnClickListener(View.OnClickListener {
//            val newFragment = InfoColorSavedDialog(this.applicationContext, SRDataColors.getItem(11), cViewModel)
//            newFragment.show(supportFragmentManager, "infoColor")
//        })
        //------------------------------------------------------------------------------------------

        // event to control the new palette colors list
        paletteColors.setOnClickListener(View.OnClickListener {
            val paletteColors = Intent(this, PaletteColors::class.java)
            paletteColors.putExtra("TAG", 1)
            startActivity(paletteColors)
        })

        // Event of the seekbar of the RGB controls
        controlbarforred.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
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

        controlbarforgreen.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
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

        controlbarforblue.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
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

        controlbarforred.progress = r
        controlbarforgreen.progress = g
        controlbarforblue.progress = b

//        rgbfield_red.setText(r.toString())
//        rgbfield_green.setText(g.toString())
//        rgbfield_blue.setText(b.toString())

//        hexfield_red.setText(Functions().convertToHex(r))
//        hexfield_green.setText(Functions().convertToHex(g))
//        hexfield_blue.setText(Functions().convertToHex(b))

        val colorHSL = ColorDesign().getHSLColorFromRGB(rgbColor)

//        hslfield_hue.setText(((colorHSL[0] * 360).toInt()).toString())
//        hslfield_saturation.setText(((colorHSL[1] * 100).toInt()).toString())
//        hslfield_lightness.setText(((colorHSL[2] * 100).toInt()).toString())
    }

    private fun updateColor() {
        val data = SRDataColors.customPreference(this).getlist()

        if (data.size != 0) {
            if (data.size + 1 > 1) {
                buttonColor1.visibility = View.VISIBLE
                buttonColor1.background.setTint(data[0])
            }
            else
                buttonColor1.visibility = View.INVISIBLE

            if (data.size + 1 > 2){
                buttonColor2.visibility = View.VISIBLE
                buttonColor2.background.setTint(data[1])
            }
            else
                buttonColor2.visibility = View.INVISIBLE

            if (data.size + 1 > 3){
                buttonColor3.visibility = View.VISIBLE
                buttonColor3.background.setTint(data[2])
            }
            else
                buttonColor3.visibility = View.INVISIBLE

//            if (data.size + 1 > 4){
//                savedcolor4.visibility = View.VISIBLE
//                savedcolor4.setBackgroundColor(data[3])
//            }
//            else
//                savedcolor4.visibility = View.INVISIBLE
//
//            if (data.size + 1 > 5){
//                savedcolor5.visibility = View.VISIBLE
//                savedcolor5.setBackgroundColor(data[4])
//            }
//            else
//                savedcolor5.visibility = View.INVISIBLE
//
//            if (data.size + 1 > 6){
//                savedcolor6.visibility = View.VISIBLE
//                savedcolor6.setBackgroundColor(data[5])
//            }
//            else
//                savedcolor6.visibility = View.INVISIBLE
//
//            if (data.size + 1 > 7){
//                savedcolor7.visibility = View.VISIBLE
//                savedcolor7.setBackgroundColor(data[6])
//            }
//            else
//                savedcolor7.visibility = View.INVISIBLE
//
//            if (data.size + 1 > 8){
//                savedcolor8.visibility = View.VISIBLE
//                savedcolor8.setBackgroundColor(data[7])
//            }
//            else
//                savedcolor8.visibility = View.INVISIBLE
//
//            if (data.size + 1 > 9){
//                savedcolor9.visibility = View.VISIBLE
//                savedcolor9.setBackgroundColor(data[8])
//            }
//            else
//                savedcolor9.visibility = View.INVISIBLE
//
//            if (data.size + 1 > 10){
//                savedcolor10.visibility = View.VISIBLE
//                savedcolor10.setBackgroundColor(data[9])
//            }
//            else
//                savedcolor10.visibility = View.INVISIBLE
//
//            if (data.size + 1 > 11){
//                savedcolor11.visibility = View.VISIBLE
//                savedcolor11.setBackgroundColor(data[10])
//            }
//            else
//                savedcolor11.visibility = View.INVISIBLE
//
//            if (data.size + 1 > 12){
//                savedcolor12.visibility = View.VISIBLE
//                savedcolor12.setBackgroundColor(data[11])
//            }
//            else
//                savedcolor12.visibility = View.INVISIBLE
        }else{
            buttonColor1.visibility = View.INVISIBLE
        }
    }
}