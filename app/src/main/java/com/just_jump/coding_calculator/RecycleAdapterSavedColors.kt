package com.just_jump.coding_calculator

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.just_jump.coding_calculator.data.local.SRDataColors
import com.just_jump.coding_calculator.databinding.ItemColorSavedBinding


class RecycleAdapterSavedColors(var listDataColors: ArrayList<Int>, private var RVSavedColorInt: RVSavedColorInt): RecyclerView.Adapter<RecycleAdapterSavedColors.ViewHolder>() {

    override fun getItemCount(): Int {
        return listDataColors.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemColorSavedBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listDataColors[position]
        holder.render(item)
    }

    inner class ViewHolder(private val binding: ItemColorSavedBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.deleteSavedColor.setOnClickListener{
                removeItem(layoutPosition)
            }

            binding.loadSavedColor.setOnClickListener{
                loadItem(layoutPosition)
            }
        }

        @SuppressLint("SetTextI18n", "DefaultLocale")
        fun render(item: Int){

            println(listDataColors)

            val hexColor = "#" + Integer.toHexString(item).substring(2)
            binding.colorCodeHex.text = "HEX "+hexColor.toUpperCase()
            binding.colorCodeRGB.text = "RGB(${Color.red(item)},${Color.green(item)},${Color.blue(item)})"
            binding.colorSaved.setBackgroundColor(item)
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