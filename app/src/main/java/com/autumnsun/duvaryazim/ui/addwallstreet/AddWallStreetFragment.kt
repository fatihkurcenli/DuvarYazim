package com.autumnsun.duvaryazim.ui.addwallstreet

import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.autumnsun.duvaryazim.R
import com.autumnsun.duvaryazim.data.local.entity.WallStreet
import com.autumnsun.duvaryazim.databinding.FragmentAddWallStreetBinding
import com.autumnsun.duvaryazim.ui.BaseFragment
import com.autumnsun.duvaryazim.utils.Constants
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddWallStreetFragment :
    BaseFragment<FragmentAddWallStreetBinding,
            AddWallStreetViewModel>(R.layout.fragment_add_wall_street) {
    private val safeArgs: AddWallStreetFragmentArgs by navArgs()

    override val mViewModel: AddWallStreetViewModel
        get() = ViewModelProvider(requireActivity()).get(AddWallStreetViewModel::class.java)

    override fun getViewBinding(): FragmentAddWallStreetBinding =
        FragmentAddWallStreetBinding.inflate(layoutInflater)

    override fun initializeUi() {
        safeArgs.let {
            it.wallStreetUpdate.apply {
                binding.wallStreet.setText(this.wallStreet)
                binding.wallStreetWriter.setText(this.writer)
                binding.wallStreetImageUrl.setText(this.imageUrl)
                imageUrl?.let {
                    Glide.with(requireActivity())
                        .load(imageUrl.toString())
                        .error(Constants.NO_IMAGE_URL)
                        .into(binding.imageViewWithUrl)
                }
            }
        }
        binding.wallStreetImageUrl.addTextChangedListener { imageUrl ->
            Glide.with(requireActivity())
                .load(imageUrl.toString())
                .error(Constants.NO_IMAGE_URL)
                .into(binding.imageViewWithUrl)
        }
        binding.addWallStreetButton.setOnClickListener {
            mViewModel.insertStreetWrite(
                WallStreet(
                    writer = binding.wallStreetWriter.text.toString(),
                    wallStreet = binding.wallStreet.text.toString(),
                    imageUrl = binding.wallStreetImageUrl.text.toString()
                )
            )
        }
    }
}