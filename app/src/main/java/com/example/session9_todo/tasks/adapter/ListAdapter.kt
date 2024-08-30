package com.example.session9_todo.tasks.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.session9_todo.R
import com.example.session9_todo.database.model.Todo

class ListAdapter (private var itemsList : MutableList<Todo>?):
     RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    class ViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){
        val title : TextView =  itemView.findViewById(R.id.title_item)
        val subTitle : TextView =  itemView.findViewById(R.id.sub_title_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemsList?.size?:0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemsList?.get(position)
        holder.title.text = item?.name
        holder.subTitle.text = item?.details

    }

    //
    //change data in adapter
    fun changeData(newItem: MutableList<Todo>){
        //class list == new list
        itemsList = newItem
        // notify db
        notifyDataSetChanged()
    }

}