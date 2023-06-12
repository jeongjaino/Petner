package com.jaino.petner.di

import com.jaino.petner.data.source.TimerDataSource
import com.jaino.petner.data.source.TimerDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun provideTimerDataSource(
        timerDataSourceImpl: TimerDataSourceImpl
    ): TimerDataSource
}
