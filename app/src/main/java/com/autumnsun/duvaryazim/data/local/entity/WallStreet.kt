package com.autumnsun.duvaryazim.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/*
 Created by Fatih Kurcenli on 12/7/2021
*/

@Entity
data class WallStreet(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val writer: String,
    val wallStreet: String,
    val timestamp: Long = System.currentTimeMillis(),
    val isLiked: Boolean = false
)