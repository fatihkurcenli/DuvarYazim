package com.autumnsun.duvaryazim.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

/*
 Created by Fatih Kurcenli on 12/7/2021
*/

abstract class BaseFragment<T : ViewBinding, D : ViewModel>(@LayoutRes layoutRes: Int) :
    Fragment(layoutRes) {
    private var _binding: T? = null
    protected val binding get() = _binding!!
    protected abstract val mViewModel: D

    abstract fun getViewBinding(): T
    abstract fun initializeUi()

    protected val navController by lazy {
        (activity as MainActivity).navController
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = getViewBinding()
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeUi()
    }

/*    protected val activityViewModel: MainViewModel
        get() = (activity as MainActivity).viewModel*/

    protected val mainActivity: MainActivity
        get() = (activity as MainActivity)

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}