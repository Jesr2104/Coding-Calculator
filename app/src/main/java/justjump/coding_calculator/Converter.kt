package justjump.coding_calculator

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import justjump.coding_calculator.R

class Converter : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_converter)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }
}