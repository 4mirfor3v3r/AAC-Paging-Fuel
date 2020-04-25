package com.acemirr.training_task_1.ui.menu.paging

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
import com.acemirr.training_task_1.data.model.PagingModel
import com.acemirr.training_task_1.databinding.PagingFragmentBinding
import com.acemirr.training_task_1.ui.PagingDetailActivity
import com.acemirr.training_task_1.ui.adapter.PagingRVAdapter

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
        viewModel = ViewModelProvider(this).get(PagingViewModel(activity?.application!!)::class.java)
        binding.vm = viewModel

        setupSwipeRefresh()
        setupRecyclerView()
        observeLiveData()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        activity?.recreate()
    }

    private fun setupSwipeRefresh() {
        binding.swipeRefreshListNotification.setOnRefreshListener {
            viewModel.refreshListNotification()
        }
    }

    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(context)
        binding.recyclerViewListNotification.layoutManager = layoutManager
        adapter = PagingRVAdapter{
            onItemClicked(it)
        }

        binding.recyclerViewListNotification.adapter = adapter
    }

    private fun observeLiveData() {
        viewModel.pagingList.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
        viewModel.getLoadingState().observe(viewLifecycleOwner, Observer {

        })
    }

    private fun onItemClicked(pagingModel: PagingModel) {
        val intent = Intent(context, PagingDetailActivity::class.java)
        intent.putExtra(PagingDetailActivity.EXTRA_DATA_NOTIFICATION, pagingModel)
        startActivity(intent)
        activity?.overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
    }

}
