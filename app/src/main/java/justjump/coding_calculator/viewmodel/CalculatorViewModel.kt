package justjump.coding_calculator.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import justjump.coding_calculator.extensions.deleteComma

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
            if (number == '0') {
                var newSplitNumber = ""
                var cont = dataString.length - 1
                var controlDecimal = true
                var checkValue = false

                if (dataString[dataString.length - 1] == '+' ||
                    dataString[dataString.length - 1] == '-' ||
                    dataString[dataString.length - 1] == '*' ||
                    dataString[dataString.length - 1] == '/'
                ) {
                    dataString += "0"
                }

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
                    dataString = "$dataString 0"
                    dataFieldExpression.postValue(dataString)
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
                lastNumber = lastNumber.reversed()

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
                            dataString = "$dataString$number"
                            dataFieldExpression.postValue(dataString)
                        } else {
                            dataString = dataString.substring(0, dataString.length - 1) + number
                            dataFieldExpression.postValue(dataString)
                        }
                    } else {
                        dataString = "$dataString$number"
                        dataFieldExpression.postValue(dataString)
                    }
                }
                else
                {
                    dataString = "$dataString"
                    dataFieldExpression.postValue(dataString)
                }
            }
        } else {
            dataString = "$dataString$number"
            dataFieldExpression.postValue(dataString)
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

    fun addpoint(): Int {
        var dataString = "" + dataFieldExpression.value
        if (dataString.isNotEmpty()) {
            if (dataString[dataString.length - 1].isDigit()) {
                var run = true
                var typeDecimal = 0
                var cont: Int = dataString.length - 1

                while (cont >= 0 && run) {
                    if (dataString[cont].isDigit()) {
                        cont--
                    } else {
                        if (dataString[cont] == '.') {
                            run = false
                            typeDecimal = 1
                        } else if (dataString[cont] == '(' ||
                            dataString[cont] == ')' ||
                            dataString[cont] == '+' ||
                            dataString[cont] == '-' ||
                            dataString[cont] == '*' ||
                            dataString[cont] == '/' ||
                            dataString[cont] == '%'
                        ) {
                            run = false
                            typeDecimal = 2
                        }
                    }
                }

                if (typeDecimal == 0 || typeDecimal == 2) {
                    dataString = "$dataString."
                    dataFieldExpression.postValue(dataString)
                } else if (typeDecimal == 1) {
//                    val toast = Toast.makeText(applicationContext,
//                        "Invalid format used.",
//                        Toast.LENGTH_SHORT)
//                    toast.show()
                    return -1
                }
            } else if (dataString[dataString.length - 1] == ')') {
                dataString = "$dataString *0."
                dataFieldExpression.postValue(dataString)
            } else if (dataString[dataString.length - 1] != '.') {
                dataString = "$dataString 0."
                dataFieldExpression.postValue(dataString)
            }
        } else {
            dataString = "0."
            dataFieldExpression.postValue(dataString)
        }
        return 0
    }



}