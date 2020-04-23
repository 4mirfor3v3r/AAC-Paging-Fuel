package com.acemirr.training_task_1.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.acemirr.training_task_1.R
import com.acemirr.training_task_1.data.model.PlaceList
import com.acemirr.training_task_1.databinding.ItemListBinding

class ListRVAdapter(val onClick:(PlaceList) -> Unit):RecyclerView.Adapter<ListRVAdapter.Holder>() {
    private val data = ArrayList<PlaceList>()
    class Holder(val itemListBinding: ItemListBinding):RecyclerView.ViewHolder(itemListBinding.root) {
        fun bindView(placeList: PlaceList) {
            itemListBinding.data = placeList

            itemListBinding.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = DataBindingUtil.inflate<ItemListBinding>(LayoutInflater.from(parent.context), R.layout.item_list,parent,false)
        return Holder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val placeList = data[position]
        holder.bindView(placeList)
        holder.itemView.setOnClickListener {
            onClick(placeList)
        }
    }

    fun replaceData(list: Collection<PlaceList>){
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }
}