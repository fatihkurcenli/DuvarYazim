package com.autumnsun.duvaryazim.utils

import com.autumnsun.duvaryazim.R
import com.autumnsun.duvaryazim.databinding.ModelLoadingShimmerBinding

/*
 Created by Fatih Kurcenli on 12/8/2021
*/

class LoadingEpoxyModel(val shimmerStartStop: Boolean) :
    ViewBindingKotlinModel<ModelLoadingShimmerBinding>(R.layout.model_loading_shimmer) {

    override fun ModelLoadingShimmerBinding.bind() {
        if (shimmerStartStop) {
            shimmer.startShimmer()
        } else {
            shimmer.stopShimmer()
        }
    }

    override fun getSpanSize(totalSpanCount: Int, position: Int, itemCount: Int): Int {
        return totalSpanCount
    }
}