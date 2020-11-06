package com.just_jump.coding_calculator.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.just_jump.coding_calculator.utilities.Functions

class ViewModelNumericalSystem: ViewModel() {

    var dataNumber = MutableLiveData<String>()
    var resultDecimal = MutableLiveData<String>()
    var resultBinary = MutableLiveData<String>()
    var resultOctal = MutableLiveData<String>()
    var resultHex = MutableLiveData<String>()

    init {
        dataNumber.value = ""
    }

    /**
     *  this function have the control of the update of the calculate numerical systems.
     *
     *  @param: the param of the function is the type of system selected.
     *  @return: none.
     */
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
                if (dataNumber.value!!.isNotEmpty()) {

                    val newDecimalNumber = Functions().convertOctalToDecimal(dataNumber.value!!.toLong())

                    resultBinary.value = Functions().convertDecToBin(newDecimalNumber.toLong())
                    resultDecimal.value = newDecimalNumber
                    resultHex.value = Functions().convertDecToHex(newDecimalNumber.toLong())
                } else {
                    resultBinary.value = ""
                    resultDecimal.value = ""
                    resultHex.value = ""
                }
            }

            "Hex" -> {
                if (dataNumber.value!!.isNotEmpty()){

                    val newDecimalNumber = Functions().convertHexToDecimal(dataNumber.value!!)

                    resultBinary.value = Functions().convertDecToBin(newDecimalNumber.toLong())
                    resultOctal.value = Functions().convertDecToOct(newDecimalNumber.toLong())
                    resultDecimal.value = newDecimalNumber
                }
                else
                {
                    resultBinary.value = ""
                    resultOctal.value = ""
                    resultDecimal.value = ""
                }
            }
        }
    }
}