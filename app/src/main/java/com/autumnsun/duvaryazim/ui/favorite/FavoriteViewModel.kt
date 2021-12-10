package com.autumnsun.duvaryazim.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.autumnsun.duvaryazim.data.local.WallStreetDao
import com.autumnsun.duvaryazim.data.local.entity.WallStreet
import com.autumnsun.duvaryazim.ui.home.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

/*
 Created by Fatih Kurcenli on 12/10/2021
*/

@HiltViewModel
class FavoriteViewModel @Inject constructor(val wallStreetDao: WallStreetDao) : ViewModel() {

    val homeRepo = HomeRepository(wallStreetDao)

    private val _wallStreetItem = MutableLiveData<List<WallStreet>>()
    val wallStreetItem: LiveData<List<WallStreet>> = _wallStreetItem

    init {
        getFavoriteList()
    }

    private fun getFavoriteList() = viewModelScope.launch(Dispatchers.IO) {
        homeRepo.getFavoriteStreet().collect {
            _wallStreetItem.postValue(it)
        }
    }

    fun deleteWallStreetEntity(wallStreet: WallStreet) = viewModelScope.launch(Dispatchers.IO) {
        homeRepo.deleteEntity(wallStreet)
    }

    fun likedWallStreetEntity(wallStreet: WallStreet) = viewModelScope.launch(Dispatchers.IO) {
        val localEntity = homeRepo.getEntityById(wallStreet.id)
        val wallStreetEntityUpdated = if (localEntity.isLiked) {
            localEntity.copy(isLiked = false)
        } else {
            localEntity.copy(isLiked = true)
        }
        homeRepo.updateWallWrite(wallStreetEntityUpdated)
    }


}