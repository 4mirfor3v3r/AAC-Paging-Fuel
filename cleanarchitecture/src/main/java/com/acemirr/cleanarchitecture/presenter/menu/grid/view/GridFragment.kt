package com.acemirr.cleanarchitecture.presenter.menu.grid.view

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.acemirr.cleanarchitecture.R
import com.acemirr.cleanarchitecture.data.model.GridGalleryModel
import com.acemirr.cleanarchitecture.databinding.GridFragmentBinding
import com.acemirr.cleanarchitecture.presenter.base.BaseFragment
import com.acemirr.cleanarchitecture.presenter.menu.grid.adapter.GridRVAction
import com.acemirr.cleanarchitecture.presenter.menu.grid.adapter.GridRVAdapter
import com.acemirr.cleanarchitecture.presenter.menu.grid.viewmodel.GridViewModel
import javax.inject.Inject

class GridFragment : BaseFragment<GridViewModel, GridFragmentBinding>(R.layout.grid_fragment) {

    private var adapter = GridRVAdapter()


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun createViewModel(): GridViewModel {
        return ViewModelProvider(this, viewModelFactory).get(GridViewModel::class.java)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.vm = viewModel

        setupSwipeRefresh()
        setupRecyclerView()
        observeData()

        if (binding.rvGrid.layoutAnimation.isDone)
            viewModel.getListGallery(requireContext())

    }

    override fun onResume() {
        super.onResume()
        binding.rvGrid.startLayoutAnimation()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        activity?.recreate()
    }

    private fun observeData() {
        viewModel.liveDataGallery.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
            binding.rvGrid.startLayoutAnimation()
        })
    }


    private fun setupSwipeRefresh() {
        binding.swipeRefreshGrid.setOnRefreshListener {
            viewModel.getListGallery(requireContext())
        }
    }

    private fun setupRecyclerView() {
        val layoutManager = GridLayoutManager(context, 3)
        binding.rvGrid.layoutManager = layoutManager

        adapter.setOnAction(object : GridRVAction {
            override fun onContainerClickListener(v: View, data: GridGalleryModel, position: Int) {
                onItemClick(data, position)
            }
        })

        binding.rvGrid.adapter = adapter
    }

    private fun onItemClick(gridGalleryModel: GridGalleryModel, position: Int) {
        val action =
            GridFragmentDirections.actionToDetailGridFragment(
                gridGalleryModel,
                position
            )
        action.dataGrid = gridGalleryModel
        action.position = position
        findNavController().navigate(action)
    }

}
