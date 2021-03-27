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
import com.just_jump.coding_calculator.databinding.ActivityConverterNewBinding
import com.just_jump.coding_calculator.utilities.ConvertUtilities
import com.just_jump.coding_calculator.utilities.PrefixText
import com.just_jump.coding_calculator.utilities.ValidateExponential
import java.text.DecimalFormat

class Converter : AppCompatActivity() {

    var statePrint = true
    private lateinit var binding:ActivityConverterNewBinding

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
        binding = ActivityConverterNewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapterMenu = ArrayAdapter(
            this,
            R.layout.spinner_item,
            menuAreaList
        )
        adapterMenu.setDropDownViewResource(R.layout.spinner_dropdown_item)

        binding.dataA.hint = getString(R.string.insert_number)
        binding.dataA.setHintTextColor(ContextCompat.getColor(this, R.color.grey_hint))

        binding.dataB.hint = getString(R.string.insert_number)
        binding.dataB.setHintTextColor(ContextCompat.getColor(this, R.color.grey_hint))

        binding.SpinnerMenuA.adapter = adapterMenu
        binding.SpinnerMenuB.adapter = adapterMenu
        binding.SpinnerMenuB.setSelection(1)

        // valor to check what system of convert is selected
        var systemOfConvert = 1

        /**
         *  Event to control: when the new field lost the focus
         */
        binding.dataA.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus){
                binding.dataA.hint = ""
            } else {
                if (binding.dataA.text!!.isEmpty()){
                    binding.dataA.hint = getString(R.string.insert_number)
                }
            }
        }

        binding.dataB.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus){
                binding.dataB.hint = ""
            } else {
                if (binding.dataB.text!!.isEmpty()){
                    binding.dataB.hint = getString(R.string.insert_number)
                }
            }
        }

        // Event when you press button Area
        binding.cardArea.setOnClickListener {

            stateTemp = false
            val adapterMenuList = ArrayAdapter(
                this,
                R.layout.spinner_item,
                menuAreaList
            )
            adapterMenuList.setDropDownViewResource(R.layout.spinner_dropdown_item)

            binding.SpinnerMenuA.adapter = adapterMenuList
            binding.SpinnerMenuB.adapter = adapterMenuList
            binding.SpinnerMenuB.setSelection(1)

            systemOfConvert = 1

            binding.textArea.setTextColor(Color.rgb(150, 150, 150))

            binding.TitleTypeConvert.text = getString(R.string.area)

            // change color lost selection
            binding.textLength.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))
            binding.textTime.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))
            binding.textTemp.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))
            binding.textVolume.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))
            binding.textWeight.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))
            binding.textData.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))
            binding.textSpeed.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))

            binding.dataA.setText("")
            binding.dataB.setText("")
        }

        binding.cardLength.setOnClickListener {

            stateTemp = false
            val adapterMenuList = ArrayAdapter(
                this,
                R.layout.spinner_item,
                menuLengthList
            )
            adapterMenuList.setDropDownViewResource(R.layout.spinner_dropdown_item)

            binding.SpinnerMenuA.adapter = adapterMenuList
            binding.SpinnerMenuB.adapter = adapterMenuList
            binding.SpinnerMenuB.setSelection(1)

            systemOfConvert = 2

            binding.textLength.setTextColor(Color.rgb(150, 150, 150))

            binding.TitleTypeConvert.text = getString(R.string.length)

            // change color lost selection
            binding.textArea.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))
            binding.textTime.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))
            binding.textTemp.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))
            binding.textVolume.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))
            binding.textWeight.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))
            binding.textData.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))
            binding.textSpeed.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))

            binding.dataA.setText("")
            binding.dataB.setText("")
        }

        binding.cardTime.setOnClickListener {

            stateTemp = false
            val adapterMenuList = ArrayAdapter(
                this,
                R.layout.spinner_item,
                menuTimeList
            )
            adapterMenuList.setDropDownViewResource(R.layout.spinner_dropdown_item)

            binding.SpinnerMenuA.adapter = adapterMenuList
            binding.SpinnerMenuB.adapter = adapterMenuList
            binding.SpinnerMenuB.setSelection(1)

            systemOfConvert = 3

            binding.textTime.setTextColor(Color.rgb(150, 150, 150))

            binding.TitleTypeConvert.text = getString(R.string.time)

            // change color lost selection
            binding.textArea.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))
            binding.textLength.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))
            binding.textTemp.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))
            binding.textVolume.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))
            binding.textWeight.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))
            binding.textData.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))
            binding.textSpeed.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))

            binding.dataA.setText("")
            binding.dataB.setText("")
        }

        binding.cardTemp.setOnClickListener {

            stateTemp = true
            val adapterMenuList = ArrayAdapter(
                this,
                R.layout.spinner_item,
                menuTemperatureList
            )
            adapterMenuList.setDropDownViewResource(R.layout.spinner_dropdown_item)

            binding.SpinnerMenuA.adapter = adapterMenuList
            binding.SpinnerMenuB.adapter = adapterMenuList
            binding.SpinnerMenuB.setSelection(1)

            systemOfConvert = 4

            binding.textTemp.setTextColor(Color.rgb(150, 150, 150))

            binding.TitleTypeConvert.text = getString(R.string.temp)

            // change color lost selection
            binding.textArea.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))
            binding.textLength.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))
            binding.textTime.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))
            binding.textVolume.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))
            binding.textWeight.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))
            binding.textData.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))
            binding.textSpeed.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))

            binding.dataA.setText("")
            binding.dataB.setText("")
        }

        binding.cardVolume.setOnClickListener {

            stateTemp = false
            val adapterMenuList = ArrayAdapter(
                this,
                R.layout.spinner_item,
                menuVolumeList
            )
            adapterMenuList.setDropDownViewResource(R.layout.spinner_dropdown_item)

            binding.SpinnerMenuA.adapter = adapterMenuList
            binding.SpinnerMenuB.adapter = adapterMenuList
            binding.SpinnerMenuB.setSelection(1)

            systemOfConvert = 5

            binding.textVolume.setTextColor(Color.rgb(150, 150, 150))

            binding.TitleTypeConvert.text = getString(R.string.volume)

            // change color lost selection
            binding.textArea.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))
            binding.textLength.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))
            binding.textTime.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))
            binding.textTemp.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))
            binding.textWeight.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))
            binding.textData.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))
            binding.textSpeed.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))

            binding.dataA.setText("")
            binding.dataB.setText("")
        }

        binding.cardWeight.setOnClickListener {

            stateTemp = false
            val adapterMenuList = ArrayAdapter(
                this,
                R.layout.spinner_item,
                menuWeightList
            )
            adapterMenuList.setDropDownViewResource(R.layout.spinner_dropdown_item)

            binding.SpinnerMenuA.adapter = adapterMenuList
            binding.SpinnerMenuB.adapter = adapterMenuList
            binding.SpinnerMenuB.setSelection(1)

            systemOfConvert = 6

            binding.textWeight.setTextColor(Color.rgb(150, 150, 150))

            binding.TitleTypeConvert.text = getString(R.string.weight)

            // change color lost selection
            binding.textArea.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))
            binding.textLength.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))
            binding.textTime.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))
            binding.textTemp.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))
            binding.textVolume.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))
            binding.textData.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))
            binding.textSpeed.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))

            binding.dataA.setText("")
            binding.dataB.setText("")
        }

        binding.cardData.setOnClickListener {

            stateTemp = false
            val adapterMenuList = ArrayAdapter(
                this,
                R.layout.spinner_item,
                menuDataList
            )
            adapterMenuList.setDropDownViewResource(R.layout.spinner_dropdown_item)

            binding.SpinnerMenuA.adapter = adapterMenuList
            binding.SpinnerMenuB.adapter = adapterMenuList
            binding.SpinnerMenuB.setSelection(1)

            systemOfConvert = 7

            binding.textData.setTextColor(Color.rgb(150, 150, 150))

            binding.TitleTypeConvert.text = getString(R.string.data)

            // change color lost selection
            binding.textArea.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))
            binding.textLength.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))
            binding.textTime.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))
            binding.textTemp.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))
            binding.textVolume.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))
            binding.textWeight.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))
            binding.textSpeed.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))

            binding.dataA.setText("")
            binding.dataB.setText("")
        }

        binding.cardSpeed.setOnClickListener {

            stateTemp = false
            val adapterMenuList = ArrayAdapter(
                this,
                R.layout.spinner_item,
                menuSpeedList
            )
            adapterMenuList.setDropDownViewResource(R.layout.spinner_dropdown_item)

            binding.SpinnerMenuA.adapter = adapterMenuList
            binding.SpinnerMenuB.adapter = adapterMenuList
            binding.SpinnerMenuB.setSelection(1)

            systemOfConvert = 8

            binding.textSpeed.setTextColor(Color.rgb(150, 150, 150))

            binding.TitleTypeConvert.text = getString(R.string.speed)

            // change color lost selection
            binding.textArea.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))
            binding.textLength.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))
            binding.textTime.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))
            binding.textTemp.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))
            binding.textVolume.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))
            binding.textWeight.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))
            binding.textData.setTextColor(ContextCompat.getColor(this, R.color.Front_ColorGrey))

            binding.dataA.setText("")
            binding.dataB.setText("")
        }

        // this event control when the user change the metric but the value is done on the field.
        binding.SpinnerMenuA.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>?,
                selectedItemView: View?,
                position: Int,
                id: Long
            ) {
                if (binding.dataA.text.toString().isNotEmpty()) {
                    if (statePrint) {
                        if (binding.dataA.text.toString().isNotEmpty()) {

                            //calculator the prefix
                            binding.textInputLayoutA.prefixText =
                                PrefixText().getPrefix(binding.SpinnerMenuA.selectedItem.toString())
                            binding.textInputLayoutB.prefixText =
                                PrefixText().getPrefix(binding.SpinnerMenuB.selectedItem.toString())

                            binding.textInputLayoutB.prefixText

                            val resultValue = ConvertUtilities().checkConvert(
                                systemOfConvert,
                                binding.dataA.text.toString().replace(",","").toDouble(),
                                binding.SpinnerMenuA.selectedItem.toString(),
                                binding.SpinnerMenuB.selectedItem.toString()
                            )

                            // Insert the value on the result field
                            statePrint = false
                            binding.dataB.setText(resultValue)
                            statePrint = true
                        } else {
                            binding.dataB.setText("")
                            binding.textInputLayoutA.prefixText = ""
                            binding.textInputLayoutB.prefixText = ""
                        }
                    }
                }
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {}
        }

        binding.SpinnerMenuB.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (statePrint) {
                    if (binding.dataB.text.toString().isNotEmpty()) {

                        //calculator the prefix
                        binding.textInputLayoutA.prefixText =
                            PrefixText().getPrefix(binding.SpinnerMenuA.selectedItem.toString())
                        binding.textInputLayoutB.prefixText =
                            PrefixText().getPrefix(binding.SpinnerMenuB.selectedItem.toString())

                        val resultValue = ConvertUtilities().checkConvert(
                            systemOfConvert,
                            binding.dataB.text.toString().replace(",","").toDouble(),
                            binding.SpinnerMenuB.selectedItem.toString(),
                            binding.SpinnerMenuA.selectedItem.toString()
                        )

                        // Insert the value on the result field
                        statePrint = false
                        binding.dataA.setText(resultValue)
                        statePrint = true
                    } else {
                        binding.dataA.setText("")
                        binding.textInputLayoutA.prefixText = ""
                        binding.textInputLayoutB.prefixText = ""
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        binding.dataA.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {

                if (!stateTemp) {
                    if (binding.dataA.text.toString() == "-") {
                        p0!!.delete(p0.length - 1, p0.length)
                    }
                }

                if (statePrint) {
                    if (binding.dataA.text.toString()
                            .isNotEmpty() && binding.dataA.text.toString() != "." && binding.dataA.text.toString() != "-"
                    ) {

                        var resultValue = ""
                        val format = DecimalFormat()
                        format.maximumFractionDigits = 7

                        //calculator the prefix
                        binding.textInputLayoutA.prefixText =
                            PrefixText().getPrefix(binding.SpinnerMenuA.selectedItem.toString())
                        binding.textInputLayoutB.prefixText =
                            PrefixText().getPrefix(binding.SpinnerMenuB.selectedItem.toString())

                        if (binding.dataA.text.toString().contains('E') || binding.dataA.text.toString()
                                .contains('e')
                        ) {
                            if (ValidateExponential().validate(binding.dataA.text.toString())) {
                                resultValue = ConvertUtilities().checkConvert(
                                    systemOfConvert,
                                    binding.dataA.text.toString().replace(",","").toDouble(),
                                    binding.SpinnerMenuA.selectedItem.toString(),
                                    binding.SpinnerMenuB.selectedItem.toString()
                                )
                            }
                        } else {
                            resultValue = ConvertUtilities().checkConvert(
                                systemOfConvert,
                                binding.dataA.text.toString().replace(",","").toDouble(),
                                binding.SpinnerMenuA.selectedItem.toString(),
                                binding.SpinnerMenuB.selectedItem.toString()
                            )
                        }

                        // Insert the value on the result field
                        statePrint = false
                        binding.dataB.setText(format.format(resultValue.toDouble()))
                        statePrint = true
                    } else {
                        if (binding.dataB.text!!.isNotEmpty()) {
                            binding.dataB.setText("")
                            binding.textInputLayoutA.prefixText = ""
                            binding.textInputLayoutB.prefixText = ""
                        }
                    }
                }
            }
        })

        binding.dataB.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {

                if (!stateTemp) {
                    if (binding.dataB.text.toString() == "-") {
                        p0!!.delete(p0.length - 1, p0.length)
                    }
                }

                if (statePrint) {
                    if (binding.dataB.text.toString()
                            .isNotEmpty() && binding.dataB.text.toString() != "." && binding.dataB.text.toString() != "-"
                    ) {

                        var resultValue = ""
                        val format = DecimalFormat()
                        format.maximumFractionDigits = 7

                        //calculator the prefix
                        binding.textInputLayoutA.prefixText =
                            PrefixText().getPrefix(binding.SpinnerMenuA.selectedItem.toString())
                        binding.textInputLayoutB.prefixText =
                            PrefixText().getPrefix(binding.SpinnerMenuB.selectedItem.toString())

                        if (binding.dataB.text.toString().contains('E') || binding.dataB.text.toString()
                                .contains('e')
                        ) {
                            if (ValidateExponential().validate(binding.dataB.text.toString())) {
                                resultValue = ConvertUtilities().checkConvert(
                                    systemOfConvert,
                                    binding.dataB.text.toString().replace(",","").toDouble(),
                                    binding.SpinnerMenuB.selectedItem.toString(),
                                    binding.SpinnerMenuA.selectedItem.toString()
                                )
                            }
                        } else {
                            resultValue = ConvertUtilities().checkConvert(
                                systemOfConvert,
                                binding.dataB.text.toString().replace(",","").toDouble(),
                                binding.SpinnerMenuB.selectedItem.toString(),
                                binding.SpinnerMenuA.selectedItem.toString()
                            )
                        }

                        // Insert the value on the result field
                        statePrint = false
                        binding.dataA.setText(format.format(resultValue.toDouble()))
                        statePrint = true
                    } else {
                        if (binding.dataA.text!!.isNotEmpty()) {
                            binding.dataA.setText("")
                            binding.textInputLayoutA.prefixText = ""
                            binding.textInputLayoutB.prefixText = ""
                        }
                    }
                }
            }
        })

        /**
         * Event to control: button come back to the parent
         */
        binding.buttonBack.setOnClickListener {
            finish()
        }
    }
}