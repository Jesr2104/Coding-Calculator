package justjump.coding_calculator

import android.content.pm.ActivityInfo
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import justjump.coding_calculator.utilities.ConvertUtilities
import justjump.coding_calculator.utilities.PrefixText
import justjump.coding_calculator.utilities.ValidateExponential
import kotlinx.android.synthetic.main.activity_converter.*

class Converter : AppCompatActivity() {

    var statePrint = true
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
        "Grams (g)"
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
        setContentView(R.layout.activity_converter)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        val adapterMenu = ArrayAdapter(
            this,
            R.layout.spinner_item,
            menuAreaList
        )
        adapterMenu.setDropDownViewResource(R.layout.spinner_dropdown_item)

        SpinnerMenuA.adapter = adapterMenu
        SpinnerMenuB.adapter = adapterMenu
        SpinnerMenuB.setSelection(1)

        // valor to check what system of convert is selected
        var systemOfConvert: Int = 1

        // Event when you press button Area
        Button_Area.setOnClickListener {

            val adapterMenu = ArrayAdapter(
                this,
                R.layout.spinner_item,
                menuAreaList
            )
            adapterMenu.setDropDownViewResource(R.layout.spinner_dropdown_item)

            SpinnerMenuA.adapter = adapterMenu
            SpinnerMenuB.adapter = adapterMenu
            SpinnerMenuB.setSelection(1)

            systemOfConvert = 1

            textArea.setTextColor(Color.rgb(150, 150, 150))

            Title_TypeConvert.text = "Area"

            // change color lost selection
            textLength.setTextColor(resources.getColor(R.color.Front_ColorGrey))
            textTime.setTextColor(resources.getColor(R.color.Front_ColorGrey))
            textTemp.setTextColor(resources.getColor(R.color.Front_ColorGrey))
            textVolume.setTextColor(resources.getColor(R.color.Front_ColorGrey))
            textWeight.setTextColor(resources.getColor(R.color.Front_ColorGrey))
            textData.setTextColor(resources.getColor(R.color.Front_ColorGrey))
            textSpeed.setTextColor(resources.getColor(R.color.Front_ColorGrey))

            dataA.setText("")
            dataB.setText("")
        }

        Button_Length.setOnClickListener {

            val adapterMenu = ArrayAdapter(
                this,
                R.layout.spinner_item,
                menuLengthList
            )
            adapterMenu.setDropDownViewResource(R.layout.spinner_dropdown_item)

            SpinnerMenuA.adapter = adapterMenu
            SpinnerMenuB.adapter = adapterMenu
            SpinnerMenuB.setSelection(1)

            systemOfConvert = 2

            textLength.setTextColor(Color.rgb(150, 150, 150))

            Title_TypeConvert.text = "Length"

            // change color lost selection
            textArea.setTextColor(resources.getColor(R.color.Front_ColorGrey))
            textTime.setTextColor(resources.getColor(R.color.Front_ColorGrey))
            textTemp.setTextColor(resources.getColor(R.color.Front_ColorGrey))
            textVolume.setTextColor(resources.getColor(R.color.Front_ColorGrey))
            textWeight.setTextColor(resources.getColor(R.color.Front_ColorGrey))
            textData.setTextColor(resources.getColor(R.color.Front_ColorGrey))
            textSpeed.setTextColor(resources.getColor(R.color.Front_ColorGrey))

            dataA.setText("")
            dataB.setText("")
        }

        Button_Time.setOnClickListener {

            val adapterMenu = ArrayAdapter(
                this,
                R.layout.spinner_item,
                menuTimeList
            )
            adapterMenu.setDropDownViewResource(R.layout.spinner_dropdown_item)

            SpinnerMenuA.adapter = adapterMenu
            SpinnerMenuB.adapter = adapterMenu
            SpinnerMenuB.setSelection(1)

            systemOfConvert = 3

            textTime.setTextColor(Color.rgb(150, 150, 150))

            Title_TypeConvert.text = "Time"

            // change color lost selection
            textArea.setTextColor(resources.getColor(R.color.Front_ColorGrey))
            textLength.setTextColor(resources.getColor(R.color.Front_ColorGrey))
            textTemp.setTextColor(resources.getColor(R.color.Front_ColorGrey))
            textVolume.setTextColor(resources.getColor(R.color.Front_ColorGrey))
            textWeight.setTextColor(resources.getColor(R.color.Front_ColorGrey))
            textData.setTextColor(resources.getColor(R.color.Front_ColorGrey))
            textSpeed.setTextColor(resources.getColor(R.color.Front_ColorGrey))

            dataA.setText("")
            dataB.setText("")
        }

