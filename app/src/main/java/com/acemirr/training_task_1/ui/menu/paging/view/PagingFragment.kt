package com.acemirr.training_task_1.ui.menu.paging.view

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.acemirr.training_task_1.R
import com.acemirr.training_task_1.ui.menu.paging.model.News
//import com.acemirr.training_task_1.data.model.PagingModel
import com.acemirr.training_task_1.databinding.PagingFragmentBinding
import com.acemirr.training_task_1.ui.activities.PagingDetailActivity
import com.acemirr.training_task_1.ui.menu.paging.adapter.PagingRVAdapter
import com.acemirr.training_task_1.ui.menu.paging.viewmodel.PagingViewModel
import com.acemirr.training_task_1.utils.Constants.EXTRA_DATA_PAGING
import kotlinx.android.synthetic.main.paging_fragment.*

class PagingFragment : Fragment() {

    private lateinit var binding: PagingFragmentBinding
    private lateinit var viewModel: PagingViewModel

    private lateinit var adapter: PagingRVAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.paging_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(
            PagingViewModel(
                activity?.application!!
            )::class.java)
        binding.vm = viewModel

        setupRecyclerView()
        observeLiveData()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        activity?.recreate()
    }

    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(context)
        binding.recyclerViewListNotification.layoutManager = layoutManager
        adapter =
            PagingRVAdapter {
                onItemClicked(it)
            }

        binding.recyclerViewListNotification.adapter = adapter
    }

    private fun observeLiveData() {
        viewModel.pagingList.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
            binding.recyclerViewListNotification.startLayoutAnimation()
        })
        viewModel.getLoadingState().observe(viewLifecycleOwner, Observer {

        })
    }

    private fun onItemClicked(pagingModel: News) {
        val intent = Intent(context, PagingDetailActivity::class.java)
        intent.putExtra(EXTRA_DATA_PAGING, pagingModel)
        startActivity(intent)
        activity?.overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
    }

}
