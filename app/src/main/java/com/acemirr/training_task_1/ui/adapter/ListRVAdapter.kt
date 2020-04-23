package com.acemirr.training_task_1.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.acemirr.training_task_1.R
import com.acemirr.training_task_1.data.model.ListModel
import com.acemirr.training_task_1.databinding.ItemListBinding

class ListRVAdapter(val onClick:(ListModel) -> Unit):RecyclerView.Adapter<ListRVAdapter.Holder>() {
    private val data = ArrayList<ListModel>()
    class Holder(private val itemListBinding: ItemListBinding):RecyclerView.ViewHolder(itemListBinding.root) {
        fun bindView(listModel: ListModel) {
            itemListBinding.data = listModel

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
        val listModel = data[position]
        holder.bindView(listModel)
        holder.itemView.setOnClickListener {
            onClick(listModel)
        }
    }

    fun replaceData(listModel: Collection<ListModel>){
        data.clear()
        data.addAll(listModel)
        notifyDataSetChanged()
    }
}