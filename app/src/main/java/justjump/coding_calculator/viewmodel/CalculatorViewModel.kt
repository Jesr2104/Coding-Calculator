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
                var checkvalue = false

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
                newSplitNumber = newSplitNumber.reversed()

                if(newSplitNumber.contains('.')){
                    dataString += "0"
                    dataFieldExpression.postValue(dataString)
                }
                else
                {
                    for (item in newSplitNumber.reversed()) {
                        if (Character.getNumericValue(item.toInt()) > 0) {
                            dataString += "0"
                            checkvalue = true
                        }
                    }
                    if (checkvalue)
                    {
                        dataFieldExpression.postValue(dataString)
                    }
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
                } else {
                    dataFieldExpression.postValue(dataString)
                }
            }
        } else {
            dataString = "$dataString$number"
            dataFieldExpression.postValue(dataString)
        }
    }

    // this function insert the sigh of plus '+'
    fun sighPlus() {
        var dataString = "" + dataFieldExpression.value
        if (dataString.isNotEmpty()) {
            // we check if we have already another arithmetic sigh to change for this one
            if (dataString[dataString.length - 1] == '+' || dataString[dataString.length - 1] == '-' || dataString[dataString.length - 1] == '*' || dataString[dataString.length - 1] == '/') {
                if (!(dataString[dataString.length - 2] == '(' && dataString[dataString.length - 1] == '-')) {
                    dataString = (dataString.substring(0, dataString.length - 1) + "+")
                } else {
                    dataString = (dataString.substring(0, dataString.length - 1))
                }
            }
            // if we don't have any arithmetic sign we need just to put the new one
            else {
                if (dataString[dataString.length - 1] != '(') {
                    dataString = (dataString + "+")
                }
            }
        }
        dataFieldExpression.postValue(dataString)
    }

    // this function insert the sigh of less '-'
    fun sighLess() {
        var dataString = "" + dataFieldExpression.value
        if (dataString.isNotEmpty()) {
            if (dataString[dataString.length - 1] == '+' || dataString[dataString.length - 1] == '-' || dataString[dataString.length - 1] == '*' || dataString[dataString.length - 1] == '/') {
                if (!(dataString[dataString.length - 2] == '(' && dataString[dataString.length - 1] == '-')) {
                    dataString = dataString.substring(0, dataString.length - 1) + "-"
                }
            } else {
                dataString += "-"
            }
        }
        dataFieldExpression.postValue(dataString)
    }

    // this function insert the sigh of multiply '*'
    fun sighMultiply() {
        var dataString = "" + dataFieldExpression.value

        if (dataString.isNotEmpty()) {
            if (dataString[dataString.length - 1] == '+' || dataString[dataString.length - 1] == '-' || dataString[dataString.length - 1] == '*' || dataString[dataString.length - 1] == '/') {
                if (!(dataString[dataString.length - 2] == '(' && dataString[dataString.length - 1] == '-')) {
                    dataString = dataString.substring(0, dataString.length - 1) + "*"
                } else {
                    dataString = dataString.substring(0, dataString.length - 1)
                }
            } else {
                if (dataString[dataString.length - 1] != '(') {
                    dataString += "*"
                }
            }
        }
        dataFieldExpression.postValue(dataString)
    }

    // this function insert the sigh of divide '/'
    fun sighDivide() {
        var dataString = "" + dataFieldExpression.value

        if (dataString.isNotEmpty()) {
            if (dataString[dataString.length - 1] == '+' || dataString[dataString.length - 1] == '-' || dataString[dataString.length - 1] == '*' || dataString[dataString.length - 1] == '/') {
                if (!(dataString[dataString.length - 2] == '(' && dataString[dataString.length - 1] == '-')) {
                    dataString = dataString.substring(0, dataString.length - 1) + "/"
                } else {
                    dataString = dataString.substring(0, dataString.length - 1)
                }
            } else {
                if (dataString[dataString.length - 1] != '(') {
                    dataString = "$dataString/"
                }
            }
        }
        dataFieldExpression.postValue(dataString)
    }

    // this function insert the sigh of point '.'
    fun addPoint(): Int {
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

    // this function insert the sigh of percentage '%'
    fun sighPercentage(){
        var dataString = "" + dataFieldExpression.value
        if (dataString.isNotEmpty()) {
            if (!(dataString[dataString.length - 1] == '+' ||
                        dataString[dataString.length - 1] == '-' ||
                        dataString[dataString.length - 1] == '*' ||
                        dataString[dataString.length - 1] == '/' ||
                        dataString[dataString.length - 1] == '%' ||
                        dataString[dataString.length - 1] == '(')
            ) {
                dataString = dataString.toString() + "%"
            }
        }
        dataFieldExpression.postValue(dataString)
    }

    // this function insert open parenthesis '('
    fun openParenthesis(){
        var dataString = "" + dataFieldExpression.value
        if (dataString.isNotEmpty()) {
            if (dataString[dataString.length - 1] == ')' || dataString[dataString.length - 1].isDigit()) {
                dataString += "*("
            } else {
                dataString += "("
            }
        } else {
            dataString += "("
        }
        dataFieldExpression.postValue(dataString)
    }

    // this function insert close parenthesis ')'
    fun closeParenthesis(){
        var dataString = "" + dataFieldExpression.value
        dataString += ")"
        dataFieldExpression.postValue(dataString)
    }

    // this function delete the fields if you press = to see the result.
    fun clearExpression(check: Boolean): Boolean {
        if (check) {
            dataFieldExpression.value = ""
        }
        return false
    }

    // this function clear result field and expression field
    fun allClear(){
        dataFieldResult.postValue("")
        dataFieldExpression.postValue("")
    }

    // this function delete the last character inserted
    fun backSpace(){
        var dataString = "" + dataFieldExpression.value
        if (dataString.isNotEmpty()) {
            dataString = dataString.substring(0,dataString.length - 1)
            dataFieldExpression.postValue(dataString)
        }
    }

    // this function use the result like the information for the next expression
    fun ansData(check: Boolean = true): Boolean {
        if (check) {
            dataFieldExpression.value = (dataFieldResult.value.toString()).deleteComma()
            dataFieldResult.value = ""
        }
        return false
    }
}