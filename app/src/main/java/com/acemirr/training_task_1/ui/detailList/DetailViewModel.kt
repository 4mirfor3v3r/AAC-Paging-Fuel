package com.acemirr.training_task_1.ui.detailList

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import com.acemirr.training_task_1.data.model.PlaceList

class DetailViewModel(placeList: PlaceList, application: Application) : AndroidViewModel(application) {
    var name: ObservableField<String> = ObservableField(placeList.name)
    var imageUrl: ObservableField<String> = ObservableField(placeList.image)
    var description: ObservableField<String> = ObservableField(placeList.description)
}
