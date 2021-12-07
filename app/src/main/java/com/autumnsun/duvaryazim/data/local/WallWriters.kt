package com.autumnsun.duvaryazim.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.autumnsun.duvaryazim.data.local.entity.WallStreet
import com.autumnsun.duvaryazim.utils.Constants.DB_VERSION_CODE

/*
 Created by Fatih Kurcenli on 12/7/2021
*/

@Database(
    entities = [WallStreet::class],
    version = DB_VERSION_CODE,
    exportSchema = false
)
abstract class WallStreetDatabase : RoomDatabase() {
    abstract fun getNoteDao(): WallStreetDao
}