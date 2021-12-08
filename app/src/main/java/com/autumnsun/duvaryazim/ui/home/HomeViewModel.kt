package com.autumnsun.duvaryazim.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.autumnsun.duvaryazim.data.local.entity.WallStreet
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

/*
 Created by Fatih Kurcenli on 12/7/2021
*/

@HiltViewModel
class HomeViewModel @Inject constructor(val homeRepo: HomeRepository) : ViewModel() {

    private val _wallStreetItem = MutableLiveData<List<WallStreet>>()
    val wallStreetItem: LiveData<List<WallStreet>> = _wallStreetItem

    private fun getDataFromDatabase() = viewModelScope.launch(Dispatchers.IO) {
        homeRepo.getAllWallStreet().collect {
            _wallStreetItem.postValue(it)
        }
    }

    init {
        getDataFromDatabase()
    }

    fun setSomeData() = viewModelScope.launch(Dispatchers.IO) {
        for (i in 0..10) {
            homeRepo.insertWallWrite(
                WallStreet(
                    writer = "Fatih",
                    wallStreet = "Hello this is test"
                )
            )
        }
    }

}