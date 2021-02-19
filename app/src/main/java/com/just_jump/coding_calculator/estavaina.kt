package com.just_jump.coding_calculator

/*
class estavaina {

    fun hasta(){
        val oldStr = expression_value.text.toString()
        val cursorPos = expression_value.selectionStart

        val leftStr = oldStr.substring(0, cursorPos)
        val rightStr = oldStr.substring(cursorPos)

        var num = ""
        var cont = leftStr.length - 1

        while (cont > -1){
            if (!"+-×÷%()".contains(leftStr[cont])){
                num += leftStr[cont]
            } else { cont = 0 }
            cont --
        }
        num = num.reversed()
        cont = 0

        if (rightStr.isNotEmpty()){
            while (cont < rightStr.length) {
                if (!"+-×÷%()".contains(rightStr[cont])){
                    num += rightStr[cont]
                } else { cont = rightStr.length }
                cont++
            }
        }

        if (num.isNotEmpty()){
            if (num == "0"){
                expression_value.setText(formatColor(leftStr.substring(0, leftStr.length - 1) + numberToAdd + rightStr)) //#formatColor
                expression_value.setSelection(cursorPos)
            } else if(num.toDouble() < 1 && num.toDouble() > 0){
                //expression_value.setText(formatColor(leftStr.substring(0, leftStr.length -1) + numberToAdd + rightStr)) //#formatColor
                //expression_value.setSelection(cursorPos + 1)
                // falta por repatar esta zona
                // falta por repatar esta zona
                // falta por repatar esta zona
                // falta por repatar esta zona
                // falta por repatar esta zona
                // falta por repatar esta zona
                // falta por repatar esta zona
                // falta por repatar esta zona
            } else {
                expression_value.setText(formatColor(leftStr + numberToAdd + rightStr)) //#formatColor
                expression_value.setSelection(cursorPos + 1)
            }
        } else {
            if (oldStr.isEmpty()){
                expression_value.setText(formatColor(leftStr + numberToAdd + rightStr)) //#formatColor
                expression_value.setSelection(cursorPos + 1)
            } else {
                if (leftStr[leftStr.length - 1 ] == ')' || leftStr[leftStr.length - 1 ] == '%'){
                    if (rightStr.isNotEmpty()){
                        if (rightStr[0] == '('){
                            expression_value.setText(formatColor("$leftStr×$numberToAdd×$rightStr")) //#formatColor
                            expression_value.setSelection(cursorPos + 2)
                        } else {
                            expression_value.setText(formatColor("$leftStr×$numberToAdd$rightStr")) //#formatColor
                            expression_value.setSelection(cursorPos + 2)
                        }
                    } else {
                        expression_value.setText(formatColor("$leftStr×$numberToAdd$rightStr")) //#formatColor
                        expression_value.setSelection(cursorPos + 2)
                    }
                } else {
                    if (rightStr.isNotEmpty()){
                        if (rightStr[0] == '('){
                            if ("+-×÷".contains(leftStr[leftStr.length - 1])){
                                expression_value.setText(formatColor("$leftStr$numberToAdd×$rightStr")) //#formatColor
                                expression_value.setSelection(cursorPos + 2)
                            } else {
                                expression_value.setText(formatColor("$leftStr×$numberToAdd×$rightStr")) //#formatColor
                                expression_value.setSelection(cursorPos + 2)
                            }
                        }
                        else {
                            if ("+-×÷".contains(leftStr[leftStr.length - 1])){
                                expression_value.setText(formatColor("$leftStr$numberToAdd$rightStr")) //#formatColor
                                expression_value.setSelection(cursorPos + 2)
                            } else {
                                expression_value.setText(formatColor("$leftStr×$numberToAdd$rightStr")) //#formatColor
                                expression_value.setSelection(cursorPos + 2)
                            }
                        }
                    } else {
                        expression_value.setText(formatColor(leftStr + numberToAdd + rightStr)) //#formatColor
                        expression_value.setSelection(cursorPos + 1)
                    }
                }
            }
        }
    }
}*/
