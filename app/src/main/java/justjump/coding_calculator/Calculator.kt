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
import justjump.coding_calculator.extensions.paintString
import justjump.coding_calculator.viewmodel.CalculatorViewModel
import kotlinx.android.synthetic.main.activity_calculator.*

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
        val myObserverExpression = Observer<String> {
            expression.text = Html.fromHtml(it.paintString())
        }
        val myObserverResult = Observer<String> {
            tResult.text = it
        }

        // this observer works when the expression change
        cViewModel.dataFieldExpression.observe(this@Calculator, myObserverExpression)
        // this observer works when the result change
        cViewModel.dataFieldResult.observe(this@Calculator, myObserverResult)

        /***************************************************************************/
        // History
        /***************************************************************************/

        /** Falta modificar este codigo **/
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
        // Events of Numbers
        /***************************************************************************/

        // this listen event puts number zero on the expression but check first if it's allow
        number0.setOnClickListener{
            state = cViewModel.clearExpression(state)
            cViewModel.insertNumbers('0')
        }

        // this Click Listener for number 1
        number1.setOnClickListener{
            state = cViewModel.clearExpression(state)
            cViewModel.insertNumbers('1')
        }

        // this Click Listener for number 2
        number2.setOnClickListener{
            state = cViewModel.clearExpression(state)
            cViewModel.insertNumbers('2')
        }

        // this Click Listener for number 3
        number3.setOnClickListener{
            state = cViewModel.clearExpression(state)
            cViewModel.insertNumbers('3')
        }

        // this Click Listener for number 4
        number4.setOnClickListener{
            state = cViewModel.clearExpression(state)
            cViewModel.insertNumbers('4')
        }

        // this Click Listener for number 5
        number5.setOnClickListener{
            state = cViewModel.clearExpression(state)
            cViewModel.insertNumbers('5')
        }

        // this Click Listener for number 6
        number6.setOnClickListener{
            state = cViewModel.clearExpression(state)
            cViewModel.insertNumbers('6')
        }

        // this Click Listener for number 7
        number7.setOnClickListener{
            state = cViewModel.clearExpression(state)
            cViewModel.insertNumbers('7')
        }

        // this Click Listener for number 8
        number8.setOnClickListener{
            state = cViewModel.clearExpression(state)
            cViewModel.insertNumbers('8')
        }

        // this Click Listener for number 9
        number9.setOnClickListener{
            state = cViewModel.clearExpression(state)
            cViewModel.insertNumbers('9')
        }

        /***************************************************************************/
        // Events of Math signs
        /***************************************************************************/

        // this Click Listener for sign +
        numberPlus.setOnClickListener{
            state = cViewModel.ansData(state)
            cViewModel.sighPlus()
        }

        // this Click Listener for sign -
        numberLess.setOnClickListener {
            state = cViewModel.ansData(state)
            cViewModel.sighLess()
        }

        // this Click Listener for sign *
        numberMultiply.setOnClickListener{
            state = cViewModel.ansData(state)
            cViewModel.sighMultiply()
        }

        // this Click Listener for sign /
        numberDivide.setOnClickListener{
            state = cViewModel.ansData(state)
            cViewModel.sighDivide()
        }

        // this Click Listener for sign .
        numberPoint.setOnClickListener{
            state = cViewModel.ansData(state)
            cViewModel.addPoint()
        }

        // this Click Listener for sign %
        numberPercentage.setOnClickListener{
            state = cViewModel.ansData(state)
            cViewModel.sighPercentage()
        }

        // this Click Listener for sign (-
        numberPlusLess.setOnClickListener{
            cViewModel.plusLess()
        }

        // this Click Listener for sign (
        numberOpenParenthesis.setOnClickListener {
            cViewModel.openParenthesis()
        }

        // this Click Listener for sign )
        numberCloseParenthesis.setOnClickListener{
            cViewModel.closeParenthesis()
        }

        /***************************************************************************/
        // clear field
        /***************************************************************************/

        // this Click Listener for "All Clear"
        numberAllClear.setOnClickListener{
            cViewModel.allClear()
        }

        // this click listener for BackSpace
        numberBackSpace.setOnClickListener{
            cViewModel.backSpace()
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

        /** Falta modificar este codigo **/
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
            state = cViewModel.result()
            if (state) {
                with(PreferenceHelper) {
                    this.customPreference(this@Calculator)
                    this.setList(cViewModel.dataFieldExpression.value!!)
                    this.setList("=" + cViewModel.dataFieldResult.value!!)
                }

            } else {
                val toast = Toast.makeText(applicationContext,"Invalid format used.",Toast.LENGTH_SHORT)
                toast.show()
            }

        }
    }
}