        Button_Temp.setOnClickListener {

            val adapterMenu = ArrayAdapter(
                this,
                R.layout.spinner_item,
                menuTemperatureList
            )
            adapterMenu.setDropDownViewResource(R.layout.spinner_dropdown_item)

            SpinnerMenuA.adapter = adapterMenu
            SpinnerMenuB.adapter = adapterMenu
            SpinnerMenuB.setSelection(1)

            systemOfConvert = 4

            textTemp.setTextColor(Color.rgb(150, 150, 150))

            Title_TypeConvert.text = "Temperature"

            // change color lost selection
            textArea.setTextColor(resources.getColor(R.color.Front_ColorGrey))
            textLength.setTextColor(resources.getColor(R.color.Front_ColorGrey))
            textTime.setTextColor(resources.getColor(R.color.Front_ColorGrey))
            textVolume.setTextColor(resources.getColor(R.color.Front_ColorGrey))
            textWeight.setTextColor(resources.getColor(R.color.Front_ColorGrey))
            textData.setTextColor(resources.getColor(R.color.Front_ColorGrey))
            textSpeed.setTextColor(resources.getColor(R.color.Front_ColorGrey))

            dataA.setText("")
            dataB.setText("")
        }

        Button_Volume.setOnClickListener {

            val adapterMenu = ArrayAdapter(
                this,
                R.layout.spinner_item,
                menuVolumeList
            )
            adapterMenu.setDropDownViewResource(R.layout.spinner_dropdown_item)

            SpinnerMenuA.adapter = adapterMenu
            SpinnerMenuB.adapter = adapterMenu
            SpinnerMenuB.setSelection(1)

            systemOfConvert = 5

            textVolume.setTextColor(Color.rgb(150, 150, 150))

            Title_TypeConvert.text = "Volume"

            // change color lost selection
            textArea.setTextColor(resources.getColor(R.color.Front_ColorGrey))
            textLength.setTextColor(resources.getColor(R.color.Front_ColorGrey))
            textTime.setTextColor(resources.getColor(R.color.Front_ColorGrey))
            textTemp.setTextColor(resources.getColor(R.color.Front_ColorGrey))
            textWeight.setTextColor(resources.getColor(R.color.Front_ColorGrey))
            textData.setTextColor(resources.getColor(R.color.Front_ColorGrey))
            textSpeed.setTextColor(resources.getColor(R.color.Front_ColorGrey))

            dataA.setText("")
            dataB.setText("")
        }

        Button_Weight.setOnClickListener {

            val adapterMenu = ArrayAdapter(
                this,
                R.layout.spinner_item,
                menuWeightList
            )
            adapterMenu.setDropDownViewResource(R.layout.spinner_dropdown_item)

            SpinnerMenuA.adapter = adapterMenu
            SpinnerMenuB.adapter = adapterMenu
            SpinnerMenuB.setSelection(1)

            systemOfConvert = 6

            textWeight.setTextColor(Color.rgb(150, 150, 150))

            Title_TypeConvert.text = "Weight"

            // change color lost selection
            textArea.setTextColor(resources.getColor(R.color.Front_ColorGrey))
            textLength.setTextColor(resources.getColor(R.color.Front_ColorGrey))
            textTime.setTextColor(resources.getColor(R.color.Front_ColorGrey))
            textTemp.setTextColor(resources.getColor(R.color.Front_ColorGrey))
            textVolume.setTextColor(resources.getColor(R.color.Front_ColorGrey))
            textData.setTextColor(resources.getColor(R.color.Front_ColorGrey))
            textSpeed.setTextColor(resources.getColor(R.color.Front_ColorGrey))

            dataA.setText("")
            dataB.setText("")
        }

