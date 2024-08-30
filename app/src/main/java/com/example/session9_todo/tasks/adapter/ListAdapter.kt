package com.example.session9_todo.tasks.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.session9_todo.R
import com.example.session9_todo.database.model.Todo

// instead of data class,  mutuble list  Of nullable _todo used
class ListAdapter (var items_list : MutableList<Todo>?):
     RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    //viewholder
    class ViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){
        val title : TextView =  itemView.findViewById(R.id.title_item)
        val subTitle : TextView =  itemView.findViewById(R.id.sub_title_item)
        val markAsDone : ImageView = itemView.findViewById(R.id.checkAsDone_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items_list?.size?:0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items_list?.get(position)
        holder.title.setText(item?.name)
        holder.subTitle.setText(item?.details)

    }

    //
    //change data in adapter
    fun changeData(newItem: MutableList<Todo>){
        //class list == new list
        items_list = newItem
        // notify db
        notifyDataSetChanged()
    }

}