package com.acemirr.cleanarchitecture.presenter.menu.list.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.acemirr.cleanarchitecture.domain.usecase.ListUseCase
import javax.inject.Inject

class DetailListViewModel @Inject constructor(useCase: ListUseCase) : ViewModel() {
    var name: ObservableField<String> = ObservableField()
    var imageUrl: ObservableField<String> = ObservableField()
    var description: ObservableField<String> = ObservableField()
}
