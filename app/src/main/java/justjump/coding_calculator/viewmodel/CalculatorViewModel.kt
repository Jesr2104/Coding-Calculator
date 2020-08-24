package justjump.coding_calculator.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import justjump.coding_calculator.extensions.paintString

class CalculatorViewModel: ViewModel() {

    var dataFieldResult = MutableLiveData<String>()
    var dataFieldExpression = MutableLiveData<String>()

    init {
        dataFieldExpression.value = ""
        dataFieldResult.value = ""
    }

    // this function check all about the insert numbers and check to insert number zero when write one? and when not?
    fun insertNumbers(number: Char) {
        var lastNumber = ""
        var controldecimal = true
        var newExpression = dataFieldResult.value

        if (newExpression!!.isNotEmpty()) {
            if(number != '0')
            {
                var cont: Int = newExpression.length - 1
                var checkvalue = false

                while (cont >= 0) {
                    if (newExpression[cont].isDigit() || newExpression[cont] == '.') {
                        lastNumber += newExpression[cont]
                    } else {
                        cont = 0
                    }
                    cont--
                }

                for (item in lastNumber) {
                    if (item == '.') {
                        newExpression = (newExpression.toString() + number).paintString()
                        controldecimal = false
                    }
                }

                if (controldecimal) {
                    while (cont >= 0) {
                        if (newExpression!![cont].isDigit()) {
                            if (Character.getNumericValue(newExpression[cont]) > 0) {
                                checkvalue = true
                            }
                        } else {
                            cont = 0
                        }
                        cont--
                    }

                    if (checkvalue) {
                        newExpression = (newExpression.toString() + number).paintString()
                    } else if ((newExpression!![newExpression.length - 1] == '0')) {
                        cont = newExpression.length - 1
                        while (cont >= 0) {
                            if (newExpression[cont].isDigit() || newExpression[cont] == '.') {
                                lastNumber += newExpression[cont]
                            } else {
                                cont = 0
                            }
                            cont--
                        }

                        if (lastNumber.substring(0, lastNumber.length - 1).toDouble() > 0) {
                            newExpression = (newExpression.toString() + number).paintString()
                        } else {
                            newExpression = (newExpression.substring(0,newExpression.length - 1).paintString() + number)
                        }
                    } else {
                        newExpression = (newExpression.toString() + number).paintString()
                    }
                }
            }
            else if(number == '0')
            {
                var cont: Int = newExpression.length - 1
                var checkValue = false
                var controlDecimal = true
                var newString = ""

                if (newExpression[newExpression.length - 1] == '+' ||
                    newExpression[newExpression.length - 1] == '-' ||
                    newExpression[newExpression.length - 1] == '*' ||
                    newExpression[newExpression.length - 1] == '/'
                ) {
                    newExpression = (newExpression.toString() + "0").paintString()
                }

                while (cont >= 0) {
                    if (newExpression[cont].isDigit() || newExpression[cont] == '.') {
                        newString += newExpression[cont]
                    } else {
                        cont = 0
                    }
                    cont--
                }

                for (item in newString) {
                    if (item == '.') {
                        newExpression = (newExpression.toString() + "0").paintString()
                        controlDecimal = false
                    }
                }

                for (item in newString) {
                    if (controlDecimal) {
                        if (Character.getNumericValue(item.toInt()) > 0) {
                            checkValue = true
                        }
                    }
                }

                if (checkValue) {
                    newExpression = (newExpression.toString() + "0").paintString()
                    dataFieldExpression.value += newExpression
                }
            }
        } else {
            newExpression = (newExpression.toString() + number).paintString()
            dataFieldExpression.value += newExpression
        }
    }







































}
