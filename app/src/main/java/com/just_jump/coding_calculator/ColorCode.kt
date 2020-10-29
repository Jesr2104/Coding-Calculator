package com.just_jump.coding_calculator

import android.content.Intent
import android.content.pm.ActivityInfo.*
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.skydoves.colorpickerview.ColorPickerDialog
import com.skydoves.colorpickerview.listeners.ColorEnvelopeListener
import com.just_jump.coding_calculator.data.local.SRDataColors
import com.just_jump.coding_calculator.utilities.ColorDesign
import com.just_jump.coding_calculator.utilities.Functions
import com.just_jump.coding_calculator.viewmodel.ViewModelColorCode
import kotlinx.android.synthetic.main.activity_colorcode.*

class ColorCode : AppCompatActivity(){

    lateinit var cViewModel: ViewModelColorCode
    private var complementaryColorStore: Int = 0
    private lateinit var splitComplementaryColorStore: Array<Int>
    private lateinit var analogousColorStore: Array<Int>
    private lateinit var triadicColorStore: Array<Int>
    private lateinit var tetradicColorStore: Array<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_colorcode)

        cViewModel = ViewModelProviders.of(this).get(ViewModelColorCode::class.java)
        requestedOrientation = SCREEN_ORIENTATION_PORTRAIT

        /***************************************************************************/
        // observer
        /***************************************************************************/
        val myObserverColor = Observer<Int> {

            //  Primary color
            mainColor.setBackgroundColor(cViewModel.getRGBColor())

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
            Triadic_Color1.setBackgroundColor(triadicColorStore[0])
            Triadic_Color2.setBackgroundColor(triadicColorStore[1])

            // Tetradic
            tetradicColorStore = cViewModel.loadTetradicColor()
            Tetradic_Color1.setBackgroundColor(tetradicColorStore[0])
            Tetradic_Color2.setBackgroundColor(tetradicColorStore[1])
            Tetradic_Color3.setBackgroundColor(tetradicColorStore[2])

            // this function need to be update and implement more clear
            updateColor()
        }

        // this observer works when the expression change
        cViewModel.colorRGB.observe(this@ColorCode, myObserverColor)

        //------------------------------------------------------------------------------------------
        // this event control when you press the color select
        //------------------------------------------------------------------------------------------
        ColorPicker.setOnClickListener {

            ColorPickerDialog.Builder(this)
                .setTitle("Color Picker")
                .setPreferenceName("MyColorPickerDialog")
                .setPositiveButton("confirm",
                    ColorEnvelopeListener { envelope, _ ->
                        cViewModel.setRGBColor(envelope.color)
                    })
                .setNegativeButton("Cancel") { dialogInterface, _ ->
                    dialogInterface.dismiss()
                }
                .attachAlphaSlideBar(false) // the default value is true.
                .attachBrightnessSlideBar(true) // the default value is true.
                .setBottomSpace(12) // set a bottom space between the last slidebar and buttons.
                .show()
        }
        //------------------------------------------------------------------------------------------
        // Dialog with the color information
        //------------------------------------------------------------------------------------------
        mainColor.setOnClickListener {
            val newFragment = InfoColorDialog(
                this.applicationContext,
                cViewModel.getRGBColor(),
                cViewModel
            )
            newFragment.show(supportFragmentManager, "infoColor")
        }

        Color1_Complementary.setOnClickListener {
            val newFragment = InfoColorDialog(
                this.applicationContext,
                complementaryColorStore,
                cViewModel
            )
            newFragment.show(supportFragmentManager, "infoColor")
        }

        SplitCC_Color1.setOnClickListener {
            val newFragment = InfoColorDialog(
                this.applicationContext,
                splitComplementaryColorStore[0],
                cViewModel
            )
            newFragment.show(supportFragmentManager, "infoColor")
        }

        SplitCC_Color2.setOnClickListener {
            val newFragment = InfoColorDialog(
                this.applicationContext,
                splitComplementaryColorStore[1],
                cViewModel
            )
            newFragment.show(supportFragmentManager, "infoColor")
        }

        Analogous_Color1.setOnClickListener {
            val newFragment = InfoColorDialog(
                this.applicationContext,
                analogousColorStore[0],
                cViewModel
            )
            newFragment.show(supportFragmentManager, "infoColor")
        }

        Analogous_Color2.setOnClickListener {
            val newFragment = InfoColorDialog(
                this.applicationContext,
                analogousColorStore[1],
                cViewModel
            )
            newFragment.show(supportFragmentManager, "infoColor")
        }

        Analogous_Color3.setOnClickListener {
            val newFragment = InfoColorDialog(
                this.applicationContext,
                analogousColorStore[2],
                cViewModel
            )
            newFragment.show(supportFragmentManager, "infoColor")
        }

        Analogous_Color4.setOnClickListener {
            val newFragment = InfoColorDialog(
                this.applicationContext,
                analogousColorStore[3],
                cViewModel
            )
            newFragment.show(supportFragmentManager, "infoColor")
        }

        Analogous_Color5.setOnClickListener {
            val newFragment = InfoColorDialog(
                this.applicationContext,
                analogousColorStore[4],
                cViewModel
            )
            newFragment.show(supportFragmentManager, "infoColor")
        }

        Triadic_Color1.setOnClickListener {
            val newFragment = InfoColorDialog(
                this.applicationContext,
                triadicColorStore[0],
                cViewModel
            )
            newFragment.show(supportFragmentManager, "infoColor")
        }

        Triadic_Color2.setOnClickListener {
            val newFragment = InfoColorDialog(
                this.applicationContext,
                triadicColorStore[1],
                cViewModel
            )
            newFragment.show(supportFragmentManager, "infoColor")
        }

        Tetradic_Color1.setOnClickListener {
            val newFragment = InfoColorDialog(
                this.applicationContext,
                tetradicColorStore[0],
                cViewModel
            )
            newFragment.show(supportFragmentManager, "infoColor")
        }

        Tetradic_Color2.setOnClickListener {
            val newFragment = InfoColorDialog(
                this.applicationContext,
                tetradicColorStore[1],
                cViewModel
            )
            newFragment.show(supportFragmentManager, "infoColor")
        }

        Tetradic_Color3.setOnClickListener {
            val newFragment = InfoColorDialog(
                this.applicationContext,
                tetradicColorStore[2],
                cViewModel
            )
            newFragment.show(supportFragmentManager, "infoColor")
        }
        //------------------------------------------------------------------------------------------
        // Dialog with the saved color information
        //------------------------------------------------------------------------------------------
        buttonColor1.setOnClickListener {
            val newFragment = InfoColorSavedDialog(
                this.applicationContext,
                SRDataColors.getItem(0),
                cViewModel
            )
            newFragment.show(supportFragmentManager, "infoColor")
        }

        buttonColor2.setOnClickListener {
            val newFragment = InfoColorSavedDialog(
                this.applicationContext,
                SRDataColors.getItem(1),
                cViewModel
            )
            newFragment.show(supportFragmentManager, "infoColor")
        }

        buttonColor3.setOnClickListener {
            val newFragment = InfoColorSavedDialog(
                this.applicationContext,
                SRDataColors.getItem(2),
                cViewModel
            )
            newFragment.show(supportFragmentManager, "infoColor")
        }
        //------------------------------------------------------------------------------------------
        // Dialog to show the controls to change the code color
        //------------------------------------------------------------------------------------------
        complementaryInfo.setOnClickListener {
            val newFragment = InfoSystemDialog("IC")
            newFragment.show(supportFragmentManager, "infoColor")
        }

        splitComplementaryInfo.setOnClickListener {
            val newFragment = InfoSystemDialog("ISC")
            newFragment.show(supportFragmentManager, "infoColor")
        }

        analogousInfo.setOnClickListener {
            val newFragment = InfoSystemDialog("IA")
            newFragment.show(supportFragmentManager, "infoColor")
        }

        triadicInfo.setOnClickListener {
            val newFragment = InfoSystemDialog("ITR")
            newFragment.show(supportFragmentManager, "infoColor")
        }

        tetradicInfo.setOnClickListener {
            val newFragment = InfoSystemDialog("ITE")
            newFragment.show(supportFragmentManager, "infoColor")
        }
        //------------------------------------------------------------------------------------------
        // Dialog to show the controls to change the code color
        //------------------------------------------------------------------------------------------
        moreColors.setOnClickListener {
            val newFragment = ListSavedColorsDialog(this,cViewModel.colorRGB)
            newFragment.show(supportFragmentManager, "infoColor")
        }
        //------------------------------------------------------------------------------------------
        // Dialog to show the controls to change the code color
        //------------------------------------------------------------------------------------------
        HEX_redValue.setOnClickListener {

            val newFragment = InfoChangeValueColorDialog(
                HEX_redValue.text.toString(),
                "HEXR",
                cViewModel.colorRGB
            )
            newFragment.show(supportFragmentManager, "changeColorValue")
        }

        HEX_greenValue.setOnClickListener {

            val newFragment = InfoChangeValueColorDialog(
                HEX_greenValue.text.toString(),
                "HEXG",
                cViewModel.colorRGB
            )
            newFragment.show(supportFragmentManager, "changeColorValue")
        }

        HEX_blueValue.setOnClickListener {

            val newFragment = InfoChangeValueColorDialog(
                HEX_blueValue.text.toString(),
                "HEXB",
                cViewModel.colorRGB
            )
            newFragment.show(supportFragmentManager, "changeColorValue")
        }

        RGBValue_RedColor.setOnClickListener {

            val newFragment = InfoChangeValueColorDialog(
                RGBValue_RedColor.text.toString(),
                "RGBR",
                cViewModel.colorRGB
            )
            newFragment.show(supportFragmentManager, "changeColorValue")
        }
        RGBValue_GreenColor.setOnClickListener {

            val newFragment = InfoChangeValueColorDialog(
                RGBValue_GreenColor.text.toString(),
                "RGBG",
                cViewModel.colorRGB
            )
            newFragment.show(supportFragmentManager, "changeColorValue")
        }
        RGBValue_BlueColor.setOnClickListener {

            val newFragment = InfoChangeValueColorDialog(
                RGBValue_BlueColor.text.toString(),
                "RGBB",
                cViewModel.colorRGB
            )
            newFragment.show(supportFragmentManager, "changeColorValue")
        }
        //------------------------------------------------------------------------------------------
        // event to control the new palette colors list
        //------------------------------------------------------------------------------------------
        BPaletteColors.setOnClickListener {
            val paletteColors = Intent(this, PaletteColors::class.java)
            paletteColors.putExtra("TAG", 1)
            startActivity(paletteColors)
        }
        //------------------------------------------------------------------------------------------
        // Event of the seekBar of the RGB controls
        //------------------------------------------------------------------------------------------
        controlbarforred.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                RGBValue_RedColor.text = p1.toString()
                val valueRed = Functions().convertDecToHex(p1).toString()
                cViewModel.setRGBColor(Color.parseColor("#$valueRed${cViewModel.getGreen()}${cViewModel.getBlue()}"))
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })

        controlbarforgreen.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                RGBValue_GreenColor.text = p1.toString()
                val valueGreen = Functions().convertDecToHex(p1).toString()
                cViewModel.setRGBColor(Color.parseColor("#${cViewModel.getRed()}$valueGreen${cViewModel.getBlue()}"))
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })

        controlbarforblue.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                RGBValue_BlueColor.text = p1.toString()
                val valueBlue = Functions().convertDecToHex(p1).toString()
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

        HEX_redValue.text = Functions().convertDecToHex(r)
        HEX_greenValue.text = Functions().convertDecToHex(g)
        HEX_blueValue.text = Functions().convertDecToHex(b)

        val colorHSL = ColorDesign().getHSLColorFromRGB(rgbColor)

        HSL_hueValue.text = ((colorHSL[0] * 360).toInt()).toString()
        HSL_saturacionValue.text = ((colorHSL[1] * 100).toInt()).toString()
        HSL_luminosidadValue.text = ((colorHSL[2] * 100).toInt()).toString()
    }

    private fun updateColor() {
        val data = SRDataColors.customPreference(this).getlist()

        if (data.size != 0) {
            if (data.size + 1 > 1) {
                buttonColor1.visibility = View.VISIBLE
                buttonColor1.background.setTint(data[0])
            }
            else{
                buttonColor1.visibility = View.INVISIBLE
            }

            if (data.size + 1 > 2){
                buttonColor2.visibility = View.VISIBLE
                buttonColor2.background.setTint(data[1])
            }
            else{
                buttonColor2.visibility = View.INVISIBLE
            }

            if (data.size + 1 > 3){
                buttonColor3.visibility = View.VISIBLE
                buttonColor3.background.setTint(data[2])
            }
            else{
                buttonColor3.visibility = View.INVISIBLE
            }
        }else{
            buttonColor1.visibility = View.INVISIBLE
        }
    }
}