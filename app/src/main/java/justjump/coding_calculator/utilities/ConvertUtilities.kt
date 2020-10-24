package justjump.coding_calculator.utilities

import kotlinx.android.synthetic.main.activity_converter.*

class ConvertUtilities {

    fun checkConvert(systemOfConvert: Int, value: Double, spinnerA: String, spinnerB: String): String {
        var result = ""

        if (systemOfConvert == 1){

            when (spinnerA) {
                "Acres (AC)" -> {
                    when (spinnerB){
                        "Acres (AC)" -> {
                            result = spinnerB
                        }
                        "Ares (A)" -> {
                            result = (value * 40.468564224).toString()
                        }
                        "Hectares (HA)" -> {
                            result = (value* 0.4046856422).toString()
                        }
                        "Squares Centimetres (CM²)" -> {
                            result = (value * 40468564.224).toString()
                        }
                        "Squares Feet (FT²)" -> {
                            result = (value * 43560).toString()
                        }
                        "Squares Inches (IN²)" -> {
                            result = (value * 6272640).toString()
                        }
                        "Squares Metres (M²)" -> {
                            result = (value * 4046.8564224).toString()
                            result += " m²"
                        }
                    }
                }
                "Ares (A)" -> {
                    when (spinnerB){
                        "Acres (AC)" -> {

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
                "Hectares (HA)" -> {
                    when (spinnerB){
                        "Acres (AC)" -> {

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
                "Squares Centimetres (CM²)" -> {
                    when (spinnerB){
                        "Acres (AC)" -> {

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
                "Squares Feet (FT²)" -> {
                    when (spinnerB){
                        "Acres (AC)" -> {

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
                "Squares Inches (IN²)" -> {
                    when (spinnerB){
                        "Acres (AC)" -> {

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
                "Squares Metres (M²)" -> {
                    when (spinnerB){
                        "Acres (AC)" -> {

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


            }
        }
        return result
    }

}