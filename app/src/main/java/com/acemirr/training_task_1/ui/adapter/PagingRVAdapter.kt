package com.acemirr.training_task_1.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.acemirr.training_task_1.R
import com.acemirr.training_task_1.data.model.News
import com.acemirr.training_task_1.databinding.ItemPagingBinding
import com.acemirr.training_task_1.utils.AdapterCallback
import com.acemirr.training_task_1.utils.LoadingState

class PagingRVAdapter (val onClick:(News) ->Unit):PagedListAdapter<News,RecyclerView.ViewHolder>(AdapterCallback.DiffPagingCallback){

    companion object {
        const val VIEW_TYPE_ITEM = 1
        const val VIEW_TYPE_LOAD = 2
    }
    private var loadingState = LoadingState.LOADING

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType == VIEW_TYPE_ITEM) {
            val binding: ItemPagingBinding =
                DataBindingUtil.inflate(inflater, R.layout.item_paging, parent, false)
            PagingHolder(binding)
        } else {
            val x = inflater.inflate(R.layout.item_load_more,parent,false)
            LoadMoreHolder(x)
        }
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is PagingHolder){
            try {
                val news: News? = getItem(holder.adapterPosition)
                news?.let {
                    holder.bindItem(news)
                    holder.itemView.setOnClickListener {
                        onClick(news)
                    }
                }
            }catch (e:IndexOutOfBoundsException){
                e.printStackTrace()
            }
        }
    }
    override fun getItemViewType(position: Int): Int {
        return if (position < super.getItemCount()) VIEW_TYPE_ITEM else VIEW_TYPE_LOAD
    }
    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasFooter()) 1 else 0
    }

    private fun hasFooter(): Boolean {
        return super.getItemCount() != 0 && loadingState == LoadingState.LOADING
    }

    class PagingHolder(val binding: ItemPagingBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindItem(pagingModel: News) {
            binding.data = pagingModel
            binding.executePendingBindings()
        }

    }

    class LoadMoreHolder(x: View) : RecyclerView.ViewHolder(x)

}