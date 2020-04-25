package com.acemirr.training_task_1.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.acemirr.training_task_1.R
import com.acemirr.training_task_1.data.model.GridModel
import com.acemirr.training_task_1.databinding.ItemGridDetailBinding
import com.acemirr.training_task_1.utils.AdapterCallback

class GridDetailRVAdapter :ListAdapter<GridModel,GridDetailRVAdapter.Holder>(AdapterCallback.DiffDetailGridCallback){
    class Holder(val binding: ItemGridDetailBinding):RecyclerView.ViewHolder(binding.root) {
        fun bindView(gridModel: GridModel) {
            binding.data = gridModel
            binding.executePendingBindings()
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val x = DataBindingUtil.inflate<ItemGridDetailBinding>(LayoutInflater.from(parent.context),
            R.layout.item_grid_detail,parent,false)
        return Holder(x)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bindView(getItem(holder.adapterPosition))
    }
}