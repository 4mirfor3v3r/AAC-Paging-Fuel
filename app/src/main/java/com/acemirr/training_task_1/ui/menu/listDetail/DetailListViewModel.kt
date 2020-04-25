package com.acemirr.training_task_1.ui.menu.listDetail

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import com.acemirr.training_task_1.data.model.ListModel

class DetailListViewModel(listModel: ListModel, application: Application) : AndroidViewModel(application) {
    var name: ObservableField<String> = ObservableField(listModel.name)
    var imageUrl: ObservableField<String> = ObservableField(listModel.image)
    var description: ObservableField<String> = ObservableField(listModel.description)
}
