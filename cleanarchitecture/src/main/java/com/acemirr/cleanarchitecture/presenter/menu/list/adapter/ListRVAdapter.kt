package com.acemirr.cleanarchitecture.presenter.menu.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.acemirr.cleanarchitecture.R
import com.acemirr.cleanarchitecture.data.model.ListPlaceRemote
import com.acemirr.cleanarchitecture.databinding.ItemListBinding
import com.acemirr.cleanarchitecture.external.AdapterCallback

class ListRVAdapter(val onClick:(ListPlaceRemote) -> Unit):ListAdapter<ListPlaceRemote, ListRVAdapter.Holder>(AdapterCallback.DiffListCallback) {
    class Holder(private val itemListBinding: ItemListBinding):RecyclerView.ViewHolder(itemListBinding.root) {
        fun bindView(listModel: ListPlaceRemote) {
            itemListBinding.data = listModel

            itemListBinding.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = DataBindingUtil.inflate<ItemListBinding>(LayoutInflater.from(parent.context), R.layout.item_list,parent,false)
        return Holder(
            binding
        )
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val listModel = getItem(holder.adapterPosition)
        holder.bindView(listModel)
        holder.itemView.setOnClickListener {
            onClick(listModel)
        }
    }

}