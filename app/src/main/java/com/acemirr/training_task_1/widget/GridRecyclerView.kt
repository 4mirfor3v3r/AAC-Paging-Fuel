package com.acemirr.training_task_1.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.view.animation.GridLayoutAnimationController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class GridRecyclerView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : RecyclerView(context, attrs, defStyleAttr) {
    override fun setLayoutManager(layout: LayoutManager?) {
        if (layout is GridLayoutManager) {
            super.setLayoutManager(layout)
        }else{
            throw ClassCastException("Uncompatible LayoutManager use GridLayoutManager Instead")
        }
    }

    override fun attachLayoutAnimationParameters(child: View?, params: ViewGroup.LayoutParams, index: Int, count: Int) {
        if (adapter != null && layoutManager is GridLayoutManager){
            var param = params.layoutAnimationParameters as GridLayoutAnimationController.AnimationParameters?
            if (param == null){
                param = GridLayoutAnimationController.AnimationParameters()
                params.layoutAnimationParameters = param
            }
            val column = (layoutManager as GridLayoutManager).spanCount

            param.count = count
            param.index = index
            param.columnsCount = column
            param.rowsCount = count/column

            val mIndex = count -1 -index
            param.column =column -1 -(mIndex%column)
            param.row = param.rowsCount -1 -mIndex/column
        }else {
            super.attachLayoutAnimationParameters(child, params, index, count)
        }
    }
}