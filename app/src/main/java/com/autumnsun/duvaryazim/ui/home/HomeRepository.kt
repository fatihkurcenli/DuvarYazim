package com.autumnsun.duvaryazim.ui.home

import com.autumnsun.duvaryazim.data.local.WallStreetDao
import com.autumnsun.duvaryazim.data.local.entity.WallStreet
import javax.inject.Inject

/*
 Created by Fatih Kurcenli on 12/7/2021
*/

class HomeRepository @Inject constructor(val wallStreetDao: WallStreetDao) : HomeRepo {
    override fun getAllWallStreet(): List<WallStreet> {
        return emptyList()
    }



    override suspend fun insertWallWrite(wallWriter: WallStreet) {
        wallStreetDao.insert(wallWriter)
    }


}