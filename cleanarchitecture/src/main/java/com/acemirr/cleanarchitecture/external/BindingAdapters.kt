package com.acemirr.cleanarchitecture.external

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.acemirr.cleanarchitecture.R
import com.squareup.picasso.Picasso
import com.squareup.picasso.RequestCreator

object BindingAdapters {
    private val picasso: Picasso
        get() = Picasso.get()

    private fun ImageView.load(path:String?, request:(RequestCreator) ->RequestCreator){
        if (!path.isNullOrEmpty()) {
            request(picasso.load(path))
                .priority(Picasso.Priority.HIGH)
                .placeholder(R.drawable.ic_image_placeholder)
                .error(R.drawable.ic_image_broken)
                .into(this)
        }
    }

    @BindingAdapter("imageUrl")
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