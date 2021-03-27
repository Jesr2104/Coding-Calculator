package com.just_jump.coding_calculator

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.just_jump.coding_calculator.*
import com.just_jump.coding_calculator.data.local.SRDataColors
import com.just_jump.coding_calculator.databinding.ActivityColorcodeBinding
import com.just_jump.coding_calculator.utilities.ColorDesign
import com.just_jump.coding_calculator.utilities.Functions
import com.just_jump.coding_calculator.viewmodel.ViewModelColorCode
import com.skydoves.colorpickerview.ColorPickerDialog
import com.skydoves.colorpickerview.listeners.ColorEnvelopeListener

class ColorCode : AppCompatActivity(){

    lateinit var cViewModel: ViewModelColorCode
    private var complementaryColorStore: Int = 0
    private lateinit var splitComplementaryColorStore: Array<Int>
    private lateinit var analogousColorStore: Array<Int>
    private lateinit var triadicColorStore: Array<Int>
    private lateinit var tetradicColorStore: Array<Int>
    private lateinit var binding: ActivityColorcodeBinding

    override fun onResume() {
        cViewModel.setRGBColor(cViewModel.getRGBColor())
        super.onResume()
    }

    override fun onPause() {
        cViewModel.setRGBColor(cViewModel.getRGBColor())
        super.onPause()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if(requestCode == 1){
            if (resultCode == RESULT_OK){
                val resultTemp = data!!.getIntExtra("loadColor",0)

                if(resultTemp != 0){
                    cViewModel.colorRGB.value = resultTemp
                }
            }
        }

        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityColorcodeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cViewModel = ViewModelProvider(this)[ViewModelColorCode::class.java]

        /***************************************************************************/
        // observer
        /***************************************************************************/
        val myObserverColor = Observer<Int> {

            //  Primary color
            binding.mainColor.setBackgroundColor(cViewModel.getRGBColor())

            loadDataColor(cViewModel.getRGBColor())

            // Complementary color viewer
            complementaryColorStore = cViewModel.loadComplementaryColor()
            binding.Color1Complementary.setBackgroundColor(complementaryColorStore)

            // Split Complementary color viewer
            splitComplementaryColorStore = cViewModel.loadSplitComplementaryColor()
            binding.SplitCCColor1.setBackgroundColor(splitComplementaryColorStore[0])
            binding.SplitCCColor2.setBackgroundColor(splitComplementaryColorStore[1])

            // Analogous
            analogousColorStore = cViewModel.loadAnalogous()
            binding.AnalogousColor1.setBackgroundColor(analogousColorStore[0])
            binding.AnalogousColor2.setBackgroundColor(analogousColorStore[1])
            binding.AnalogousColor3.setBackgroundColor(analogousColorStore[2])
            binding.AnalogousColor4.setBackgroundColor(analogousColorStore[3])
            binding.AnalogousColor5.setBackgroundColor(analogousColorStore[4])

            // Triadic
            triadicColorStore = cViewModel.loadTriadic()
            binding.TriadicColor1.setBackgroundColor(triadicColorStore[0])
            binding.TriadicColor2.setBackgroundColor(triadicColorStore[1])

            // Tetradic
            tetradicColorStore = cViewModel.loadTetradicColor()
            binding.TetradicColor1.setBackgroundColor(tetradicColorStore[0])
            binding.TetradicColor2.setBackgroundColor(tetradicColorStore[1])
            binding.TetradicColor3.setBackgroundColor(tetradicColorStore[2])

            // this function need to be update and implement more clear
            updateColor()
        }

        // this observer works when the expression change
        cViewModel.colorRGB.observe(this@ColorCode, myObserverColor)

        //------------------------------------------------------------------------------------------
        // this event control when you press the color select
        //------------------------------------------------------------------------------------------
        binding.ColorPicker.setOnClickListener {

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
                .setBottomSpace(12) // set a bottom space between the last slide bar and buttons.
                .show()
        }
        //------------------------------------------------------------------------------------------
        // Dialog with the color information
        //------------------------------------------------------------------------------------------
        binding.mainColor.setOnClickListener {
            val newFragment = InfoColorDialog(
                this.applicationContext,
                cViewModel.getRGBColor(),
                cViewModel
            )
            newFragment.show(supportFragmentManager, "infoColor")
        }

        binding.Color1Complementary.setOnClickListener {
            val newFragment = InfoColorDialog(
                this.applicationContext,
                complementaryColorStore,
                cViewModel
            )
            newFragment.show(supportFragmentManager, "infoColor")
        }

        binding.SplitCCColor1.setOnClickListener {
            val newFragment = InfoColorDialog(
                this.applicationContext,
                splitComplementaryColorStore[0],
                cViewModel
            )
            newFragment.show(supportFragmentManager, "infoColor")
        }

        binding.SplitCCColor2.setOnClickListener {
            val newFragment = InfoColorDialog(
                this.applicationContext,
                splitComplementaryColorStore[1],
                cViewModel
            )
            newFragment.show(supportFragmentManager, "infoColor")
        }

        binding.AnalogousColor1.setOnClickListener {
            val newFragment = InfoColorDialog(
                this.applicationContext,
                analogousColorStore[0],
                cViewModel
            )
            newFragment.show(supportFragmentManager, "infoColor")
        }

        binding.AnalogousColor2.setOnClickListener {
            val newFragment = InfoColorDialog(
                this.applicationContext,
                analogousColorStore[1],
                cViewModel
            )
            newFragment.show(supportFragmentManager, "infoColor")
        }

        binding.AnalogousColor3.setOnClickListener {
            val newFragment = InfoColorDialog(
                this.applicationContext,
                analogousColorStore[2],
                cViewModel
            )
            newFragment.show(supportFragmentManager, "infoColor")
        }

        binding.AnalogousColor4.setOnClickListener {
            val newFragment = InfoColorDialog(
                this.applicationContext,
                analogousColorStore[3],
                cViewModel
            )
            newFragment.show(supportFragmentManager, "infoColor")
        }

        binding.AnalogousColor5.setOnClickListener {
            val newFragment = InfoColorDialog(
                this.applicationContext,
                analogousColorStore[4],
                cViewModel
            )
            newFragment.show(supportFragmentManager, "infoColor")
        }

        binding.TriadicColor1.setOnClickListener {
            val newFragment = InfoColorDialog(
                this.applicationContext,
                triadicColorStore[0],
                cViewModel
            )
            newFragment.show(supportFragmentManager, "infoColor")
        }

        binding.TriadicColor2.setOnClickListener {
            val newFragment = InfoColorDialog(
                this.applicationContext,
                triadicColorStore[1],
                cViewModel
            )
            newFragment.show(supportFragmentManager, "infoColor")
        }

        binding.TetradicColor1.setOnClickListener {
            val newFragment = InfoColorDialog(
                this.applicationContext,
                tetradicColorStore[0],
                cViewModel
            )
            newFragment.show(supportFragmentManager, "infoColor")
        }

        binding.TetradicColor2.setOnClickListener {
            val newFragment = InfoColorDialog(
                this.applicationContext,
                tetradicColorStore[1],
                cViewModel
            )
            newFragment.show(supportFragmentManager, "infoColor")
        }

        binding.TetradicColor3.setOnClickListener {
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
        binding.buttonColor1.setOnClickListener {
            val newFragment = InfoColorSavedDialog(
                SRDataColors.getItem(0),
                cViewModel
            )
            newFragment.show(supportFragmentManager, "infoColor")
        }

        binding.buttonColor2.setOnClickListener {
            val newFragment = InfoColorSavedDialog(
                SRDataColors.getItem(1),
                cViewModel
            )
            newFragment.show(supportFragmentManager, "infoColor")
        }

        binding.buttonColor3.setOnClickListener {
            val newFragment = InfoColorSavedDialog(
                SRDataColors.getItem(2),
                cViewModel
            )
            newFragment.show(supportFragmentManager, "infoColor")
        }
        //------------------------------------------------------------------------------------------
        // Dialog to show the controls to change the code color
        //------------------------------------------------------------------------------------------
        binding.complementaryInfo.setOnClickListener {
            val newFragment = InfoSystemDialog("IC")
            newFragment.show(supportFragmentManager, "infoColor")
        }

        binding.splitComplementaryInfo.setOnClickListener {
            val newFragment = InfoSystemDialog("ISC")
            newFragment.show(supportFragmentManager, "infoColor")
        }

        binding.analogousInfo.setOnClickListener {
            val newFragment = InfoSystemDialog("IA")
            newFragment.show(supportFragmentManager, "infoColor")
        }

        binding.triadicInfo.setOnClickListener {
            val newFragment = InfoSystemDialog("ITR")
            newFragment.show(supportFragmentManager, "infoColor")
        }

        binding.tetradicInfo.setOnClickListener {
            val newFragment = InfoSystemDialog("ITE")
            newFragment.show(supportFragmentManager, "infoColor")
        }
        //------------------------------------------------------------------------------------------
        // Dialog to show the controls to change the code color
        //------------------------------------------------------------------------------------------
        binding.moreColors.setOnClickListener {
            val listSavedColors = Intent(this, ListSavedColors::class.java)
            startActivityForResult(listSavedColors,1)
        }
        //------------------------------------------------------------------------------------------
        // Dialog to show the controls to change the code color
        //------------------------------------------------------------------------------------------
        binding.HEXRedValue.setOnClickListener {

            val newFragment = InfoChangeValueColorDialog(
                binding.HEXRedValue.text.toString(),
                "HEXR",
                cViewModel.colorRGB
            )
            newFragment.show(supportFragmentManager, "changeColorValue")
        }

        binding.HEXGreenValue.setOnClickListener {

            val newFragment = InfoChangeValueColorDialog(
                binding.HEXGreenValue.text.toString(),
                "HEXG",
                cViewModel.colorRGB
            )
            newFragment.show(supportFragmentManager, "changeColorValue")
        }

        binding.HEXBlueValue.setOnClickListener {

            val newFragment = InfoChangeValueColorDialog(
                binding.HEXBlueValue.text.toString(),
                "HEXB",
                cViewModel.colorRGB
            )
            newFragment.show(supportFragmentManager, "changeColorValue")
        }

        binding.RGBValueRedColor.setOnClickListener {

            val newFragment = InfoChangeValueColorDialog(
                binding.RGBValueRedColor.text.toString(),
                "RGBR",
                cViewModel.colorRGB
            )
            newFragment.show(supportFragmentManager, "changeColorValue")
        }
        binding.RGBValueGreenColor.setOnClickListener {

            val newFragment = InfoChangeValueColorDialog(
                binding.RGBValueGreenColor.text.toString(),
                "RGBG",
                cViewModel.colorRGB
            )
            newFragment.show(supportFragmentManager, "changeColorValue")
        }
        binding.RGBValueBlueColor.setOnClickListener {

            val newFragment = InfoChangeValueColorDialog(
                binding.RGBValueBlueColor.text.toString(),
                "RGBB",
                cViewModel.colorRGB
            )
            newFragment.show(supportFragmentManager, "changeColorValue")
        }
        //------------------------------------------------------------------------------------------
        // event to control the new palette colors list
        //------------------------------------------------------------------------------------------
        binding.BPaletteColors.setOnClickListener {
            val paletteColors = Intent(this, PaletteColors::class.java)
            paletteColors.putExtra("TAG", 1)
            startActivity(paletteColors)
        }
        //------------------------------------------------------------------------------------------
        // Event of the seekBar of the RGB controls
        //------------------------------------------------------------------------------------------
        binding.controlBarRed.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                binding.RGBValueRedColor.text = p1.toString()
                val valueRed = Functions().convertDecToHex(p1)
                cViewModel.setRGBColor(Color.parseColor("#$valueRed${cViewModel.getGreen()}${cViewModel.getBlue()}"))
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })

