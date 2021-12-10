package com.autumnsun.duvaryazim.data.remote

import com.autumnsun.duvaryazim.data.local.entity.WallStreet


/*
 Created by Fatih Kurcenli on 12/10/2021
*/

object DataFileGetLocal {
    //TODO this list come from local!!
    val localData = listOf(
        WallStreet(writer = "Deneme", wallStreet = "test"),
        WallStreet(writer = "ada", wallStreet = "test"),
        WallStreet(writer = "Denedsadsame", wallStreet = "test"),
        WallStreet(writer = "Denedsadsame", wallStreet = "test"),
        WallStreet(writer = "Deneddasdame", wallStreet = "test"),
        WallStreet(writer = "Denedsame", wallStreet = "test")
    )
}