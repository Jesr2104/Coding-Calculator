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
import kotlinx.android.synthetic.main.activity_converter.*


class Converter : AppCompatActivity(){

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
        "Cetres (m)",
        "Kilometres (km)",
        "Inches (in)",
        "Feet (ft)",
        "Yards (yd)",
        "Mile (mi)",
        "Nautical (nm)",
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
        "Celsius (°c)",
        "Fahrenheit (°f)",
        "Kelvin (k)"
    )
    private val menuVolumeList = listOf(
        "Uk gallons (gas)",
        "Us gallons (gas)",
        "Litres (l)",
        "Millilitres (ml)",
        "Cubic centimetres (cc, cm³)",
        "Cubic metres (m³)",
        "Cubic inches (in³)",
        "Cubic feet (ft³)"
    )
    private val menuWeightList = listOf(
        "Tons (t)",
        "Uk tons (t)",
        "Us tons  (t)",
        "Pounds (lb)",
        "Ounces (oz)",
        "Kilogrammes (kg)",
        "Grams (g)"
    )
    private val menuDataList = listOf(
        "Bits (bit)",
        "Bytes (b)",
        "Kilobytes (kb)",
        "Megabytes (mb)",
        "Gigabytes (gb)",
        "Terabytes (tb)",
        "Petabytes (pb)",
        "Exabytes (xb)",
        "Zettabytes (zb)",
        "Yottabytes (yb)",
        "Brontonbytes (bb)",
        "Geopbytes (gpb)"
    )
    private val menuSpeedList = listOf(
        "Metres per second (m/s)",
        "Metres per hour (m/h)",
        "Kilometres per second (km/s)",
        "Kilometres per hour (km/h)",
        "Inches per second (in/h)",
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
            R.layout.support_simple_spinner_dropdown_item,
            menuAreaList
        )
        SpinnerMenuA.adapter = adapterMenu
        SpinnerMenuB.adapter = adapterMenu

        // valor to check what system of convert is selected
        var systemOfConvert: Int = 1

        // Event when you press button Area
        Button_Area.setOnClickListener {

            val adapterMenu = ArrayAdapter(
                this,
                R.layout.support_simple_spinner_dropdown_item,
                menuAreaList
            )
            SpinnerMenuA.adapter = adapterMenu
            SpinnerMenuB.adapter = adapterMenu

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
                R.layout.support_simple_spinner_dropdown_item,
                menuLengthList
            )
            SpinnerMenuA.adapter = adapterMenu
            SpinnerMenuB.adapter = adapterMenu

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
                R.layout.support_simple_spinner_dropdown_item,
                menuTimeList
            )
            SpinnerMenuA.adapter = adapterMenu
            SpinnerMenuB.adapter = adapterMenu

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
                R.layout.support_simple_spinner_dropdown_item,
                menuTemperatureList
            )
            SpinnerMenuA.adapter = adapterMenu
            SpinnerMenuB.adapter = adapterMenu

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
                R.layout.support_simple_spinner_dropdown_item,
                menuVolumeList
            )
            SpinnerMenuA.adapter = adapterMenu
            SpinnerMenuB.adapter = adapterMenu

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
                R.layout.support_simple_spinner_dropdown_item,
                menuWeightList
            )
            SpinnerMenuA.adapter = adapterMenu
            SpinnerMenuB.adapter = adapterMenu

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
                R.layout.support_simple_spinner_dropdown_item,
                menuDataList
            )
            SpinnerMenuA.adapter = adapterMenu
            SpinnerMenuB.adapter = adapterMenu

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
                R.layout.support_simple_spinner_dropdown_item,
                menuSpeedList
            )
            SpinnerMenuA.adapter = adapterMenu
            SpinnerMenuB.adapter = adapterMenu

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
            override fun onItemSelected(parentView: AdapterView<*>?, selectedItemView: View?, position: Int, id: Long) {
                if (dataA.text.toString().isNotEmpty()) {
                    val resultValue = ConvertUtilities().checkConvert(
                        systemOfConvert,
                        dataA.text.toString().toDouble(),
                        SpinnerMenuA.selectedItem.toString(),
                        SpinnerMenuB.selectedItem.toString()
                    )

                    // Insert the value on the result field
                    dataB.setText(resultValue)
                }
            }
            override fun onNothingSelected(parentView: AdapterView<*>?) {}
        }

        dataA.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {

                if (dataA.text.toString().isNotEmpty()) {
                    val resultValue = ConvertUtilities().checkConvert(
                        systemOfConvert,
                        dataA.text.toString().toDouble(),
                        SpinnerMenuA.selectedItem.toString(),
                        SpinnerMenuB.selectedItem.toString()
                    )

                    // Insert the value on the result field
                    dataB.setText(resultValue)
                } else {
                    dataB.setText("")
                }
            }
        })

        dataB.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {

                val text: String = SpinnerMenuA.selectedItem.toString()
                //Toast.makeText(applicationContext, "$text", Toast.LENGTH_SHORT).show()
            }
        })
    }
}