package com.acemirr.training_task_1.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.ListAdapter
import com.acemirr.training_task_1.R
import com.acemirr.training_task_1.data.model.ListModel
import com.acemirr.training_task_1.databinding.ItemListBinding
import com.acemirr.training_task_1.utils.AdapterCallback

class ListRVAdapter(val onClick:(ListModel) -> Unit):ListAdapter<ListModel,ListRVAdapter.Holder>(AdapterCallback.DiffListCallback) {
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

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val listModel = getItem(holder.adapterPosition)
        holder.bindView(listModel)
        holder.itemView.setOnClickListener {
            onClick(listModel)
        }
    }

}