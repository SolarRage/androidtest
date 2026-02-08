package com.solarrage.androidtest.di

import android.content.res.AssetManager
import com.solarrage.androidtest.data.mapper.ChannelMapper
import com.solarrage.androidtest.data.repository.ChannelRepositoryImpl
import com.solarrage.androidtest.data.source.ChannelLocalDataSource
import com.solarrage.androidtest.data.source.ChannelLocalDataSourceImpl
import com.solarrage.androidtest.domain.repository.ChannelRepository
import com.solarrage.androidtest.domain.usecase.SearchChannelsUseCase
import com.solarrage.androidtest.presentation.viewmodel.ChannelsViewModel
import com.solarrage.androidtest.utils.DefaultDispatchersProvider
import com.solarrage.androidtest.utils.DispatchersProvider
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<AssetManager> {
        androidContext().assets
    }

    single<ChannelLocalDataSource> {
        ChannelLocalDataSourceImpl(
            get()
        )
    }

    single { ChannelMapper() }

    single<ChannelRepository> {
        ChannelRepositoryImpl(
            localDataSource = get(),
            mapper = get()
        )
    }

    single { SearchChannelsUseCase(get()) }

    single<DispatchersProvider> { DefaultDispatchersProvider() }

    viewModel {
        ChannelsViewModel(
            searchUseCase = get(),
            dispatchers = get()
        )
    }
}