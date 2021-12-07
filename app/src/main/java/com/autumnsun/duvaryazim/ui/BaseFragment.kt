package com.autumnsun.duvaryazim.ui

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import dagger.hilt.android.AndroidEntryPoint

/*
 Created by Fatih Kurcenli on 12/7/2021
*/

@AndroidEntryPoint
abstract class BaseFragment<T : ViewBinding>(@LayoutRes layoutRes: Int) : Fragment(layoutRes) {
    private var _binding: T? = null
    protected val binding get() = _binding!!

    /*  protected val navController by lazy {
          (activity as MainActivity).navController
      }*/

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeUi()
    }

    abstract fun initializeUi()

/*    protected val activityViewModel: MainViewModel
        get() = (activity as MainActivity).viewModel*/

    protected val mainActivity: MainActivity
        get() = (activity as MainActivity)

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}