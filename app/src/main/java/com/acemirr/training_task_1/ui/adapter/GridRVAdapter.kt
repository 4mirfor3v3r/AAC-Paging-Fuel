package com.acemirr.training_task_1.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.acemirr.training_task_1.R
import com.acemirr.training_task_1.data.model.GridModel
import com.acemirr.training_task_1.databinding.ItemGridBinding

class GridRVAdapter(val onClick:(GridModel) -> Unit): RecyclerView.Adapter<GridRVAdapter.Holder>() {
    private val data = ArrayList<GridModel>()
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

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val gridModel = data[position]
        holder.bindView(gridModel)
        holder.itemView.setOnClickListener {
            onClick(gridModel)
        }
    }

    fun replaceData(list: Collection<GridModel>){
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }
}