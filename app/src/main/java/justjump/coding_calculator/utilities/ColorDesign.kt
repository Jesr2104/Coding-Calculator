package justjump.coding_calculator.utilities

import android.graphics.Color
import android.graphics.Paint
import justjump.coding_calculator.Functions

class ColorDesign {

    /**
     *  Convert a RGB Color to it corresponding HSL values.
     *
     *  @param: colorInt code.
     *  @return: an array containing the 3 HSL values.
     */
    private fun getHSLColorFromRGB(color: Int): FloatArray {

        val hsl = FloatArray(3)
        val r = Color.red(color) / 255f
        val g = Color.green(color) / 255f
        val b = Color.blue(color) / 255f

        val max = Math.max(r, Math.max(g, b))
        val min = Math.min(r, Math.min(g, b))
        hsl[2] = (max + min) / 2

        if (max == min) {
            hsl[1] = 0f
            hsl[0] = hsl[1]

        } else {
            val d = max - min

            hsl[1] = if (hsl[2] > 0.5f) d / (2f - max - min) else d / (max + min)
            when (max) {
                r -> hsl[0] = (g - b) / d + (if (g < b) 6 else 0)
                g -> hsl[0] = (b - r) / d + 2
                b -> hsl[0] = (r - g) / d + 4
            }
            hsl[0] /= 6f
        }
        return hsl
    }

    /**
     *  Convert a HLS Color to it corresponding RGB values.
     *
     *  @param: Array<Float> with the 3 HSL values.
     *  @return: an array containing the 3 RGB color values.
     */
    private fun getRGBColorFromHSL(hsl: FloatArray): Int {
        val r: Float
        val g: Float
        val b: Float

        val h = hsl[0]
        val s = hsl[1]
        val l = hsl[2]

        if (s == 0f) {
            b = l
            g = b
            r = g
        } else {
            val q = if (l < 0.5f) l * (1 + s) else l + s - l * s
            val p = 2 * l - q
            r = hue2rgb(p, q, h + 1f / 3)
            g = hue2rgb(p, q, h)
            b = hue2rgb(p, q, h - 1f / 3)
        }

        return Color.rgb((r * 255).toInt(), (g * 255).toInt(), (b * 255).toInt())
    }

    private fun hue2rgb(p: Float, q: Float, t: Float): Float {
        var valueT = t
        if (valueT < 0) valueT += 1f
        if (valueT > 1) valueT -= 1f
        if (valueT < 1f / 6) return p + (q - p) * 6f * valueT
        if (valueT < 1f / 2) return q
        return if (valueT < 2f / 3) p + (q - p) * (2f / 3 - valueT) * 6f else p
    }

    /**
     *  calculates the corresponding complementary color.
     *
     *  @param: paint of the primary color.
     *  @return: String with the RGB color.
     */
    fun getComplementary(paint: Paint): Int {

        val colorHSL = getHSLColorFromRGB(paint.color)

        // with this code we calculate the complementary
        colorHSL[0] = (((colorHSL[0] * 360) + 180) % 360) / 360
        colorHSL[1] = colorHSL[1]
        colorHSL[2] = colorHSL[2]

        val color = getRGBColorFromHSL(colorHSL)

        val red = Color.red(color)
        val green = Color.green(color)
        val blue = Color.blue(color)

        return Color.parseColor("#" + Functions().convertToHex(red)+Functions().convertToHex(green)+Functions().convertToHex(blue))
    }

