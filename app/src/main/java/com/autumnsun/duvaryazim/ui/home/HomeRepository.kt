package com.autumnsun.duvaryazim.ui.home

import com.autumnsun.duvaryazim.data.local.WallStreetDao
import com.autumnsun.duvaryazim.data.local.entity.WallStreet
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/*
 Created by Fatih Kurcenli on 12/7/2021
*/

class HomeRepository @Inject constructor(val wallStreetDao: WallStreetDao) : HomeRepo {

    override fun getAllWallStreet(): Flow<List<WallStreet>> {
        return wallStreetDao.getAllWallStreet()
    }

    override suspend fun insertWallWrite(wallWriter: WallStreet) {
        wallStreetDao.insert(wallWriter)
    }

    override fun getSearchWithWallStreetName(wallStreetName: String): Flow<List<WallStreet>> {
        return wallStreetDao.searchEntityFromDatabase(wallStreetName)
    }

    override fun getFavoriteStreet(): Flow<List<WallStreet>> {
        return wallStreetDao.getAllLikedList()
    }

    override suspend fun getAllList(): List<WallStreet> {
        return wallStreetDao.getAllList()
    }

    override suspend fun deleteEntity(wallStreet: WallStreet) {
        wallStreetDao.delete(wallStreet)
    }

    override suspend fun updateWallWrite(wallWriter: WallStreet) {
        wallStreetDao.updateItem(wallWriter)
    }

    override suspend fun getEntityById(id: String): WallStreet {
        return wallStreetDao.getEntityById(id)
    }
}