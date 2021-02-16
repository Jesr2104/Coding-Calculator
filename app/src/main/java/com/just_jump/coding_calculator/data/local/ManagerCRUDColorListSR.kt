package com.just_jump.coding_calculator.data.local

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson

// this object create
object SRDataColors {

    private val gSonInstance = Gson()
    private var dataSharePreference: SharedPreferences? = null

    // this functions create the object of the Shared preference
    fun customPreference(context: Context): SRDataColors {
        if (dataSharePreference == null)
            dataSharePreference = context.getSharedPreferences("ListColors", Context.MODE_PRIVATE)
        return this
    }

    private inline fun SharedPreferences.editMe(operation: (SharedPreferences.Editor) -> Unit) {
        val editMe = edit()
        operation(editMe)
        editMe.apply()
    }

    // this function get the Array list of the history of Colors
    fun getlist(): ArrayList<Int> {

        var dataSaveList: ArrayList<Int> = ArrayList()
        if (dataSharePreference?.contains("ListColors")!!) {
            val json = dataSharePreference?.getString("ListColors", "DEFAULT")
            dataSaveList =
                gSonInstance.fromJson<ArrayList<Int>>(json, ArrayList<Int>()::class.java)

            return dataSaveList
        }
        return dataSaveList
    }

    // this function insert a new element on the list Colors
    fun setList(newData: Int): Boolean {
        val dataSaveList: ArrayList<Int> = getlist()
        val dataTemp: ArrayList<Int> = ArrayList()

        if (dataSaveList.size < 35) {
            dataTemp.add(newData)

            dataSaveList.forEach { item ->
                dataTemp.add(item)
            }

            val json = gSonInstance.toJson(dataTemp)
            dataSharePreference?.editMe {
                it.putString("ListColors", json)
            }

        } else {
            return false
        }
        return true
    }

    // this function to delete one of the color of the list
    fun deleteItem(newData: Int) {
        val dataSavedList: ArrayList<Int> = getlist()
        val tempData: ArrayList<Int> = ArrayList()

        dataSavedList.forEach { item ->
            if (item != newData) {
                tempData.add(item)
            }
        }

        val json = gSonInstance.toJson(tempData)
        dataSharePreference?.editMe {
            it.putString("ListColors", json)
        }
    }

    fun getItem(idItem: Int): Int {
        val dataSaveList: ArrayList<Int> = getlist()

        return dataSaveList[idItem]
    }
}