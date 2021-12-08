package com.autumnsun.duvaryazim.ui.addwallstreet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.autumnsun.duvaryazim.data.local.WallStreetDao
import com.autumnsun.duvaryazim.data.local.entity.WallStreet
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/*
 Created by Fatih Kurcenli on 12/8/2021
*/

@HiltViewModel
class AddWallStreetViewModel @Inject constructor (
    val wallStreetDao: WallStreetDao
) : ViewModel() {

    val addWallStreetRepo = AddWallStreetRepository(wallStreetDao)


    fun insertStreetWrite(wallStreet: WallStreet) = viewModelScope.launch(Dispatchers.IO) {
        addWallStreetRepo.insertWallWrite(wallStreet)
    }
}