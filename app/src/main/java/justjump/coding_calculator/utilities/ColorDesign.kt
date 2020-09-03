package justjump.coding_calculator.utilities

import android.graphics.Color
import android.graphics.Paint

class ColorDesign {

    /**
     *  Convert a RGB Color to it corresponding HSL values.
     *
     *  @param:
     *  @return: an array containing the 3 HSL values.
     */
    private fun getHSLColorFromRGB(color: Int): FloatArray {

        val hsl: FloatArray = FloatArray(3)

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

        //println("Color en HSL => HSL(${(((hsl[0]*360) + 180) % 360).toInt()},${(hsl[1]*100).toInt()},${(hsl[2]*100).toInt()})")

        return hsl
    }

    /**
     *  Convert a RGB Color to it corresponding HSL values.
     *
     *  @param:
     *  @return: an array containing the 3 HSL values.
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

    fun getComplementary(paint: Paint): String {

        val colorHSL = getHSLColorFromRGB(paint.color)
        val color = getRGBColorFromHSL(colorHSL)

        val red = Color.red(color)
        val blue = Color.blue(color)
        val green = Color.green(color)

        return "RGB($red,$green,$blue)"
    }


}