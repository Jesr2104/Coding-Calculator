package com.just_jump.coding_calculator

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.just_jump.coding_calculator.utilities.ConvertUtilities
import com.just_jump.coding_calculator.utilities.PrefixText
import com.just_jump.coding_calculator.utilities.ValidateExponential
import kotlinx.android.synthetic.main.activity_converter_new.*
import java.text.DecimalFormat

class Converter : AppCompatActivity() {

    var statePrint = true

    // this var is for check when the user press temp to control negative number
    var stateTemp: Boolean = false
    private val menuAreaList = listOf(
        "Acres (ac)",
        "Ares (a)",
        "Hectares (ha)",
        "Squares centimetres (cm²)",
        "Squares feet (ft²)",
        "Squares inches (in²)",
        "Squares metres (m²)"
    )
    private val menuLengthList = listOf(
        "Millimetres (mm)",
        "Centimetres (cm)",
        "Metres (m)",
        "Kilometres (km)",
        "Inches (in)",
        "Feet (ft)",
        "Yards (yd)",
        "Miles (mi)",
        "Nautical miles (NM)",
        "Mils (mil)"
    )
    private val menuTimeList = listOf(
        "Milliseconds (ms)",
        "Seconds (s)",
        "Minutes (min)",
        "Hours (h)",
        "Days (d)",
        "Weeks (wk)"
    )
    private val menuTemperatureList = listOf(
        "Celsius (°C)",
        "Fahrenheit (°F)",
        "Kelvin (K)"
    )
    private val menuVolumeList = listOf(
        "UK gallons (gal)",
        "US gallons (gal)",
        "Litres (l)",
        "Millilitres (ml)",
        "Cubic centimetres (cc, cm³)",
        "Cubic metres (m³)",
        "Cubic inches (in³)",
        "Cubic feet (ft³)"
    )
    private val menuWeightList = listOf(
        "Tons (t)",
        "UK tons (t)",
        "US tons  (t)",
        "Pounds (lb)",
        "Ounces (oz)",
        "Kilogrammes (kg)",
        "Grams (g)",
        "Milligrams (mg)"
    )
    private val menuDataList = listOf(
        "Bits (bit)",
        "Bytes (B)",
        "Kilobytes (KB)",
        "Megabytes (MB)",
        "Gigabytes (GB)",
        "Terabytes (TB)",
        "Petabytes (PB)",
        "Exabytes (XB)"
    )
    private val menuSpeedList = listOf(
        "Metres per second (m/s)",
        "Metres per hour (m/h)",
        "Kilometres per second (km/s)",
        "Kilometres per hour (km/h)",
        "Inches per second (in/s)",
        "Inches per hour (in/h)",
        "Feet per second (ft/s)",
        "Feet per hour (ft/h)",
        "Miles per second (mi/s)",
        "Miles per hour (mi/h)",
        "Knots (kn)"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_converter_new)

        val adapterMenu = ArrayAdapter(
            this,
            R.layout.spinner_item,
            menuAreaList
        )
        adapterMenu.setDropDownViewResource(R.layout.spinner_dropdown_item)

        dataA.hint = getString(R.string.insert_number)
        dataA.setHintTextColor(resources.getColor(R.color.grey_hint))

        dataB.hint = getString(R.string.insert_number)
        dataB.setHintTextColor(resources.getColor(R.color.grey_hint))

        SpinnerMenuA.adapter = adapterMenu
        SpinnerMenuB.adapter = adapterMenu
        SpinnerMenuB.setSelection(1)

        // valor to check what system of convert is selected
        var systemOfConvert = 1

