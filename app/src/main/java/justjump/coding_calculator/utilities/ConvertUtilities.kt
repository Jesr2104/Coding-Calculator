package justjump.coding_calculator.utilities

class ConvertUtilities {

    fun checkConvert(
        systemOfConvert: Int,
        value: Double,
        spinnerA: String,
        spinnerB: String
    ): String {
        var result = ""

        if (systemOfConvert == 1) {

            when (spinnerA) {
                "Acres (ac)" -> {
                    when (spinnerB) {
                        "Acres (ac)" -> {
                            result = value.toString()
                        }
                        "Ares (a)" -> {
                            result = (value * 40.468564224).toString()
                        }
                        "Hectares (ha)" -> {
                            result = (value * 0.4046856422).toString()
                        }
                        "Squares centimetres (cm²)" -> {
                            result = (value * 40468564.224).toString()
                        }
                        "Squares feet (ft²)" -> {
                            result = (value * 43560).toString()
                        }
                        "Squares inches (in²)" -> {
                            result = (value * 6272640).toString()
                        }
                        "Squares metres (m²)" -> {
                            result = (value * 4046.8564224).toString()
                            result += " m²"
                        }
                    }
                }
                "Ares (a)" -> {
                    when (spinnerB) {
                        "Acres (ac)" -> {
                            result = (value * 0.0247105381).toString()
                        }
                        "Ares (a)" -> {
                            result = value.toString()
                        }
                        "Hectares (ha)" -> {
                            result = (value * 0.01).toString()
                        }
                        "Squares centimetres (cm²)" -> {
                            result = (value * 1000000).toString()
                        }
                        "Squares feet (ft²)" -> {
                            result = (value * 1076.391041671).toString()
                        }
                        "Squares inches (in²)" -> {
                            result = (value * 155000.31000062).toString()
                        }
                        "Squares metres (m²)" -> {
                            result = (value * 100).toString()
                        }
                    }
                }
                "Hectares (ha)" -> {
                    when (spinnerB) {
                        "Acres (ac)" -> {
                            result = (value * 2.4710538147).toString()
                        }
                        "Ares (a)" -> {
                            result = (value * 100).toString()
                        }
                        "Hectares (ha)" -> {
                            result = value.toString()
                        }
                        "Squares centimetres (cm²)" -> {
                            result = (value * 100000000).toString()
                        }
                        "Squares feet (ft²)" -> {
                            result = (value * 107639.1041671).toString()
                        }
                        "Squares inches (in²)" -> {
                            result = (value * 15500031.000062).toString()
                        }
                        "Squares metres (m²)" -> {
                            result = (value * 10000).toString()
                        }
                    }
                }
                "Squares centimetres (cm²)" -> {
                    when (spinnerB) {
                        "Acres (ac)" -> {
                            result = (value * 2.47105381E-8).toString()
                        }
                        "Ares (a)" -> {
                            result = (value * 0.000001).toString()
                        }
                        "Hectares (ha)" -> {
                            result = (value * 1.00000000E-8).toString()
                        }
                        "Squares centimetres (cm²)" -> {
                            result = value.toString()
                        }
                        "Squares feet (ft²)" -> {
                            result = (value * 0.001076391).toString()

                        }
                        "Squares inches (in²)" -> {
                            result = (value * 0.15500031).toString()
                        }
                        "Squares metres (m²)" -> {
                            result = (value * 0.0001).toString()
                        }
                    }
                }
                "Squares feet (ft²)" -> {
                    when (spinnerB) {
                        "Acres (ac)" -> {
                            result = (value * 0.0000229568).toString()
                        }
                        "Ares (a)" -> {
                            result = (value * 0.0009290304).toString()
                        }
                        "Hectares (ha)" -> {
                            result = (value * 0.00000929003).toString()
                        }
                        "Squares centimetres (cm²)" -> {
                            result = (value * 929.0304).toString()
                        }
                        "Squares feet (ft²)" -> {
                            result = value.toString()
                        }
                        "Squares inches (in²)" -> {
                            result = (value * 144).toString()
                        }
                        "Squares metres (m²)" -> {
                            result = (value * 0.09290304).toString()
                        }
                    }
                }
                "Squares inches (in²)" -> {
                    when (spinnerB) {
                        "Acres (ac)" -> {
                            result = (value * 1.59422508E-7).toString()
                        }
                        "Ares (a)" -> {
                            result = (value * 0.0000064516).toString()
                        }
                        "Hectares (ha)" -> {
                            result = (value * 6.45160000E-8).toString()
                        }
                        "Squares centimetres (cm²)" -> {
                            result = (value * 6.4516).toString()
                        }
                        "Squares feet (ft²)" -> {
                            result = (value * 0.0069444444).toString()
                        }
                        "Squares inches (in²)" -> {
                            result = value.toString()
                        }
                        "Squares metres (m²)" -> {
                            result = (value * 0.00064516).toString()
                        }
                    }
                }
                "Squares metres (m²)" -> {
                    when (spinnerB) {
                        "Acres (ac)" -> {
                            result = (value * 0.0002471054).toString()
                        }
                        "Ares (a)" -> {
                            result = (value * 0.01).toString()
                        }
                        "Hectares (ha)" -> {
                            result = (value * 0.0001).toString()
                        }
                        "Squares centimetres (cm²)" -> {
                            result = (value * 10.000).toString()
                        }
                        "Squares feet (ft²)" -> {
                            result = (value * 10.7639104167).toString()
                        }
                        "Squares inches (in²)" -> {
                            result = (value * 1550.003000062).toString()
                        }
                        "Squares metres (m²)" -> {
                            result = value.toString()
                        }
                    }
                }
            }
        }
        if (systemOfConvert == 2) {
            when (spinnerA) {
                "Millimetres (mm)"->{
                    when (spinnerB) {
                        "Millimetres (mm)"->{
                            result = value.toString()
                        }
                        "Centimetres (cm)"->{
                            result = (value * 0.1).toString()
                        }
                        "Metres (m)"->{
                            result = (value * 0.001).toString()
                        }
                        "Kilometres (km)"->{
                            result = (value * 0.000001).toString()
                        }
                        "Inches (in)"->{
                            result = (value * 0.0393700787).toString()
                        }
                        "Feet (ft)"->{
                            result = (value * 0.0032808399).toString()
                        }
                        "Yards (yd)"->{
                            result = (value * 0.0010936133).toString()
                        }
                        "Miles (mi)"->{
                            result = (value * 6.21371192E-7).toString()
                        }
                        "Nautical miles (NM)"->{
                            result = (value * 5.39956803E-7).toString()
                        }
                        "Mils (mil)"->{
                            result = (value * 39.3700787402).toString()
                        }
                    }
                }
                "Centimetres (cm)"->{
                    when (spinnerB) {
                        "Millimetres (mm)"->{
                            result = (value * 10).toString()
                        }
                        "Centimetres (cm)"->{
                            result = value.toString()
                        }
                        "Metres (m)"->{
                            result = (value * 0.01).toString()
                        }
                        "Kilometres (km)"->{
                            result = (value * 0.00001).toString()
                        }
                        "Inches (in)"->{
                            result = (value * 0.3937007874).toString()
                        }
                        "Feet (ft)"->{
                            result = (value * 0.032808399).toString()
                        }
                        "Yards (yd)"->{
                            result = (value * 0.010936133).toString()
                        }
                        "Miles (mi)"->{
                            result = (value * 0.5555562137).toString()
                        }
                        "Nautical miles (NM)"->{
                            result = (value * 0.5555535996).toString()
                        }
                        "Mils (mil)"->{
                            result = (value * 393.7007874016).toString()
                        }
                    }
                }
                "Metres (m)"->{
                    when (spinnerB) {
                        "Millimetres (mm)"->{
                            result = (value * 1000).toString()
                        }
                        "Centimetres (cm)"->{
                            result = (value * 100).toString()
                        }
                        "Metres (m)"->{
                            result = value.toString()
                        }
                        "Kilometres (km)"->{
                            result = (value * 0.001).toString()
                        }
                        "Inches (in)"->{
                            result = (value * 39.3700787402).toString()
                        }
                        "Feet (ft)"->{
                            result = (value * 3.280839895).toString()
                        }
                        "Yards (yd)"->{
                            result = (value * 1.0936132983).toString()
                        }
                        "Miles (mi)"->{
                            result = (value * 0.0006213712).toString()
                        }
                        "Nautical miles (NM)"->{
                            result = (value * 0.0005399568).toString()
                        }
                        "Mils (mil)"->{
                            result = (value * 39370.078740158).toString()
                        }
                    }
                }
                "Kilometres (km)"->{
                    when (spinnerB) {
                        "Millimetres (mm)"->{
                            result = (value * 1000000).toString()
                        }
                        "Centimetres (cm)"->{
                            result = (value * 100000).toString()
                        }
                        "Metres (m)"->{
                            result = (value * 1000).toString()
                        }
                        "Kilometres (km)"->{
                            result = value.toString()
                        }
                        "Inches (in)"->{
                            result = (value * 39370.078740158).toString()
                        }
                        "Feet (ft)"->{
                            result = (value * 3280.8398950131).toString()
                        }
                        "Yards (yd)"->{
                            result = (value * 1093.6132983377).toString()
                        }
                        "Miles (mi)"->{
                            result = (value * 0.6213711922).toString()
                        }
                        "Nautical miles (NM)"->{
                            result = (value * 0.5399568035).toString()
                        }
                        "Mils (mil)"->{
                            result = (value * 39370078.740157).toString()
                        }
                    }
                }
                "Inches (in)"->{
                    when (spinnerB) {
                        "Millimetres (mm)"->{
                            result = (value * 25.4).toString()
                        }
                        "Centimetres (cm)"->{
                            result = (value * 2.54).toString()
                        }
                        "Metres (m)"->{
                            result = (value * 0.0254).toString()
                        }
                        "Kilometres (km)"->{
                            result = (value * 0.0000254).toString()
                        }
                        "Inches (in)"->{
                            result = value.toString()
                        }
                        "Feet (ft)"->{
                            result = (value * 0.0833333333).toString()
                        }
                        "Yards (yd)"->{
                            result = (value * 0.0277777778).toString()
                        }
                        "Miles (mi)"->{
                            result = (value * 0.0000157828).toString()
                        }
                        "Nautical miles (NM)"->{
                            result = (value * 0.0000137149).toString()
                        }
                        "Mils (mil)"->{
                            result = (value * 1000).toString()
                        }
                    }
                }
                "Feet (ft)"->{
                    when (spinnerB) {
                        "Millimetres (mm)"->{
                            result = (value * 304.8).toString()
                        }
                        "Centimetres (cm)"->{
                            result = (value * 30.48).toString()
                        }
                        "Metres (m)"->{
                            result = (value * 0.3048).toString()
                        }
                        "Kilometres (km)"->{
                            result = (value * 0.0003048).toString()
                        }
                        "Inches (in)"->{
                            result = (value * 12).toString()
                        }
                        "Feet (ft)"->{
                            result = value.toString()
                        }
                        "Yards (yd)"->{
                            result = (value * 0.3333333333).toString()
                        }
                        "Miles (mi)"->{
                            result = (value * 0.0001893939).toString()
                        }
                        "Nautical miles (NM)"->{
                            result = (value * 0.0001645788).toString()
                        }
                        "Mils (mil)"->{
                            result = (value * 12000).toString()
                        }
                    }
                }
                "Yards (yd)"->{
                    when (spinnerB) {
                        "Millimetres (mm)"->{
                            result = (value * 914.4).toString()
                        }
                        "Centimetres (cm)"->{
                            result = (value * 91.44).toString()
                        }
                        "Metres (m)"->{
                            result = (value * 0.9144).toString()
                        }
                        "Kilometres (km)"->{
                            result = (value * 0.0009144).toString()
                        }
                        "Inches (in)"->{
                            result = (value * 36).toString()
                        }
                        "Feet (ft)"->{
                            result = (value * 3).toString()
                        }
                        "Yards (yd)"->{
                            result = value.toString()
                        }
                        "Miles (mi)"->{
                            result = (value * 0.0005681818).toString()
                        }
                        "Nautical miles (NM)"->{
                            result = (value * 0.0004937365).toString()
                        }
                        "Mils (mil)"->{
                            result = (value * 36000).toString()
                        }
                    }
                }
                "Miles (mi)"->{
                    when (spinnerB) {
                        "Millimetres (mm)"->{
                            result = (value * 1609344).toString()
                        }
                        "Centimetres (cm)"->{
                            result = (value * 160934.4).toString()
                        }
                        "Metres (m)"->{
                            result = (value * 1609.344).toString()
                        }
                        "Kilometres (km)"->{
                            result = (value * 1.609344).toString()
                        }
                        "Inches (in)"->{
                            result = (value * 63360).toString()
                        }
                        "Feet (ft)"->{
                            result = (value * 5280).toString()
                        }
                        "Yards (yd)"->{
                            result = (value * 1760).toString()
                        }
                        "Miles (mi)"->{
                            result = value.toString()
                        }
                        "Nautical miles (NM)"->{
                            result = (value * 0.8689762419).toString()
                        }
                        "Mils (mil)"->{
                            result = (value * 63360000).toString()
                        }
                    }
                }
                "Nautical miles (NM)"->{
                    when (spinnerB) {
                        "Millimetres (mm)"->{
                            result = (value * 1852000).toString()
                        }
                        "Centimetres (cm)"->{
                            result = (value * 185200).toString()
                        }
                        "Metres (m)"->{
                            result = (value * 18520).toString()
                        }
                        "Kilometres (km)"->{
                            result = (value * 1.852).toString()
                        }
                        "Inches (in)"->{
                            result = (value * 72913.385826772).toString()
                        }
                        "Feet (ft)"->{
                            result = (value * 6076.1154855643).toString()
                        }
                        "Yards (yd)"->{
                            result = (value * 2025.3718285214).toString()
                        }
                        "Miles (mi)"->{
                            result = (value * 1.150779448).toString()
                        }
                        "Nautical miles (NM)"->{
                            result = value.toString()
                        }
                        "Mils (mil)"->{
                            result = (value * 72913385.826772).toString()
                        }
                    }
                }
                "Mils (mil)"->{
                    when (spinnerB) {
                        "Millimetres (mm)"->{
                            result = (value * 0.0254).toString()
                        }
                        "Centimetres (cm)"->{
                            result = (value * 0.00254).toString()
                        }
                        "Metres (m)"->{
                            result = (value * 0.0000254).toString()
                        }
                        "Kilometres (km)"->{
                            result = (value * 2.54000000E-8).toString()
                        }
                        "Inches (in)"->{
                            result = (value * 0.001).toString()
                        }
                        "Feet (ft)"->{
                            result = (value * 0.0000833333).toString()
                        }
                        "Yards (yd)"->{
                            result = (value * 0.0000277778).toString()
                        }
                        "Miles (mi)"->{
                            result = (value * 1.57828283E-8).toString()
                        }
                        "Nautical miles (NM)"->{
                            result = (value * 1.38149028E-8).toString()
                        }
                        "Mils (mil)"->{
                            result = value.toString()
                        }
                    }
                }
            }
        }
        if (systemOfConvert == 3) {
            when (spinnerA){
                "Milliseconds (ms)" -> {
                    when (spinnerB){
                        "Milliseconds (ms)" -> {
                            result = value.toString()
                        }
                        "Seconds (s)"  -> {
                            result = (value * 0.001).toString()
                        }
                        "Minutes (min)"  -> {
                            result = (value * 0.0000166667).toString()
                        }
                        "Hours (h)"  -> {
                            result = (value * 2.77777778E-7).toString()
                        }
                        "Days (d)"  -> {
                            result = (value * 1.15740741E-8).toString()
                        }
                        "Weeks (wk)"  -> {
                            result = (value * 1.65343915E-9).toString()
                        }
                    }
                }
                "Seconds (s)"  -> {
                    when (spinnerB){
                        "Milliseconds (ms)" -> {
                            result = (value * 1000).toString()
                        }
                        "Seconds (s)"  -> {
                            result = value.toString()
                        }
                        "Minutes (min)"  -> {
                            result = (value * 0.0166666667).toString()
                        }
                        "Hours (h)"  -> {
                            result = (value * 0.0002777778).toString()
                        }
                        "Days (d)"  -> {
                            result = (value * 0.0000115741).toString()
                        }
                        "Weeks (wk)"  -> {
                            result = (value * 0.0000016534).toString()
                        }
                    }
                }
                "Minutes (min)"  -> {
                    when (spinnerB){
                        "Milliseconds (ms)" -> {
                            result = (value * 60000).toString()
                        }
                        "Seconds (s)"  -> {
                            result = (value * 60).toString()
                        }
                        "Minutes (min)"  -> {
                            result = value.toString()
                        }
                        "Hours (h)"  -> {
                            result = (value * 0.0166666667).toString()
                        }
                        "Days (d)"  -> {
                            result = (value * 0.0006944444).toString()
                        }
                        "Weeks (wk)"  -> {
                            result = (value * 0.0000992063).toString()
                        }
                    }
                }
                "Hours (h)"  -> {
                    when (spinnerB){
                        "Milliseconds (ms)" -> {
                            result = (value * 3600000).toString()
                        }
                        "Seconds (s)"  -> {
                            result = (value * 3600).toString()
                        }
                        "Minutes (min)"  -> {
                            result = (value * 60).toString()
                        }
                        "Hours (h)"  -> {
                            result = value.toString()
                        }
                        "Days (d)"  -> {
                            result = (value * 0.0416666667).toString()
                        }
                        "Weeks (wk)"  -> {
                            result = (value * 0.005952381).toString()
                        }
                    }
                }
                "Days (d)"  -> {
                    when (spinnerB){
                        "Milliseconds (ms)" -> {
                            result = (value * 86400000).toString()
                        }
                        "Seconds (s)"  -> {
                            result = (value * 86400).toString()
                        }
                        "Minutes (min)"  -> {
                            result = (value * 1440).toString()
                        }
                        "Hours (h)"  -> {
                            result = (value * 24).toString()
                        }
                        "Days (d)"  -> {
                            result = value.toString()
                        }
                        "Weeks (wk)"  -> {
                            result = (value * 0.1428571429).toString()
                        }
                    }
                }
                "Weeks (wk)"  -> {
                    when (spinnerB){
                        "Milliseconds (ms)" -> {
                            result = (value * 604800000).toString()
                        }
                        "Seconds (s)"  -> {
                            result = (value * 604800).toString()
                        }
                        "Minutes (min)"  -> {
                            result = (value * 10080).toString()
                        }
                        "Hours (h)"  -> {
                            result = (value * 168).toString()
                        }
                        "Days (d)"  -> {
                            result = (value * 7).toString()
                        }
                        "Weeks (wk)"  -> {
                            result = value.toString()
                        }
                    }
                }
            }
        }
        if (systemOfConvert == 4) {
            when (spinnerA){
                "Celsius (°C)" ->{
                    when (spinnerB){
                        "Celsius (°C)" ->{
                            result = value.toString()
                        }
                        "Fahrenheit (°F)" ->{
                            result = (value * 33.8).toString()
                        }
                        "Kelvin (K)" ->{
                            result = (value * 274.15).toString()
                        }
                    }
                }
                "Fahrenheit (°F)" ->{
                    when (spinnerB){
                        "Celsius (°C)" ->{
                            result = (value * -17.2222222222).toString()
                        }
                        "Fahrenheit (°F)" ->{
                            result = value.toString()
                        }
                        "Kelvin (K)" ->{
                            result = (value * 255.9277777778).toString()
                        }
                    }
                }
                "Kelvin (K)" ->{
                    when (spinnerB){
                        "Celsius (°C)" ->{
                            result = (value * -272.15).toString()
                        }
                        "Fahrenheit (°F)" ->{
                            result = (value * -457.87).toString()
                        }
                        "Kelvin (K)" ->{
                            result = value.toString()
                        }
                    }
                }
            }
        }
        if (systemOfConvert == 5) {
            when (spinnerA){
                "UK gallons (gal)" -> {
                    when (spinnerB){
                        "UK gallons (gal)" -> {
                            result = value.toString()
                        }
                        "US gallons (gal)" -> {
                            result = (value * 1.2009499255).toString()
                        }
                        "Litres (l)" -> {
                            result = (value * 4.54609).toString()
                        }
                        "Millilitres (ml)" -> {
                            result = (value * 4546.09).toString()
                        }
                        "Cubic centimetres (cc, cm³)" -> {
                            result = (value * 4546.09).toString()
                        }
                        "Cubic metres (m³)" -> {
                            result = (value * 0.00454609).toString()
                        }
                        "Cubic inches (in³)" -> {
                            result = (value * 277.4194327916).toString()
                        }
                        "Cubic feet (ft³)" -> {
                            result = (value * 0.1605436532).toString()
                        }
                    }
                }
                "US gallons (gal)" -> {
                    when (spinnerB){
                        "UK gallons (gal)" -> {
                            result = (value * 0.8326741846).toString()
                        }
                        "US gallons (gal)" -> {
                            result = value.toString()
                        }
                        "Litres (l)" -> {
                            result = (value * 3.785411784).toString()
                        }
                        "Millilitres (ml)" -> {
                            result = (value * 3785.411784).toString()
                        }
                        "Cubic centimetres (cc, cm³)" -> {
                            result = (value * 3785.411784).toString()
                        }
                        "Cubic metres (m³)" -> {
                            result = (value * 0.003785411784).toString()
                        }
                        "Cubic inches (in³)" -> {
                            result = (value * 231).toString()
                        }
                        "Cubic feet (ft³)" -> {
                            result = (value * 0.1336805556).toString()
                        }
                    }
                }
                "Litres (l)" -> {
                    when (spinnerB){
                        "UK gallons (gal)" -> {
                            result = (value * 0.2199692483).toString()
                        }
                        "US gallons (gal)" -> {
                            result = (value * 0.2641720524).toString()
                        }
                        "Litres (l)" -> {
                            result = value.toString()
                        }
                        "Millilitres (ml)" -> {
                            result = (value * 1000).toString()
                        }
                        "Cubic centimetres (cc, cm³)" -> {
                            result = (value * 1000).toString()
                        }
                        "Cubic metres (m³)" -> {
                            result = (value * 0.001).toString()
                        }
                        "Cubic inches (in³)" -> {
                            result = (value * 61.0237440947).toString()
                        }
                        "Cubic feet (ft³)" -> {
                            result = (value * 0.0353146667).toString()
                        }
                    }
                }
                "Millilitres (ml)" -> {
                    when (spinnerB){
                        "UK gallons (gal)" -> {
                            result = (value * 0.0002199692).toString()
                        }
                        "US gallons (gal)" -> {
                            result = (value * 0.0002641721).toString()
                        }
                        "Litres (l)" -> {
                            result = (value * 0.001).toString()
                        }
                        "Millilitres (ml)" -> {
                            result = value.toString()
                        }
                        "Cubic centimetres (cc, cm³)" -> {
                            result = (value * 1).toString()
                        }
                        "Cubic metres (m³)" -> {
                            result = (value * 0.000001).toString()
                        }
                        "Cubic inches (in³)" -> {
                            result = (value * 0.0610237441).toString()
                        }
                        "Cubic feet (ft³)" -> {
                            result = (value * 0.0000353147).toString()
                        }
                    }
                }
                "Cubic centimetres (cc, cm³)" -> {
                    when (spinnerB){
                        "UK gallons (gal)" -> {
                            result = (value * 0.0002199692).toString()
                        }
                        "US gallons (gal)" -> {
                            result = (value * 0.0002641721).toString()
                        }
                        "Litres (l)" -> {
                            result = (value * 0.001).toString()
                        }
                        "Millilitres (ml)" -> {
                            result = (value * 1).toString()
                        }
                        "Cubic centimetres (cc, cm³)" -> {
                            result = value.toString()
                        }
                        "Cubic metres (m³)" -> {
                            result = (value * 0.000001).toString()
                        }
                        "Cubic inches (in³)" -> {
                            result = (value * 0.0610237441).toString()
                        }
                        "Cubic feet (ft³)" -> {
                            result = (value * 0.0000353147).toString()
                        }
                    }
                }
                "Cubic metres (m³)" -> {
                    when (spinnerB){
                        "UK gallons (gal)" -> {
                            result = (value * 219.9692482991).toString()
                        }
                        "US gallons (gal)" -> {
                            result = (value * 264.1720523581).toString()
                        }
                        "Litres (l)" -> {
                            result = (value * 1000).toString()
                        }
                        "Millilitres (ml)" -> {
                            result = (value * 1000000).toString()
                        }
                        "Cubic centimetres (cc, cm³)" -> {
                            result = (value * 1000000).toString()
                        }
                        "Cubic metres (m³)" -> {
                            result = value.toString()
                        }
                        "Cubic inches (in³)" -> {
                            result = (value * 61023.744094732).toString()
                        }
                        "Cubic feet (ft³)" -> {
                            result = (value * 35.3146667215).toString()
                        }
                    }
                }
                "Cubic inches (in³)" -> {
                    when (spinnerB){
                        "UK gallons (gal)" -> {
                            result = (value * 0.0036046501).toString()
                        }
                        "US gallons (gal)" -> {
                            result = (value * 0.0043290043).toString()
                        }
                        "Litres (l)" -> {
                            result = (value * 0.016387064).toString()
                        }
                        "Millilitres (ml)" -> {
                            result = (value * 16.387064).toString()
                        }
                        "Cubic centimetres (cc, cm³)" -> {
                            result = (value * 16.387064).toString()
                        }
                        "Cubic metres (m³)" -> {
                            result = (value * 0.000016387064).toString()
                        }
                        "Cubic inches (in³)" -> {
                            result = value.toString()
                        }
                        "Cubic feet (ft³)" -> {
                            result = (value * 0.0005787037).toString()
                        }
                    }
                }
                "Cubic feet (ft³)" -> {
                    when (spinnerB){
                        "UK gallons (gal)" -> {
                            result = (value * 6.228835459).toString()
                        }
                        "US gallons (gal)" -> {
                            result = (value * 7.4805194805).toString()
                        }
                        "Litres (l)" -> {
                            result = (value * 28.316846592).toString()
                        }
                        "Millilitres (ml)" -> {
                            result = (value * 28316.846592).toString()
                        }
                        "Cubic centimetres (cc, cm³)" -> {
                            result = (value * 28316.846592).toString()
                        }
                        "Cubic metres (m³)" -> {
                            result = (value * 0.028316846592).toString()
                        }
                        "Cubic inches (in³)" -> {
                            result = (value * 1728).toString()
                        }
                        "Cubic feet (ft³)" -> {
                            result = value.toString()
                        }
                    }
                }
            }
        }
        if (systemOfConvert == 6) {
            when (spinnerA) {
                "Tons (t)"  -> {
                    when (spinnerB) {
                        "Tons (t)"  -> {
                            result = value.toString()
                        }
                        "UK tons (t)"  -> {
                            result = (value * 0.9842065276).toString()
                        }
                        "US tons  (t)"  -> {
                            result = (value * 1.1023113109).toString()
                        }
                        "Pounds (lb)"  -> {
                            result = (value * 2204.6226218488).toString()
                        }
                        "Ounces (oz)"  -> {
                            result = (value * 35273.96194958).toString()
                        }
                        "Kilogrammes (kg)" -> {
                            result = (value * 1000).toString()
                        }
                        "Grams (g)" -> {
                            result = (value * 1000000).toString()
                        }
                    }
                }
                "UK tons (t)"  -> {
                    when (spinnerB) {
                        "Tons (t)"  -> {
                            result = (value * 1.0160469088).toString()
                        }
                        "UK tons (t)"  -> {
                            result = value.toString()
                        }
                        "US tons  (t)"  -> {
                            result = (value * 1.12).toString()
                        }
                        "Pounds (lb)"  -> {
                            result = (value * 2240).toString()
                        }
                        "Ounces (oz)"  -> {
                            result = (value * ).toString()
                        }
                        "Kilogrammes (kg)" -> {
                            result = (value * ).toString()
                        }
                        "Grams (g)" -> {
                            result = (value * ).toString()
                        }
                    }
                }
                "US tons  (t)"  -> {
                    when (spinnerB) {
                        "Tons (t)"  -> {
                            result = (value * ).toString()
                        }
                        "UK tons (t)"  -> {
                            result = (value * ).toString()
                        }
                        "US tons  (t)"  -> {
                            result = (value * ).toString()
                        }
                        "Pounds (lb)"  -> {
                            result = (value * ).toString()
                        }
                        "Ounces (oz)"  -> {
                            result = (value * ).toString()
                        }
                        "Kilogrammes (kg)" -> {
                            result = (value * ).toString()
                        }
                        "Grams (g)" -> {
                            result = (value * ).toString()
                        }
                    }
                }
                "Pounds (lb)"  -> {
                    when (spinnerB) {
                        "Tons (t)"  -> {
                            result = (value * ).toString()
                        }
                        "UK tons (t)"  -> {
                            result = (value * ).toString()
                        }
                        "US tons  (t)"  -> {
                            result = (value * ).toString()
                        }
                        "Pounds (lb)"  -> {
                            result = (value * ).toString()
                        }
                        "Ounces (oz)"  -> {
                            result = (value * ).toString()
                        }
                        "Kilogrammes (kg)" -> {
                            result = (value * ).toString()
                        }
                        "Grams (g)" -> {
                            result = (value * ).toString()
                        }
                    }
                }
                "Ounces (oz)"  -> {
                    when (spinnerB) {
                        "Tons (t)"  -> {
                            result = (value * ).toString()
                        }
                        "UK tons (t)"  -> {
                            result = (value * ).toString()
                        }
                        "US tons  (t)"  -> {
                            result = (value * ).toString()
                        }
                        "Pounds (lb)"  -> {
                            result = (value * ).toString()
                        }
                        "Ounces (oz)"  -> {
                            result = (value * ).toString()
                        }
                        "Kilogrammes (kg)" -> {
                            result = (value * ).toString()
                        }
                        "Grams (g)" -> {
                            result = (value * ).toString()
                        }
                    }
                }
                "Kilogrammes (kg)" -> {
                    when (spinnerB) {
                        "Tons (t)"  -> {
                            result = (value * ).toString()
                        }
                        "UK tons (t)"  -> {
                            result = (value * ).toString()
                        }
                        "US tons  (t)"  -> {
                            result = (value * ).toString()
                        }
                        "Pounds (lb)"  -> {
                            result = (value * ).toString()
                        }
                        "Ounces (oz)"  -> {
                            result = (value * ).toString()
                        }
                        "Kilogrammes (kg)" -> {
                            result = (value * ).toString()
                        }
                        "Grams (g)" -> {
                            result = (value * ).toString()
                        }
                    }
                }
                "Grams (g)" -> {
                    when (spinnerB) {
                        "Tons (t)"  -> {
                            result = (value * ).toString()
                        }
                        "UK tons (t)"  -> {
                            result = (value * ).toString()
                        }
                        "US tons  (t)"  -> {
                            result = (value * ).toString()
                        }
                        "Pounds (lb)"  -> {
                            result = (value * ).toString()
                        }
                        "Ounces (oz)"  -> {
                            result = (value * ).toString()
                        }
                        "Kilogrammes (kg)" -> {
                            result = (value * ).toString()
                        }
                        "Grams (g)" -> {
                            result = (value * ).toString()
                        }
                    }
                }
            }
        }
        if (systemOfConvert == 7) {
            when (spinnerA) {
                "Bits (bit)" -> {
                    when (spinnerB) {
                        "Bits (bit)" -> {
                            result = (value * ).toString()
                        }
                        "Bytes (B)" -> {
                            result = (value * ).toString()
                        }
                        "Kilobytes (KB)" -> {
                            result = (value * ).toString()
                        }
                        "Megabytes (MB)" -> {
                            result = (value * ).toString()
                        }
                        "Gigabytes (GB)" -> {
                            result = (value * ).toString()
                        }
                        "Terabytes (TB)" -> {
                            result = (value * ).toString()
                        }
                        "Petabytes (PB)" -> {
                            result = (value * ).toString()
                        }
                        "Exabytes (XB)" -> {
                            result = (value * ).toString()
                        }
                        "Zettabytes (ZB)" -> {
                            result = (value * ).toString()
                        }
                        "Yottabytes (YB)" -> {
                            result = (value * ).toString()
                        }
                        "Brontonbytes (BB)" -> {
                            result = (value * ).toString()
                        }
                        "Geopbytes (GPB)" -> {
                            result = (value * ).toString()
                        }
                    }
                }
                "Bytes (B)" -> {
                    when (spinnerB) {
                        "Bits (bit)" -> {
                            result = (value * ).toString()
                        }
                        "Bytes (B)" -> {
                            result = (value * ).toString()
                        }
                        "Kilobytes (KB)" -> {
                            result = (value * ).toString()
                        }
                        "Megabytes (MB)" -> {
                            result = (value * ).toString()
                        }
                        "Gigabytes (GB)" -> {
                            result = (value * ).toString()
                        }
                        "Terabytes (TB)" -> {
                            result = (value * ).toString()
                        }
                        "Petabytes (PB)" -> {
                            result = (value * ).toString()
                        }
                        "Exabytes (XB)" -> {
                            result = (value * ).toString()
                        }
                        "Zettabytes (ZB)" -> {
                            result = (value * ).toString()
                        }
                        "Yottabytes (YB)" -> {
                            result = (value * ).toString()
                        }
                        "Brontonbytes (BB)" -> {
                            result = (value * ).toString()
                        }
                        "Geopbytes (GPB)" -> {
                            result = (value * ).toString()
                        }
                    }
                }
                "Kilobytes (KB)" -> {
                    when (spinnerB) {
                        "Bits (bit)" -> {
                            result = (value * ).toString()
                        }
                        "Bytes (B)" -> {
                            result = (value * ).toString()
                        }
                        "Kilobytes (KB)" -> {
                            result = (value * ).toString()
                        }
                        "Megabytes (MB)" -> {
                            result = (value * ).toString()
                        }
                        "Gigabytes (GB)" -> {
                            result = (value * ).toString()
                        }
                        "Terabytes (TB)" -> {
                            result = (value * ).toString()
                        }
                        "Petabytes (PB)" -> {
                            result = (value * ).toString()
                        }
                        "Exabytes (XB)" -> {
                            result = (value * ).toString()
                        }
                        "Zettabytes (ZB)" -> {
                            result = (value * ).toString()
                        }
                        "Yottabytes (YB)" -> {
                            result = (value * ).toString()
                        }
                        "Brontonbytes (BB)" -> {
                            result = (value * ).toString()
                        }
                        "Geopbytes (GPB)" -> {
                            result = (value * ).toString()
                        }
                    }
                }
                "Megabytes (MB)" -> {
                    when (spinnerB) {
                        "Bits (bit)" -> {
                            result = (value * ).toString()
                        }
                        "Bytes (B)" -> {
                            result = (value * ).toString()
                        }
                        "Kilobytes (KB)" -> {
                            result = (value * ).toString()
                        }
                        "Megabytes (MB)" -> {
                            result = (value * ).toString()
                        }
                        "Gigabytes (GB)" -> {
                            result = (value * ).toString()
                        }
                        "Terabytes (TB)" -> {
                            result = (value * ).toString()
                        }
                        "Petabytes (PB)" -> {
                            result = (value * ).toString()
                        }
                        "Exabytes (XB)" -> {
                            result = (value * ).toString()
                        }
                        "Zettabytes (ZB)" -> {
                            result = (value * ).toString()
                        }
                        "Yottabytes (YB)" -> {
                            result = (value * ).toString()
                        }
                        "Brontonbytes (BB)" -> {
                            result = (value * ).toString()
                        }
                        "Geopbytes (GPB)" -> {
                            result = (value * ).toString()
                        }
                    }
                }
                "Gigabytes (GB)" -> {
                    when (spinnerB) {
                        "Bits (bit)" -> {
                            result = (value * ).toString()
                        }
                        "Bytes (B)" -> {
                            result = (value * ).toString()
                        }
                        "Kilobytes (KB)" -> {
                            result = (value * ).toString()
                        }
                        "Megabytes (MB)" -> {
                            result = (value * ).toString()
                        }
                        "Gigabytes (GB)" -> {
                            result = (value * ).toString()
                        }
                        "Terabytes (TB)" -> {
                            result = (value * ).toString()
                        }
                        "Petabytes (PB)" -> {
                            result = (value * ).toString()
                        }
                        "Exabytes (XB)" -> {
                            result = (value * ).toString()
                        }
                        "Zettabytes (ZB)" -> {
                            result = (value * ).toString()
                        }
                        "Yottabytes (YB)" -> {
                            result = (value * ).toString()
                        }
                        "Brontonbytes (BB)" -> {
                            result = (value * ).toString()
                        }
                        "Geopbytes (GPB)" -> {
                            result = (value * ).toString()
                        }
                    }
                }
                "Terabytes (TB)" -> {
                    when (spinnerB) {
                        "Bits (bit)" -> {
                            result = (value * ).toString()
                        }
                        "Bytes (B)" -> {
                            result = (value * ).toString()
                        }
                        "Kilobytes (KB)" -> {
                            result = (value * ).toString()
                        }
                        "Megabytes (MB)" -> {
                            result = (value * ).toString()
                        }
                        "Gigabytes (GB)" -> {
                            result = (value * ).toString()
                        }
                        "Terabytes (TB)" -> {
                            result = (value * ).toString()
                        }
                        "Petabytes (PB)" -> {
                            result = (value * ).toString()
                        }
                        "Exabytes (XB)" -> {
                            result = (value * ).toString()
                        }
                        "Zettabytes (ZB)" -> {
                            result = (value * ).toString()
                        }
                        "Yottabytes (YB)" -> {
                            result = (value * ).toString()
                        }
                        "Brontonbytes (BB)" -> {
                            result = (value * ).toString()
                        }
                        "Geopbytes (GPB)" -> {
                            result = (value * ).toString()
                        }
                    }
                }
                "Petabytes (PB)" -> {
                    when (spinnerB) {
                        "Bits (bit)" -> {
                            result = (value * ).toString()
                        }
                        "Bytes (B)" -> {
                            result = (value * ).toString()
                        }
                        "Kilobytes (KB)" -> {
                            result = (value * ).toString()
                        }
                        "Megabytes (MB)" -> {
                            result = (value * ).toString()
                        }
                        "Gigabytes (GB)" -> {
                            result = (value * ).toString()
                        }
                        "Terabytes (TB)" -> {
                            result = (value * ).toString()
                        }
                        "Petabytes (PB)" -> {
                            result = (value * ).toString()
                        }
                        "Exabytes (XB)" -> {
                            result = (value * ).toString()
                        }
                        "Zettabytes (ZB)" -> {
                            result = (value * ).toString()
                        }
                        "Yottabytes (YB)" -> {
                            result = (value * ).toString()
                        }
                        "Brontonbytes (BB)" -> {
                            result = (value * ).toString()
                        }
                        "Geopbytes (GPB)" -> {
                            result = (value * ).toString()
                        }
                    }
                }
                "Exabytes (XB)" -> {
                    when (spinnerB) {
                        "Bits (bit)" -> {
                            result = (value * ).toString()
                        }
                        "Bytes (B)" -> {
                            result = (value * ).toString()
                        }
                        "Kilobytes (KB)" -> {
                            result = (value * ).toString()
                        }
                        "Megabytes (MB)" -> {
                            result = (value * ).toString()
                        }
                        "Gigabytes (GB)" -> {
                            result = (value * ).toString()
                        }
                        "Terabytes (TB)" -> {
                            result = (value * ).toString()
                        }
                        "Petabytes (PB)" -> {
                            result = (value * ).toString()
                        }
                        "Exabytes (XB)" -> {
                            result = (value * ).toString()
                        }
                        "Zettabytes (ZB)" -> {
                            result = (value * ).toString()
                        }
                        "Yottabytes (YB)" -> {
                            result = (value * ).toString()
                        }
                        "Brontonbytes (BB)" -> {
                            result = (value * ).toString()
                        }
                        "Geopbytes (GPB)" -> {
                            result = (value * ).toString()
                        }
                    }
                }
                "Zettabytes (ZB)" -> {
                    when (spinnerB) {
                        "Bits (bit)" -> {
                            result = (value * ).toString()
                        }
                        "Bytes (B)" -> {
                            result = (value * ).toString()
                        }
                        "Kilobytes (KB)" -> {
                            result = (value * ).toString()
                        }
                        "Megabytes (MB)" -> {
                            result = (value * ).toString()
                        }
                        "Gigabytes (GB)" -> {
                            result = (value * ).toString()
                        }
                        "Terabytes (TB)" -> {
                            result = (value * ).toString()
                        }
                        "Petabytes (PB)" -> {
                            result = (value * ).toString()
                        }
                        "Exabytes (XB)" -> {
                            result = (value * ).toString()
                        }
                        "Zettabytes (ZB)" -> {
                            result = (value * ).toString()
                        }
                        "Yottabytes (YB)" -> {
                            result = (value * ).toString()
                        }
                        "Brontonbytes (BB)" -> {
                            result = (value * ).toString()
                        }
                        "Geopbytes (GPB)" -> {
                            result = (value * ).toString()
                        }
                    }
                }
                "Yottabytes (YB)" -> {
                    when (spinnerB) {
                        "Bits (bit)" -> {
                            result = (value * ).toString()
                        }
                        "Bytes (B)" -> {
                            result = (value * ).toString()
                        }
                        "Kilobytes (KB)" -> {
                            result = (value * ).toString()
                        }
                        "Megabytes (MB)" -> {
                            result = (value * ).toString()
                        }
                        "Gigabytes (GB)" -> {
                            result = (value * ).toString()
                        }
                        "Terabytes (TB)" -> {
                            result = (value * ).toString()
                        }
                        "Petabytes (PB)" -> {
                            result = (value * ).toString()
                        }
                        "Exabytes (XB)" -> {
                            result = (value * ).toString()
                        }
                        "Zettabytes (ZB)" -> {
                            result = (value * ).toString()
                        }
                        "Yottabytes (YB)" -> {
                            result = (value * ).toString()
                        }
                        "Brontonbytes (BB)" -> {
                            result = (value * ).toString()
                        }
                        "Geopbytes (GPB)" -> {
                            result = (value * ).toString()
                        }
                    }
                }
                "Brontonbytes (BB)" -> {
                    when (spinnerB) {
                        "Bits (bit)" -> {
                            result = (value * ).toString()
                        }
                        "Bytes (B)" -> {
                            result = (value * ).toString()
                        }
                        "Kilobytes (KB)" -> {
                            result = (value * ).toString()
                        }
                        "Megabytes (MB)" -> {
                            result = (value * ).toString()
                        }
                        "Gigabytes (GB)" -> {
                            result = (value * ).toString()
                        }
                        "Terabytes (TB)" -> {
                            result = (value * ).toString()
                        }
                        "Petabytes (PB)" -> {
                            result = (value * ).toString()
                        }
                        "Exabytes (XB)" -> {
                            result = (value * ).toString()
                        }
                        "Zettabytes (ZB)" -> {
                            result = (value * ).toString()
                        }
                        "Yottabytes (YB)" -> {
                            result = (value * ).toString()
                        }
                        "Brontonbytes (BB)" -> {
                            result = (value * ).toString()
                        }
                        "Geopbytes (GPB)" -> {
                            result = (value * ).toString()
                        }
                    }
                }
                "Geopbytes (GPB)" -> {
                    when (spinnerB) {
                        "Bits (bit)" -> {
                            result = (value * ).toString()
                        }
                        "Bytes (B)" -> {
                            result = (value * ).toString()
                        }
                        "Kilobytes (KB)" -> {
                            result = (value * ).toString()
                        }
                        "Megabytes (MB)" -> {
                            result = (value * ).toString()
                        }
                        "Gigabytes (GB)" -> {
                            result = (value * ).toString()
                        }
                        "Terabytes (TB)" -> {
                            result = (value * ).toString()
                        }
                        "Petabytes (PB)" -> {
                            result = (value * ).toString()
                        }
                        "Exabytes (XB)" -> {
                            result = (value * ).toString()
                        }
                        "Zettabytes (ZB)" -> {
                            result = (value * ).toString()
                        }
                        "Yottabytes (YB)" -> {
                            result = (value * ).toString()
                        }
                        "Brontonbytes (BB)" -> {
                            result = (value * ).toString()
                        }
                        "Geopbytes (GPB)" -> {
                            result = (value * ).toString()
                        }
                    }
                }
            }
        }
        if (systemOfConvert == 8) {
            when (spinnerA) {
                "Metres per second (m/s)" -> {
                    when (spinnerB) {
                        "Metres per second (m/s)" -> {
                            result = (value * ).toString()
                        }
                        "Metres per hour (m/h)" -> {
                            result = (value * ).toString()
                        }
                        "Kilometres per second (km/s)" -> {
                            result = (value * ).toString()
                        }
                        "Kilometres per hour (km/h)" -> {
                            result = (value * ).toString()
                        }
                        "Inches per second (in/h)" -> {
                            result = (value * ).toString()
                        }
                        "Inches per hour (in/h)" -> {
                            result = (value * ).toString()
                        }
                        "Feet per second (ft/s)" -> {
                            result = (value * ).toString()
                        }
                        "Feet per hour (ft/h)" -> {
                            result = (value * ).toString()
                        }
                        "Miles per second (mi/s)" -> {
                            result = (value * ).toString()
                        }
                        "Miles per hour (mi/h)" -> {
                            result = (value * ).toString()
                        }
                        "Knots (kn)" -> {
                            result = (value * ).toString()
                        }
                    }
                }
                "Metres per hour (m/h)" -> {
                    when (spinnerB) {
                        "Metres per second (m/s)" -> {
                            result = (value * ).toString()
                        }
                        "Metres per hour (m/h)" -> {
                            result = (value * ).toString()
                        }
                        "Kilometres per second (km/s)" -> {
                            result = (value * ).toString()
                        }
                        "Kilometres per hour (km/h)" -> {
                            result = (value * ).toString()
                        }
                        "Inches per second (in/h)" -> {
                            result = (value * ).toString()
                        }
                        "Inches per hour (in/h)" -> {
                            result = (value * ).toString()
                        }
                        "Feet per second (ft/s)" -> {
                            result = (value * ).toString()
                        }
                        "Feet per hour (ft/h)" -> {
                            result = (value * ).toString()
                        }
                        "Miles per second (mi/s)" -> {
                            result = (value * ).toString()
                        }
                        "Miles per hour (mi/h)" -> {
                            result = (value * ).toString()
                        }
                        "Knots (kn)" -> {
                            result = (value * ).toString()
                        }
                    }
                }
                "Kilometres per second (km/s)" -> {
                    when (spinnerB) {
                        "Metres per second (m/s)" -> {
                            result = (value * ).toString()
                        }
                        "Metres per hour (m/h)" -> {
                            result = (value * ).toString()
                        }
                        "Kilometres per second (km/s)" -> {
                            result = (value * ).toString()
                        }
                        "Kilometres per hour (km/h)" -> {
                            result = (value * ).toString()
                        }
                        "Inches per second (in/h)" -> {
                            result = (value * ).toString()
                        }
                        "Inches per hour (in/h)" -> {
                            result = (value * ).toString()
                        }
                        "Feet per second (ft/s)" -> {
                            result = (value * ).toString()
                        }
                        "Feet per hour (ft/h)" -> {
                            result = (value * ).toString()
                        }
                        "Miles per second (mi/s)" -> {
                            result = (value * ).toString()
                        }
                        "Miles per hour (mi/h)" -> {
                            result = (value * ).toString()
                        }
                        "Knots (kn)" -> {
                            result = (value * ).toString()
                        }
                    }
                }
                "Kilometres per hour (km/h)" -> {
                    when (spinnerB) {
                        "Metres per second (m/s)" -> {
                            result = (value * ).toString()
                        }
                        "Metres per hour (m/h)" -> {
                            result = (value * ).toString()
                        }
                        "Kilometres per second (km/s)" -> {
                            result = (value * ).toString()
                        }
                        "Kilometres per hour (km/h)" -> {
                            result = (value * ).toString()
                        }
                        "Inches per second (in/h)" -> {
                            result = (value * ).toString()
                        }
                        "Inches per hour (in/h)" -> {
                            result = (value * ).toString()
                        }
                        "Feet per second (ft/s)" -> {
                            result = (value * ).toString()
                        }
                        "Feet per hour (ft/h)" -> {
                            result = (value * ).toString()
                        }
                        "Miles per second (mi/s)" -> {
                            result = (value * ).toString()
                        }
                        "Miles per hour (mi/h)" -> {
                            result = (value * ).toString()
                        }
                        "Knots (kn)" -> {
                            result = (value * ).toString()
                        }
                    }
                }
                "Inches per second (in/h)" -> {
                    when (spinnerB) {
                        "Metres per second (m/s)" -> {
                            result = (value * ).toString()
                        }
                        "Metres per hour (m/h)" -> {
                            result = (value * ).toString()
                        }
                        "Kilometres per second (km/s)" -> {
                            result = (value * ).toString()
                        }
                        "Kilometres per hour (km/h)" -> {
                            result = (value * ).toString()
                        }
                        "Inches per second (in/h)" -> {
                            result = (value * ).toString()
                        }
                        "Inches per hour (in/h)" -> {
                            result = (value * ).toString()
                        }
                        "Feet per second (ft/s)" -> {
                            result = (value * ).toString()
                        }
                        "Feet per hour (ft/h)" -> {
                            result = (value * ).toString()
                        }
                        "Miles per second (mi/s)" -> {
                            result = (value * ).toString()
                        }
                        "Miles per hour (mi/h)" -> {
                            result = (value * ).toString()
                        }
                        "Knots (kn)" -> {
                            result = (value * ).toString()
                        }
                    }
                }
                "Inches per hour (in/h)" -> {
                    when (spinnerB) {
                        "Metres per second (m/s)" -> {
                            result = (value * ).toString()
                        }
                        "Metres per hour (m/h)" -> {
                            result = (value * ).toString()
                        }
                        "Kilometres per second (km/s)" -> {
                            result = (value * ).toString()
                        }
                        "Kilometres per hour (km/h)" -> {
                            result = (value * ).toString()
                        }
                        "Inches per second (in/h)" -> {
                            result = (value * ).toString()
                        }
                        "Inches per hour (in/h)" -> {
                            result = (value * ).toString()
                        }
                        "Feet per second (ft/s)" -> {
                            result = (value * ).toString()
                        }
                        "Feet per hour (ft/h)" -> {
                            result = (value * ).toString()
                        }
                        "Miles per second (mi/s)" -> {
                            result = (value * ).toString()
                        }
                        "Miles per hour (mi/h)" -> {
                            result = (value * ).toString()
                        }
                        "Knots (kn)" -> {
                            result = (value * ).toString()
                        }
                    }
                }
                "Feet per second (ft/s)" -> {
                    when (spinnerB) {
                        "Metres per second (m/s)" -> {
                            result = (value * ).toString()
                        }
                        "Metres per hour (m/h)" -> {
                            result = (value * ).toString()
                        }
                        "Kilometres per second (km/s)" -> {
                            result = (value * ).toString()
                        }
                        "Kilometres per hour (km/h)" -> {
                            result = (value * ).toString()
                        }
                        "Inches per second (in/h)" -> {
                            result = (value * ).toString()
                        }
                        "Inches per hour (in/h)" -> {
                            result = (value * ).toString()
                        }
                        "Feet per second (ft/s)" -> {
                            result = (value * ).toString()
                        }
                        "Feet per hour (ft/h)" -> {
                            result = (value * ).toString()
                        }
                        "Miles per second (mi/s)" -> {
                            result = (value * ).toString()
                        }
                        "Miles per hour (mi/h)" -> {
                            result = (value * ).toString()
                        }
                        "Knots (kn)" -> {
                            result = (value * ).toString()
                        }
                    }
                }
                "Feet per hour (ft/h)" -> {
                    when (spinnerB) {
                        "Metres per second (m/s)" -> {
                            result = (value * ).toString()
                        }
                        "Metres per hour (m/h)" -> {
                            result = (value * ).toString()
                        }
                        "Kilometres per second (km/s)" -> {
                            result = (value * ).toString()
                        }
                        "Kilometres per hour (km/h)" -> {
                            result = (value * ).toString()
                        }
                        "Inches per second (in/h)" -> {
                            result = (value * ).toString()
                        }
                        "Inches per hour (in/h)" -> {
                            result = (value * ).toString()
                        }
                        "Feet per second (ft/s)" -> {
                            result = (value * ).toString()
                        }
                        "Feet per hour (ft/h)" -> {
                            result = (value * ).toString()
                        }
                        "Miles per second (mi/s)" -> {
                            result = (value * ).toString()
                        }
                        "Miles per hour (mi/h)" -> {
                            result = (value * ).toString()
                        }
                        "Knots (kn)" -> {
                            result = (value * ).toString()
                        }
                    }
                }
                "Miles per second (mi/s)" -> {
                    when (spinnerB) {
                        "Metres per second (m/s)" -> {
                            result = (value * ).toString()
                        }
                        "Metres per hour (m/h)" -> {
                            result = (value * ).toString()
                        }
                        "Kilometres per second (km/s)" -> {
                            result = (value * ).toString()
                        }
                        "Kilometres per hour (km/h)" -> {
                            result = (value * ).toString()
                        }
                        "Inches per second (in/h)" -> {
                            result = (value * ).toString()
                        }
                        "Inches per hour (in/h)" -> {
                            result = (value * ).toString()
                        }
                        "Feet per second (ft/s)" -> {
                            result = (value * ).toString()
                        }
                        "Feet per hour (ft/h)" -> {
                            result = (value * ).toString()
                        }
                        "Miles per second (mi/s)" -> {
                            result = (value * ).toString()
                        }
                        "Miles per hour (mi/h)" -> {
                            result = (value * ).toString()
                        }
                        "Knots (kn)" -> {
                            result = (value * ).toString()
                        }
                    }
                }
                "Miles per hour (mi/h)" -> {
                    when (spinnerB) {
                        "Metres per second (m/s)" -> {
                            result = (value * ).toString()
                        }
                        "Metres per hour (m/h)" -> {
                            result = (value * ).toString()
                        }
                        "Kilometres per second (km/s)" -> {
                            result = (value * ).toString()
                        }
                        "Kilometres per hour (km/h)" -> {
                            result = (value * ).toString()
                        }
                        "Inches per second (in/h)" -> {
                            result = (value * ).toString()
                        }
                        "Inches per hour (in/h)" -> {
                            result = (value * ).toString()
                        }
                        "Feet per second (ft/s)" -> {
                            result = (value * ).toString()
                        }
                        "Feet per hour (ft/h)" -> {
                            result = (value * ).toString()
                        }
                        "Miles per second (mi/s)" -> {
                            result = (value * ).toString()
                        }
                        "Miles per hour (mi/h)" -> {
                            result = (value * ).toString()
                        }
                        "Knots (kn)" -> {
                            result = (value * ).toString()
                        }
                    }
                }
                "Knots (kn)" -> {
                    when (spinnerB) {
                        "Metres per second (m/s)" -> {
                            result = (value * ).toString()
                        }
                        "Metres per hour (m/h)" -> {
                            result = (value * ).toString()
                        }
                        "Kilometres per second (km/s)" -> {
                            result = (value * ).toString()
                        }
                        "Kilometres per hour (km/h)" -> {
                            result = (value * ).toString()
                        }
                        "Inches per second (in/h)" -> {
                            result = (value * ).toString()
                        }
                        "Inches per hour (in/h)" -> {
                            result = (value * ).toString()
                        }
                        "Feet per second (ft/s)" -> {
                            result = (value * ).toString()
                        }
                        "Feet per hour (ft/h)" -> {
                            result = (value * ).toString()
                        }
                        "Miles per second (mi/s)" -> {
                            result = (value * ).toString()
                        }
                        "Miles per hour (mi/h)" -> {
                            result = (value * ).toString()
                        }
                        "Knots (kn)" -> {
                            result = (value * ).toString()
                        }
                    }
                }
            }
        }
        return result
    }

}