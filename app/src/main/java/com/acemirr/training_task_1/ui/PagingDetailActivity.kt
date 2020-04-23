package com.acemirr.training_task_1.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.acemirr.training_task_1.R

class PagingDetailActivity : AppCompatActivity() {
    companion object{
        const val EXTRA_DATA_NOTIFICATION = "datapaging"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paging_detail)
    }
}
