package justjump.coding_calculator.data.local

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson

// this object create
object SRDataColors {

    private val gsonInstance = Gson()
    private var datasharepreferente: SharedPreferences? = null

    // this functions create the object of the Shared preference
    fun customPreference(context: Context): SRDataColors {
        if (datasharepreferente == null)
            datasharepreferente = context.getSharedPreferences("ListColors", Context.MODE_PRIVATE)
        return this
    }

    private inline fun SharedPreferences.editMe(operation: (SharedPreferences.Editor) -> Unit) {
        val editMe = edit()
        operation(editMe)
        editMe.apply()
    }

    // this function get the Array list of the history of Colors
    fun getlist(): ArrayList<Int> {

        var dataSaveList: ArrayList<Int> = ArrayList<Int>()
        if (datasharepreferente?.contains("ListColors")!!) {
            val json = datasharepreferente?.getString("ListColors", "DEFAULT")
            dataSaveList =
                gsonInstance.fromJson<ArrayList<Int>>(json, ArrayList<Int>()::class.java)

            return dataSaveList
        }
        return dataSaveList
    }

    // this function insert a new element on the list Colors
    fun setList(newData: Int): Boolean {
        val dataSaveList: ArrayList<kotlin.Int> = getlist()

        if (dataSaveList.size < 12) {
            dataSaveList.add(newData)

            val json = gsonInstance.toJson(dataSaveList)
            datasharepreferente?.editMe {
                it.putString("ListColors", json)
            }

        } else {
            return false
        }
        return true
    }

    // this function to delete one of the color of the list
    fun deleteItem(newData: Int) {
        val dataSavedList: ArrayList<kotlin.Int> = getlist()
        val tempData: ArrayList<kotlin.Int> = ArrayList()

        dataSavedList.forEach { item ->
            if (item != newData) {
                tempData.add(item)
            }
        }

        val json = gsonInstance.toJson(tempData)
        datasharepreferente?.editMe {
            it.putString("ListColors", json)
        }
    }

    fun getItem(idItem: Int): Int {
        val dataSaveList: ArrayList<kotlin.Int> = getlist()

        return dataSaveList[idItem]
    }
}