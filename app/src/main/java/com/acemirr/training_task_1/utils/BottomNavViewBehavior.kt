package com.acemirr.training_task_1.utils

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomnavigation.BottomNavigationView

class BottomNavViewBehavior(context: Context, attributes: AttributeSet):CoordinatorLayout.Behavior<BottomNavigationView>(context, attributes) {
    private val height = AppHelper.getToolbarHeight(context)

    override fun layoutDependsOn(parent: CoordinatorLayout, child: BottomNavigationView, dependency: View): Boolean {
        return dependency is AppBarLayout
    }

    override fun onDependentViewChanged(parent: CoordinatorLayout, child: BottomNavigationView, dependency: View): Boolean {
        if (dependency is AppBarLayout){
            val layoutParams = child.layoutParams as CoordinatorLayout.LayoutParams
            val marginBottom =layoutParams.bottomMargin
            val distanceToScroll = child.height + marginBottom
            val ratio = dependency.y / height
            child.translationY = -distanceToScroll * ratio
        }
        return super.onDependentViewChanged(parent, child, dependency)
    }
}