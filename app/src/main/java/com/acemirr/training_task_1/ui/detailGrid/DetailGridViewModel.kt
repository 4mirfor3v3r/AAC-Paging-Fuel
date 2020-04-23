package com.acemirr.training_task_1.ui.detailGrid

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.acemirr.training_task_1.data.model.GridModel

class DetailGridViewModel(gridModel: GridModel, application: Application) : AndroidViewModel(application) {
    var imageUrl: ObservableField<String> = ObservableField(gridModel.image)
    var caption: ObservableField<String> = ObservableField(gridModel.caption)
}
