package com.acemirr.training_task_1.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.acemirr.training_task_1.R

class PagingDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paging_detail)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_from_left,R.anim.slide_to_right)
    }
}
