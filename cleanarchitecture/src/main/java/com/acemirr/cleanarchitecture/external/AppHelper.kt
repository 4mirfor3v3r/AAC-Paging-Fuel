package com.acemirr.cleanarchitecture.external

import android.content.Context
import com.acemirr.cleanarchitecture.R

object AppHelper {
    fun getToolbarHeight(context: Context): Int {
        val styledAttributes = context.theme.obtainStyledAttributes(intArrayOf(R.attr.actionBarSize))
        val toolbarHeight = styledAttributes.getDimension(0, 0f).toInt()
        styledAttributes.recycle()

        return toolbarHeight
    }

}