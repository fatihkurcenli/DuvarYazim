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
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddWallStreetFragment :
    BaseFragment<FragmentAddWallStreetBinding,
            AddWallStreetViewModel>(R.layout.fragment_add_wall_street) {
    private val safeArgs: AddWallStreetFragmentArgs by navArgs()
    private var updatePage: Boolean = false
    private var selectedItemWallStreetId: String? = null

    override val mViewModel: AddWallStreetViewModel
        get() = ViewModelProvider(requireActivity()).get(AddWallStreetViewModel::class.java)

    override fun getViewBinding(): FragmentAddWallStreetBinding =
        FragmentAddWallStreetBinding.inflate(layoutInflater)

    override fun initializeUi() {
        safeArgs.let {
            it.wallStreetUpdate.apply {
                selectedItemWallStreetId = this?.id
                binding.wallStreet.setText(this?.wallStreet)
                binding.wallStreetWriter.setText(this?.writer)
                binding.wallStreetImageUrl.setText(this?.imageUrl)
                this?.imageUrl.let {
                    Glide.with(requireActivity())
                        .load(it.toString())
                        .error(Constants.NO_IMAGE_URL)
                        .into(binding.imageViewWithUrl)
                }
            }
            it.isUpdatePage.let { updateBoolean ->
                updatePage = updateBoolean
            }
        }
        binding.wallStreetImageUrl.addTextChangedListener { imageUrl ->
            Glide.with(requireActivity())
                .load(imageUrl.toString())
                .error(Constants.NO_IMAGE_URL)
                .into(binding.imageViewWithUrl)
        }

        if (!updatePage) {
            binding.addWallStreetButton.text = "Add"
            binding.addWallStreetButton.setOnClickListener {
                if (binding.wallStreet.text?.isEmpty() == true) {
                    binding.wallStreet.error = "Requeired field"
                    return@setOnClickListener
                }
                mViewModel.insertStreetWrite(
                    WallStreet(
                        writer = binding.wallStreetWriter.text.toString(),
                        wallStreet = binding.wallStreet.text.toString().trim(),
                        imageUrl = binding.wallStreetImageUrl.text.toString()
                    )
                )
            }
        } else {
            binding.addWallStreetButton.text = "Update"
            binding.addWallStreetButton.setOnClickListener {
                mViewModel.updateStreetWrite(
                    selectedItemWallStreetId!!,
                    binding.wallStreetWriter.text.toString(),
                    binding.wallStreet.text.toString(),
                    binding.wallStreetImageUrl.text.toString()
                )
            }
        }
    }
}