package com.acemirr.cleanarchitecture.presenter.menu.list.view

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.acemirr.cleanarchitecture.R
import com.acemirr.cleanarchitecture.data.model.ListPlaceModel
import com.acemirr.cleanarchitecture.databinding.ListFragmentBinding
import com.acemirr.cleanarchitecture.presenter.base.BaseFragment
import com.acemirr.cleanarchitecture.presenter.menu.list.adapter.ListRVAction
import com.acemirr.cleanarchitecture.presenter.menu.list.adapter.ListRVAdapter
import com.acemirr.cleanarchitecture.presenter.menu.list.viewmodel.ListViewModel
import javax.inject.Inject

class ListFragment : BaseFragment<ListViewModel, ListFragmentBinding>(R.layout.list_fragment) {

    private var adapter = ListRVAdapter()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun createViewModel(): ListViewModel {
        return ViewModelProvider(this, viewModelFactory).get(ListViewModel::class.java)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.vm = viewModel

        setupSwipeRefresh()
        setupRecyclerView()
        observeData()

        viewModel.getList(requireContext())

    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        activity?.recreate()
    }

    private fun observeData() {
        viewModel.liveDataList.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
            binding.rvList.startLayoutAnimation()
        })
    }


    private fun setupSwipeRefresh() {
        binding.swipeRefreshList.setOnRefreshListener {
            viewModel.getList(requireContext())
        }
    }

    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(context)
        binding.rvList.layoutManager = layoutManager

        adapter.setOnAction(object : ListRVAction {
            override fun onContainerClickListener(v: View, data: ListPlaceModel) {
                onItemClick(data)
            }
        })

        binding.rvList.adapter = adapter
    }

    private fun onItemClick(modelModel: ListPlaceModel) {
        val action =
            ListFragmentDirections.actionToDetailFragment(modelModel)
        action.dataListDetail = modelModel
        findNavController().navigate(action)
    }

}
