package com.jaino.petner.di

import com.google.firebase.firestore.FirebaseFirestore
import com.jaino.petner.data.repository.TimerRepositoryImpl
import com.jaino.petner.data.source.TimerDataSource
import com.jaino.petner.domain.TimerRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Provides
    fun provideFirestore() : FirebaseFirestore = FirebaseFirestore.getInstance()

    @Provides
    fun provideTimerRepository(timerDataSource: TimerDataSource): TimerRepository
            = TimerRepositoryImpl(timerDataSource)
}