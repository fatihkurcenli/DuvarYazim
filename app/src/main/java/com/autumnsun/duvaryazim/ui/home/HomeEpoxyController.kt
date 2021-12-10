package com.autumnsun.duvaryazim.ui.home

import android.content.Context
import android.widget.Toast
import com.airbnb.epoxy.EpoxyController
import com.autumnsun.duvaryazim.R
import com.autumnsun.duvaryazim.data.local.entity.WallStreet
import com.autumnsun.duvaryazim.databinding.ModelHomeItemBinding
import com.autumnsun.duvaryazim.utils.LoadingEpoxyModel
import com.autumnsun.duvaryazim.utils.ViewBindingKotlinModel
import com.autumnsun.duvaryazim.utils.getDate
import com.bumptech.glide.Glide
import com.chauthai.swipereveallayout.ViewBinderHelper
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


/*
 Created by Fatih Kurcenli on 12/8/2021
*/

class HomeEpoxyController(
    private val context: Context,
    private val onClicked: (WallStreet) -> Unit,
    private val likedItem: (WallStreet) -> Unit,
    private val deleteItem: (WallStreet) -> Unit
) : EpoxyController() {
    val binderHelper = ViewBinderHelper()
    var isLoading: Boolean = false
        set(value) {
            field = value
            if (field) {
                requestModelBuild()
            }
        }

    var wallStreetList = ArrayList<WallStreet>()
        set(value) {
            field = value
            isLoading = false
            requestModelBuild()
        }

    override fun buildModels() {
        if (isLoading) {
            LoadingEpoxyModel(isLoading).id("loading_state").addTo(this)
            return
        }

        if (wallStreetList.isEmpty()) {
            // todo show empty stateD
            return
        }



        wallStreetList.forEachIndexed { index, wallStreet ->
            WallStreetModel(
                context,
                wallStreet,
                onClicked,
                likedItem,
                deleteItem,
                binderHelper,
            ).id(wallStreet.id).addTo(this)
        }
    }


    data class WallStreetModel(
        val context: Context,
        val wallStreet: WallStreet,
        val onClicked: (WallStreet) -> Unit,
        val likedItem: (WallStreet) -> Unit,
        val deleteItem: (WallStreet) -> Unit,
        val binderHelper: ViewBinderHelper,
    ) : ViewBindingKotlinModel<ModelHomeItemBinding>(R.layout.model_home_item) {
        override fun ModelHomeItemBinding.bind() {
            binderHelper.bind(swipeLayout, wallStreet.id)
            deleteLayout.setOnClickListener {
                deleteItem(wallStreet)
                //Toast.makeText(context, "Delete işlemi gerçekle", Toast.LENGTH_SHORT).show()
            }
            wallStreetWrite.text = wallStreet.wallStreet
            wallStreetWriter.text = wallStreet.writer
            createTime.text = getDate(wallStreet.timestamp)
            if (wallStreet.isLiked) {
                addFavorite.setImageResource(R.drawable.ic_favorite_red)
            } else {
                addFavorite.setImageResource(R.drawable.ic_baseline_favorite_border_24)
            }
            Glide.with(context).load(wallStreet.imageUrl).into(wallImage)
            linearBackground.setOnClickListener {
                if (swipeLayout.isOpened) {
                    binderHelper.closeLayout(wallStreet.id)
                } else {
                    onClicked(wallStreet)
                }
            }
            addFavorite.setOnClickListener {
                likedItem(wallStreet)
            }
        }
    }
}