package justjump.coding_calculator

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import justjump.coding_calculator.utilities.Functions
import justjump.coding_calculator.viewmodel.NumericalSystemViewModel
import kotlinx.android.synthetic.main.activity_numerical_systems.*


class NumericalSystems : AppCompatActivity() {

    lateinit var cViewModel: NumericalSystemViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_numerical_systems)

        cViewModel = ViewModelProviders.of(this).get(NumericalSystemViewModel::class.java)

        /***************************************************************************/
        // observer
        /***************************************************************************/
        val myObserverColor = Observer<String> {

            val intSelectButton: Int = radioGroup!!.checkedRadioButtonId
            val radioButton = findViewById<RadioButton>(intSelectButton)

            when (radioButton.text) {
                "Dec" -> {
                    cViewModel.getSystemNumber(radioButton.text.toString())
                }
                "Oct" -> {
                    cViewModel.getSystemNumber(radioButton.text.toString())
                }
                "Bin" -> {
                    cViewModel.getSystemNumber(radioButton.text.toString())
                }
                "Hex" -> {
                    cViewModel.getSystemNumber(radioButton.text.toString())
                }
            }

            resultBinary.text = cViewModel.resultBinary.value
            resultOctal.text = cViewModel.resultOctal.value
            resultHex.text = cViewModel.resultHex.value
            resultDecimal.text = cViewModel.resultDecimal.value
        }

        // this observer works when the expression change
        cViewModel.dataNumber.observe(this@NumericalSystems, myObserverColor)

        radioGroup.setOnCheckedChangeListener { group, checkedId ->

            // checkedId is the RadioButton selected
            val intSelectButton: Int = radioGroup!!.checkedRadioButtonId
            val radioButton = findViewById<RadioButton>(intSelectButton)

            cViewModel.dataNumber.value = cViewModel.dataNumber.value

            when(radioButton.text){
                "Dec" -> {
                    titleDecimal.visibility = View.GONE
                    resultDecimal.visibility = View.GONE

                    titleOctal.visibility = View.VISIBLE
                    resultOctal.visibility = View.VISIBLE
                    titleBinary.visibility = View.VISIBLE
                    resultBinary.visibility = View.VISIBLE
                    titleHex.visibility = View.VISIBLE
                    resultHex.visibility = View.VISIBLE
                }
                "Oct" -> {
                    titleOctal.visibility = View.GONE
                    resultOctal.visibility = View.GONE

                    titleDecimal.visibility = View.VISIBLE
                    resultDecimal.visibility = View.VISIBLE
                    titleBinary.visibility = View.VISIBLE
                    resultBinary.visibility = View.VISIBLE
                    titleHex.visibility = View.VISIBLE
                    resultHex.visibility = View.VISIBLE
                }
                "Bin" -> {
                    titleBinary.visibility = View.GONE
                    resultBinary.visibility = View.GONE

                    titleDecimal.visibility = View.VISIBLE
                    resultDecimal.visibility = View.VISIBLE
                    titleOctal.visibility = View.VISIBLE
                    resultOctal.visibility = View.VISIBLE
                    titleHex.visibility = View.VISIBLE
                    resultHex.visibility = View.VISIBLE
                }
                "Hex" -> {
                    titleHex.visibility = View.GONE
                    resultHex.visibility = View.GONE

                    titleDecimal.visibility = View.VISIBLE
                    resultDecimal.visibility = View.VISIBLE
                    titleOctal.visibility = View.VISIBLE
                    resultOctal.visibility = View.VISIBLE
                    titleBinary.visibility = View.VISIBLE
                    resultBinary.visibility = View.VISIBLE
                }
            }
        }

        fieldNumber.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {

                // checkedId is the RadioButton selected
                val intSelectButton: Int = radioGroup!!.checkedRadioButtonId
                val radioButton = findViewById<RadioButton>(intSelectButton)

                when (radioButton.text) {
                    "Dec" -> {

                    }
                    "Oct" -> {

                    }
                    "Bin" -> {

                    }
                    "Hex" -> {

                        val text: String = p0.toString()
                        val length: Int = text.length

                        //If the String length is bigger than zero and it's not composed only by the following characters: A to F and/or 0 to 9
                        if (!Functions().validateHexNumber(text) && length > 0) {
                            //Delete the last character if the last character is not validate to hexadecimal
                            p0!!.delete(length - 1, length)
                        }
                        else
                        {
                            cViewModel.dataNumber.value = fieldNumber.text.toString()
                        }

                    }
                }


            }
        })
    }
}