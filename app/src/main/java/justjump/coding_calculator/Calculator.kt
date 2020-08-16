package justjump.coding_calculator

import android.content.Context
import android.os.Bundle
import android.text.Html
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.github.hamzaahmedkhan.spinnerdialog.callbacks.OnSpinnerOKPressedListener
import com.github.hamzaahmedkhan.spinnerdialog.enums.SpinnerSelectionType
import com.github.hamzaahmedkhan.spinnerdialog.models.SpinnerModel
import com.github.hamzaahmedkhan.spinnerdialog.ui.SpinnerDialogFragment
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_calculator.*
import java.text.DecimalFormat

/***************************************************************************/
// Calculator functions
/***************************************************************************/

class Calculator : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        var state = false

        /***************************************************************************/
        // Extra functions
        /***************************************************************************/

        // this function check if the number finish on .0 to leave just the integer part.
        fun checkInteger(result_check: String): String {
            if (result_check.length > 1) {
                if (result_check[result_check.length - 1] == '0') {
                    if (result_check[result_check.length - 2] == '.') {
                        return result_check.substring(0, result_check.length - 2)
                    }
                }
            }
            return result_check
        }

        // this function delete the fields if you press = to see the result.
        fun clearExpression() {
            if (state) {
                expression.text = ""
                state = false
            }
        }

        // this function delete the char ',' on the string it`s receives and return you string without this character
        fun deleteComma(number: String): String {
            var i = 0
            var tempString = ""

            while (number.length > i) {
                if (number[i].toInt() != 44) {
                    tempString += number[i]
                }
                i++
            }
            return tempString
        }

        // this function use the result like the information for the next expression
        fun ansData() {
            if (state) {
                expression.text = deleteComma(tResult.text.toString())
                tResult.text = ""
                state = false
            }
        }

        // with this function we give a color format to the expression on the text field
        fun paintString(expression: String): String {
            var resultemp = ""
            var cont = 0

            while (cont < expression.length) {
                if (expression[cont] == '(' || expression[cont] == ')') {
                    resultemp = resultemp + "<font color=#868686>" + expression[cont] + "</font>"
                } else if (expression[cont] == '+' || expression[cont] == '-' || expression[cont] == '*' || expression[cont] == '/' || expression[cont] == '%') {
                    // I like to change to include the negative sign with the number in white color complete
                    resultemp = resultemp + "<font color=#FFDD00>" + expression[cont] + "</font>"
                } else {
                    resultemp += expression[cont]
                }
                cont++
            }

            return resultemp
        }

        // this function save the date in the history
        fun saveHistory(newData: String) {
            val datafile = getSharedPreferences("HistoryData", Context.MODE_PRIVATE)
            val jObjectData = Gson()
            val maxData = 20

            // here we check if the file exists
            if (datafile.contains("HistoryData")) {
                var json = datafile.getString("HistoryData", "DEFAULT")
                var savehisttory =
                    jObjectData.fromJson<ArrayList<String>>(json, ArrayList<String>()::class.java)

                // we check to be sure the max of number of historial is 20 in this case
                if (savehisttory.size >= maxData) {
                    val temp: ArrayList<String> = ArrayList()
                    var i = 2

                    while (i < maxData) {
                        temp.add(savehisttory[i])
                        i++
                    }

                    savehisttory = temp
                    savehisttory.add(deleteComma(newData))
                } else {
                    savehisttory.add(deleteComma(newData))
                }

                val editor = datafile.edit()
                json = jObjectData.toJson(savehisttory)
                editor.putString("HistoryData", json)
                editor.apply()
            }

            // if the file not exists we can create one with the first item
            else {
                val temp: ArrayList<String> = ArrayList()
                temp.add(deleteComma(newData))

                val json = jObjectData.toJson(temp)
                val editor = datafile.edit()

                editor.putString("HistoryData", json)
                editor.apply()
            }
        }

        // this function load the data from the file to the new array list
        fun loadHistory(): ArrayList<String>? {
            val datafile = getSharedPreferences("HistoryData", Context.MODE_PRIVATE)
            val jObjectData = Gson()
            var saveHistory: ArrayList<String> = ArrayList()

            if (datafile.contains("HistoryData")) {
                val json = datafile.getString("HistoryData", "DEFAULT")
                saveHistory =
                    jObjectData.fromJson<ArrayList<String>>(json, ArrayList<String>()::class.java)

                return saveHistory
            }
            return saveHistory
        }

        // this function check all about the insert numbers and check to insert number zero when write one? and when not?
        fun insertNumbers(number: Char) {
            var lastNumber = ""
            var controldecimal = true

            if (expression.text.isNotEmpty()) {
                var cont: Int = expression.text.length - 1
                var checkvalue = false

                while (cont >= 0) {
                    if (expression.text[cont].isDigit() || expression.text[cont] == '.') {
                        lastNumber += expression.text[cont]
                    } else {
                        cont = 0
                    }
                    cont--
                }

                for (item in lastNumber) {
                    if (item == '.') {
                        expression.text = Html.fromHtml(paintString(expression.text.toString() + number))
                        controldecimal = false
                    }
                }

                if (controldecimal) {
                    while (cont >= 0) {
                        if (expression.text[cont].isDigit()) {
                            if (Character.getNumericValue(expression.text[cont]) > 0) {
                                checkvalue = true
                            }
                        } else {
                            cont = 0
                        }
                        cont--
                    }

                    if (checkvalue) {
                        expression.text = Html.fromHtml(paintString(expression.text.toString() + number))
                    } else if ((expression.text[expression.text.length - 1] == '0')) {
                        cont = expression.text.length - 1
                        while (cont >= 0) {
                            if (expression.text[cont].isDigit() || expression.text[cont] == '.') {
                                lastNumber += expression.text[cont]
                            } else {
                                cont = 0
                            }
                            cont--
                        }

                        if (lastNumber.substring(0, lastNumber.length - 1).toDouble() > 0) {
                            expression.text = Html.fromHtml(paintString(expression.text.toString() + number))
                        } else {
                            expression.text = Html.fromHtml(
                                paintString(
                                    expression.text.substring(
                                        0,
                                        expression.text.length - 1
                                    ) + number
                                )
                            )
                        }
                    } else {
                        expression.text = Html.fromHtml(paintString(expression.text.toString() + number))
                    }
                }
            } else {
                expression.text = Html.fromHtml(paintString(expression.text.toString() + number))
            }
        }

        // this function check the number of the parenthesis opens and closes to check that everything is closed
        fun checkParenthesis(Data: String): Boolean {
            var numeroparentecis = 0

            for (item in Data) {
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

        // this function descrease the size of the letter when length is higher than 10
        fun decreaseLetter() {
            if (expression.length() > 10) {
                expression.textSize = 25F
            }
        }

        // this function increase the size of the letter when the length is less than 10
        fun increaseLetter() {
            if (expression.length() <= 10) {
                expression.textSize = 35F
            }
        }

        /***************************************************************************/
        // History
        /***************************************************************************/

        // this function allow to select one option from the history and load on the expression
        historic.setOnClickListener {
            //Aqui va el codigo para crear el spinnerdialog
            val arraySpinnerModel: ArrayList<SpinnerModel> = ArrayList()
            val myList: ArrayList<String>? = loadHistory()

            if (myList != null) {
                var cont: Int = myList.size - 1

                while (cont >= 0) {
                    arraySpinnerModel.add(SpinnerModel(myList[cont]))
                    cont--
                }
            }

            val spinnerSingleSelectDialogFragment =
                SpinnerDialogFragment.newInstance(
                    SpinnerSelectionType.SINGLE_SELECTION, "History", arraySpinnerModel,
                    object :
                        OnSpinnerOKPressedListener {
                        override fun onSingleSelection(data: SpinnerModel, selectedPosition: Int) {
                            when {
                                data.text[0] != '=' -> {
                                    expression.text =
                                        Html.fromHtml(paintString(expression.text.toString() + data.text))
                                    tResult.text = ""
                                }
                                data.text[0] == '-' -> {
                                    expression.text =
                                        Html.fromHtml(paintString("(" + expression.text.toString() + data.text + ")"))
                                    tResult.text = ""
                                }
                                else -> {
                                    if (data.text[1] == '-') {
                                        expression.text = Html.fromHtml(
                                            paintString(
                                                expression.text.toString() + "(" + data.text.substring(
                                                    1,
                                                    data.text.length
                                                ) + ")"
                                            )
                                        )
                                    } else {
                                        expression.text = Html.fromHtml(
                                            paintString(
                                                expression.text.toString() + data.text.substring(
                                                    1,
                                                    data.text.length
                                                )
                                            )
                                        )
                                    }
                                    tResult.text = ""
                                }
                            }
                            decreaseLetter()
                        }

                        override fun onMultiSelection(
                            data: List<SpinnerModel>,
                            selectedPosition: Int
                        ) {/* It will never send Multi selection data in SINGLE_SELECTION Mode*/
                        }

                    }, 0
                )
            spinnerSingleSelectDialogFragment.showSearchBar = false
            spinnerSingleSelectDialogFragment.buttonText = "Load Data"
            spinnerSingleSelectDialogFragment.themeColorResId = resources.getColor(R.color.colorBase)
            spinnerSingleSelectDialogFragment.show(supportFragmentManager,"SpinnerDialogFragmentSingle")
        }

        /***************************************************************************/
        // Numbers
        /***************************************************************************/

        // this listen event puts number zero on the expression but check first if it's allow
        number0.setOnClickListener{
            clearExpression()

            var cont: Int = expression.text.length - 1
            var checkValue = false
            var controlDecimal = true

            if (expression.text.isNotEmpty()) {
                var nueva = ""

                if (expression.text[expression.text.length - 1] == '+' ||
                    expression.text[expression.text.length - 1] == '-' ||
                    expression.text[expression.text.length - 1] == '*' ||
                    expression.text[expression.text.length - 1] == '/'
                ) {
                    expression.text = Html.fromHtml(paintString(expression.text.toString() + "0"))
                }

                while (cont >= 0) {
                    if (expression.text[cont].isDigit() || expression.text[cont] == '.') {
                        nueva += expression.text[cont]
                    } else {
                        cont = 0
                    }
                    cont--
                }

                for (item in nueva) {
                    if (item == '.') {
                        expression.text = Html.fromHtml(paintString(expression.text.toString() + "0"))
                        controlDecimal = false
                    }
                }

                for (item in nueva) {
                    if (controlDecimal) {
                        if (Character.getNumericValue(item.toInt()) > 0) {
                            checkValue = true
                        }
                    }
                }

                if (checkValue) {
                    expression.text = Html.fromHtml(paintString(expression.text.toString() + "0"))
                }
            } else {
                expression.text = Html.fromHtml(paintString(expression.text.toString() + "0"))
            }
        }

        // this Click Listener for number 1
        number1.setOnClickListener{
            clearExpression()
            decreaseLetter()
            insertNumbers('1')
        }

        // this Click Listener for number 2
        number2.setOnClickListener{
            clearExpression()
            decreaseLetter()
            insertNumbers('2')
        }

        // this Click Listener for number 3
        number3.setOnClickListener{
            clearExpression()
            decreaseLetter()
            insertNumbers('3')
        }

        // this Click Listener for number 4
        number4.setOnClickListener{
            clearExpression()
            decreaseLetter()
            insertNumbers('4')
        }

        // this Click Listener for number 5
        number5.setOnClickListener{
            clearExpression()
            decreaseLetter()
            insertNumbers('5')
        }

        // this Click Listener for number 6
        number6.setOnClickListener{
            clearExpression()
            decreaseLetter()
            insertNumbers('6')
        }

        // this Click Listener for number 7
        number7.setOnClickListener{
            clearExpression()
            decreaseLetter()
            insertNumbers('7')
        }

        // this Click Listener for number 8
        number8.setOnClickListener{
            clearExpression()
            decreaseLetter()
            insertNumbers('8')
        }

        // this Click Listener for number 9
        number9.setOnClickListener{
            clearExpression()
            decreaseLetter()
            insertNumbers('9')
        }

        /***************************************************************************/
        // Math signs
        /***************************************************************************/

        // this Click Listener for sign +
        numberPlus.setOnClickListener{
            ansData()
            if (expression.text.isNotEmpty()) {
                // we check if we have already another arithmetic sigh to change for this one
                if (expression.text[expression.text.length - 1] == '+' || expression.text[expression.text.length - 1] == '-' || expression.text[expression.text.length - 1] == '*' || expression.text[expression.text.length - 1] == '/') {
                    if (!(expression.text[expression.text.length - 2] == '(' && expression.text[expression.text.length - 1] == '-')) {
                        expression.text = Html.fromHtml(paintString(expression.text.substring(0,expression.text.length - 1) + "+")
                        )
                    } else {
                        expression.text = Html.fromHtml(paintString(expression.text.substring(0,expression.text.length - 1)))
                    }
                }
                // if we dont have any arithmetic sign we need just to put the new one
                else {
                    if (expression.text[expression.text.length - 1] != '(') {
                        expression.text = Html.fromHtml(paintString(expression.text.toString() + "+"))
                    }
                }
            }
        }

        // this Click Listener for sign -
        numberLess.setOnClickListener{
            ansData()
            if (expression.text.isNotEmpty()) {
                if (expression.text[expression.text.length - 1] == '+' || expression.text[expression.text.length - 1] == '-' || expression.text[expression.text.length - 1] == '*' || expression.text[expression.text.length - 1] == '/') {
                    if (!(expression.text[expression.text.length - 2] == '(' && expression.text[expression.text.length - 1] == '-')) {
                        expression.text = Html.fromHtml(paintString(expression.text.substring(0, expression.text.length - 1) + "-"))
                    }
                } else {
                    expression.text = Html.fromHtml(paintString(expression.text.toString() + "-"))
                }
            }
        }

        // this Click Listener for sign *
        numberMultiply.setOnClickListener{
            ansData()
            if (expression.text.isNotEmpty()) {
                if (expression.text[expression.text.length - 1] == '+' || expression.text[expression.text.length - 1] == '-' || expression.text[expression.text.length - 1] == '*' || expression.text[expression.text.length - 1] == '/') {
                    if (!(expression.text[expression.text.length - 2] == '(' && expression.text[expression.text.length - 1] == '-')) {
                        expression.text = Html.fromHtml(paintString(expression.text.substring(0, expression.text.length - 1) + "*"))
                    } else {
                        expression.text = Html.fromHtml(paintString(expression.text.substring(0, expression.text.length - 1)))
                    }
                } else {
                    if (expression.text[expression.text.length - 1] != '(') {
                        expression.text = Html.fromHtml(paintString(expression.text.toString() + "*"))
                    }
                }
            }
        }

        // this Click Listener for sign /
        numberDivide.setOnClickListener{
            ansData()
            if (expression.text.isNotEmpty()) {
                if (expression.text[expression.text.length - 1] == '+' || expression.text[expression.text.length - 1] == '-' || expression.text[expression.text.length - 1] == '*' || expression.text[expression.text.length - 1] == '/') {
                    if (!(expression.text[expression.text.length - 2] == '(' && expression.text[expression.text.length - 1] == '-')) {
                        expression.text = Html.fromHtml(paintString(expression.text.substring(0, expression.text.length - 1) + "/"))
                    } else {
                        expression.text = expression.text.substring(0, expression.text.length - 1)
                    }
                }
                else {
                    if (expression.text[expression.text.length - 1] != '(') {
                        expression.text = Html.fromHtml(paintString(expression.text.toString() + "/"))
                    }
                }
            }
        }

        // this Click Listener for sign .
        numberPoint.setOnClickListener{
            if (expression.text.isNotEmpty()) {
                if (expression.text[expression.text.length - 1].isDigit()) {
                    var run = true
                    var typeDecimal = 0
                    var cont: Int = expression.text.length - 1

                    while (cont >= 0 && run) {
                        if (expression.text[cont].isDigit()) {
                            cont--
                        } else {
                            if (expression.text[cont] == '.') {
                                run = false
                                typeDecimal = 1
                            }
                            else if (expression.text[cont] == '(' ||
                                expression.text[cont] == ')' ||
                                expression.text[cont] == '+' ||
                                expression.text[cont] == '-' ||
                                expression.text[cont] == '*' ||
                                expression.text[cont] == '/' ||
                                expression.text[cont] == '%'
                            ) {
                                run = false
                                typeDecimal = 2
                            }
                        }
                    }

                    if (typeDecimal == 0 || typeDecimal == 2) {
                        expression.text = Html.fromHtml(paintString(expression.text.toString() + "."))
                    }
                    else if (typeDecimal == 1) {
                        val toast = Toast.makeText(applicationContext,"Invalid format used.",Toast.LENGTH_SHORT)
                        toast.show()
                    }
                } else if (expression.text[expression.text.length - 1] == ')') {
                    expression.text = Html.fromHtml(paintString(expression.text.toString() + "*0."))
                } else if (expression.text[expression.text.length - 1] != '.') {
                    expression.text = Html.fromHtml(paintString(expression.text.toString() + "0."))
                }
            } else {
                expression.text = "0."
            }
        }

        // this Click Listener for sign %
        numberPercentage.setOnClickListener{
            if (expression.text.isNotEmpty()) {
                if (!(expression.text[expression.text.length - 1] == '+' ||
                            expression.text[expression.text.length - 1] == '-' ||
                            expression.text[expression.text.length - 1] == '*' ||
                            expression.text[expression.text.length - 1] == '/' ||
                            expression.text[expression.text.length - 1] == '%' ||
                            expression.text[expression.text.length - 1] == '(')
                ) {
                    expression.text = Html.fromHtml(paintString(expression.text.toString() + "%"))
                } else {
                    val toast = Toast.makeText(applicationContext,"Invalid format used.", Toast.LENGTH_SHORT)
                    toast.show()
                }
            }
        }

        // this Click Listener for sign %
        numberPlusLess.setOnClickListener{
            if (expression.text.isNotEmpty())
            {
                if(expression.text[expression.text.length - 1] == ')')
                {
                    expression.text = Html.fromHtml(paintString(expression.text.toString() + "*(-"))
                }
                else if((expression.text[expression.text.length - 1] == '+' ||
                         expression.text[expression.text.length - 1] == '-' ||
                         expression.text[expression.text.length - 1] == '*' ||
                         expression.text[expression.text.length - 1] == '/') &&
                         !(expression.text[expression.text.length - 1] == '-' && expression.text[expression.text.length - 2] == '('))
                {
                    expression.text = Html.fromHtml(paintString(expression.text.toString() + "(-"))
                }
                else if (expression.text.length >= 2 && (expression.text[expression.text.length - 1] == '-' && expression.text[expression.text.length - 2] == '('))
                {
                    val restString = expression.text.substring(0, expression.text.length - 2)
                    expression.text = Html.fromHtml(paintString(restString))
                }
                else
                {
                    var number = ""
                    var cont: Int = expression.text.length - 1
                    var finish = true

                    while (finish) {
                        if (cont >= 0) {
                            if (expression.text[cont].isDigit() || expression.text[cont] == '.') {
                                number += expression.text[cont].toString()
                                cont--
                            } else {
                                finish = false
                            }
                        } else {
                            finish = false
                        }
                    }
                    number = number.reversed()

                    val restString= expression.text.substring(0, expression.text.length - number.length)

                    if (expression.text.length > number.length) {
                        if (expression.text[(expression.text.length - number.length) - 1] == '-' && expression.text[(expression.text.length - number.length) - 2] == '(') {
                            expression.text = Html.fromHtml(paintString("${restString.substring(0, restString.length - 2)}${number}"))
                        } else if (expression.text.length - number.length - 1 >= 0) {
                            if ((expression.text[(expression.text.length - number.length) - 1] == '+' ||
                                        expression.text[(expression.text.length - number.length) - 1] == '-' ||
                                        expression.text[(expression.text.length - number.length) - 1] == '*' ||
                                        expression.text[(expression.text.length - number.length) - 1] == '/') &&
                                        expression.text[(expression.text.length - number.length) - 2] != '('
                            ) {
                                expression.text = Html.fromHtml(paintString("${restString.substring(0, restString.length)}(-${number}"))
                            }
                        }
                    }
                    else {
                        expression.text = Html.fromHtml(paintString("(-${number}"))
                    }
                }
            }
            else {
                expression.text = Html.fromHtml(paintString(expression.text.toString() + "(-"))
            }
        }

        // this Click Listener for sign (
        numberOpenParenthesis.setOnClickListener {
            if (expression.text.isNotEmpty()) {
                if (expression.text[expression.text.length - 1] == ')' || expression.text[expression.text.length - 1].isDigit()) {
                    expression.text = Html.fromHtml(paintString(expression.text.toString() + "*("))
                } else {
                    expression.text = Html.fromHtml(paintString(expression.text.toString() + "("))
                }
            } else {
                expression.text = Html.fromHtml(paintString(expression.text.toString() + "("))
            }
        }

        // this Click Listener for sign )
        numberCloseParenthesis.setOnClickListener{
            expression.text = Html.fromHtml(paintString(expression.text.toString() + ")"))
        }

        /***************************************************************************/
        // clear field
        /***************************************************************************/

        // this Click Listener for "All Clear"
        numberAllClear.setOnClickListener{
            expression.text = ""
            tResult.text = ""
            increaseLetter()
        }

        // this click listener for BackSpace
        numberBackSpace.setOnClickListener{
            if (expression.text.isNotEmpty()) {
                expression.text = Html.fromHtml(paintString(expression.text.substring(0, expression.text.length - 1)))
            }
            increaseLetter()
        }

        /***************************************************************************/
        // Result expression
        /***************************************************************************/

        // This click listener get result
        numberResult.setOnClickListener {
            if (expression.text.contains('%')) {
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

                    tResult.text = checkInteger(format.format(Calculator_functions().basicEquations(dataResult)))
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

                        tResult.text = checkInteger(format.format(Calculator_functions().basicEquations(checkExp)))
                        state = true

                        saveHistory(expression.text.toString())
                        saveHistory("=" + tResult.text.toString())
                    } else {
                        val toast = Toast.makeText(applicationContext,"Invalid format used.",Toast.LENGTH_SHORT)
                        toast.show()
                    }
                }
            }
        }
    }
}