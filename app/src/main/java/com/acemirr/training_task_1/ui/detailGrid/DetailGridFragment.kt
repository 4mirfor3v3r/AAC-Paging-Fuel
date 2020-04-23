package com.acemirr.training_task_1.ui.detailGrid

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
import com.acemirr.training_task_1.databinding.DetailGridFragmentBinding
import com.acemirr.training_task_1.ui.MainActivity
import com.acemirr.training_task_1.ui.base.CustomGridDetailViewModelFactory

class DetailGridFragment: Fragment() {

    private lateinit var binding: DetailGridFragmentBinding
    private lateinit var viewModel: DetailGridViewModel

    private val args: DetailGridFragmentArgs by navArgs()
    private var galleryModel: GridModel? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        galleryModel = args.dataGrid
        binding = DataBindingUtil.inflate(inflater, R.layout.detail_grid_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).hideNavigation(true)
        viewModel = ViewModelProvider(this, CustomGridDetailViewModelFactory(galleryModel!!, activity!!.application)).get(DetailGridViewModel::class.java)
        binding.vm = viewModel
    }

    override fun onDestroy() {
        super.onDestroy()
        (activity as MainActivity).hideNavigation(false)
    }

}