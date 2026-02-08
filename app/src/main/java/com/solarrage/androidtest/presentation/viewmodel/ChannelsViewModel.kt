package com.solarrage.androidtest.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.solarrage.androidtest.domain.usecase.SearchChannelsUseCase
import com.solarrage.androidtest.utils.DispatchersProvider

class ChannelsViewModel(
    private val searchUseCase: SearchChannelsUseCase,
    private val dispatchers: DispatchersProvider
) : ViewModel()