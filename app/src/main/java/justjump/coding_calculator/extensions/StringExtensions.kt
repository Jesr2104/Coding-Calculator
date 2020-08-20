package justjump.coding_calculator.extensions

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

fun String.deleteComma(): String {
    var i = 0
    var tempString = ""
    val sizeofString = this.length

    while (sizeofString > i) {
        if (this[i].toInt() != 44) {
            tempString += this[i]
        }
        i++
    }
    return tempString
}

fun String.checkParenthesis(): Boolean {
    var numeroparentecis = 0

    for (item in this) {
        if (item == '(') {
            numeroparentecis++
        }

        if (item == ')') {
            numeroparentecis--
        }
    }

    if (numeroparentecis != 0) {
        return false
    }
    return true
}