package justjump.coding_calculator

import android.widget.Toast
import androidx.lifecycle.ViewModel
import java.text.DecimalFormat

class CalculatorViewModel: ViewModel() {

    fun a() {
        /*if (expression.text.contains('%')) {
            var i: Int = expression.text.length - 1
            var number = ""
            var dataResult = ""

            while (0 <= i) {
                if (expression.text[i] == '%') {
                    var index = i - 1

                    while (0 <= index) {
                        if (index >= 0) {
                            if (expression.text[index].isDigit() || expression.text[index] == '.') {
                                number += expression.text[index]
                            } else {
                                index = -1
                            }
                        }
                        index--
                    }
                    // I dont have exactly this one hire
                    dataResult = if (number.isNotEmpty()) {
                        "(${number.reversed()}/100)" + dataResult
                    } else {
                        "${number.reversed()}/100" + dataResult
                    }

                    i -= number.length
                    number = ""
                } else {
                    dataResult = expression.text[i].toString() + dataResult
                }
                i--
            }

            if (checkParenthesis(dataResult)) {
                val format = DecimalFormat()
                format.maximumFractionDigits = 4

                tResult.text = checkInteger(format.format(Functions().basicEquations(dataResult)))
                state = true

                saveHistory(expression.text.toString())
                saveHistory("=" + tResult.text.toString())
            } else {
                val toast = Toast.makeText(applicationContext,"Invalid format used.", Toast.LENGTH_SHORT)
                toast.show()
            }
        } else {
            if (expression.text.isNotEmpty()) {
                val checkExp = expression.text.toString()

                if (checkParenthesis(checkExp)) {
                    val format = DecimalFormat()
                    format.maximumFractionDigits = 4

                    tResult.text = checkInteger(format.format(Functions().basicEquations(checkExp)))
                    state = true

                    saveHistory(expression.text.toString())
                    saveHistory("=" + tResult.text.toString())
                } else {
                    val toast = Toast.makeText(applicationContext,"Invalid format used.", Toast.LENGTH_SHORT)
                    toast.show()
                }
            }
        }
    }

    mvvm*/
    }


    override fun onCleared() {
        super.onCleared()
    }
}