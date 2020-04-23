package com.acemirr.training_task_1.ui.grid

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager

import com.acemirr.training_task_1.R
import com.acemirr.training_task_1.data.model.GridModel
import com.acemirr.training_task_1.databinding.GridFragmentBinding
import com.acemirr.training_task_1.ui.adapter.GridRVAdapter

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

//        viewModel.getList(context!!)

    }
    fun String.toIMGAssetPath() = "file:///android_asset/images/$this"
    val dummy = mutableListOf(
        GridModel("Title 1","rendang.jpg".toIMGAssetPath(),"rendang.jpg".toIMGAssetPath()),
        GridModel("Title 1","rendang.jpg".toIMGAssetPath(),"rendang.jpg".toIMGAssetPath()),
        GridModel("Title 1","rendang.jpg".toIMGAssetPath(),"rendang.jpg".toIMGAssetPath()),
        GridModel("Title 1","rendang.jpg".toIMGAssetPath(),"rendang.jpg".toIMGAssetPath()),
        GridModel("Title 1","rendang.jpg".toIMGAssetPath(),"rendang.jpg".toIMGAssetPath()),
        GridModel("Title 1","rendang.jpg".toIMGAssetPath(),"rendang.jpg".toIMGAssetPath()),
        GridModel("Title 1","rendang.jpg".toIMGAssetPath(),"rendang.jpg".toIMGAssetPath()),
        GridModel("Title 1","rendang.jpg".toIMGAssetPath(),"rendang.jpg".toIMGAssetPath()),
        GridModel("Title 1","rendang.jpg".toIMGAssetPath(),"rendang.jpg".toIMGAssetPath()),
        GridModel("Title 1","rendang.jpg".toIMGAssetPath(),"rendang.jpg".toIMGAssetPath()),
        GridModel("Title 1","rendang.jpg".toIMGAssetPath(),"rendang.jpg".toIMGAssetPath())
    )

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        activity?.recreate()
    }

    private fun observeData() {
        adapter.replaceData(dummy)
//        viewModel.liveDataList.observe(viewLifecycleOwner, Observer {
//            adapter.replaceData(it)
//            Log.e("LOG",it.toString())
//        })
    }


    private fun setupSwipeRefresh() {
        binding.swipeRefreshGrid.setOnRefreshListener {
            viewModel.getListGallery(context!!)
        }
    }

    private fun setupRecyclerView() {
        val layoutManager = GridLayoutManager(context,3)
        binding.rvGrid.layoutManager = layoutManager

        adapter = GridRVAdapter {
            onItemClick(it)
        }

        binding.rvGrid.adapter = adapter
    }

    private fun onItemClick(gridModel: GridModel) {
        val action = GridFragmentDirections.actionToDetailGridFragment(gridModel)
        action.dataGrid = gridModel
        findNavController().navigate(action)
    }

}
