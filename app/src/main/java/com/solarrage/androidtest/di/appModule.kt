package com.solarrage.androidtest.di

import com.solarrage.androidtest.data.mapper.ChannelMapper
import com.solarrage.androidtest.data.repository.ChannelRepositoryImpl
import com.solarrage.androidtest.data.source.ChannelLocalDataSource
import com.solarrage.androidtest.data.source.ChannelLocalDataSourceImpl
import com.solarrage.androidtest.domain.repository.ChannelRepository
import com.solarrage.androidtest.domain.usecase.SearchChannelsUseCase
import com.solarrage.androidtest.presentation.viewmodel.ChannelsViewModel
import com.solarrage.androidtest.utils.DefaultDispatchersProvider
import com.solarrage.androidtest.utils.DispatchersProvider
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<ChannelLocalDataSource> { ChannelLocalDataSourceImpl(get()) }

    single { ChannelMapper() }

    single<ChannelRepository> {
        ChannelRepositoryImpl(
            localDataSource = get(),
            mapper = get()
        )
    }

    single { SearchChannelsUseCase(get()) }

    viewModel { ChannelsViewModel(get(), get()) }

    single<DispatchersProvider> { DefaultDispatchersProvider() }
}