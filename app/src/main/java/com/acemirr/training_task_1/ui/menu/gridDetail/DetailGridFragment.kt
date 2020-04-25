package com.acemirr.training_task_1.ui.menu.gridDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.acemirr.training_task_1.R
import com.acemirr.training_task_1.data.model.GridModel
import com.acemirr.training_task_1.databinding.DetailGridFragmentBinding
import com.acemirr.training_task_1.ui.MainActivity
import com.acemirr.training_task_1.ui.adapter.GridDetailRVAdapter
import com.acemirr.training_task_1.ui.base.CustomGridDetailViewModelFactory
import com.acemirr.training_task_1.utils.CenterZoomLayout

class DetailGridFragment: Fragment() {

    private lateinit var binding: DetailGridFragmentBinding
    private lateinit var viewModel: DetailGridViewModel
    private var adapter = GridDetailRVAdapter()

    private val args: DetailGridFragmentArgs by navArgs()
    private var galleryModel: GridModel? = null
    private var position : Int ?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        galleryModel = args.dataGrid
        position = args.position
        binding = DataBindingUtil.inflate(inflater, R.layout.detail_grid_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).hideNavigation(true)
        viewModel = ViewModelProvider(this, CustomGridDetailViewModelFactory(activity!!.application)).get(DetailGridViewModel::class.java)
        binding.vm = viewModel

        setupRecyclerView()
        observeData()

        viewModel.getListGallery(context!!)
    }
    override fun onDestroy() {
        super.onDestroy()
        (activity as MainActivity).hideNavigation(false)
    }

    private fun setupRecyclerView() {
        val layoutManager = CenterZoomLayout(context!!)
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