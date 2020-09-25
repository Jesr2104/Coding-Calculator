package justjump.coding_calculator

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapterGammaColors(itemsList: Array<Array<String>>):RecyclerView.Adapter<RecyclerAdapterGammaColors.ViewHolder>() {

    private var items: Array<Array<String>>? = null

    init {
        items = itemsList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(
            R.layout.item_layout,
            parent,
            false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items?.get(position)
        var checkNumber :Boolean = true

        // code to paint the 3 first item on color white
        item?.get(0)!!.forEach { item ->
            if (!item.isDigit()){
                checkNumber = false
            }
        }

        if (checkNumber){
            when (item[0]) {
                "50" -> {
                    holder.codeColor!!.setTextColor(Color.parseColor("#000000"))
                    holder.rgbColor!!.setTextColor(Color.parseColor("#000000"))
                    holder.hexColor!!.setTextColor(Color.parseColor("#000000"))
                }
                "100" -> {
                    holder.codeColor!!.setTextColor(Color.parseColor("#000000"))
                    holder.rgbColor!!.setTextColor(Color.parseColor("#000000"))
                    holder.hexColor!!.setTextColor(Color.parseColor("#000000"))
                }
                "200" -> {
                    holder.codeColor!!.setTextColor(Color.parseColor("#000000"))
                    holder.rgbColor!!.setTextColor(Color.parseColor("#000000"))
                    holder.hexColor!!.setTextColor(Color.parseColor("#000000"))
                }
                else -> {
                    holder.codeColor!!.setTextColor(Color.parseColor("#ffffff"))
                    holder.rgbColor!!.setTextColor(Color.parseColor("#ffffff"))
                    holder.hexColor!!.setTextColor(Color.parseColor("#ffffff"))
                }
            }
        }
        else
        {
            holder.codeColor!!.setTextColor(Color.parseColor("#ffffff"))
            holder.rgbColor!!.setTextColor(Color.parseColor("#ffffff"))
            holder.hexColor!!.setTextColor(Color.parseColor("#ffffff"))
        }

        holder.codeColor?.text = item[0]
        holder.hexColor?.text = item[1].toUpperCase()

        val rgb = Color.parseColor(item[1])
        holder.rgbColor?.text = "RGB(${Color.red(rgb)},${Color.green(rgb)},${Color.blue(rgb)})"

        holder.mainLayout?.setBackgroundColor(Color.parseColor(item[1]))
    }

    override fun getItemCount(): Int {
        return items?.count()!!
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var rgbColor: TextView? = null
        var codeColor: TextView? = null
        var hexColor: TextView? = null
        var mainLayout: LinearLayout? = null

        init {
            rgbColor = itemView.findViewById(R.id.rgbColor)
            hexColor = itemView.findViewById(R.id.hexColor)
            codeColor = itemView.findViewById(R.id.codecolor)
            mainLayout = itemView.findViewById(R.id.mainlayout)

            itemView.setOnClickListener {}
        }
    }
}