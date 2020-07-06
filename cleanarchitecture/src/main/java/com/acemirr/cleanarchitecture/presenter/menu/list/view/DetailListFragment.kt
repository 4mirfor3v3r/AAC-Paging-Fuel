package com.acemirr.cleanarchitecture.presenter.menu.list.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.acemirr.cleanarchitecture.R
import com.acemirr.cleanarchitecture.data.model.ListPlaceRemote
import com.acemirr.cleanarchitecture.databinding.DetailFragmentBinding
import com.acemirr.cleanarchitecture.presenter.activities.MainActivity
import com.acemirr.cleanarchitecture.presenter.base.ViewModelFactory
import com.acemirr.cleanarchitecture.presenter.menu.list.viewmodel.DetailListViewModel

class DetailListFragment : Fragment() {

    lateinit var binding: DetailFragmentBinding
    private lateinit var viewModel: DetailListViewModel

    private val args: DetailListFragmentArgs? by navArgs()
    private var listModel: ListPlaceRemote? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        listModel = args?.dataListDetail
        binding = DataBindingUtil.inflate(inflater, R.layout.detail_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as MainActivity).hideNavigation(true)


        viewModel = ViewModelProvider(this, ViewModelFactory(lifecycleScope)).get(DetailListViewModel::class.java)
        binding.tvTitle.visibility = View.VISIBLE
        binding.vm = viewModel

        setupDetail()
    }
    override fun onDestroy() {
        super.onDestroy()
        (activity as MainActivity).hideNavigation(false)
    }
    private fun setupDetail(){
        viewModel.name.set(listModel?.name)
        viewModel.description.set(listModel?.description)
        viewModel.imageUrl.set(listModel?.image)
    }

}
