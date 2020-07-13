package com.acemirr.cleanarchitecture.presenter.menu.grid.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.acemirr.cleanarchitecture.R
import com.acemirr.cleanarchitecture.data.model.GridGalleryModel
import com.acemirr.cleanarchitecture.databinding.ItemGridBinding
import com.acemirr.cleanarchitecture.external.AdapterCallback

class GridRVAdapter : ListAdapter<GridGalleryModel, GridRVAdapter.Holder>(AdapterCallback.DiffGridCallback) {

    private var rvAction: GridRVAction? = null
    fun setOnAction(onGetAction: GridRVAction) {
        this.rvAction = onGetAction
    }

    class Holder(private val itemGridBinding: ItemGridBinding) :
        RecyclerView.ViewHolder(itemGridBinding.root) {
        fun bindView(placeList: GridGalleryModel, action: GridRVAction?, position: Int) {
            itemGridBinding.data = placeList
            itemGridBinding.action = action
            itemGridBinding.position = position
            itemGridBinding.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = DataBindingUtil.inflate<ItemGridBinding>(LayoutInflater.from(parent.context), R.layout.item_grid, parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val gridModel = getItem(holder.adapterPosition)
        holder.bindView(gridModel, rvAction, position)
    }
}