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
import com.acemirr.training_task_1.data.model.ListModel
import com.acemirr.training_task_1.databinding.DetailFragmentBinding
import com.acemirr.training_task_1.ui.MainActivity
import com.acemirr.training_task_1.ui.base.CustomListDetailViewModelFactory

class DetailListFragment : Fragment() {

    lateinit var binding: DetailFragmentBinding
    private lateinit var listViewModel: DetailListViewModel

    private val args: DetailListFragmentArgs? by navArgs()
    private var listModel: ListModel? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        listModel = args?.dataListDetail
        binding = DataBindingUtil.inflate(inflater, R.layout.detail_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as MainActivity).hideNavigation(true)


        listViewModel = ViewModelProvider(this, CustomListDetailViewModelFactory(listModel!!, activity!!.application)).get(DetailListViewModel::class.java)
        binding.tvTitle.visibility = View.VISIBLE

        binding.vm = listViewModel

    }

    override fun onDestroy() {
        super.onDestroy()
        (activity as MainActivity).hideNavigation(false)
    }

}
