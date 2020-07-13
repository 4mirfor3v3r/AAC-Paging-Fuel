package com.acemirr.cleanarchitecture.presenter.menu.grid.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.acemirr.cleanarchitecture.R
import com.acemirr.cleanarchitecture.data.model.GridGalleryModel
import com.acemirr.cleanarchitecture.databinding.ItemGridDetailBinding
import com.acemirr.cleanarchitecture.external.AdapterCallback

class GridDetailRVAdapter :ListAdapter<GridGalleryModel, GridDetailRVAdapter.Holder>(AdapterCallback.DiffGridCallback){
    class Holder(val binding: ItemGridDetailBinding):RecyclerView.ViewHolder(binding.root) {
        fun bindView(gridGalleryModel: GridGalleryModel) {
            binding.data = gridGalleryModel
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