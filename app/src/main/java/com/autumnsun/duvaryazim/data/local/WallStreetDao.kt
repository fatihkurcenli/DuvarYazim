package com.autumnsun.duvaryazim.data.local

import androidx.room.Dao
import androidx.room.Query
import com.autumnsun.duvaryazim.data.local.entity.WallStreet
import kotlinx.coroutines.flow.Flow

/*
 Created by Fatih Kurcenli on 12/7/2021
*/

@Dao
interface WallStreetDao {

    @Query("SELECT * FROM WALLSTREET")
    fun getAllWallStreet(): Flow<List<WallStreet>>

}