package justjump.coding_calculator.utilities

class PrefixText {

    fun getPrefix(type: String): String {
        when(type){
            "Acres (ac)" -> {
                return "ac"
            }
            "Ares (a)" -> {
                return "a"
            }
            "Hectares (ha)" -> {
                return "ha"
            }
            "Squares centimetres (cm²)" -> {
                return "cm²"
            }
            "Squares feet (ft²)" -> {
                return "ft²"
            }
            "Squares inches (in²)" -> {
                return "in²"
            }
            "Squares metres (m²)" -> {
                return "m²"
            }
            "Millimetres (mm)" -> {
                return "mm"
            }
            "Centimetres (cm)" -> {
                return "cm"
            }
            "Metres (m)" -> {
                return "m"
            }
            "Kilometres (km)" -> {
                return "km"
            }
            "Inches (in)" -> {
                return "in"
            }
            "Feet (ft)" -> {
                return "ft"
            }
            "Yards (yd)" -> {
                return "yd"
            }
            "Miles (mi)" -> {
                return "mi"
            }
            "Nautical miles (NM)" -> {
                return "NM"
            }
            "Mils (mil)" -> {
                return "mil"
            }
            "Milliseconds (ms)" -> {
                return "ms"
            }
            "Seconds (s)" -> {
                return "s"
            }
            "Minutes (min)" -> {
                return "min"
            }
            "Hours (h)" -> {
                return "h"
            }
            "Days (d)" -> {
                return "d"
            }
            "Weeks (wk)" -> {
                return "wk"
            }
            "Celsius (°C)" -> {
                return "°C"
            }
            "Fahrenheit (°F)" -> {
                return "°F"
            }
            "Kelvin (K)" -> {
                return "K"
            }
            "UK gallons (gal)" -> {
                return "gal"
            }
            "US gallons (gal)" -> {
                return "gal"
            }
            "Litres (l)" -> {
                return "l"
            }
            "Millilitres (ml)" -> {
                return "ml"
            }
            "Cubic centimetres (cc, cm³)" -> {
                return "cc, cml³"
            }
            "Cubic metres (m³)" -> {
                return "m³"
            }
            "Cubic inches (in³)" -> {
                return "in³"
            }
            "Cubic feet (ft³)" -> {
                return "ft³"
            }
            "Tons (t)" -> {
                return "t"
            }
            "UK tons (t)" -> {
                return "t"
            }
            "US tons  (t)" -> {
                return "t"
            }
            "Pounds (lb)" -> {
                return "lb"
            }
            "Ounces (oz)" -> {
                return "oz"
            }
            "Kilogrammes (kg)" -> {
                return "kg"
            }
            "Grams (g)" -> {
                return "g"
            }
            "Bits (bit)" -> {
                return "bit"
            }
            "Bytes (B)" -> {
                return "B"
            }
            "Kilobytes (KB)" -> {
                return "KB"
            }
            "Megabytes (MB)" -> {
                return "MB"
            }
            "Gigabytes (GB)" -> {
                return "GB"
            }
            "Terabytes (TB)" -> {
                return "TB"
            }
            "Petabytes (PB)" -> {
                return "PB"
            }
            "Exabytes (XB)" -> {
                return "XB"
            }
            "Metres per second (m/s)" -> {
                return "m/s"
            }
            "Metres per hour (m/h)" -> {
                return "m/h"
            }
            "Kilometres per second (km/s)" -> {
                return "km/s"
            }
            "Kilometres per hour (km/h)" -> {
                return "km/h"
            }
            "Inches per second (in/s)" -> {
                return "in/s"
            }
            "Inches per hour (in/h)" -> {
                return "in/h"
            }
            "Feet per second (ft/s)" -> {
                return "ft/s"
            }
            "Feet per hour (ft/h)" -> {
                return "ft/h"
            }
            "Miles per second (mi/s)" -> {
                return "mi/s"
            }
            "Miles per hour (mi/h)" -> {
                return "mi/h"
            }
            "Knots (kn)" -> {
                return "kn"
            }
        }
        return "error"
    }
}