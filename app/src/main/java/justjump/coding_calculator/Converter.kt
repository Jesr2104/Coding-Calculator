package justjump.coding_calculator

import android.content.pm.ActivityInfo
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_converter.*

class Converter : AppCompatActivity(){

    private val menuAreaList = listOf(
        "Acres (AC)",
        "Ares (A)",
        "Hectares (HA)",
        "Squares Centimetres (CM²)",
        "Squares Feet (FT²)",
        "Squares Inches (IN²)",
        "Squares Metres (M²)"
    )
    private val menuLengthList = listOf(
        "Millimetres (MM)",
        "Centimetres (CM)",
        "Metres (M)",
        "Kilometres (KM)",
        "Inches (IN)",
        "Feet (FT)",
        "Yards (YD)",
        "Mile (MI)",
        "Nautical (NM)",
        "Mils (MIL)"
    )
    private val menuTimeList = listOf(
        "Milliseconds (MS)",
        "Seconds (S)",
        "Minutes (Min)",
        "Hours (H)",
        "Days (D)",
        "Weeks (WK)"
    )
    private val menuTemperatureList = listOf(
        "Celsius (°C)",
        "Fahrenheit (°F)",
        "Kelvin (K)"
    )
    private val menuVolumeList = listOf(
        "Uk Gallons (GAS)",
        "Us Gallons (GAS)",
        "Litres (L)",
        "Millilitres (ML)",
        "Cubic Centimetres (CC, CM³)",
        "Cubic Metres (M³)",
        "Cubic Inches (IN³)",
        "Cubic Feet (FT³)"
    )
    private val menuWeightList = listOf(
        "Tons (T)",
        "Uk Tons (T)",
        "Us Tons  (T)",
        "Pounds (LB)",
        "Ounces (OZ)",
        "Kilogrammes (KG)",
        "Grams (G)"
    )
    private val menuDataList = listOf(
        "Bits (Bit)",
        "Bytes (B)",
        "Kilobytes (KB)",
        "Megabytes (MB)",
        "Gigabytes (GB)",
        "Terabytes (TB)",
        "Petabytes (PB)",
        "Exabytes (XB)",
        "Zettabytes (ZB)",
        "Yottabytes (YB)",
        "Brontonbytes (BB)",
        "Geopbytes (GPB)"
    )
    private val menuSpeedList = listOf(
        "Metres per second (M/S)",
        "Metres per hour (M/H)",
        "kilometres per second (KM/S)",
        "kilometres per hour (KM/H)",
        "Inches per second (IN/H)",
        "Inches per hour (IN/H)",
        "Feet per second (FT/S)",
        "Feet per hour (FT/H)",
        "Miles per second (MI/S)",
        "Miles per hour (MI/H)",
        "Knots (KN)"
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

        // Event when you press button Area
        Button_Area.setOnClickListener {

            val adapterMenu = ArrayAdapter(
                this,
                R.layout.support_simple_spinner_dropdown_item,
                menuAreaList
            )
            SpinnerMenuA.adapter = adapterMenu
            SpinnerMenuB.adapter = adapterMenu

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

        dataA.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {

                when (SpinnerMenuA.selectedItem.toString()) {
                    "Acres (AC)" -> {

                        SpinnerMenuB.selectedItem.toString()

                    }
                    "Ares (A)" -> {

                    }
                    "Hectares (HA)" -> {

                    }
                    "Squares Centimetres (CM²)" -> {

                    }
                    "Squares Feet (FT²)" -> {

                    }
                    "Squares Inches (IN²)" -> {

                    }
                    "Squares Metres (M²)" -> {

                    }
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