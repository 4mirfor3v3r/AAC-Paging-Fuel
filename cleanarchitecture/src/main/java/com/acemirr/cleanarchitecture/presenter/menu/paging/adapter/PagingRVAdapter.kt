package com.acemirr.cleanarchitecture.presenter.menu.paging.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.acemirr.cleanarchitecture.R
import com.acemirr.cleanarchitecture.data.model.PagingNewsModel
import com.acemirr.cleanarchitecture.databinding.ItemPagingBinding
import com.acemirr.cleanarchitecture.external.AdapterCallback
import com.acemirr.cleanarchitecture.external.LoadingState

class PagingRVAdapter : PagedListAdapter<PagingNewsModel, RecyclerView.ViewHolder>(AdapterCallback.DiffPagingCallback) {

    companion object {
        const val VIEW_TYPE_ITEM = 1
        const val VIEW_TYPE_LOAD = 2
    }

    private var rvAction: PagingRVAction? = null
    fun setOnAction(onGetAction: PagingRVAction) {
        this.rvAction = onGetAction
    }

    private var loadingState = LoadingState.LOADING
    var lastPosition = -1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType == VIEW_TYPE_ITEM) {
            val binding: ItemPagingBinding = DataBindingUtil.inflate(inflater, R.layout.item_paging, parent, false)
            PagingHolder(binding)
        } else {
            val x = inflater.inflate(R.layout.item_load_more, parent, false)
            LoadMoreHolder(x)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is PagingHolder) {
            try {
                val pagingNewsModel: PagingNewsModel? = getItem(holder.adapterPosition)
                pagingNewsModel?.let {
                    holder.bindItem(pagingNewsModel,rvAction)
//                    holder.itemView.setOnClickListener { onClick(pagingNewsModel) }
                    setAnimation(
                        holder.itemView.context.applicationContext,
                        holder.itemView,
                        holder.adapterPosition
                    )
                }
            } catch (e: IndexOutOfBoundsException) {
                e.printStackTrace()
            }
        }
    }

    override fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder) {
        holder.itemView.clearAnimation()
        super.onViewDetachedFromWindow(holder)
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

    private fun setAnimation(context: Context, viewToAnimate: View, position: Int) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            val animation: Animation = AnimationUtils.loadAnimation(context, R.anim.rv_paging_list_animation)
            viewToAnimate.startAnimation(animation)
            lastPosition = position
        }
    }

    class PagingHolder(val binding: ItemPagingBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindItem(pagingModel: PagingNewsModel, action:PagingRVAction?) {
            binding.data = pagingModel
            binding.action = action
            binding.executePendingBindings()
        }

    }

    class LoadMoreHolder(x: View) : RecyclerView.ViewHolder(x)

}