package justjump.coding_calculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.text.Html
import android.view.MotionEvent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.github.hamzaahmedkhan.spinnerdialog.callbacks.OnSpinnerOKPressedListener
import com.github.hamzaahmedkhan.spinnerdialog.enums.SpinnerSelectionType
import com.github.hamzaahmedkhan.spinnerdialog.models.SpinnerModel
import com.github.hamzaahmedkhan.spinnerdialog.ui.SpinnerDialogFragment
import justjump.coding_calculator.data.local.PreferenceHelper
import justjump.coding_calculator.extensions.checkInteger
import justjump.coding_calculator.extensions.checkParenthesis
import justjump.coding_calculator.extensions.paintString
import justjump.coding_calculator.viewmodel.CalculatorViewModel
import kotlinx.android.synthetic.main.activity_calculator.*
import java.text.DecimalFormat

/***************************************************************************/
// Calculator functions
/***************************************************************************/

class Calculator : AppCompatActivity() {

    var mainHandler: Handler? = null
    lateinit var cViewModel: CalculatorViewModel

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        var state = false
        cViewModel = ViewModelProviders.of(this).get(CalculatorViewModel::class.java)

        /***************************************************************************/
        // observer
        /***************************************************************************/
        val myObserver = Observer<String> {
            expression.text = it
        }
        cViewModel.dataFieldExpression.observe(this@Calculator, myObserver)

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
                        expression.text = Html.fromHtml((expression.text.toString() + number).paintString())
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
                        expression.text = Html.fromHtml((expression.text.toString() + number).paintString())
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
                            expression.text = Html.fromHtml((expression.text.toString() + number).paintString())
                        } else {
                            expression.text = Html.fromHtml(
                                (
                                    expression.text.substring(
                                        0,
                                        expression.text.length - 1
                                    ) + number
                                ).paintString()
                            )
                        }
                    } else {
                        expression.text = Html.fromHtml((expression.text.toString() + number).paintString())
                    }
                }
            } else {
                expression.text = Html.fromHtml((expression.text.toString() + number).paintString())
            }
        }

        /***************************************************************************/
        // History
        /***************************************************************************/

        // this function allow to select one option from the history and load on the expression
        historic.setOnClickListener {
            val arraySpinnerModel: ArrayList<SpinnerModel> = ArrayList()
            val myList: ArrayList<String>? = PreferenceHelper.customPreference(this).getlist()

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
                                        Html.fromHtml((expression.text.toString() + data.text).paintString())
                                    tResult.text = ""
                                }
                                data.text[0] == '-' -> {
                                    expression.text =
                                        Html.fromHtml(("(" + expression.text.toString() + data.text + ")").paintString())
                                    tResult.text = ""
                                }
                                else -> {
                                    if (data.text[1] == '-') {
                                        expression.text = Html.fromHtml(
                                            (
                                                    expression.text.toString() + "(" + data.text.substring(
                                                        1,
                                                        data.text.length
                                                    ) + ")"
                                                    ).paintString()
                                        )
                                    } else {
                                        expression.text = Html.fromHtml(
                                            (
                                                    expression.text.toString() + data.text.substring(
                                                        1,
                                                        data.text.length
                                                    )
                                                    ).paintString()
                                        )
                                    }
                                    tResult.text = ""
                                }
                            }
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
            spinnerSingleSelectDialogFragment.show(
                supportFragmentManager,
                "SpinnerDialogFragmentSingle"
            )
        }

        /***************************************************************************/
        // Numbers
        /***************************************************************************/

        // this listen event puts number zero on the expression but check first if it's allow
        number0.setOnClickListener{
            //state = cViewModel.clearExpression(state)
            cViewModel.insertNumbers('0')

//            var cont: Int = expression.text.length - 1
//            var checkValue = false
//            var controlDecimal = true
//
//            if (expression.text.isNotEmpty()) {
//                var nueva = ""
//
//                if (expression.text[expression.text.length - 1] == '+' ||
//                    expression.text[expression.text.length - 1] == '-' ||
//                    expression.text[expression.text.length - 1] == '*' ||
//                    expression.text[expression.text.length - 1] == '/'
//                ) {
//                    expression.text = Html.fromHtml((expression.text.toString() + "0").paintString())
//                }
//
//                while (cont >= 0) {
//                    if (expression.text[cont].isDigit() || expression.text[cont] == '.') {
//                        nueva += expression.text[cont]
//                    } else {
//                        cont = 0
//                    }
//                    cont--
//                }
//
//                for (item in nueva) {
//                    if (item == '.') {
//                        expression.text = Html.fromHtml((expression.text.toString() + "0").paintString())
//                        controlDecimal = false
//                    }
//                }
//
//                for (item in nueva) {
//                    if (controlDecimal) {
//                        if (Character.getNumericValue(item.toInt()) > 0) {
//                            checkValue = true
//                        }
//                    }
//                }
//
//                if (checkValue) {
//                    expression.text = Html.fromHtml((expression.text.toString() + "0").paintString())
//                }
//            } else {
//                expression.text = Html.fromHtml((expression.text.toString() + "0").paintString())
//            }
        }

        // this Click Listener for number 1
        number1.setOnClickListener{
            //state = cViewModel.clearExpression(state)
            //insertNumbers('1')
            cViewModel.insertNumbers('1')
        }

        // this Click Listener for number 2
        number2.setOnClickListener{
            //state = cViewModel.clearExpression(state)
            cViewModel.insertNumbers('2')
        }

        // this Click Listener for number 3
        number3.setOnClickListener{
            //state = cViewModel.clearExpression(state)
//            cViewModel.insertNumbers('3')
        }

        // this Click Listener for number 4
        number4.setOnClickListener{
            //state = cViewModel.clearExpression(state)
//            cViewModel.insertNumbers('4')
        }

        // this Click Listener for number 5
        number5.setOnClickListener{
            //state = cViewModel.clearExpression(state)
//            cViewModel.insertNumbers('5')
        }

        // this Click Listener for number 6
        number6.setOnClickListener{
            //state = cViewModel.clearExpression(state)
//            cViewModel.insertNumbers('6')
        }

        // this Click Listener for number 7
        number7.setOnClickListener{
            //state = cViewModel.clearExpression(state)
//            cViewModel.insertNumbers('7')
        }

        // this Click Listener for number 8
        number8.setOnClickListener{
            //state = cViewModel.clearExpression(state)
//            cViewModel.insertNumbers('8')
        }

        // this Click Listener for number 9
        number9.setOnClickListener{
            //state = cViewModel.clearExpression(state)
//            cViewModel.insertNumbers('9')
        }

        /***************************************************************************/
        // Math signs
        /***************************************************************************/

        // this Click Listener for sign +
        numberPlus.setOnClickListener{
            //state = cViewModel.ansData(state)
            //cViewModel.insertSighPlus()

//            if (expression.text.isNotEmpty()) {
//                // we check if we have already another arithmetic sigh to change for this one
//                if (expression.text[expression.text.length - 1] == '+' || expression.text[expression.text.length - 1] == '-' || expression.text[expression.text.length - 1] == '*' || expression.text[expression.text.length - 1] == '/') {
//                    if (!(expression.text[expression.text.length - 2] == '(' && expression.text[expression.text.length - 1] == '-')) {
//                        expression.text = Html.fromHtml(
//                            (expression.text.substring(
//                                0,
//                                expression.text.length - 1
//                            ) + "+").paintString()
//                        )
//                    } else {
//                        expression.text = Html.fromHtml(
//                            (expression.text.substring(
//                                0,
//                                expression.text.length - 1
//                            )).paintString()
//                        )
//                    }
//                }
//                // if we dont have any arithmetic sign we need just to put the new one
//                else {
//                    if (expression.text[expression.text.length - 1] != '(') {
//                        expression.text = Html.fromHtml((expression.text.toString() + "+").paintString())
//                    }
//                }
//            }
        }

        // this Click Listener for sign -
        numberLess.setOnClickListener{
            //ansData()
            if (expression.text.isNotEmpty()) {
                if (expression.text[expression.text.length - 1] == '+' || expression.text[expression.text.length - 1] == '-' || expression.text[expression.text.length - 1] == '*' || expression.text[expression.text.length - 1] == '/') {
                    if (!(expression.text[expression.text.length - 2] == '(' && expression.text[expression.text.length - 1] == '-')) {
                        expression.text = Html.fromHtml(
                            (expression.text.substring(
                                0,
                                expression.text.length - 1
                            ) + "-").paintString()
                        )
                    }
                } else {
                    expression.text = Html.fromHtml((expression.text.toString() + "-").paintString())
                }
            }
        }

        // this Click Listener for sign *
        numberMultiply.setOnClickListener{
            //ansData()
            if (expression.text.isNotEmpty()) {
                if (expression.text[expression.text.length - 1] == '+' || expression.text[expression.text.length - 1] == '-' || expression.text[expression.text.length - 1] == '*' || expression.text[expression.text.length - 1] == '/') {
                    if (!(expression.text[expression.text.length - 2] == '(' && expression.text[expression.text.length - 1] == '-')) {
                        expression.text = Html.fromHtml(
                            (expression.text.substring(
                                0,
                                expression.text.length - 1
                            ) + "*").paintString()
                        )
                    } else {
                        expression.text = Html.fromHtml(
                            (expression.text.substring(
                                0,
                                expression.text.length - 1
                            )).paintString()
                        )
                    }
                } else {
                    if (expression.text[expression.text.length - 1] != '(') {
                        expression.text = Html.fromHtml((expression.text.toString() + "*").paintString())
                    }
                }
            }
        }

        // this Click Listener for sign /
        numberDivide.setOnClickListener{
            //ansData()
            if (expression.text.isNotEmpty()) {
                if (expression.text[expression.text.length - 1] == '+' || expression.text[expression.text.length - 1] == '-' || expression.text[expression.text.length - 1] == '*' || expression.text[expression.text.length - 1] == '/') {
                    if (!(expression.text[expression.text.length - 2] == '(' && expression.text[expression.text.length - 1] == '-')) {
                        expression.text = Html.fromHtml(
                            (expression.text.substring(
                                0,
                                expression.text.length - 1
                            ) + "/").paintString()
                        )
                    } else {
                        expression.text = expression.text.substring(0, expression.text.length - 1)
                    }
                }
                else {
                    if (expression.text[expression.text.length - 1] != '(') {
                        expression.text = Html.fromHtml((expression.text.toString() + "/").paintString())
                    }
                }
            }
        }

        // this Click Listener for sign .
        numberPoint.setOnClickListener{
            cViewModel.addpoint()
//            if (expression.text.isNotEmpty()) {
//                if (expression.text[expression.text.length - 1].isDigit()) {
//                    var run = true
//                    var typeDecimal = 0
//                    var cont: Int = expression.text.length - 1
//
//                    while (cont >= 0 && run) {
//                        if (expression.text[cont].isDigit()) {
//                            cont--
//                        } else {
//                            if (expression.text[cont] == '.') {
//                                run = false
//                                typeDecimal = 1
//                            }
//                            else if (expression.text[cont] == '(' ||
//                                expression.text[cont] == ')' ||
//                                expression.text[cont] == '+' ||
//                                expression.text[cont] == '-' ||
//                                expression.text[cont] == '*' ||
//                                expression.text[cont] == '/' ||
//                                expression.text[cont] == '%'
//                            ) {
//                                run = false
//                                typeDecimal = 2
//                            }
//                        }
//                    }
//
//                    if (typeDecimal == 0 || typeDecimal == 2) {
//                        expression.text = Html.fromHtml((expression.text.toString() + ".").paintString())
//                    }
//                    else if (typeDecimal == 1) {
//                        val toast = Toast.makeText(
//                            applicationContext,
//                            "Invalid format used.",
//                            Toast.LENGTH_SHORT
//                        )
//                        toast.show()
//                    }
//                } else if (expression.text[expression.text.length - 1] == ')') {
//                    expression.text = Html.fromHtml((expression.text.toString() + "*0.").paintString())
//                } else if (expression.text[expression.text.length - 1] != '.') {
//                    expression.text = Html.fromHtml((expression.text.toString() + "0.").paintString())
//                }
//            } else {
//                expression.text = "0."
//            }
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
                    expression.text = Html.fromHtml((expression.text.toString() + "%").paintString())
                } else {
                    val toast = Toast.makeText(
                        applicationContext,
                        "Invalid format used.",
                        Toast.LENGTH_SHORT
                    )
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
                    expression.text = Html.fromHtml((expression.text.toString() + "*(-").paintString())
                }
                else if((expression.text[expression.text.length - 1] == '+' ||
                         expression.text[expression.text.length - 1] == '-' ||
                         expression.text[expression.text.length - 1] == '*' ||
                         expression.text[expression.text.length - 1] == '/') &&
                         !(expression.text[expression.text.length - 1] == '-' && expression.text[expression.text.length - 2] == '('))
                {
                    expression.text = Html.fromHtml((expression.text.toString() + "(-").paintString())
                }
                else if (expression.text.length >= 2 && (expression.text[expression.text.length - 1] == '-' && expression.text[expression.text.length - 2] == '('))
                {
                    val restString = expression.text.substring(0, expression.text.length - 2)
                    expression.text = Html.fromHtml((restString).paintString())
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

                    val restString= expression.text.substring(
                        0,
                        expression.text.length - number.length
                    )

                    if (expression.text.length > number.length) {
                        if (expression.text[(expression.text.length - number.length) - 1] == '-' && expression.text[(expression.text.length - number.length) - 2] == '(') {
                            expression.text = Html.fromHtml(
                                ("${
                                    restString.substring(
                                        0,
                                        restString.length - 2
                                    )
                                }${number}").paintString()
                            )
                        } else if (expression.text.length - number.length - 1 >= 0) {
                            if ((expression.text[(expression.text.length - number.length) - 1] == '+' ||
                                        expression.text[(expression.text.length - number.length) - 1] == '-' ||
                                        expression.text[(expression.text.length - number.length) - 1] == '*' ||
                                        expression.text[(expression.text.length - number.length) - 1] == '/') &&
                                        expression.text[(expression.text.length - number.length) - 2] != '('
                            ) {
                                expression.text = Html.fromHtml(
                                    ("${
                                        restString.substring(
                                            0,
                                            restString.length
                                        )
                                    }(-${number}").paintString()
                                )
                            }
                        }
                    }
                    else {
                        expression.text = Html.fromHtml(("(-${number}").paintString())
                    }
                }
            }
            else {
                expression.text = Html.fromHtml((expression.text.toString() + "(-").paintString())
            }
        }

        // this Click Listener for sign (
        numberOpenParenthesis.setOnClickListener {
            if (expression.text.isNotEmpty()) {
                if (expression.text[expression.text.length - 1] == ')' || expression.text[expression.text.length - 1].isDigit()) {
                    expression.text = Html.fromHtml((expression.text.toString() + "*(").paintString())
                } else {
                    expression.text = Html.fromHtml((expression.text.toString() + "(").paintString())
                }
            } else {
                expression.text = Html.fromHtml((expression.text.toString() + "(").paintString())
            }
        }

        // this Click Listener for sign )
        numberCloseParenthesis.setOnClickListener{
            expression.text = Html.fromHtml((expression.text.toString() + ")").paintString())
        }

        /***************************************************************************/
        // clear field
        /***************************************************************************/

        // this Click Listener for "All Clear"
        numberAllClear.setOnClickListener{
            expression.text = ""
            tResult.text = ""
        }

        // this click listener for BackSpace
        numberBackSpace.setOnClickListener{
            if (expression.text.isNotEmpty()) {
                expression.text = Html.fromHtml(
                    (expression.text.substring(
                        0,
                        expression.text.length - 1
                    )).paintString()
                )
            }
        }

        val action = object : Runnable {
            override fun run() {
                if (expression.text.isNotEmpty()) {
                    expression.text = Html.fromHtml(
                        (
                                expression.text.substring(
                                    0,
                                    expression.text.length - 1
                                )
                                ).paintString()
                    )
                }
                mainHandler?.postDelayed(this, 200)
            }
        }

        // this Listener when you keep press the button to clear
        numberBackSpace.setOnTouchListener { view, motionEvent ->
                when (motionEvent.action) {
                    MotionEvent.ACTION_DOWN -> {
                        if (mainHandler != null)
                            true
                        mainHandler = Handler()
                        mainHandler?.postDelayed(action, 200)
                        false
                    }
                    MotionEvent.ACTION_UP -> {
                        if (mainHandler == null)
                            true
                        mainHandler?.removeCallbacks(action)
                        mainHandler = null
                        false
                    }
                    else -> false
                }
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

                if (dataResult.checkParenthesis()) {
                    val format = DecimalFormat()
                    format.maximumFractionDigits = 4

                    tResult.text = format.format(Functions().basicEquations(dataResult)).checkInteger()
                    state = true

                    with(PreferenceHelper) {
                        this.customPreference(this@Calculator)
                        this.setList(expression.text.toString())
                        this.setList("=" + tResult.text.toString())
                    }
                } else {
                    val toast = Toast.makeText(
                        applicationContext,
                        "Invalid format used.",
                        Toast.LENGTH_SHORT
                    )
                    toast.show()
                }
            } else {
                if (expression.text.isNotEmpty()) {
                    val checkExp = expression.text.toString()

                    if (checkExp.checkParenthesis()) {
                        val format = DecimalFormat()
                        format.maximumFractionDigits = 4

                        tResult.text = format.format(Functions().basicEquations(checkExp)).checkInteger()
                        state = true

                        with(PreferenceHelper) {
                            customPreference(this@Calculator)
                            setList(expression.text.toString())
                            setList("=" + tResult.text.toString())
                        }

                    } else {
                        val toast = Toast.makeText(
                            applicationContext,
                            "Invalid format used.",
                            Toast.LENGTH_SHORT
                        )
                        toast.show()
                    }
                }
            }
        }
    }
}