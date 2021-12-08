package com.autumnsun.duvaryazim.ui.home

import androidx.lifecycle.ViewModelProvider
import com.autumnsun.duvaryazim.R
import com.autumnsun.duvaryazim.data.local.entity.WallStreet
import com.autumnsun.duvaryazim.databinding.FragmentHomeBinding
import com.autumnsun.duvaryazim.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(R.layout.fragment_home) {

    override fun initializeUi() {
        //mViewModel.setSomeData()

        val homeEpoxy = HomeEpoxyController()
        homeEpoxy.isLoading = true
        binding.homeEpoxy.setController(homeEpoxy)
        mViewModel.wallStreetItem.observe(viewLifecycleOwner) { listWallStreet ->
            homeEpoxy.wallStreetList = listWallStreet as ArrayList<WallStreet>
        }
    }

    override val mViewModel: HomeViewModel
        get() = ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)

    override fun getViewBinding() = FragmentHomeBinding.inflate(layoutInflater)

}