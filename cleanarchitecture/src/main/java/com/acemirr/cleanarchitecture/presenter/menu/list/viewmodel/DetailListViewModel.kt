package com.acemirr.cleanarchitecture.presenter.menu.list.viewmodel

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import com.acemirr.cleanarchitecture.data.model.ListPlaceRemote

class DetailListViewModel(listModel: ListPlaceRemote, application: Application) : AndroidViewModel(application) {
    var name: ObservableField<String> = ObservableField(listModel.name)
    var imageUrl: ObservableField<String> = ObservableField(listModel.image)
    var description: ObservableField<String> = ObservableField(listModel.description)
}
