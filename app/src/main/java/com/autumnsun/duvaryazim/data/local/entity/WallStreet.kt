package com.autumnsun.duvaryazim.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*

/*
 Created by Fatih Kurcenli on 12/7/2021
*/

@Entity
data class WallStreet(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    val writer: String,
    val wallStreet: String,
    val imageUrl: String? = null,
    val imageTypeConverter: String? = null,
    val timestamp: Long = System.currentTimeMillis(),
    val isLiked: Boolean = false
) : Serializable