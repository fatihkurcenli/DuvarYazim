package com.autumnsun.duvaryazim.ui.search

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
 Created by Fatih Kurcenli on 12/9/2021
*/

@HiltViewModel
class SearchViewModel @Inject constructor(val wallStreetDao: WallStreetDao) : ViewModel() {

    val homeRepository = HomeRepository(wallStreetDao)

    private val _wallStreetItem = MutableLiveData<List<WallStreet>>()
    val wallStreetItem: LiveData<List<WallStreet>> = _wallStreetItem


    fun searchEntity(wallName: String) = viewModelScope.launch(Dispatchers.IO) {
        homeRepository.getSearchWithWallStreetName("%$wallName%").collect {
            _wallStreetItem.postValue(it)
        }
    }

    fun deleteWallStreetEntity(wallStreet: WallStreet) = viewModelScope.launch(Dispatchers.IO) {
        homeRepository.deleteEntity(wallStreet)
    }

    fun likedWallStreetEntity(wallStreet: WallStreet) = viewModelScope.launch(Dispatchers.IO) {
        val localEntity = homeRepository.getEntityById(wallStreet.id)
        val wallStreetEntityUpdated = if (localEntity.isLiked) {
            localEntity.copy(isLiked = false)
        } else {
            localEntity.copy(isLiked = true)
        }
       /* val newList: ArrayList<WallStreet> = ArrayList()
        _wallStreetItem.value?.let {
            newList.addAll(it)
        }
        newList.remove(localEntity)
        newList.add(wallStreetEntityUpdated)*/
       // _wallStreetItem.postValue(newList)
        homeRepository.updateWallWrite(wallStreetEntityUpdated)
    }

}