package com.autumnsun.duvaryazim.ui.search

import androidx.lifecycle.ViewModel
import com.autumnsun.duvaryazim.data.local.WallStreetDao
import dagger.hilt.android.lifecycle.HiltViewModel

/*
 Created by Fatih Kurcenli on 12/9/2021
*/

@HiltViewModel
class SearchViewModel(val wallStreetDao: WallStreetDao) : ViewModel() {

}