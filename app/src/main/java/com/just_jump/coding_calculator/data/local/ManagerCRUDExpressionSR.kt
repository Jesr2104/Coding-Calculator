package com.just_jump.coding_calculator.data.local

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson

// this object create
object SRDataExpression {

    private val gSonInstance = Gson()
    private var dataSharePreference: SharedPreferences? = null

    // this functions create the object of the Shared preference
    fun customPreference(context: Context): SRDataExpression {
        if (dataSharePreference == null)
            dataSharePreference = context.getSharedPreferences("HistoryData", Context.MODE_PRIVATE)
        return this
    }

    private inline fun SharedPreferences.editMe(operation: (SharedPreferences.Editor) -> Unit) {
        val editMe = edit()
        operation(editMe)
        editMe.apply()
    }

    // this function get the Array list of the history of expressions
    fun getlist(): ArrayList<String> {

        var dataSaveList: ArrayList<String> = ArrayList()
        if (dataSharePreference?.contains("HistoryData")!!) {
            val json = dataSharePreference?.getString("HistoryData", "DEFAULT")
            dataSaveList =
                gSonInstance.fromJson<ArrayList<String>>(json, ArrayList<String>()::class.java)

            return dataSaveList
        }
        return dataSaveList
    }

    // this function insert a new element on the list of the expressions
    fun setList(newData: String) {

        val maxData = 16
        var dataSaveList: ArrayList<String> = getlist()
        val tempData: ArrayList<String> = ArrayList()

        if (dataSaveList.size >= maxData) {
            var cont = 2
            while (cont < maxData) {
                tempData.add(dataSaveList[cont])
                cont++
            }
            dataSaveList = tempData
            dataSaveList.add(newData)
        } else {
            dataSaveList.add(newData)
        }

        val json = gSonInstance.toJson(dataSaveList)

        dataSharePreference?.editMe {
            it.putString("HistoryData", json)
        }
    }
}