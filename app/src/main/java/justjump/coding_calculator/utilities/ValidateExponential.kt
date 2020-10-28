package justjump.coding_calculator.utilities

import java.util.regex.Pattern

class ValidateExponential {

    fun validate(num: String): Boolean {
        val p = Pattern.compile("[-+]?[0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)")
        val m = p.matcher(num)
        val b = m.matches()

        if (b) {
            return true
        }
        return false
    }
}