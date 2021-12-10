package com.autumnsun.duvaryazim.ui.search

import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.ViewModelProvider
import com.autumnsun.duvaryazim.R
import com.autumnsun.duvaryazim.data.local.entity.WallStreet
import com.autumnsun.duvaryazim.databinding.FragmentSearchBinding
import com.autumnsun.duvaryazim.ui.BaseFragment
import com.autumnsun.duvaryazim.ui.home.HomeEpoxyController
import com.autumnsun.duvaryazim.ui.home.HomeFragmentDirections


class SearchFragment :
    BaseFragment<FragmentSearchBinding, SearchViewModel>(R.layout.fragment_search) {

    private var searchText = ""
    private val handler = Handler(Looper.getMainLooper())
    private val searching = Runnable {
        Log.d("TAG", searchText)
        mViewModel.searchEntity(searchText)
    }

    override fun initializeUi() {
        mainActivity.toolBar.visibility = View.GONE
        binding.searchEditText.doAfterTextChanged {
            searchText = it?.toString() ?: ""

            handler.removeCallbacks(searching)
            handler.postDelayed(searching, 500L)
        }

        val searchEpoxy = HomeEpoxyController(requireActivity(), { wallStreet ->
            val navDirectionAction =
                HomeFragmentDirections.actionHomeFragmentToAddWallStreetFragment(wallStreet, true)
            navController.navigate(navDirectionAction)
        }, { likedWallStreet ->
            mViewModel.likedWallStreetEntity(likedWallStreet)
        }, { deleteItem ->
            mViewModel.deleteWallStreetEntity(deleteItem)
        })
        searchEpoxy.isLoading = false
        binding.epoxyRecyclerView.setController(searchEpoxy)
        mViewModel.wallStreetItem.observe(viewLifecycleOwner) { listWallStreet ->
            searchEpoxy.wallStreetList = listWallStreet as ArrayList<WallStreet>
        }
    }


    override val mViewModel: SearchViewModel
        get() = ViewModelProvider(requireActivity()).get(SearchViewModel::class.java)

    override fun getViewBinding(): FragmentSearchBinding =
        FragmentSearchBinding.inflate(layoutInflater)


    override fun onDestroyView() {
        super.onDestroyView()
        mainActivity.toolBar.visibility = View.VISIBLE
    }
}