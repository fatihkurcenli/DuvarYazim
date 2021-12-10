package com.autumnsun.duvaryazim.ui.home

import com.autumnsun.duvaryazim.data.local.entity.WallStreet
import kotlinx.coroutines.flow.Flow

/*
 Created by Fatih Kurcenli on 12/7/2021
*/

interface HomeRepo {
    fun getAllWallStreet(): Flow<List<WallStreet>>
    suspend fun deleteEntity(wallStreet: WallStreet)
    suspend fun updateWallWrite(wallWriter: WallStreet)
    suspend fun getEntityById(id: String): WallStreet
    suspend fun getAllList(): List<WallStreet>
    suspend fun insertWallWrite(wallWriter: WallStreet)
    fun getSearchWithWallStreetName(wallStreetName: String): Flow<List<WallStreet>>
    fun getFavoriteStreet(): Flow<List<WallStreet>>
}