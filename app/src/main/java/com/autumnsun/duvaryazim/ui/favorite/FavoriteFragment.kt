package com.autumnsun.duvaryazim.ui.favorite

import androidx.lifecycle.ViewModelProvider
import com.autumnsun.duvaryazim.R
import com.autumnsun.duvaryazim.data.local.entity.WallStreet
import com.autumnsun.duvaryazim.databinding.FragmentFavoriteBinding
import com.autumnsun.duvaryazim.ui.BaseFragment
import com.autumnsun.duvaryazim.ui.home.HomeEpoxyController
import com.autumnsun.duvaryazim.ui.home.HomeFragmentDirections


class FavoriteFragment :
    BaseFragment<FragmentFavoriteBinding, FavoriteViewModel>(R.layout.fragment_favorite) {

    override fun initializeUi() {
        val favoriteEpoxy = HomeEpoxyController(requireActivity(), { wallStreet ->
            val navDirectionAction =
                HomeFragmentDirections.actionHomeFragmentToAddWallStreetFragment(wallStreet, true)
            navController.navigate(navDirectionAction)
        }, { likedWallStreet ->
            mViewModel.likedWallStreetEntity(likedWallStreet)
        }, { deleteItem ->
            mViewModel.deleteWallStreetEntity(deleteItem)
        })
        favoriteEpoxy.isLoading = true
        binding.favoriteFragment.setController(favoriteEpoxy)
        mViewModel.wallStreetItem.observe(viewLifecycleOwner) { listWallStreet ->
            favoriteEpoxy.wallStreetList = listWallStreet as ArrayList<WallStreet>
        }
    }

    override val mViewModel: FavoriteViewModel
        get() = ViewModelProvider(requireActivity()).get(FavoriteViewModel::class.java)

    override fun getViewBinding(): FragmentFavoriteBinding =
        FragmentFavoriteBinding.inflate(layoutInflater)
}