package com.acemirr.cleanarchitecture.presenter.menu.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.acemirr.cleanarchitecture.R
import com.acemirr.cleanarchitecture.data.model.ListPlaceModel
import com.acemirr.cleanarchitecture.databinding.ItemListBinding
import com.acemirr.cleanarchitecture.external.AdapterCallback

class ListRVAdapter : ListAdapter<ListPlaceModel, ListRVAdapter.Holder>(AdapterCallback.DiffListCallback) {

    private var rvAction: ListRVAction? = null
    fun setOnAction(onGetAction: ListRVAction) {
        this.rvAction = onGetAction
    }

    class Holder(private val itemListBinding: ItemListBinding) :
        RecyclerView.ViewHolder(itemListBinding.root) {
        fun bindView(listModel: ListPlaceModel, action: ListRVAction?) {
            itemListBinding.data = listModel
            itemListBinding.action = action
            itemListBinding.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = DataBindingUtil.inflate<ItemListBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_list,
            parent,
            false
        )
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val listModel = getItem(holder.adapterPosition)
        holder.bindView(listModel, rvAction)
    }

}