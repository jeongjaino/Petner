package com.jaino.petner.data.source

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.jaino.petner.data.const.DB_TIMER
import com.jaino.petner.data.const.DOC_FEED
import com.jaino.petner.data.dto.ScheduleDto
import com.jaino.petner.data.util.await
import com.jaino.petner.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TimerDataSourceImpl @Inject constructor(
    private val fireStore: FirebaseFirestore,
    @IoDispatcher private val coroutineDispatcher: CoroutineDispatcher
): TimerDataSource{
    override suspend fun setFeedTime(schedule: ScheduleDto): Result<Unit> =
        withContext(coroutineDispatcher){
            runCatching {
                fireStore.collection(DB_TIMER).add(schedule).await()
                Unit
            }.onFailure {
                it.printStackTrace()
                Result.failure<Unit>(it)
            }
        }

    override suspend fun getFeedTime(): Result<List<ScheduleDto>> =
        withContext(coroutineDispatcher){
            runCatching {
                fireStore.collection(DB_TIMER).get().await().toObjects(ScheduleDto::class.java)
            }
        }.onFailure {
            it.printStackTrace()
            Result.failure<ScheduleDto>(it)
        }
}