        Button_Data.setOnClickListener {

            val adapterMenu = ArrayAdapter(
                this,
                R.layout.spinner_item,
                menuDataList
            )
            adapterMenu.setDropDownViewResource(R.layout.spinner_dropdown_item)

            SpinnerMenuA.adapter = adapterMenu
            SpinnerMenuB.adapter = adapterMenu
            SpinnerMenuB.setSelection(1)

            systemOfConvert = 7

            textData.setTextColor(Color.rgb(150, 150, 150))

            Title_TypeConvert.text = "Data"

            // change color lost selection
            textArea.setTextColor(resources.getColor(R.color.Front_ColorGrey))
            textLength.setTextColor(resources.getColor(R.color.Front_ColorGrey))
            textTime.setTextColor(resources.getColor(R.color.Front_ColorGrey))
            textTemp.setTextColor(resources.getColor(R.color.Front_ColorGrey))
            textVolume.setTextColor(resources.getColor(R.color.Front_ColorGrey))
            textWeight.setTextColor(resources.getColor(R.color.Front_ColorGrey))
            textSpeed.setTextColor(resources.getColor(R.color.Front_ColorGrey))

            dataA.setText("")
            dataB.setText("")
        }

        Button_Speed.setOnClickListener {

            val adapterMenu = ArrayAdapter(
                this,
                R.layout.spinner_item,
                menuSpeedList
            )
            adapterMenu.setDropDownViewResource(R.layout.spinner_dropdown_item)

            SpinnerMenuA.adapter = adapterMenu
            SpinnerMenuB.adapter = adapterMenu
            SpinnerMenuB.setSelection(1)

            systemOfConvert = 8

            textSpeed.setTextColor(Color.rgb(150, 150, 150))

            Title_TypeConvert.text = "Speed"

            // change color lost selection
            textArea.setTextColor(resources.getColor(R.color.Front_ColorGrey))
            textLength.setTextColor(resources.getColor(R.color.Front_ColorGrey))
            textTime.setTextColor(resources.getColor(R.color.Front_ColorGrey))
            textTemp.setTextColor(resources.getColor(R.color.Front_ColorGrey))
            textVolume.setTextColor(resources.getColor(R.color.Front_ColorGrey))
            textWeight.setTextColor(resources.getColor(R.color.Front_ColorGrey))
            textData.setTextColor(resources.getColor(R.color.Front_ColorGrey))

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

                            val resultValue = ConvertUtilities().checkConvert(
                                systemOfConvert,
                                dataA.text.toString().toDouble(),
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
                            dataB.text.toString().toDouble(),
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

                if (statePrint) {
                    if (dataA.text.toString().isNotEmpty()) {

                        var resultValue = ""

                        //calculator the prefix
                        textInputLayoutA.prefixText = PrefixText().getPrefix(SpinnerMenuA.selectedItem.toString())
                        textInputLayoutB.prefixText = PrefixText().getPrefix(SpinnerMenuB.selectedItem.toString())

                        if (dataA.text.toString().contains('E') || dataA.text.toString().contains('e')) {
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
                        dataB.setText(resultValue)
                        statePrint = true
                    } else {
                        if (dataB.text!!.isNotEmpty()) {
                            dataB.setText("")
                        }
                    }
                }
            }
        })

        dataB.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {

                if (statePrint) {
                    if (dataB.text.toString().isNotEmpty()) {

                        var resultValue = ""

                        //calculator the prefix
                        textInputLayoutA.prefixText = PrefixText().getPrefix(SpinnerMenuA.selectedItem.toString())
                        textInputLayoutB.prefixText = PrefixText().getPrefix(SpinnerMenuB.selectedItem.toString())

                        if (dataB.text.toString().contains('E') || dataB.text.toString().contains('e')) {
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
                        dataA.setText(resultValue)
                        statePrint = true
                    } else {
                        if (dataA.text!!.isNotEmpty()) {
                            dataA.setText("")
                        }
                    }
                }
            }
        })
    }
}