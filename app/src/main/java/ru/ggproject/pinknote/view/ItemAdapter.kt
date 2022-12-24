package ru.ggproject.pinknote.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.ggproject.pinknote.R
import ru.ggproject.pinknote.model.Item
import java.util.ArrayList

class ItemAdapter : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    private val items : ArrayList<Item> = ArrayList()
    private var listener : OnItemClickListener? = null


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemAdapter.ItemViewHolder {
        val itemView : View = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return ItemViewHolder(itemView)
    }

    override fun onBindViewHolder(
        holder: ItemAdapter.ItemViewHolder,
        position: Int) {
        val currentItem = items[position]
        holder.descriptionTextView.text = currentItem.description
    }

    override fun getItemCount(): Int {
        return items.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setItems (items : List<Item>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }


    fun getItemAt (position : Int) : Item {
        return items[position]
    }

    inner class ItemViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView) {
        val descriptionTextView : TextView = itemView.findViewById(R.id.descriptionTextView)

        init {
            itemView.setOnClickListener(View.OnClickListener {
                listener?.let { listener ->
                    val position : Int = adapterPosition
                    if (position in 0..itemCount) {
                        listener.onItemClick(items[position])
                    }
                }
            })
        }
    }

    fun setOnItemClickListener (listener : OnItemClickListener) {
        this.listener = listener
    }

    interface OnItemClickListener {
        fun onItemClick(item: Item?)
    }

}