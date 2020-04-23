package com.acemirr.training_task_1.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.acemirr.training_task_1.R
import com.acemirr.training_task_1.data.model.PagingModel
import com.acemirr.training_task_1.databinding.ItemPagingBinding
import com.acemirr.training_task_1.utils.AdapterCallback
import com.acemirr.training_task_1.utils.LoadingState
import java.lang.IndexOutOfBoundsException

class PagingRVAdapter(val onClick: (PagingModel) -> Unit): PagedListAdapter<PagingModel, RecyclerView.ViewHolder>(AdapterCallback.DiffNotificationCallback) {

    companion object {
        const val VIEW_TYPE_ITEM = 1
        const val VIEW_TYPE_LOAD = 2
    }
    private var loadingState = LoadingState.LOADING

    override fun getItemViewType(position: Int): Int {
        return if (position<super.getItemCount()) VIEW_TYPE_ITEM else VIEW_TYPE_LOAD
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasFooter()) 1 else 0
    }

    private fun hasFooter():Boolean {
        return super.getItemCount() != 0 && loadingState == LoadingState.LOADING
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        if (viewType == VIEW_TYPE_ITEM) {
            val binding: ItemPagingBinding =
                DataBindingUtil.inflate(inflater, R.layout.item_paging, parent, false)
            return PagingHolder(binding)
        } else {
            val x = inflater.inflate(R.layout.item_load_more,parent,false)
            return LoadMoreHolder(x)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is PagingHolder){
            try {
                val notificationModel: PagingModel? = getItem(holder.adapterPosition)
                notificationModel?.let {
                    holder.bindItem(notificationModel)
                    holder.itemView.setOnClickListener {
                        onClick(notificationModel)
                    }
                }
            }catch (e:IndexOutOfBoundsException){
                e.printStackTrace()
            }
        }
    }

    class PagingHolder(val binding: ItemPagingBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindItem(pagingModel: PagingModel) {
            binding.data = pagingModel
            binding.executePendingBindings()
        }

    }

    class LoadMoreHolder(x: View) : RecyclerView.ViewHolder(x)

}