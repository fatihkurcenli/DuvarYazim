package com.autumnsun.duvaryazim.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.autumnsun.duvaryazim.data.local.entity.WallStreet
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/*
 Created by Fatih Kurcenli on 12/7/2021
*/

@HiltViewModel
class HomeViewModel @Inject constructor(val homeRepo: HomeRepository) : ViewModel() {

    fun setSomeData() = viewModelScope.launch(Dispatchers.IO) {
        homeRepo.insertWallWrite(
            WallStreet(
                writer = "Fatih",
                wallStreet = "Hello this is test"
            )
        )
    }

}