package com.autumnsun.duvaryazim.ui.addwallstreet

import android.content.Context
import androidx.core.widget.addTextChangedListener
import com.airbnb.epoxy.EpoxyController
import com.autumnsun.duvaryazim.R
import com.autumnsun.duvaryazim.databinding.ModelAddWallStreetBinding
import com.autumnsun.duvaryazim.utils.Constants.NO_IMAGE_URL
import com.autumnsun.duvaryazim.utils.ViewBindingKotlinModel
import com.bumptech.glide.Glide

/*
 Created by Fatih Kurcenli on 12/8/2021
*/

class AddWallStreetEpoxyController(
    val context: Context
) : EpoxyController() {


    override fun buildModels() {
        AddWallStreetModel(context).id("initial").addTo(this)
    }

    data class AddWallStreetModel(
        val context: Context,
    ) : ViewBindingKotlinModel<ModelAddWallStreetBinding>(R.layout.model_add_wall_street) {
        override fun ModelAddWallStreetBinding.bind() {
/*
            wallStreetImageUrl.text.toString().apply {
                Glide.with(context).load(this).into(imageViewWithUrl)
            }*/
            wallStreetImageUrl.addTextChangedListener {
                Glide.with(context)
                    .load(it.toString())
                    .error(NO_IMAGE_URL)
                    .into(imageViewWithUrl)
            }

            /*Glide.with(context).load(news.newsImage).into(newsImage)
            root.setOnClickListener {
                onClicked(news)
            }*/
        }
    }
}