package com.acemirr.training_task_1.ui.list

import android.content.res.Configuration
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

import com.acemirr.training_task_1.R
import com.acemirr.training_task_1.data.model.PlaceList
import com.acemirr.training_task_1.databinding.ListFragmentBinding
import com.acemirr.training_task_1.ui.adapter.ListRVAdapter

class ListFragment : Fragment() {

    private lateinit var viewModel: ListViewModel
    lateinit var binding:ListFragmentBinding
    private lateinit var adapter: ListRVAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.list_fragment, container, false)
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        binding.vm = viewModel

        setupSwipeRefresh()
        setupRecyclerView()
        observeData()

        viewModel.getList(context!!)

    }
    fun String.toIMGAssetPath() = "file:///android_asset/images/$this"
    val dummy = mutableListOf(
        PlaceList("Title 1","Indonesia","DEscription","rendang.jpg".toIMGAssetPath(),"rendang.jpg".toIMGAssetPath()),
        PlaceList("Title 1","Indonesia","DEscription","rendang.jpg".toIMGAssetPath(),"rendang.jpg".toIMGAssetPath()),
        PlaceList("Title 1","Indonesia","DEscription","rendang.jpg".toIMGAssetPath(),"rendang.jpg".toIMGAssetPath()),
        PlaceList("Title 1","Indonesia","DEscription","rendang.jpg".toIMGAssetPath(),"rendang.jpg".toIMGAssetPath()),
        PlaceList("Title 1","Indonesia","DEscription","rendang.jpg".toIMGAssetPath(),"rendang.jpg".toIMGAssetPath()),
        PlaceList("Title 1","Indonesia","DEscription","rendang.jpg".toIMGAssetPath(),"rendang.jpg".toIMGAssetPath()),
        PlaceList("Title 1","Indonesia","DEscription","rendang.jpg".toIMGAssetPath(),"rendang.jpg".toIMGAssetPath()),
        PlaceList("Title 1","Indonesia","DEscription","rendang.jpg".toIMGAssetPath(),"rendang.jpg".toIMGAssetPath()),
        PlaceList("Title 1","Indonesia","DEscription","rendang.jpg".toIMGAssetPath(),"rendang.jpg".toIMGAssetPath()),
        PlaceList("Title 1","Indonesia","DEscription","rendang.jpg".toIMGAssetPath(),"rendang.jpg".toIMGAssetPath()),
        PlaceList("Title 1","Indonesia","DEscription","rendang.jpg".toIMGAssetPath(),"rendang.jpg".toIMGAssetPath())
    )

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        activity?.recreate()
    }

    private fun observeData() {
//        adapter.replaceData(dummy)
        viewModel.liveDataList.observe(viewLifecycleOwner, Observer {
            adapter.replaceData(it)
            Log.e("LOG",it.toString())
        })
    }


    private fun setupSwipeRefresh() {
        binding.swipeRefreshList.setOnRefreshListener {
            viewModel.getList(context!!)
        }
    }

    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(context)
        binding.rvList.layoutManager = layoutManager

        adapter = ListRVAdapter {
            onItemClick(it)
        }

        binding.rvList.adapter = adapter
    }

    private fun onItemClick(placeModel: PlaceList) {
        val action = ListFragmentDirections.actionToDetailFragment(placeModel)
        action.dataListDetail = placeModel
        findNavController().navigate(action)
    }
}
