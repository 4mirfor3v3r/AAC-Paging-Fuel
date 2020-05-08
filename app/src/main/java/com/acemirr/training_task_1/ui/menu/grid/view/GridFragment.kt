package com.acemirr.training_task_1.ui.menu.grid.view

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.acemirr.training_task_1.R
import com.acemirr.training_task_1.databinding.GridFragmentBinding
import com.acemirr.training_task_1.ui.menu.grid.adapter.GridRVAdapter
import com.acemirr.training_task_1.ui.menu.grid.model.GridModel
import com.acemirr.training_task_1.ui.menu.grid.viewmodel.GridViewModel

class GridFragment : Fragment() {

    private lateinit var viewModel: GridViewModel
    lateinit var binding:GridFragmentBinding
    private lateinit var adapter: GridRVAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.grid_fragment, container, false)
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(GridViewModel::class.java)
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
        viewModel.liveDataListGallery.observe(viewLifecycleOwner, Observer {
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
        val layoutManager = GridLayoutManager(context,3)
        binding.rvGrid.layoutManager = layoutManager

        adapter =
            GridRVAdapter { gridModel, position ->
                onItemClick(gridModel, position)
            }

        binding.rvGrid.adapter = adapter
    }

    private fun onItemClick(gridModel: GridModel, position: Int) {
        val action =
            GridFragmentDirections.actionToDetailGridFragment(
                gridModel,
                position
            )
        action.dataGrid = gridModel
        action.position = position
        findNavController().navigate(action)
    }

}
