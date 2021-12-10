package com.autumnsun.duvaryazim.ui.splash

import android.animation.Animator
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.autumnsun.duvaryazim.R
import com.autumnsun.duvaryazim.databinding.FragmentSplashBinding
import com.autumnsun.duvaryazim.ui.BaseFragment

class SplashFragment :
    BaseFragment<FragmentSplashBinding, SplashViewModel>(R.layout.fragment_splash) {

    override fun initializeUi() {
        mainActivity.setTheme(R.style.Theme_DuvarYazim)
        binding.lottiAnimation.playAnimation()
        mainActivity.toolBar.visibility = View.GONE
        binding.lottiAnimation.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator?) {
            }

            override fun onAnimationEnd(animation: Animator?) {
                Log.d("TAG", "listener bozuk")
                binding.lottiAnimation.cancelAnimation()
                navController.navigate(R.id.action_splashFragment_to_homeFragment)
            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationRepeat(animation: Animator?) {
            }

        })
    }

    override val mViewModel: SplashViewModel
        get() = ViewModelProvider(requireActivity()).get(SplashViewModel::class.java)

    override fun getViewBinding(): FragmentSplashBinding =
        FragmentSplashBinding.inflate(layoutInflater)

    override fun onDestroyView() {
        super.onDestroyView()
        mainActivity.toolBar.visibility = View.VISIBLE
    }
}
