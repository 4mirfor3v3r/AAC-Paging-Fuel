package com.acemirr.training_task_1.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.acemirr.training_task_1.R
import com.acemirr.training_task_1.data.model.GridModel
import com.acemirr.training_task_1.databinding.ItemGridBinding
import com.acemirr.training_task_1.utils.AdapterCallback

class GridRVAdapter(val onClick:(GridModel, Int) -> Unit): ListAdapter<GridModel,GridRVAdapter.Holder>(AdapterCallback.DiffGridCallback) {
    class Holder(private val itemGridBinding: ItemGridBinding):RecyclerView.ViewHolder(itemGridBinding.root) {
        fun bindView(placeList: GridModel) {
            itemGridBinding.data = placeList
            itemGridBinding.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = DataBindingUtil.inflate<ItemGridBinding>(LayoutInflater.from(parent.context), R.layout.item_grid,parent,false)
        return Holder(binding)
    }
    override fun onBindViewHolder(holder: Holder, position: Int) {
        val gridModel = getItem(holder.adapterPosition)
        holder.bindView(gridModel)
        holder.itemView.setOnClickListener {
            onClick(gridModel, position)
        }
    }
}