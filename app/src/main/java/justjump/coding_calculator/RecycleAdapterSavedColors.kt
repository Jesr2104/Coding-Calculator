package justjump.coding_calculator

import android.app.AlertDialog
import android.content.DialogInterface
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import justjump.coding_calculator.data.local.SRDataColors


class RecycleAdapterSavedColors(var listDataColors: ArrayList<Int>, newColorRGB: MutableLiveData<Int>): RecyclerView.Adapter<RecycleAdapterSavedColors.ViewHolder>() {

    private val newColorRGB = newColorRGB

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(
            R.layout.item_saved_layout,
            parent,
            false
        )
        return ViewHolder(view,newColorRGB)
    }

    override fun getItemCount(): Int {
        return listDataColors?.count()!!
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listDataColors[position]

        val hexColor = "#" + Integer.toHexString(item).substring(2)
        holder.hexColor?.text = hexColor.toUpperCase()
        holder.rgbColor?.text = "RGB(${Color.red(item)},${Color.green(item)},${Color.blue(item)})"
        holder.mainLayout?.setBackgroundColor(item)

        holder.codeColor = item
    }

    class ViewHolder(itemView: View, newColorRGB: MutableLiveData<Int>) : RecyclerView.ViewHolder(itemView) {
        var rgbColor: TextView? = null
        var hexColor: TextView? = null
        var mainLayout: LinearLayout? = null
        var codeColor: Int = 0
        var ColorRGB = newColorRGB

        init {
            rgbColor = itemView.findViewById(R.id.rgbColor)
            hexColor = itemView.findViewById(R.id.hexColor)
            mainLayout = itemView.findViewById(R.id.mainlayout)


            itemView.setOnClickListener {
                val position: Int = adapterPosition

                val builderDialog: AlertDialog.Builder = AlertDialog.Builder(itemView.context)
                builderDialog.setMessage("What do you want to do?")
                builderDialog.setCancelable(true)

                    builderDialog.setNeutralButton(
                        "Close",
                        DialogInterface.OnClickListener { dialog, id ->
                            dialog.dismiss()
                        })

                    builderDialog.setPositiveButton(
                        "Load",
                        DialogInterface.OnClickListener { dialog, id ->
                            ColorRGB.value = codeColor
                            dialog.dismiss()

                        })

                    builderDialog.setNegativeButton(
                        "Delete",
                        DialogInterface.OnClickListener { dialog, id ->
                            SRDataColors.deleteItem(codeColor)
                            ColorRGB.value = ColorRGB.value
                        })

                val alert: AlertDialog = builderDialog.create()
                alert.show()
            }
        }
    }
}