package com.just_jump.coding_calculator

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.just_jump.coding_calculator.databinding.ItemLayoutBinding

class RecyclerAdapterGammaColors(itemsList: Array<Array<String>>):RecyclerView.Adapter<RecyclerAdapterGammaColors.ViewHolder>() {

    private var items: Array<Array<String>>? = null

    init {
        items = itemsList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    @SuppressLint("SetTextI18n", "DefaultLocale")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items?.get(position)
        var checkNumber = true

        // code to paint the 3 first item on color white
        item?.get(0)!!.forEach {
            if (!it.isDigit()){
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

    class ViewHolder(binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        var rgbColor: TextView? = null
        var codeColor: TextView? = null
        var hexColor: TextView? = null
        var mainLayout: LinearLayout? = null

        init {
            rgbColor = itemView.findViewById(R.id.rgbColor)
            hexColor = itemView.findViewById(R.id.hexColor)
            codeColor = itemView.findViewById(R.id.codeColor)
            mainLayout = itemView.findViewById(R.id.mainLayoutPalette)

            itemView.setOnClickListener {}
        }
    }
}