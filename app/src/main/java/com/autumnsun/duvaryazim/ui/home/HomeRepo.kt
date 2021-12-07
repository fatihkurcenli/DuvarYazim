package com.autumnsun.duvaryazim.ui.home

import com.autumnsun.duvaryazim.data.local.entity.WallStreet


/*
 Created by Fatih Kurcenli on 12/7/2021
*/



interface HomeRepo {
    fun getAllWallStreet(): List<WallStreet>
    suspend fun insertWallWrite(wallWriter: WallStreet)
}