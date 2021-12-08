package com.autumnsun.duvaryazim.ui.addwallstreet

import com.autumnsun.duvaryazim.data.local.WallStreetDao
import com.autumnsun.duvaryazim.data.local.entity.WallStreet
import javax.inject.Inject

/*
 Created by Fatih Kurcenli on 12/8/2021
*/

class AddWallStreetRepository @Inject
constructor(val wallStreetDao: WallStreetDao) : AddWallStreetRepo {
    override suspend fun insertWallWrite(wallWriter: WallStreet) {
        wallStreetDao.insert(wallWriter)
    }
}