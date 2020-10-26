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

        }
        return result
    }

}