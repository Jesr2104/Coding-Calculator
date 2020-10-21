package justjump.coding_calculator.viewmodel

import android.graphics.Color
import android.graphics.Paint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import justjump.coding_calculator.utilities.Functions
import justjump.coding_calculator.utilities.ColorDesign

class ViewModelColorCode: ViewModel() {

    var colorRGB = MutableLiveData<Int>()

    private var colorHLS = MutableLiveData<Array<String>>()
    private var colorHEX = MutableLiveData<Array<String>>()

    init {
        colorRGB.value = Color.parseColor("#000000")
        colorHLS.value = arrayOf("","","")
        colorHEX.value = arrayOf("","","")
    }

    fun getRGBColor(): Int {
        return colorRGB.value!!
    }

    fun setRGBColor(color: Int) {
        colorRGB.postValue(color)
    }

    fun getRed(): String? {
        val r = Color.red(getRGBColor())
        return Functions().convertDecToHex(r)
    }

    fun getGreen(): String? {
        val g = Color.green(getRGBColor())
        return Functions().convertDecToHex(g)
    }

    fun getBlue(): String? {
        val b = Color.blue(getRGBColor())
        return Functions().convertDecToHex(b)
    }

    fun loadComplementaryColor(): Int {
        val paint = Paint()
        val r = Color.red(getRGBColor())
        val g = Color.green(getRGBColor())
        val b = Color.blue(getRGBColor())

        paint.color = Color.parseColor("#${Functions().convertDecToHex(r)}${Functions().convertDecToHex(g)}${Functions().convertDecToHex(b)}")
        return ColorDesign().getComplementary(paint)
    }

    fun loadSplitComplementaryColor(): Array<Int> {
        val paint = Paint()
        val r = Color.red(getRGBColor())
        val g = Color.green(getRGBColor())
        val b = Color.blue(getRGBColor())

        paint.color = Color.parseColor("#${Functions().convertDecToHex(r)}${Functions().convertDecToHex(g)}${Functions().convertDecToHex(b)}")
        return ColorDesign().getSplitComplementary(paint)
    }

    fun loadAnalogous(): Array<Int> {
        val paint = Paint()
        val r = Color.red(getRGBColor())
        val g = Color.green(getRGBColor())
        val b = Color.blue(getRGBColor())

        paint.color = Color.parseColor("#${Functions().convertDecToHex(r)}${Functions().convertDecToHex(g)}${Functions().convertDecToHex(b)}")
        return ColorDesign().getAnalogous(paint)
    }

    fun loadTriadic(): Array<Int> {
        val paint = Paint()
        val r = Color.red(getRGBColor())
        val g = Color.green(getRGBColor())
        val b = Color.blue(getRGBColor())

        paint.color = Color.parseColor("#${Functions().convertDecToHex(r)}${Functions().convertDecToHex(g)}${Functions().convertDecToHex(b)}")
        return ColorDesign().getTriadic(paint)
    }

    fun loadTetradicColor(): Array<Int> {
        val paint = Paint()
        val r = Color.red(getRGBColor())
        val g = Color.green(getRGBColor())
        val b = Color.blue(getRGBColor())

        paint.color = Color.parseColor("#${Functions().convertDecToHex(r)}${Functions().convertDecToHex(g)}${Functions().convertDecToHex(b)}")
        return ColorDesign().getTetradic(paint)
    }
}