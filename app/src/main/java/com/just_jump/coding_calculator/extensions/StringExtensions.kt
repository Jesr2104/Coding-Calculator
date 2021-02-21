package com.just_jump.coding_calculator.extensions

// this function delete the decimal part of the number if that is = zero
fun String.checkInteger(): String {
    val sizeString = this.length

    if (sizeString > 1) {
        if (this[sizeString - 1] == '0') {
            if (this[sizeString - 2] == '.') {
                return this.substring(0, sizeString - 2)
            }
        }
    }
    return this
}

// this function check if the parenthesis is correct
fun String.checkParenthesis(): Boolean {
    var numberParenthesis = 0

    for (item in this) {
        if (item == '(') {
            numberParenthesis++
        }

        if (item == ')') {
            numberParenthesis--
        }
    }

    if (numberParenthesis != 0) {
        return false
    }
    return true
}

// this function colors the string of the expression
fun String.paintString(): String {
    var resultTemp = ""
    var cont = 0

    val colorParenthesis = "<font color=#9E9E9E>"
    val colorSign = "<font color=#FFDD00>"
    val fontClose = "</font>"


    while (cont < this.length) {
        if (this[cont] == '(' || this[cont] == ')') {
            resultTemp = resultTemp + colorParenthesis + this[cont] + fontClose
        } else if (this[cont] == '+' || this[cont] == '-' || this[cont] == '×' || this[cont] == '÷'|| this[cont] == '%') {
            // I like to change to include the negative sign with the number in white_arrow color complete
            resultTemp = resultTemp + colorSign + this[cont] + fontClose
        } else {
            resultTemp += this[cont]
        }
        cont++
    }
    return resultTemp
}