package com.autumnsun.duvaryazim.ui.home

import android.graphics.Canvas
import android.graphics.Color
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
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

    override fun initializeUi() {
        //mViewModel.setSomeData()

        val homeEpoxy = HomeEpoxyController(requireActivity()) { wallStreet ->
            val navDirectionAction =
                HomeFragmentDirections.actionHomeFragmentToAddWallStreetFragment(wallStreet, true)
            navController.navigate(navDirectionAction)
        }
        homeEpoxy.isLoading = true
        binding.homeEpoxy.setController(homeEpoxy)
        mViewModel.wallStreetItem.observe(viewLifecycleOwner) { listWallStreet ->
            homeEpoxy.wallStreetList = listWallStreet as ArrayList<WallStreet>
        }

        // Setup swipe-to-delete
        EpoxyTouchHelper.initSwiping(binding.homeEpoxy)
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
                    val itemWasEntityRemove = model?.wallStreet ?: return
                    mViewModel.deleteWallStreetEntity(itemWasEntityRemove)
                    itemView?.setBackgroundColor(Color.TRANSPARENT)
                    Log.d("TAG", "deleted Item")
                }

                override fun onSwipeProgressChanged(
                    model: HomeEpoxyController.WallStreetModel?,
                    itemView: View?,
                    swipeProgress: Float,
                    canvas: Canvas?
                ) {
                    super.onSwipeProgressChanged(model, itemView, swipeProgress, canvas)
                    if (swipeProgress > 0.15) {
                        itemView?.setBackgroundColor(
                            ContextCompat.getColor(
                                requireContext(),
                                R.color.delete_color
                            )
                        )
                    } else {
                        itemView?.setBackgroundColor(Color.TRANSPARENT)
                    }
                }

                override fun clearView(
                    model: HomeEpoxyController.WallStreetModel?,
                    itemView: View?
                ) {
                    super.clearView(model, itemView)
                    itemView?.setBackgroundColor(Color.TRANSPARENT)
                }
            })

        EpoxyTouchHelper.initDragging(homeEpoxy) // an EpoxyController must be used
            .withRecyclerView(binding.homeEpoxy) // The recyclerview the controller is used with
            .forVerticalList() // Specify the directions that you want to drag in
            .withTarget(HomeEpoxyController.WallStreetModel::class.java) // Specify the type of model or models that should be draggable
            .andCallbacks(object : DragCallbacks<HomeEpoxyController.WallStreetModel>() {
                override fun onModelMoved(
                    fromPosition: Int, toPosition: Int,
                    modelBeingMoved: HomeEpoxyController.WallStreetModel, itemView: View
                ) {
                    // Called when a view has been dragged to a new position.
                    // Epoxy will automatically update the models in the controller and notify
                    // the RecyclerView of the move
                    mViewModel.updateList(fromPosition, toPosition)
                    // You MUST use this callback to update your data to reflect the move
                }

                // You may optionally implement the below methods as hooks into the drag lifecycle.
                // This allows you to style or animate the view as it is dragged.
                override fun onDragStarted(
                    model: HomeEpoxyController.WallStreetModel,
                    itemView: View,
                    adapterPosition: Int
                ) {
                }

                override fun onDragReleased(
                    model: HomeEpoxyController.WallStreetModel,
                    itemView: View
                ) {
                }

                override fun clearView(
                    model: HomeEpoxyController.WallStreetModel,
                    itemView: View
                ) {
                }

                override fun isDragEnabledForModel(model: HomeEpoxyController.WallStreetModel): Boolean {
                    // Override this to toggle disabling dragging for a model
                    return true
                }
            })
    }

    override val mViewModel: HomeViewModel
        get() = ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)

    override fun getViewBinding() = FragmentHomeBinding.inflate(layoutInflater)

}