package justjump.coding_calculator.viewmodel

import android.graphics.Color
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ColorCodeViewModel: ViewModel() {

    var colorRGB = MutableLiveData<Int>()
    private var colorHLS = MutableLiveData<Array<String>>()
    private var colorHEX = MutableLiveData<Array<String>>()

    init {
        colorRGB.value = Color.parseColor("#FFFFFF")
        colorHLS.value = arrayOf("","","")
        colorHEX.value = arrayOf("","","")
    }

    fun getRGBColor(): Int {
        return colorRGB.value!!
    }

    fun setRGBColor(color: Int) {
        colorRGB.value = color
    }


    fun loadHarmonyColors() {

    }

}