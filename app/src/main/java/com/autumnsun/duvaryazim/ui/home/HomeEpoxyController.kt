package com.autumnsun.duvaryazim.ui.home

import android.content.Context
import com.airbnb.epoxy.EpoxyController
import com.autumnsun.duvaryazim.R
import com.autumnsun.duvaryazim.data.local.entity.WallStreet
import com.autumnsun.duvaryazim.databinding.ModelHomeItemBinding
import com.autumnsun.duvaryazim.utils.LoadingEpoxyModel
import com.autumnsun.duvaryazim.utils.ViewBindingKotlinModel
import com.bumptech.glide.Glide

/*
 Created by Fatih Kurcenli on 12/8/2021
*/

class HomeEpoxyController(
    private val context: Context,
    private val onClicked: (WallStreet) -> Unit
) : EpoxyController() {
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
            WallStreetModel(context, wallStreet, onClicked).id(index).addTo(this)
        }
    }


    data class WallStreetModel(
        val context: Context,
        val wallStreet: WallStreet,
        val onClicked: (WallStreet) -> Unit
    ) : ViewBindingKotlinModel<ModelHomeItemBinding>(R.layout.model_home_item) {
        override fun ModelHomeItemBinding.bind() {
            wallStreetWrite.text = wallStreet.wallStreet
            wallStreetWriter.text = wallStreet.writer
            Glide.with(context).load(wallStreet.imageUrl).into(wallImage)
            root.setOnClickListener {
                onClicked(wallStreet)
            }
        }
    }
}