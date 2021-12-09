package com.autumnsun.duvaryazim.ui.addwallstreet

import com.autumnsun.duvaryazim.data.local.entity.WallStreet

/*
 Created by Fatih Kurcenli on 12/8/2021
*/

interface AddWallStreetRepo {
    suspend fun insertWallWrite(wallWriter: WallStreet)
    suspend fun updateWallWrite(wallWriter: WallStreet)
    suspend fun getEntityById(id: String): WallStreet
}