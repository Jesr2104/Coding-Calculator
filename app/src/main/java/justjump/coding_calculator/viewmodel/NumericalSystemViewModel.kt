package justjump.coding_calculator.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import justjump.coding_calculator.utilities.Functions

class NumericalSystemViewModel: ViewModel() {

    var dataNumber = MutableLiveData<String>()
    var resultDecimal = MutableLiveData<String>()
    var resultBinary = MutableLiveData<String>()
    var resultOctal = MutableLiveData<String>()
    var resultHex = MutableLiveData<String>()

    init {
        dataNumber.value = ""
    }

    fun getSystemNumber(typeSNumber: String) {

        when(typeSNumber) {
            "Dec" -> {
                if (dataNumber.value!!.isNotEmpty()){
                    resultBinary.value = Functions().convertDecToBin(dataNumber.value!!.toLong())
                    resultOctal.value = Functions().convertDecToOct(dataNumber.value!!.toLong())
                    resultHex.value = Functions().convertDecToHex(dataNumber.value!!.toLong())
                }
                else
                {
                    resultBinary.value = ""
                    resultOctal.value = ""
                    resultHex.value = ""
                }
            }

            "Bin" -> {
                if (dataNumber.value!!.isNotEmpty()){

                    val newDecimalNumber = Functions().convertBinaryToDecimal(dataNumber.value!!)

                    resultOctal.value = Functions().convertDecToOct(newDecimalNumber.toLong())
                    resultDecimal.value = newDecimalNumber
                    resultHex.value = Functions().convertDecToHex(newDecimalNumber.toLong())
                }
                else
                {
                    resultOctal.value = ""
                    resultDecimal.value = ""
                    resultHex.value = ""
                }
            }

            "Oct" -> {
//                if (dataNumber.value!!.isNotEmpty()) {
//
//                    //here I'm gonna change the value of dataNumber to decimal
//                    val newDecimalNumber = Functions().convertOctalToDecimal(dataNumber.value!!)
//
//                    resultBinary.value = Functions().convertToBinary(newDecimalNumber)
//                    resultDecimal.value = newDecimalNumber.toString()
//                    resultHex.value = Functions().convertToHex(newDecimalNumber)
//                } else {
//                    resultBinary.value = ""
//                    resultDecimal.value = ""
//                    resultHex.value = ""
//                }
            }

            "Hex" -> {
//                if (dataNumber.value!!.isNotEmpty()){
//
//                    //here I'm gonna change the value of dataNumber to decimal
//                    val newDecimalNumber = Functions().convertHexToDecimal(dataNumber.value!!)
//
//                    resultBinary.value = Functions().convertToBinary(newDecimalNumber)
//                    resultOctal.value = Functions().convertToOctal(newDecimalNumber)
//                    resultDecimal.value = newDecimalNumber.toString()
//                }
//                else
//                {
//                    resultBinary.value = ""
//                    resultOctal.value = ""
//                    resultDecimal.value = ""
//                }
            }
        }
    }
}