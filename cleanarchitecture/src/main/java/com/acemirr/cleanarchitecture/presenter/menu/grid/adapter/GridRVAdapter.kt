package com.acemirr.cleanarchitecture.presenter.menu.grid.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.acemirr.cleanarchitecture.R
import com.acemirr.cleanarchitecture.databinding.ItemGridBinding
import com.acemirr.cleanarchitecture.external.AppHelper.genericRvDiffUtil
import com.acemirr.cleanarchitecture.presenter.menu.grid.model.GridModel

class GridRVAdapter(val onClick:(GridModel, Int) -> Unit): ListAdapter<GridModel, GridRVAdapter.Holder>(
    genericRvDiffUtil(0)) {
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