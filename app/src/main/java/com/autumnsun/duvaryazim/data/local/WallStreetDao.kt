package com.autumnsun.duvaryazim.data.local

import androidx.room.*
import com.autumnsun.duvaryazim.data.local.entity.WallStreet
import kotlinx.coroutines.flow.Flow

/*
 Created by Fatih Kurcenli on 12/7/2021
*/

@Dao
interface WallStreetDao {

    @Query("SELECT * FROM WALLSTREET ORDER BY timestamp")
    fun getAllWallStreet(): Flow<List<WallStreet>>

    @Query("SELECT * FROM WALLSTREET WHERE id=:id")
    fun getEntityById(id: String): WallStreet

    @Query("SELECT * FROM WALLSTREET WHERE wallStreet LIKE :searchQuery")
    fun searchEntityFromDatabase(searchQuery: String): Flow<List<WallStreet>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(wallStreetWrite: WallStreet)

    @Update
    suspend fun updateItem(wallStreetWrite: WallStreet)

    @Delete
    suspend fun delete(wallStreetWrite: WallStreet)

}