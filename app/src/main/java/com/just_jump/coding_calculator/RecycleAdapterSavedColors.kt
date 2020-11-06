package com.just_jump.coding_calculator

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.just_jump.coding_calculator.data.local.SRDataColors
import kotlinx.android.synthetic.main.item_color_saved.view.*

class RecycleAdapterSavedColors(var listDataColors: ArrayList<Int>, private var RVSavedColorInt: RVSavedColorInt): RecyclerView.Adapter<RecycleAdapterSavedColors.ViewHolder>() {

    override fun getItemCount(): Int {
        return listDataColors.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(
            R.layout.item_color_saved,
            parent,
            false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listDataColors[position]
        holder.render(item)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.deleteSavedColor.setOnClickListener{
                removeItem(layoutPosition)
            }

            itemView.loadSavedColor.setOnClickListener{
                loadItem(layoutPosition)
            }
        }

        fun render(item: Int){

            println(listDataColors)

            val hexColor = "#" + Integer.toHexString(item).substring(2)
            itemView.colorCodeHex.text = "HEX "+hexColor.toUpperCase()
            itemView.colorCodeRGB.text = "RGB(${Color.red(item)},${Color.green(item)},${Color.blue(item)})"
            itemView.colorSaved.setBackgroundColor(item)
        }
    }

    fun removeItem(position: Int) {
        // first remove from the database
        SRDataColors.deleteItem(listDataColors[position])
        listDataColors.removeAt(position)

        notifyItemRemoved(position)
    }

    fun loadItem(position: Int){
        RVSavedColorInt.colorIntValue(listDataColors[position])
    }
}