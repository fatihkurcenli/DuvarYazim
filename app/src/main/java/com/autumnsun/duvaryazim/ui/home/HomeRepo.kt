package com.autumnsun.duvaryazim.ui.home

import com.autumnsun.duvaryazim.data.local.entity.WallStreet
import kotlinx.coroutines.flow.Flow

/*
 Created by Fatih Kurcenli on 12/7/2021
*/

interface HomeRepo {
    fun getAllWallStreet(): Flow<List<WallStreet>>
    suspend fun deleteEntity(wallStreet: WallStreet)
}