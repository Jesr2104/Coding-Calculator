package justjump.coding_calculator

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(itemsList: Array<String>): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    var items: Array<String>? = null

    init {
        items = itemsList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_colors,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items?.count()!!
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items?.get(position)
        holder.itemName?.text = item
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var itemName: TextView? = null

        init {
            itemName = itemView.findViewById(R.id.idColorname)

            itemView.setOnClickListener(View.OnClickListener {
                val position: Int = adapterPosition
                Toast.makeText(itemView.context,"You clicked on item # ${position+1}",Toast.LENGTH_LONG).show()
            })

        }
    }
}