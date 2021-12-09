package com.autumnsun.duvaryazim.ui.search

import android.view.WindowManager
import androidx.lifecycle.ViewModelProvider
import com.autumnsun.duvaryazim.R
import com.autumnsun.duvaryazim.databinding.FragmentSearchBinding
import com.autumnsun.duvaryazim.ui.BaseFragment


class SearchFragment :
    BaseFragment<FragmentSearchBinding, SearchViewModel>(R.layout.fragment_search) {

    override fun initializeUi() {
        activity?.window?.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }


    override val mViewModel: SearchViewModel
        get() = ViewModelProvider(requireActivity()).get(SearchViewModel::class.java)

    override fun getViewBinding(): FragmentSearchBinding =
        FragmentSearchBinding.inflate(layoutInflater)
}