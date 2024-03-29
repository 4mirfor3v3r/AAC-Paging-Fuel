package com.acemirr.cleanarchitecture.presenter.menu.grid.view

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.acemirr.cleanarchitecture.R
import com.acemirr.cleanarchitecture.data.model.GridGalleryModel
import com.acemirr.cleanarchitecture.databinding.DetailGridFragmentBinding
import com.acemirr.cleanarchitecture.external.CenterZoomLayoutManager
import com.acemirr.cleanarchitecture.presenter.activities.MainActivity
import com.acemirr.cleanarchitecture.presenter.base.BaseFragment
import com.acemirr.cleanarchitecture.presenter.menu.grid.adapter.GridDetailRVAdapter
import com.acemirr.cleanarchitecture.presenter.menu.grid.viewmodel.DetailGridViewModel
import javax.inject.Inject

class DetailGridFragment: BaseFragment<DetailGridViewModel,DetailGridFragmentBinding>(R.layout.detail_grid_fragment) {

    private var adapter = GridDetailRVAdapter()

    private val args: DetailGridFragmentArgs by navArgs()
    private var gridGalleryModel: GridGalleryModel? = null
    private var position : Int ?=null

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun createViewModel(): DetailGridViewModel {
        return ViewModelProvider(this,viewModelFactory).get(DetailGridViewModel::class.java)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        gridGalleryModel = args.dataGrid
        position = args.position
        (activity as MainActivity).hideNavigation(true)
        binding.vm = viewModel

        setupRecyclerView()
        observeData()

        viewModel.getListGallery(requireContext())
    }
    override fun onDestroy() {
        super.onDestroy()
        (activity as MainActivity).hideNavigation(false)
    }

    private fun setupRecyclerView() {
        val layoutManager = CenterZoomLayoutManager(requireContext())
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        layoutManager.reverseLayout = false
        layoutManager.stackFromEnd = false
        binding.rvGridDetail.layoutManager = layoutManager

        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding.rvGridDetail)
        binding.rvGridDetail.isNestedScrollingEnabled = false
        binding.rvGridDetail.adapter = adapter
    }

    private fun observeData() {
        viewModel.liveDataListGallery.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
            binding.rvGridDetail.scrollToPosition(position?:0)
        })
    }

}