package justjump.coding_calculator.data.local

import android.content.Context
import android.content.SharedPreferences
import android.system.Os.remove
import com.google.gson.Gson

// this object create
object PreferenceHelper {

    private val gsonInstance = Gson()
    private var datasharepreferente: SharedPreferences? = null

    // this functions create the object of the Shared preference
    fun customPreference(context: Context): PreferenceHelper {
        if (datasharepreferente == null)
            datasharepreferente = context.getSharedPreferences("HistoryData", Context.MODE_PRIVATE)
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
        if (datasharepreferente?.contains("HistoryData")!!) {
            val json = datasharepreferente?.getString("HistoryData", "DEFAULT")
            dataSaveList =
                gsonInstance.fromJson<ArrayList<String>>(json, ArrayList<String>()::class.java)

            return dataSaveList
        }
        return dataSaveList
    }

    // this function insert a new element on the list of the expressions
    fun setList(newData: String) {

        val maxData = 10
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

        val json = gsonInstance.toJson(dataSaveList)

        datasharepreferente?.editMe {
            it.putString("HistoryData", json)
        }
    }

    // this function to delete the file with the list of the history
    fun deleteList()
    {
        datasharepreferente?.editMe {
            it.remove("HistoryData")
        }
    }
}