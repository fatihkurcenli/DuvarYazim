package com.autumnsun.duvaryazim.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.autumnsun.duvaryazim.data.local.WallStreetDao
import com.autumnsun.duvaryazim.data.local.entity.WallStreet
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

/*
 Created by Fatih Kurcenli on 12/7/2021
*/

@HiltViewModel
class HomeViewModel @Inject constructor(val wallStreetDao: WallStreetDao) : ViewModel() {

    val homeRepo = HomeRepository(wallStreetDao)

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
        /*val newList: ArrayList<WallStreet> = ArrayList()
        _wallStreetItem.value?.let {
            newList.addAll(it)
        }
        newList.remove(localEntity)
        newList.add(wallStreetEntityUpdated)
        _wallStreetItem.postValue(newList)*/
        homeRepo.updateWallWrite(wallStreetEntityUpdated)
    }

    fun updateList(fromPosition: Int, toPosition: Int) = viewModelScope.launch(Dispatchers.IO) {
        delay(500L)
/*        val allListItem = homeRepo.getAllList()
        val changedModelToPosition = allListItem[toPosition]
        val changedModelFromPosition = allListItem[fromPosition]
        homeRepo.deleteEntity(allListItem[fromPosition])
        homeRepo.deleteEntity(allListItem[toPosition])
        homeRepo.insertWallWrite(changedModelToPosition)
        homeRepo.insertWallWrite(changedModelFromPosition)*/
        val newList: ArrayList<WallStreet> = ArrayList()
        _wallStreetItem.value?.let {
            newList.addAll(it)
        }
        Collections.swap(newList, fromPosition, toPosition);
        _wallStreetItem.postValue(newList)
    }

/*    fun setSomeData() = viewModelScope.launch(Dispatchers.IO) {
        for (i in 0..10) {
            homeRepo.insertWallWrite(
                WallStreet(
                    writer = "Fatih",
                    wallStreet = "Hello this is test"
                )
            )
        }
    }*/

}