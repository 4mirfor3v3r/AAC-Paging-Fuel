package com.acemirr.training_task_1.utils

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.abs

class CenterZoomLayout(context: Context) : LinearLayoutManager(context) {
    private val mShrinkAmount = 0.15f
    private val mShrinkDistance = 0.9f

    override fun scrollHorizontallyBy(
        dx: Int,
        recycler: RecyclerView.Recycler?,
        state: RecyclerView.State?
    ): Int {
        val orientation = orientation
        if (orientation == HORIZONTAL){
            val scrolled = super.scrollHorizontallyBy(dx, recycler, state)

            val midpoint = (width/2) - 10
            val d0 = 0f
            val d1 = mShrinkDistance * midpoint
            val s0 = 1f
            val s1 = 1f - mShrinkAmount

            for (i in 0 until childCount){
                val child = getChildAt(i)
                val chilMidePoint = (getDecoratedRight(child!!) + getDecoratedLeft(child) / 2f)
//                val d = min(d1, abs(midpoint-chilMidePoint))
                val d = d1.coerceAtMost(abs(midpoint-chilMidePoint))
                val scale = s0+(s1-s0)*(d-d0)/(d1-d0)
                child.scaleX = scale
                child.scaleY = scale
            }
            return scrolled
        }else{
            return 0
        }
    }
}