        /**
         *  Event to control: when the new field lost the focus
         */
        dataA.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus){
                dataA.hint = ""
            } else {
                if (dataA.text!!.isEmpty()){
                    dataA.hint = getString(R.string.insert_number)
                }
            }
        }

        dataB.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus){
                dataB.hint = ""
            } else {
                if (dataB.text!!.isEmpty()){
                    dataB.hint = getString(R.string.insert_number)
                }
            }
        }

        // Event when you press button Area
        card_Area.setOnClickListener {

            stateTemp = false
            val adapterMenuList = ArrayAdapter(
                this,
                R.layout.spinner_item,
                menuAreaList
            )
            adapterMenuList.setDropDownViewResource(R.layout.spinner_dropdown_item)

            SpinnerMenuA.adapter = adapterMenuList
            SpinnerMenuB.adapter = adapterMenuList
            SpinnerMenuB.setSelection(1)

            systemOfConvert = 1

            textArea.setTextColor(Color.rgb(150, 150, 150))

            Title_TypeConvert.text = getString(R.string.area)

            // change color lost selection
            textLength.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))
            textTime.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))
            textTemp.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))
            textVolume.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))
            textWeight.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))
            textData.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))
            textSpeed.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))

            dataA.setText("")
            dataB.setText("")
        }

        card_Length.setOnClickListener {

            stateTemp = false
            val adapterMenuList = ArrayAdapter(
                this,
                R.layout.spinner_item,
                menuLengthList
            )
            adapterMenuList.setDropDownViewResource(R.layout.spinner_dropdown_item)

            SpinnerMenuA.adapter = adapterMenuList
            SpinnerMenuB.adapter = adapterMenuList
            SpinnerMenuB.setSelection(1)

            systemOfConvert = 2

            textLength.setTextColor(Color.rgb(150, 150, 150))

            Title_TypeConvert.text = getString(R.string.length)

            // change color lost selection
            textArea.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))
            textTime.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))
            textTemp.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))
            textVolume.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))
            textWeight.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))
            textData.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))
            textSpeed.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))

            dataA.setText("")
            dataB.setText("")
        }

        card_Time.setOnClickListener {

            stateTemp = false
            val adapterMenuList = ArrayAdapter(
                this,
                R.layout.spinner_item,
                menuTimeList
            )
            adapterMenuList.setDropDownViewResource(R.layout.spinner_dropdown_item)

            SpinnerMenuA.adapter = adapterMenuList
            SpinnerMenuB.adapter = adapterMenuList
            SpinnerMenuB.setSelection(1)

            systemOfConvert = 3

            textTime.setTextColor(Color.rgb(150, 150, 150))

            Title_TypeConvert.text = getString(R.string.time)

            // change color lost selection
            textArea.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))
            textLength.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))
            textTemp.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))
            textVolume.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))
            textWeight.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))
            textData.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))
            textSpeed.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))

            dataA.setText("")
            dataB.setText("")
        }

        card_Temp.setOnClickListener {

            stateTemp = true
            val adapterMenuList = ArrayAdapter(
                this,
                R.layout.spinner_item,
                menuTemperatureList
            )
            adapterMenuList.setDropDownViewResource(R.layout.spinner_dropdown_item)

            SpinnerMenuA.adapter = adapterMenuList
            SpinnerMenuB.adapter = adapterMenuList
            SpinnerMenuB.setSelection(1)

            systemOfConvert = 4

            textTemp.setTextColor(Color.rgb(150, 150, 150))

            Title_TypeConvert.text = getString(R.string.temp)

            // change color lost selection
            textArea.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))
            textLength.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))
            textTime.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))
            textVolume.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))
            textWeight.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))
            textData.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))
            textSpeed.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))

            dataA.setText("")
            dataB.setText("")
        }

        card_Volume.setOnClickListener {

            stateTemp = false
            val adapterMenuList = ArrayAdapter(
                this,
                R.layout.spinner_item,
                menuVolumeList
            )
            adapterMenuList.setDropDownViewResource(R.layout.spinner_dropdown_item)

            SpinnerMenuA.adapter = adapterMenuList
            SpinnerMenuB.adapter = adapterMenuList
            SpinnerMenuB.setSelection(1)

            systemOfConvert = 5

            textVolume.setTextColor(Color.rgb(150, 150, 150))

            Title_TypeConvert.text = getString(R.string.volume)

            // change color lost selection
            textArea.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))
            textLength.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))
            textTime.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))
            textTemp.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))
            textWeight.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))
            textData.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))
            textSpeed.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))

            dataA.setText("")
            dataB.setText("")
        }

        card_Weight.setOnClickListener {

            stateTemp = false
            val adapterMenuList = ArrayAdapter(
                this,
                R.layout.spinner_item,
                menuWeightList
            )
            adapterMenuList.setDropDownViewResource(R.layout.spinner_dropdown_item)

            SpinnerMenuA.adapter = adapterMenuList
            SpinnerMenuB.adapter = adapterMenuList
            SpinnerMenuB.setSelection(1)

            systemOfConvert = 6

            textWeight.setTextColor(Color.rgb(150, 150, 150))

            Title_TypeConvert.text = getString(R.string.weight)

            // change color lost selection
            textArea.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))
            textLength.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))
            textTime.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))
            textTemp.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))
            textVolume.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))
            textData.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))
            textSpeed.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))

            dataA.setText("")
            dataB.setText("")
        }

        card_Data.setOnClickListener {

            stateTemp = false
            val adapterMenuList = ArrayAdapter(
                this,
                R.layout.spinner_item,
                menuDataList
            )
            adapterMenuList.setDropDownViewResource(R.layout.spinner_dropdown_item)

            SpinnerMenuA.adapter = adapterMenuList
            SpinnerMenuB.adapter = adapterMenuList
            SpinnerMenuB.setSelection(1)

            systemOfConvert = 7

            textData.setTextColor(Color.rgb(150, 150, 150))

            Title_TypeConvert.text = getString(R.string.data)

            // change color lost selection
            textArea.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))
            textLength.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))
            textTime.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))
            textTemp.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))
            textVolume.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))
            textWeight.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))
            textSpeed.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))

            dataA.setText("")
            dataB.setText("")
        }

        card_Speed.setOnClickListener {

            stateTemp = false
            val adapterMenuList = ArrayAdapter(
                this,
                R.layout.spinner_item,
                menuSpeedList
            )
            adapterMenuList.setDropDownViewResource(R.layout.spinner_dropdown_item)

            SpinnerMenuA.adapter = adapterMenuList
            SpinnerMenuB.adapter = adapterMenuList
            SpinnerMenuB.setSelection(1)

            systemOfConvert = 8

            textSpeed.setTextColor(Color.rgb(150, 150, 150))

            Title_TypeConvert.text = getString(R.string.speed)

            // change color lost selection
            textArea.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))
            textLength.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))
            textTime.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))
            textTemp.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))
            textVolume.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))
            textWeight.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))
            textData.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))

            dataA.setText("")
            dataB.setText("")
        }

        // this event control when the user change the metric but the value is done on the field.
        SpinnerMenuA.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>?,
                selectedItemView: View?,
                position: Int,
                id: Long
            ) {
                if (dataA.text.toString().isNotEmpty()) {
                    if (statePrint) {
                        if (dataA.text.toString().isNotEmpty()) {

                            //calculator the prefix
                            textInputLayoutA.prefixText =
                                PrefixText().getPrefix(SpinnerMenuA.selectedItem.toString())
                            textInputLayoutB.prefixText =
                                PrefixText().getPrefix(SpinnerMenuB.selectedItem.toString())

                            textInputLayoutB.prefixText

                            val resultValue = ConvertUtilities().checkConvert(
                                systemOfConvert,
                                dataA.text.toString().replace(",","").toDouble(),
                                SpinnerMenuA.selectedItem.toString(),
                                SpinnerMenuB.selectedItem.toString()
                            )

                            // Insert the value on the result field
                            statePrint = false
                            dataB.setText(resultValue)
                            statePrint = true
                        } else {
                            dataB.setText("")
                            textInputLayoutA.prefixText = ""
                            textInputLayoutB.prefixText = ""
                        }
                    }
                }
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {}
        }

        SpinnerMenuB.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (statePrint) {
                    if (dataB.text.toString().isNotEmpty()) {

                        //calculator the prefix
                        textInputLayoutA.prefixText =
                            PrefixText().getPrefix(SpinnerMenuA.selectedItem.toString())
                        textInputLayoutB.prefixText =
                            PrefixText().getPrefix(SpinnerMenuB.selectedItem.toString())

                        val resultValue = ConvertUtilities().checkConvert(
                            systemOfConvert,
                            dataB.text.toString().replace(",","").toDouble(),
                            SpinnerMenuB.selectedItem.toString(),
                            SpinnerMenuA.selectedItem.toString()
                        )

                        // Insert the value on the result field
                        statePrint = false
                        dataA.setText(resultValue)
                        statePrint = true
                    } else {
                        dataA.setText("")
                        textInputLayoutA.prefixText = ""
                        textInputLayoutB.prefixText = ""
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        dataA.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {

                if (!stateTemp) {
                    if (dataA.text.toString() == "-") {
                        p0!!.delete(p0.length - 1, p0.length)
                    }
                }

                if (statePrint) {
                    if (dataA.text.toString()
                            .isNotEmpty() && dataA.text.toString() != "." && dataA.text.toString() != "-"
                    ) {

                        var resultValue = ""
                        val format = DecimalFormat()
                        format.maximumFractionDigits = 7

                        //calculator the prefix
                        textInputLayoutA.prefixText =
                            PrefixText().getPrefix(SpinnerMenuA.selectedItem.toString())
                        textInputLayoutB.prefixText =
                            PrefixText().getPrefix(SpinnerMenuB.selectedItem.toString())

                        if (dataA.text.toString().contains('E') || dataA.text.toString()
                                .contains('e')
                        ) {
                            if (ValidateExponential().validate(dataA.text.toString())) {
                                resultValue = ConvertUtilities().checkConvert(
                                    systemOfConvert,
                                    dataA.text.toString().toDouble(),
                                    SpinnerMenuA.selectedItem.toString(),
                                    SpinnerMenuB.selectedItem.toString()
                                )
                            }
                        } else {
                            resultValue = ConvertUtilities().checkConvert(
                                systemOfConvert,
                                dataA.text.toString().toDouble(),
                                SpinnerMenuA.selectedItem.toString(),
                                SpinnerMenuB.selectedItem.toString()
                            )
                        }

                        // Insert the value on the result field
                        statePrint = false
                        dataB.setText(format.format(resultValue.toDouble()))
                        statePrint = true
                    } else {
                        if (dataB.text!!.isNotEmpty()) {
                            dataB.setText("")
                            textInputLayoutA.prefixText = ""
                            textInputLayoutB.prefixText = ""
                        }
                    }
                }
            }
        })

        dataB.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {

                if (!stateTemp) {
                    if (dataB.text.toString() == "-") {
                        p0!!.delete(p0.length - 1, p0.length)
                    }
                }

                if (statePrint) {
                    if (dataB.text.toString()
                            .isNotEmpty() && dataB.text.toString() != "." && dataB.text.toString() != "-"
                    ) {

                        var resultValue = ""
                        val format = DecimalFormat()
                        format.maximumFractionDigits = 7

                        //calculator the prefix
                        textInputLayoutA.prefixText =
                            PrefixText().getPrefix(SpinnerMenuA.selectedItem.toString())
                        textInputLayoutB.prefixText =
                            PrefixText().getPrefix(SpinnerMenuB.selectedItem.toString())

                        if (dataB.text.toString().contains('E') || dataB.text.toString()
                                .contains('e')
                        ) {
                            if (ValidateExponential().validate(dataB.text.toString())) {
                                resultValue = ConvertUtilities().checkConvert(
                                    systemOfConvert,
                                    dataB.text.toString().toDouble(),
                                    SpinnerMenuB.selectedItem.toString(),
                                    SpinnerMenuA.selectedItem.toString()
                                )
                            }
                        } else {
                            resultValue = ConvertUtilities().checkConvert(
                                systemOfConvert,
                                dataB.text.toString().toDouble(),
                                SpinnerMenuB.selectedItem.toString(),
                                SpinnerMenuA.selectedItem.toString()
                            )
                        }

                        // Insert the value on the result field
                        statePrint = false
                        dataA.setText(format.format(resultValue.toDouble()))
                        statePrint = true
                    } else {
                        if (dataA.text!!.isNotEmpty()) {
                            dataA.setText("")
                            textInputLayoutA.prefixText = ""
                            textInputLayoutB.prefixText = ""
                        }
                    }
                }
            }
        })

        /**
         * Event to control: button come back to the parent
         */
        button_back.setOnClickListener {
            finish()
        }
    }
}