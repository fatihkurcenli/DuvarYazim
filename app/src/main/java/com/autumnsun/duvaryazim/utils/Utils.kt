package com.autumnsun.duvaryazim.utils

import java.text.SimpleDateFormat
import java.util.*


/*
 Created by Fatih Kurcenli on 12/10/2021
*/

fun getDate(timeLongValue: Long): String {
    val sdf = SimpleDateFormat("dd/MM/yyyy kk:mm", Locale.getDefault())
    val netDate = Date(timeLongValue)
    return sdf.format(netDate)
}

fun <T> MutableList<T>.swap(index1: Int, index2: Int) {
    val tmp = this[index1] // 'this' corresponds to the list
    this[index1] = this[index2]
    this[index2] = tmp
}