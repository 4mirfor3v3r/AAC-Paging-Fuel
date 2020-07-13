package com.acemirr.cleanarchitecture.presenter.menu.list.view

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.acemirr.cleanarchitecture.R
import com.acemirr.cleanarchitecture.data.model.ListPlaceModel
import com.acemirr.cleanarchitecture.databinding.DetailFragmentBinding
import com.acemirr.cleanarchitecture.presenter.activities.MainActivity
import com.acemirr.cleanarchitecture.presenter.base.BaseFragment
import com.acemirr.cleanarchitecture.presenter.menu.list.viewmodel.DetailListViewModel
import javax.inject.Inject

class DetailListFragment : BaseFragment<DetailListViewModel,DetailFragmentBinding>(R.layout.detail_fragment) {

    private val args: DetailListFragmentArgs? by navArgs()
    private var listModel: ListPlaceModel? = null

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory


    override fun createViewModel(): DetailListViewModel {
        return ViewModelProvider(this, viewModelFactory).get(DetailListViewModel::class.java)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as MainActivity).hideNavigation(true)
        listModel = args?.dataListDetail

//        viewModel = ViewModelProvider(this, ViewModelFactory(lifecycleScope)).get(DetailListViewModel::class.java)
        binding.tvTitle.visibility = View.VISIBLE
        binding.vm = viewModel

        setupDetail()
    }
    override fun onDestroy() {
        super.onDestroy()
        (activity as MainActivity).hideNavigation(false)
    }
    private fun setupDetail(){
        viewModel.name.set(listModel?.name)
        viewModel.description.set(listModel?.description)
        viewModel.imageUrl.set(listModel?.image)
    }


}
