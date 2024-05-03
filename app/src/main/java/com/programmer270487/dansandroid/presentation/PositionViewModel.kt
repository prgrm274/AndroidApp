package com.programmer270487.dansandroid.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import com.programmer270487.dansandroid.data.local.PositionEntity
import com.programmer270487.dansandroid.data.mappers.toPosition
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class PositionViewModel @Inject constructor(
    pager: Pager<Int, PositionEntity>
): ViewModel() {
    val pagingFlow = pager
        .flow
        .map { pagingData ->
            pagingData.map {
                it.toPosition()
            }
        }
        .cachedIn(viewModelScope)
}