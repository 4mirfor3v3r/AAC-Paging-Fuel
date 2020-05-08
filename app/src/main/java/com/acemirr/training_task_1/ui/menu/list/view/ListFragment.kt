package com.acemirr.training_task_1.ui.menu.list.view

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
import androidx.recyclerview.widget.LinearLayoutManager
import com.acemirr.training_task_1.R
import com.acemirr.training_task_1.ui.menu.list.model.ListModel
import com.acemirr.training_task_1.databinding.ListFragmentBinding
import com.acemirr.training_task_1.ui.menu.list.adapter.ListRVAdapter
import com.acemirr.training_task_1.ui.menu.list.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.list_fragment.*

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

        viewModel.getList(requireContext())

    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        activity?.recreate()
    }

    private fun observeData() {
        viewModel.liveDataListModel.observe(viewLifecycleOwner, Observer {
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

        adapter =
            ListRVAdapter {
                onItemClick(it)
            }

        binding.rvList.adapter = adapter
    }

    private fun onItemClick(modelModel: ListModel) {
        val action =
            ListFragmentDirections.actionToDetailFragment(
                modelModel
            )
        action.dataListDetail = modelModel
        findNavController().navigate(action)
    }
}
