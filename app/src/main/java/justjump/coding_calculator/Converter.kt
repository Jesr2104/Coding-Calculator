package justjump.coding_calculator

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.graphics.Color
import android.os.Bundle
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_converter.*


class Converter : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_converter)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        var converterSelect = 1
        var menuSelected = R.menu.convert_area

        Button_Area.setOnClickListener {
            converterSelect = 1
            menuSelected = R.menu.convert_area

            textArea.setTextColor(Color.rgb(150,150,150))

            Title_TypeConvert.text = "Area"

            // change color lost selection
            textLength.setTextColor(resources.getColor(R.color.Front_ColorGrey))
            textTime.setTextColor(resources.getColor(R.color.Front_ColorGrey))
            textTemp.setTextColor(resources.getColor(R.color.Front_ColorGrey))
            textVolume.setTextColor(resources.getColor(R.color.Front_ColorGrey))
            textWeight.setTextColor(resources.getColor(R.color.Front_ColorGrey))
            textData.setTextColor(resources.getColor(R.color.Front_ColorGrey))
            textSpeed.setTextColor(resources.getColor(R.color.Front_ColorGrey))

            btnMenuPopupA.text = getString(R.string.Select_option)
            btnMenuPopupB.text = getString(R.string.Select_option)
        }

        Button_Length.setOnClickListener {
            converterSelect = 2
            menuSelected = R.menu.convert_length

            textLength.setTextColor(Color.rgb(150,150,150))

            Title_TypeConvert.text = "Length"

            // change color lost selection
            textArea.setTextColor(resources.getColor(R.color.Front_ColorGrey))
            textTime.setTextColor(resources.getColor(R.color.Front_ColorGrey))
            textTemp.setTextColor(resources.getColor(R.color.Front_ColorGrey))
            textVolume.setTextColor(resources.getColor(R.color.Front_ColorGrey))
            textWeight.setTextColor(resources.getColor(R.color.Front_ColorGrey))
            textData.setTextColor(resources.getColor(R.color.Front_ColorGrey))
            textSpeed.setTextColor(resources.getColor(R.color.Front_ColorGrey))

            btnMenuPopupA.text = getString(R.string.Select_option)
            btnMenuPopupB.text = getString(R.string.Select_option)
        }

        Button_Time.setOnClickListener {
            converterSelect = 3
            menuSelected = R.menu.convert_time

            textTime.setTextColor(Color.rgb(150,150,150))

            Title_TypeConvert.text = "Time"

            // change color lost selection
            textArea.setTextColor(resources.getColor(R.color.Front_ColorGrey))
            textLength.setTextColor(resources.getColor(R.color.Front_ColorGrey))
            textTemp.setTextColor(resources.getColor(R.color.Front_ColorGrey))
            textVolume.setTextColor(resources.getColor(R.color.Front_ColorGrey))
            textWeight.setTextColor(resources.getColor(R.color.Front_ColorGrey))
            textData.setTextColor(resources.getColor(R.color.Front_ColorGrey))
            textSpeed.setTextColor(resources.getColor(R.color.Front_ColorGrey))

            btnMenuPopupA.text = getString(R.string.Select_option)
            btnMenuPopupB.text = getString(R.string.Select_option)
        }

        Button_Temp.setOnClickListener {
            converterSelect = 4
            menuSelected = R.menu.convert_temperature

            textTemp.setTextColor(Color.rgb(150,150,150))

            Title_TypeConvert.text = "Temperature"

            // change color lost selection
            textArea.setTextColor(resources.getColor(R.color.Front_ColorGrey))
            textLength.setTextColor(resources.getColor(R.color.Front_ColorGrey))
            textTime.setTextColor(resources.getColor(R.color.Front_ColorGrey))
            textVolume.setTextColor(resources.getColor(R.color.Front_ColorGrey))
            textWeight.setTextColor(resources.getColor(R.color.Front_ColorGrey))
            textData.setTextColor(resources.getColor(R.color.Front_ColorGrey))
            textSpeed.setTextColor(resources.getColor(R.color.Front_ColorGrey))

            btnMenuPopupA.text = getString(R.string.Select_option)
            btnMenuPopupB.text = getString(R.string.Select_option)
        }

        Button_Volume.setOnClickListener {
            converterSelect = 5
            menuSelected = R.menu.convert_volume

            textVolume.setTextColor(Color.rgb(150,150,150))

            Title_TypeConvert.text = "Volume"

            // change color lost selection
            textArea.setTextColor(resources.getColor(R.color.Front_ColorGrey))
            textLength.setTextColor(resources.getColor(R.color.Front_ColorGrey))
            textTime.setTextColor(resources.getColor(R.color.Front_ColorGrey))
            textTemp.setTextColor(resources.getColor(R.color.Front_ColorGrey))
            textWeight.setTextColor(resources.getColor(R.color.Front_ColorGrey))
            textData.setTextColor(resources.getColor(R.color.Front_ColorGrey))
            textSpeed.setTextColor(resources.getColor(R.color.Front_ColorGrey))

            btnMenuPopupA.text = getString(R.string.Select_option)
            btnMenuPopupB.text = getString(R.string.Select_option)
        }

        Button_Weight.setOnClickListener {
            converterSelect = 6
            menuSelected = R.menu.convert_mass

            textWeight.setTextColor(Color.rgb(150,150,150))

            Title_TypeConvert.text = "Weight"

            // change color lost selection
            textArea.setTextColor(resources.getColor(R.color.Front_ColorGrey))
            textLength.setTextColor(resources.getColor(R.color.Front_ColorGrey))
            textTime.setTextColor(resources.getColor(R.color.Front_ColorGrey))
            textTemp.setTextColor(resources.getColor(R.color.Front_ColorGrey))
            textVolume.setTextColor(resources.getColor(R.color.Front_ColorGrey))
            textData.setTextColor(resources.getColor(R.color.Front_ColorGrey))
            textSpeed.setTextColor(resources.getColor(R.color.Front_ColorGrey))

            btnMenuPopupA.text = getString(R.string.Select_option)
            btnMenuPopupB.text = getString(R.string.Select_option)
        }

        Button_Data.setOnClickListener {
            converterSelect = 7
            menuSelected = R.menu.convert_data

            textData.setTextColor(Color.rgb(150,150,150))

            Title_TypeConvert.text = "Data"

            // change color lost selection
            textArea.setTextColor(resources.getColor(R.color.Front_ColorGrey))
            textLength.setTextColor(resources.getColor(R.color.Front_ColorGrey))
            textTime.setTextColor(resources.getColor(R.color.Front_ColorGrey))
            textTemp.setTextColor(resources.getColor(R.color.Front_ColorGrey))
            textVolume.setTextColor(resources.getColor(R.color.Front_ColorGrey))
            textWeight.setTextColor(resources.getColor(R.color.Front_ColorGrey))
            textSpeed.setTextColor(resources.getColor(R.color.Front_ColorGrey))

            btnMenuPopupA.text = getString(R.string.Select_option)
            btnMenuPopupB.text = getString(R.string.Select_option)
        }

        Button_Speed.setOnClickListener {
            converterSelect = 8
            menuSelected = R.menu.convert_speed

            textSpeed.setTextColor(Color.rgb(150,150,150))

            Title_TypeConvert.text = "Speed"

            // change color lost selection
            textArea.setTextColor(resources.getColor(R.color.Front_ColorGrey))
            textLength.setTextColor(resources.getColor(R.color.Front_ColorGrey))
            textTime.setTextColor(resources.getColor(R.color.Front_ColorGrey))
            textTemp.setTextColor(resources.getColor(R.color.Front_ColorGrey))
            textVolume.setTextColor(resources.getColor(R.color.Front_ColorGrey))
            textWeight.setTextColor(resources.getColor(R.color.Front_ColorGrey))
            textData.setTextColor(resources.getColor(R.color.Front_ColorGrey))

            btnMenuPopupA.text = getString(R.string.Select_option)
            btnMenuPopupB.text = getString(R.string.Select_option)
        }

        btnMenuPopupA.setOnClickListener {
            val popup = PopupMenu(applicationContext, it)
            val inflater = popup.menuInflater
            inflater.inflate(menuSelected, popup.menu)

            popup.setOnMenuItemClickListener { item ->
                btnMenuPopupA.text = item.title
                Toast.makeText(this, "You Clicked : ${item.title}", Toast.LENGTH_SHORT).show()
                true
            }

            popup.show()
        }

        btnMenuPopupB.setOnClickListener {
            val popup = PopupMenu(applicationContext, it)
            val inflater = popup.menuInflater
            inflater.inflate(menuSelected, popup.menu)

            popup.setOnMenuItemClickListener { item ->
                btnMenuPopupB.text = item.title
                Toast.makeText(this, "You Clicked : ${item.title}", Toast.LENGTH_SHORT).show()
                true
            }

            popup.show()
        }
    }
}