        binding.controlBarGreen.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                binding.RGBValueGreenColor.text = p1.toString()
                val valueGreen = Functions().convertDecToHex(p1)
                cViewModel.setRGBColor(Color.parseColor("#${cViewModel.getRed()}$valueGreen${cViewModel.getBlue()}"))
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })

        binding.controlBarBlue.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                binding.RGBValueBlueColor.text = p1.toString()
                val valueBlue = Functions().convertDecToHex(p1)
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

        binding.controlBarRed.progress = r
        binding.controlBarGreen.progress = g
        binding.controlBarBlue.progress = b

        binding.HEXRedValue.text = Functions().convertDecToHex(r)
        binding.HEXGreenValue.text = Functions().convertDecToHex(g)
        binding.HEXBlueValue.text = Functions().convertDecToHex(b)

        val colorHSL = ColorDesign().getHSLColorFromRGB(rgbColor)

        binding.HSLHueValue.text = ((colorHSL[0] * 360).toInt()).toString()
        binding.HSLSaturationValue.text = ((colorHSL[1] * 100).toInt()).toString()
        binding.HSLLightnessValue.text = ((colorHSL[2] * 100).toInt()).toString()
    }

    private fun updateColor() {
        val data = SRDataColors.customPreference(this).getlist()

        if (data.size != 0) {
            if (data.size + 1 > 1) {
                binding.buttonColor1.visibility = View.VISIBLE
                binding.buttonColor1.background.setTint(data[0])
            }
            else{
                binding.buttonColor1.visibility = View.INVISIBLE
            }

            if (data.size + 1 > 2){
                binding.buttonColor2.visibility = View.VISIBLE
                binding.buttonColor2.background.setTint(data[1])
            }
            else{
                binding.buttonColor2.visibility = View.INVISIBLE
            }

            if (data.size + 1 > 3){
                binding.buttonColor3.visibility = View.VISIBLE
                binding.buttonColor3.background.setTint(data[2])
            }
            else{
                binding.buttonColor3.visibility = View.INVISIBLE
            }
        }else{
            binding.buttonColor1.visibility = View.INVISIBLE
        }
    }
}