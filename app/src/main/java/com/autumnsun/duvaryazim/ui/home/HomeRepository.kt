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

    override suspend fun deleteEntity(wallStreet: WallStreet) {
        wallStreetDao.delete(wallStreet)
    }

}