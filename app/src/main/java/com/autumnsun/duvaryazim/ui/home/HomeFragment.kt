package com.autumnsun.duvaryazim.ui.home

import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.airbnb.epoxy.EpoxyTouchHelper
import com.airbnb.epoxy.EpoxyTouchHelper.DragCallbacks
import com.autumnsun.duvaryazim.R
import com.autumnsun.duvaryazim.data.local.entity.WallStreet
import com.autumnsun.duvaryazim.databinding.FragmentHomeBinding
import com.autumnsun.duvaryazim.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(R.layout.fragment_home) {
    private lateinit var homeEpoxy: HomeEpoxyController

    override fun initializeUi() {
        //mViewModel.setSomeData()

        homeEpoxy = HomeEpoxyController(requireActivity(), { wallStreet ->
            val navDirectionAction =
                HomeFragmentDirections.actionHomeFragmentToAddWallStreetFragment(wallStreet, true)
            navController.navigate(navDirectionAction)
        }, { likedWallStreet ->
            mViewModel.likedWallStreetEntity(likedWallStreet)
        }, { deleteItem ->
            mViewModel.deleteWallStreetEntity(deleteItem)
        })
        homeEpoxy.isLoading = true
        binding.homeEpoxy.setController(homeEpoxy)
        mViewModel.wallStreetItem.observe(viewLifecycleOwner) { listWallStreet ->
            homeEpoxy.wallStreetList = listWallStreet as ArrayList<WallStreet>
        }

        // Setup swipe-to-delete
/*        EpoxyTouchHelper.initSwiping(binding.homeEpoxy)
            .right()
            .withTarget(HomeEpoxyController.WallStreetModel::class.java)
            .andCallbacks(object :
                EpoxyTouchHelper.SwipeCallbacks<HomeEpoxyController.WallStreetModel>() {
                override fun onSwipeCompleted(
                    model: HomeEpoxyController.WallStreetModel?,
                    itemView: View?,
                    position: Int,
                    direction: Int
                ) {
                    val itemWasEntity = model?.wallStreet ?: return
                    mViewModel.deleteWallStreetEntity(itemWasEntity)
                }

                override fun onSwipeProgressChanged(
                    model: HomeEpoxyController.WallStreetModel?,
                    itemView: View?,
                    swipeProgress: Float,
                    canvas: Canvas?
                ) {
                    super.onSwipeProgressChanged(model, itemView, swipeProgress, canvas)
                    itemView?.alpha = (1f - swipeProgress.absoluteValue)
                    when {
                        swipeProgress > 0 -> {
                            itemView?.setBackgroundColor(
                                ContextCompat.getColor(
                                    requireContext(),
                                    R.color.delete_color
                                )
                            )
                        }
                        swipeProgress == 0.0F -> {
                            itemView?.setBackgroundColor(Color.TRANSPARENT)
                        }
                        else -> {
                            itemView?.setBackgroundColor(
                                ContextCompat.getColor(
                                    requireContext(),
                                    R.color.teal_200
                                )
                            )
                        }
                    }
                }

                override fun clearView(
                    model: HomeEpoxyController.WallStreetModel?,
                    itemView: View?
                ) {
                    super.clearView(model, itemView)
                    itemView?.setBackgroundColor(Color.TRANSPARENT)
                    itemView?.alpha = 1f
                }

                override fun onSwipeReleased(
                    model: HomeEpoxyController.WallStreetModel?,
                    itemView: View?
                ) {
                    super.onSwipeReleased(model, itemView)
                    itemView?.alpha = 1f
                }
            })*/

        EpoxyTouchHelper.initDragging(homeEpoxy) // an EpoxyController must be used
            .withRecyclerView(binding.homeEpoxy) // The recyclerview the controller is used with
            .forVerticalList() // Specify the directions that you want to drag in
            .withTarget(HomeEpoxyController.WallStreetModel::class.java) // Specify the type of model or models that should be draggable
            .andCallbacks(object : DragCallbacks<HomeEpoxyController.WallStreetModel>() {
                override fun onModelMoved(
                    fromPosition: Int, toPosition: Int,
                    modelBeingMoved: HomeEpoxyController.WallStreetModel, itemView: View
                ) {
                    mViewModel.updateList(fromPosition, toPosition)
                }
            })
    }

    override val mViewModel: HomeViewModel
        get() = ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)

    override fun getViewBinding() = FragmentHomeBinding.inflate(layoutInflater)

}