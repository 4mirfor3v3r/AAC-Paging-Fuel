package com.acemirr.training_task_1.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.squareup.picasso.Picasso
import com.squareup.picasso.RequestCreator

object BindingExtensions {
    private val picasso: Picasso
        get() = Picasso.get()

    private fun ImageView.load(path:String, request:(RequestCreator) ->RequestCreator){
        request(picasso.load(path))
            .into(this)
    }

    @BindingAdapter("app:imageUrl")
    @JvmStatic
    fun loadImage(view: ImageView, url:String?){
        if (url!=null){
            view.load(url){requestCreator ->
                requestCreator.fit().centerCrop()
            }
        }
    }

    @JvmStatic
    @BindingAdapter("setRefresh")
    fun SwipeRefreshLayout.setRefresh(isRefresh: Boolean) {
        this.isRefreshing = isRefresh
    }
}