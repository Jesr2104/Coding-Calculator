package com.just_jump.coding_calculator.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.just_jump.coding_calculator.utilities.Functions
import com.just_jump.coding_calculator.extensions.checkInteger
import com.just_jump.coding_calculator.extensions.checkParenthesis
import com.just_jump.coding_calculator.extensions.deleteComma
import java.text.DecimalFormat

class ViewModelCalculator: ViewModel() {

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
                var checkValue = false

                if (dataString[dataString.length - 1] == '+' ||
                    dataString[dataString.length - 1] == '-' ||
                    dataString[dataString.length - 1] == '*' ||
                    dataString[dataString.length - 1] == '/'
                ) {
                    dataString += "0"
                    dataFieldExpression.postValue(dataString)
                }
                else
                {
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
                                checkValue = true
                            }
                        }
                        if (checkValue)
                        {
                            dataString += "0"
                            dataFieldExpression.postValue(dataString)
                        }
                    }
                }
            }
            // logic for the rest of the number
            else {
                var lastNumber = ""
                var controlDecimal = true
                var cont: Int = dataString.length - 1
                var checkValue = false

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
                        controlDecimal = false
                    }
                }

                if (controlDecimal) {
                    while (cont >= 0) {
                        if (dataString[cont].isDigit()) {
                            if (Character.getNumericValue(dataString[cont]) > 0) {
                                checkValue = true
                            }
                        } else {
                            cont = 0
                        }
                        cont--
                    }
                    if (checkValue) {
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
                dataString = if (!(dataString[dataString.length - 2] == '(' && dataString[dataString.length - 1] == '-')) {
                    (dataString.substring(0, dataString.length - 1) + "+")
                } else {
                    (dataString.substring(0, dataString.length - 1))
                }
            }
            // if we don't have any arithmetic sign we need just to put the new one
            else {
                if (dataString[dataString.length - 1] != '(') {
                    dataString = ("$dataString+")
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
                dataString = if (!(dataString[dataString.length - 2] == '(' && dataString[dataString.length - 1] == '-')) {
                    dataString.substring(0, dataString.length - 1) + "*"
                } else {
                    dataString.substring(0, dataString.length - 1)
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
                dataString = if (!(dataString[dataString.length - 2] == '(' && dataString[dataString.length - 1] == '-')) {
                    dataString.substring(0, dataString.length - 1) + "/"
                } else {
                    dataString.substring(0, dataString.length - 1)
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
                dataString = "${dataString}0."
                dataFieldExpression.postValue(dataString)
            }
        } else {
            dataString = "0."
            dataFieldExpression.postValue(dataString)
        }
        return 0
    }

    // this function insert the sigh of navb_percentage '%'
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
                dataString += "%"
            }
        }
        dataFieldExpression.postValue(dataString)
    }

    // this function insert open parenthesis '('
    fun openParenthesis(){
        var dataString = "" + dataFieldExpression.value
        dataString += if (dataString.isNotEmpty()) {
            if (dataString[dataString.length - 1] == ')' || dataString[dataString.length - 1].isDigit()) {
                "*("
            } else {
                "("
            }
        } else {
            "("
        }
        dataFieldExpression.postValue(dataString)
    }

    // this function insert close parenthesis ')'
    fun closeParenthesis(){
        var dataString = "" + dataFieldExpression.value
        dataString += if (dataString.isNotEmpty()) {
            ")"
        } else {
            "("
        }

        dataFieldExpression.postValue(dataString)
    }

    // this function insert character (- to convert one number in negative
    fun plusLess(){
        var dataString = "" + dataFieldExpression.value
        if (dataString.isNotEmpty())
        {
            if(dataString[dataString.length - 1] == ')')
            {
                dataString = "$dataString*(-"
            }
            else if((dataString[dataString.length - 1] == '+' ||
                        dataString[dataString.length - 1] == '-' ||
                        dataString[dataString.length - 1] == '*' ||
                        dataString[dataString.length - 1] == '/') &&
                !(dataString[dataString.length - 1] == '-' && dataString[dataString.length - 2] == '('))
            {
                dataString = "$dataString(-"
            }
            else if (dataString.length >= 2 && (dataString[dataString.length - 1] == '-' && dataString[dataString.length - 2] == '('))
            {
                val restString = dataString.substring(0, dataString.length - 2)
                dataString = restString
            }
            else
            {
                var number = ""
                var cont: Int = dataString.length - 1
                var finish = true

                while (finish) {
                    if (cont >= 0) {
                        if (dataString[cont].isDigit() || dataString[cont] == '.') {
                            number += dataString[cont].toString()
                            cont--
                        } else {
                            finish = false
                        }
                    } else {
                        finish = false
                    }
                }
                number = number.reversed()

                val restString= dataString.substring(0,dataString.length - number.length)

                if (dataString.length > number.length) {
                    if (dataString[(dataString.length - number.length) - 1] == '-' && dataString[(dataString.length - number.length) - 2] == '(') {
                        dataString = "${restString.substring(0,restString.length - 2)}${number}"
                    } else if (dataString.length - number.length - 1 >= 0) {
                        if ((dataString[(dataString.length - number.length) - 1] == '+' ||
                                    dataString[(dataString.length - number.length) - 1] == '-' ||
                                    dataString[(dataString.length - number.length) - 1] == '*' ||
                                    dataString[(dataString.length - number.length) - 1] == '/') &&
                            dataString[(dataString.length - number.length) - 2] != '('
                        ) {
                            dataString = "${restString.substring(0,restString.length)}(-${number}"
                        }
                    }
                }
                else {
                    dataString = "(-${number}"
                }
            }
        }
        else {
            dataString = "$dataString(-"
        }
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

    // this function solve the result of the expression
    fun result(): Boolean {
        val dataString = "" + dataFieldExpression.value
        var resultData = ""
        if (dataString.contains('%')) {
            var i: Int = dataString.length - 1
            var number = ""
            var dataResult = ""

            while (0 <= i) {
                if (dataString[i] == '%') {
                    var index = i - 1

                    while (0 <= index) {
                        if (index >= 0) {
                            if (dataString[index].isDigit() || dataString[index] == '.') {
                                number += dataString[index]
                            } else {
                                index = -1
                            }
                        }
                        index--
                    }
                    // I don't have exactly this one hire
                    dataResult = if (number.isNotEmpty()) {
                        "(${number.reversed()}/100)" + dataResult
                    } else {
                        "${number.reversed()}/100" + dataResult
                    }

                    i -= number.length
                    number = ""
                } else {
                    dataResult = dataString[i].toString() + dataResult
                }
                i--
            }

            if (dataResult.checkParenthesis()) {
                val format = DecimalFormat()
                val dataStringInfo = Functions().basicEquations(dataResult)
                format.maximumFractionDigits = 5

                if (dataStringInfo != "#2104")
                {
                    resultData = format.format(dataStringInfo.toDouble()).checkInteger()
                }
                else
                {
                    return false
                }

            } else {
                return false
            }
        } else {
            if (dataString.isNotEmpty()) {
                if (dataString.checkParenthesis()) {
                    val dataStringInfo = Functions().basicEquations(dataString)
                    val format = DecimalFormat()
                    format.maximumFractionDigits = 5

                    if (dataStringInfo != "#2104")
                    {
                        resultData = format.format(dataStringInfo.toDouble()).checkInteger()
                    }
                    else
                    {
                        return false
                    }

                } else {
                    return false
                }
            }
        }
        dataFieldResult.value = resultData
        return true
    }

    // this function allow correct the result without clear the expression
    fun correctResult(): Boolean {
        dataFieldResult.value = ""
        return false
    }
}