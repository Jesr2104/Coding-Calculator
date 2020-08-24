package justjump.coding_calculator.viewmodel

import android.text.Html
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import justjump.coding_calculator.extensions.deleteComma
import justjump.coding_calculator.extensions.paintString
import kotlinx.android.synthetic.main.activity_calculator.*

class CalculatorViewModel: ViewModel() {

    var dataFieldResult = MutableLiveData<String>()
    var dataFieldExpression = MutableLiveData<String>()

    init {
        dataFieldExpression.value = ""
        dataFieldResult.value = ""
    }

    // this function check all about the insert numbers and check to insert number zero when write one? and when not?
    fun insertNumbers(number: Char) {
        var dataString = "" + dataFieldExpression.value

        if (dataString.isNotEmpty()) {
            // logic for the number zero
            if (number == '0')
            {
                var newSplitNumber = ""
                var cont = dataString.length -1
                var controlDecimal = true
                var checkValue = false

                if (dataString[dataString.length - 1] == '+' ||
                    dataString[dataString.length - 1] == '-' ||
                    dataString[dataString.length - 1] == '*' ||
                    dataString[dataString.length - 1] == '/'
                ) { dataString += "0"}

                while (cont >= 0) {
                    if (dataString[cont].isDigit() || dataString[cont] == '.') {
                        newSplitNumber += dataString[cont]
                    } else {
                        cont = 0
                    }
                    cont--
                }

                for (item in newSplitNumber) {
                    if (item == '.') {
                        dataString += "0"
                        controlDecimal = false
                    }
                }

                for (item in newSplitNumber) {
                    if (controlDecimal) {
                        if (Character.getNumericValue(item.toInt()) > 0) {
                            checkValue = true
                        }
                    }
                }

                if (checkValue) {
                    dataString += "0"
                    dataFieldExpression.value = dataString
                }
            }
            // logic for the rest of the number
            else {
                var lastNumber = ""
                var controldecimal = true
                var cont: Int = dataString.length - 1
                var checkvalue = false

                while (cont >= 0) {
                    if (dataString[cont].isDigit() || dataString[cont] == '.') {
                        lastNumber += dataString[cont]
                    } else {
                        cont = 0
                    }
                    cont--
                }

                for (item in lastNumber) {
                    if (item == '.') {
                        dataString += number
                        controldecimal = false
                    }
                }

                if (controldecimal) {
                    while (cont >= 0) {
                        if (dataString[cont].isDigit()) {
                            if (Character.getNumericValue(dataString[cont]) > 0) {
                                checkvalue = true
                            }
                        } else {
                            cont = 0
                        }
                        cont--
                    }
                    if (checkvalue) {
                        dataString += number
                    } else if ((dataString[dataString.length - 1] == '0')) {
                        cont = dataString.length - 1
                        while (cont >= 0) {
                            if (dataString[cont].isDigit() || dataString[cont] == '.') {
                                lastNumber += dataString[cont]
                            } else {
                                cont = 0
                            }
                            cont--
                        }

                        if (lastNumber.substring(0, lastNumber.length - 1).toDouble() > 0) {
                            dataString = dataString + number
                            dataFieldExpression.value = dataString
                        } else {
                            dataString = dataString.substring(0,dataString.length - 1) + number
                            dataFieldExpression.value = dataString
                        }
                    } else {
                        dataString += number
                        dataFieldExpression.value = dataString
                    }
                }
            }
        }
        else
        {
            dataString = dataString + number
            dataFieldExpression.value = dataString
        }
    }

    // this function use the result like the information for the next expression
    fun ansData(check: Boolean): Boolean {
        if (check) {
            dataFieldExpression.value = (dataFieldResult.value.toString()).deleteComma()
            dataFieldResult.value = ""
        }
        return false
    }

    // this function delete the fields if you press = to see the result.
    fun clearExpression(check: Boolean): Boolean {
        if (check) {
            dataFieldExpression.value = ""
        }
        return false
    }
}