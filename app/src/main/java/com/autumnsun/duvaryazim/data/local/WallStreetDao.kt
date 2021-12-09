package com.autumnsun.duvaryazim.data.local

import androidx.room.*
import com.autumnsun.duvaryazim.data.local.entity.WallStreet
import kotlinx.coroutines.flow.Flow

/*
 Created by Fatih Kurcenli on 12/7/2021
*/

@Dao
interface WallStreetDao {

    @Query("SELECT * FROM WALLSTREET")
    fun getAllWallStreet(): Flow<List<WallStreet>>

    @Query("SELECT * FROM WALLSTREET WHERE id=:id")
    fun getEntityById(id: String): WallStreet

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(wallStreetWrite: WallStreet)

    @Update
    suspend fun updateItem(wallStreetWrite: WallStreet)

    @Delete
    suspend fun delete(wallStreetWrite: WallStreet)

}