    fun getSplitComplementary(paint: Paint): Array<Int> {

        val colorHSL = getHSLColorFromRGB(paint.color)
        var resultColors: Array<Int> = arrayOf(0,0)
        val color150: FloatArray = FloatArray(3)
        val color210: FloatArray = FloatArray(3)

        // with this code we calculate the complementary
        color150[0] = (((colorHSL[0] * 360) + 150) % 360) / 360
        color150[1] = colorHSL[1]
        color150[2] = colorHSL[2]

        // with this code we calculate the complementary
        color210[0] = (((colorHSL[0] * 360) + 210) % 360) / 360
        color210[1] = colorHSL[1]
        color210[2] = colorHSL[2]

        val color1 = getRGBColorFromHSL(color150)
        val color2 = getRGBColorFromHSL(color210)

        val redColor1 = Color.red(color1)
        val greenColor1 = Color.green(color1)
        val blueColor1 = Color.blue(color1)

        val redColor2 = Color.red(color2)
        val greenColor2 = Color.green(color2)
        val blueColor2 = Color.blue(color2)

        resultColors[0] = Color.parseColor("#" + Functions().convertToHex(redColor1)+Functions().convertToHex(greenColor1)+Functions().convertToHex(blueColor1))
        resultColors[1] = Color.parseColor("#" + Functions().convertToHex(redColor2)+Functions().convertToHex(greenColor2)+Functions().convertToHex(blueColor2))

        return resultColors
    }

    fun getAnalogous(paint: Paint): Array<Int> {

        val colorHSL = getHSLColorFromRGB(paint.color)
        var resultColors: Array<Int> = arrayOf(0,0,0,0,0,0)
        val color30: FloatArray = FloatArray(3)
        val color60: FloatArray = FloatArray(3)
        val color90: FloatArray = FloatArray(3)
        val color120: FloatArray = FloatArray(3)
        val color150: FloatArray = FloatArray(3)
        val color180: FloatArray = FloatArray(3)

        // with this code we calculate the complementary
        color30[0] = (((colorHSL[0] * 360) + 30) % 360) / 360
        color30[1] = colorHSL[1]
        color30[2] = colorHSL[2]

        // with this code we calculate the complementary
        color60[0] = (((colorHSL[0] * 360) + 60) % 360) / 360
        color60[1] = colorHSL[1]
        color60[2] = colorHSL[2]

        // with this code we calculate the complementary
        color90[0] = (((colorHSL[0] * 360) + 90) % 360) / 360
        color90[1] = colorHSL[1]
        color90[2] = colorHSL[2]

        // with this code we calculate the complementary
        color120[0] = (((colorHSL[0] * 360) + 120) % 360) / 360
        color120[1] = colorHSL[1]
        color120[2] = colorHSL[2]

        // with this code we calculate the complementary
        color150[0] = (((colorHSL[0] * 360) + 150) % 360) / 360
        color150[1] = colorHSL[1]
        color150[2] = colorHSL[2]

        // with this code we calculate the complementary
        color180[0] = (((colorHSL[0] * 360) + 180) % 360) / 360
        color180[1] = colorHSL[1]
        color180[2] = colorHSL[2]

        val color1 = getRGBColorFromHSL(color30)
        val color2 = getRGBColorFromHSL(color60)
        val color3 = getRGBColorFromHSL(color90)
        val color4 = getRGBColorFromHSL(color120)
        val color5 = getRGBColorFromHSL(color150)
        val color6 = getRGBColorFromHSL(color180)

        val redColor1 = Color.red(color1)
        val greenColor1 = Color.green(color1)
        val blueColor1 = Color.blue(color1)

        val redColor2 = Color.red(color2)
        val greenColor2 = Color.green(color2)
        val blueColor2 = Color.blue(color2)

        val redColor3 = Color.red(color3)
        val greenColor3 = Color.green(color3)
        val blueColor3 = Color.blue(color3)

        val redColor4 = Color.red(color4)
        val greenColor4 = Color.green(color4)
        val blueColor4 = Color.blue(color4)

        val redColor5 = Color.red(color5)
        val greenColor5 = Color.green(color5)
        val blueColor5 = Color.blue(color5)

        val redColor6 = Color.red(color6)
        val greenColor6 = Color.green(color6)
        val blueColor6 = Color.blue(color6)

        resultColors[0] = Color.parseColor("#" + Functions().convertToHex(redColor1)+Functions().convertToHex(greenColor1)+Functions().convertToHex(blueColor1))
        resultColors[1] = Color.parseColor("#" + Functions().convertToHex(redColor2)+Functions().convertToHex(greenColor2)+Functions().convertToHex(blueColor2))
        resultColors[2] = Color.parseColor("#" + Functions().convertToHex(redColor3)+Functions().convertToHex(greenColor3)+Functions().convertToHex(blueColor3))
        resultColors[3] = Color.parseColor("#" + Functions().convertToHex(redColor4)+Functions().convertToHex(greenColor4)+Functions().convertToHex(blueColor4))
        resultColors[4] = Color.parseColor("#" + Functions().convertToHex(redColor5)+Functions().convertToHex(greenColor5)+Functions().convertToHex(blueColor5))
        resultColors[5] = Color.parseColor("#" + Functions().convertToHex(redColor6)+Functions().convertToHex(greenColor6)+Functions().convertToHex(blueColor6))

        return resultColors
    }

