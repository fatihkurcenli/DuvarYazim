package com.autumnsun.duvaryazim.ui.addwallstreet

import androidx.lifecycle.ViewModelProvider
import com.autumnsun.duvaryazim.R
import com.autumnsun.duvaryazim.databinding.FragmentAddWallStreetBinding
import com.autumnsun.duvaryazim.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddWallStreetFragment :
    BaseFragment<FragmentAddWallStreetBinding,
            AddWallStreetViewModel>(R.layout.fragment_add_wall_street) {

    override val mViewModel: AddWallStreetViewModel
        get() = ViewModelProvider(requireActivity()).get(AddWallStreetViewModel::class.java)

    override fun getViewBinding(): FragmentAddWallStreetBinding =
        FragmentAddWallStreetBinding.inflate(layoutInflater)

    override fun initializeUi() {
        val addEpoxyController = AddWallStreetEpoxyController(requireActivity())
        binding.addEpoxy.setController(addEpoxyController)
        binding.addEpoxy.requestModelBuild()
    }


}