package com.acemirr.cleanarchitecture.external

import android.content.Context
import androidx.recyclerview.widget.DiffUtil
import com.acemirr.cleanarchitecture.R

object AppHelper {

    fun getToolbarHeight(context: Context): Int {
        val styledAttributes = context.theme.obtainStyledAttributes(intArrayOf(R.attr.actionBarSize))
        val toolbarHeight = styledAttributes.getDimension(0, 0f).toInt()
        styledAttributes.recycle()

        return toolbarHeight
    }

    fun <T> genericRvDiffUtil(paramKey: Int) = object : DiffUtil.ItemCallback<T>() {
        override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
            return try {
                val old = oldItem as Class<*>
                val new = newItem as Class<*>

                old.fields[paramKey] == new.fields[paramKey]
            }catch (e:ClassCastException){
                e.printStackTrace()
                false
            }
        }
    }
}