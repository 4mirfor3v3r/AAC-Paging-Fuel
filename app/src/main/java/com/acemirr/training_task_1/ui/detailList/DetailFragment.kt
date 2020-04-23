package com.acemirr.training_task_1.ui.detailList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.acemirr.training_task_1.R
import com.acemirr.training_task_1.data.model.GridModel
import com.acemirr.training_task_1.data.model.PlaceList
import com.acemirr.training_task_1.databinding.DetailFragmentBinding
import com.acemirr.training_task_1.ui.MainActivity
import com.acemirr.training_task_1.ui.base.CustomListDetailViewModelFactory

class DetailFragment : Fragment() {

    lateinit var binding: DetailFragmentBinding
    private lateinit var viewModel: DetailViewModel

    private val args: DetailFragmentArgs? by navArgs()
    private var placeList: PlaceList? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        placeList = args?.dataListDetail
        binding = DataBindingUtil.inflate(inflater, R.layout.detail_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as MainActivity).hideNavigation(true)


        viewModel = ViewModelProvider(this, CustomListDetailViewModelFactory(placeList!!, activity!!.application)).get(DetailViewModel::class.java)
        binding.tvTitle.visibility = View.VISIBLE

        binding.vm = viewModel

    }

    override fun onDestroy() {
        super.onDestroy()
        (activity as MainActivity).hideNavigation(false)
    }

}
