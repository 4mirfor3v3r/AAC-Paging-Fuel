package com.acemirr.cleanarchitecture.presenter.menu.paging.view

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.acemirr.cleanarchitecture.R
import com.acemirr.cleanarchitecture.data.model.PagingNewsModel
import com.acemirr.cleanarchitecture.databinding.PagingFragmentBinding
import com.acemirr.cleanarchitecture.external.Constant.EXTRA_DATA_PAGING
import com.acemirr.cleanarchitecture.presenter.activities.MainActivity
import com.acemirr.cleanarchitecture.presenter.activities.PagingDetailActivity
import com.acemirr.cleanarchitecture.presenter.base.BaseFragment
import com.acemirr.cleanarchitecture.presenter.menu.paging.adapter.PagingRVAction
import com.acemirr.cleanarchitecture.presenter.menu.paging.adapter.PagingRVAdapter
import com.acemirr.cleanarchitecture.presenter.menu.paging.viewmodel.PagingViewModel
import javax.inject.Inject

class PagingFragment : BaseFragment<PagingViewModel, PagingFragmentBinding>(R.layout.paging_fragment) {

    private var adapter = PagingRVAdapter()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun createViewModel(): PagingViewModel {
        return ViewModelProvider(this, viewModelFactory).get(PagingViewModel::class.java)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.vm = viewModel

        setupRecyclerView()
        viewModel.setPaging((context as MainActivity).applicationContext)
        observeLiveData()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        activity?.recreate()
    }

    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(context)
        binding.recyclerViewListNotification.layoutManager = layoutManager

        adapter.setOnAction(object : PagingRVAction {
            override fun onContainerClick(v: View, data: PagingNewsModel) {
                onItemClicked(data)
            }
        })

        binding.recyclerViewListNotification.adapter = adapter
    }

    private fun observeLiveData() {
        viewModel.pagingList?.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
            binding.recyclerViewListNotification.startLayoutAnimation()
        })
        viewModel.getLoadingState()?.observe(viewLifecycleOwner, Observer {

        })
    }

    private fun onItemClicked(pagingModel: PagingNewsModel) {
        val intent = Intent(context, PagingDetailActivity::class.java)
        intent.putExtra(EXTRA_DATA_PAGING, pagingModel)
        startActivity(intent)
        activity?.overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
    }

}
