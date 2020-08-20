package justjump.coding_calculator.data.local

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.google.gson.Gson


object PreferenceHelper {

    val gson = Gson()
    var datasharepreferente: SharedPreferences? = null

    //fun defaultPreference(context: Context): SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    fun customPreference(context: Context): PreferenceHelper {
        if (datasharepreferente == null)
            datasharepreferente = context.getSharedPreferences("HistoryData", Context.MODE_PRIVATE)
        return this
    }

    inline fun SharedPreferences.editMe(operation: (SharedPreferences.Editor) -> Unit) {
        val editMe = edit()
        operation(editMe)
        editMe.apply()
    }

    fun getlist(): ArrayList<String> {

        var dataSaveList: ArrayList<String> = ArrayList()
        if (datasharepreferente?.contains("HistoryData")!!) {
            val json = datasharepreferente?.getString("HistoryData", "DEFAULT")
            dataSaveList = gson.fromJson<ArrayList<String>>(json, ArrayList<String>()::class.java)

            return dataSaveList
        }
        return dataSaveList

    }

    fun setList(newData: String)
    {
        var dataSaveList: ArrayList<String> = ArrayList()

        dataSaveList = getlist()
        dataSaveList.add(newData)

        var json = gson.toJson(dataSaveList)

        datasharepreferente?.editMe {
            it.putString("HistoryData", json)
        }
    }
}