    fun getTriadic(paint: Paint): Array<Int> {
        val colorHSL = getHSLColorFromRGB(paint.color)
        var resultColors: Array<Int> = arrayOf(0,0)
        val color120: FloatArray = FloatArray(3)
        val color240: FloatArray = FloatArray(3)

        // with this code we calculate the complementary
        color120[0] = (((colorHSL[0] * 360) + 120) % 360) / 360
        color120[1] = colorHSL[1]
        color120[2] = colorHSL[2]

        // with this code we calculate the complementary
        color240[0] = (((colorHSL[0] * 360) + 240) % 360) / 360
        color240[1] = colorHSL[1]
        color240[2] = colorHSL[2]

        val color1 = getRGBColorFromHSL(color120)
        val color2 = getRGBColorFromHSL(color240)

        val redColor1 = Color.red(color1)
        val greenColor1 = Color.green(color1)
        val blueColor1 = Color.blue(color1)

        val redColor2 = Color.red(color2)
        val greenColor2 = Color.green(color2)
        val blueColor2 = Color.blue(color2)

        resultColors[0] = Color.parseColor("#" + Functions().convertToHex(redColor1)+Functions().convertToHex(greenColor1)+Functions().convertToHex(blueColor1))
        resultColors[1] = Color.parseColor("#" + Functions().convertToHex(redColor2)+Functions().convertToHex(greenColor2)+Functions().convertToHex(blueColor2))

        return resultColors
    }

    fun getTetradic(paint: Paint): Array<Int> {
        val colorHSL = getHSLColorFromRGB(paint.color)
        var resultColors: Array<Int> = arrayOf(0,0,0,0)
        val color90: FloatArray = FloatArray(3)
        val color180: FloatArray = FloatArray(3)
        val color270: FloatArray = FloatArray(3)

        // with this code we calculate the complementary
        color90[0] = (((colorHSL[0] * 360) + 90) % 360) / 360
        color90[1] = colorHSL[1]
        color90[2] = colorHSL[2]

        // with this code we calculate the complementary
        color180[0] = (((colorHSL[0] * 360) + 180) % 360) / 360
        color180[1] = colorHSL[1]
        color180[2] = colorHSL[2]

        // with this code we calculate the complementary
        color270[0] = (((colorHSL[0] * 360) + 270) % 360) / 360
        color270[1] = colorHSL[1]
        color270[2] = colorHSL[2]

        val color1 = getRGBColorFromHSL(color90)
        val color2 = getRGBColorFromHSL(color180)
        val color3 = getRGBColorFromHSL(color270)

        val redColor1 = Color.red(color1)
        val greenColor1 = Color.green(color1)
        val blueColor1 = Color.blue(color1)

        val redColor2 = Color.red(color2)
        val greenColor2 = Color.green(color2)
        val blueColor2 = Color.blue(color2)

        val redColor3 = Color.red(color3)
        val greenColor3 = Color.green(color3)
        val blueColor3 = Color.blue(color3)

        resultColors[0] = Color.parseColor("#" + Functions().convertToHex(redColor1)+Functions().convertToHex(greenColor1)+Functions().convertToHex(blueColor1))
        resultColors[1] = Color.parseColor("#" + Functions().convertToHex(redColor2)+Functions().convertToHex(greenColor2)+Functions().convertToHex(blueColor2))
        resultColors[2] = Color.parseColor("#" + Functions().convertToHex(redColor3)+Functions().convertToHex(greenColor3)+Functions().convertToHex(blueColor3))

        return resultColors
    }
}