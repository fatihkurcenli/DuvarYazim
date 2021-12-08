package com.autumnsun.duvaryazim.ui.home

import androidx.lifecycle.ViewModelProvider
import com.autumnsun.duvaryazim.R
import com.autumnsun.duvaryazim.databinding.FragmentHomeBinding
import com.autumnsun.duvaryazim.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(R.layout.fragment_home) {

    override fun initializeUi() {
        mViewModel.setSomeData()

    }

    override val mViewModel: HomeViewModel
        get() = ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)

}