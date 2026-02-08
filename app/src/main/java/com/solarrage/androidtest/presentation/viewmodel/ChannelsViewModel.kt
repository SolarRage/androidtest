package com.solarrage.androidtest.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solarrage.androidtest.domain.model.Channel
import com.solarrage.androidtest.domain.usecase.SearchChannelsUseCase
import com.solarrage.androidtest.utils.DispatchersProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.withContext

class ChannelsViewModel(
    private val searchUseCase: SearchChannelsUseCase,
    private val dispatchers: DispatchersProvider
) : ViewModel() {

    private val query = MutableStateFlow("")

    val channels: StateFlow<List<Channel>> =
        query
            .debounce(300)
            .distinctUntilChanged()
            .mapLatest { query ->
                withContext(dispatchers.default) {
                    searchUseCase.execute(query)
                }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = emptyList()
            )

    fun onQueryChanged(value: String){
        query.value = value
    }


            }