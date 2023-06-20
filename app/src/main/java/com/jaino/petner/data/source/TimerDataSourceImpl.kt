package com.jaino.petner.data.source

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.jaino.petner.data.const.*
import com.jaino.petner.data.dto.ScheduleDto
import com.jaino.petner.data.dto.WeightDto
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
                val data = fireStore.collection(DB_TIMER).add(schedule).await()
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

    override suspend fun getWeight(): Result<WeightDto> =
        withContext(coroutineDispatcher){
            runCatching{
                val data = fireStore.collection(DB_PETNER).document(DOC_WEIGHT)
                    .get().await().toObject(WeightDto::class.java)
                requireNotNull(data)
            }.onFailure {
                it.printStackTrace()
                Result.failure<WeightDto>(it)
            }
        }

    override suspend fun setRunPump(): Result<Unit> =
        withContext(coroutineDispatcher){
            runCatching {
                val data = fireStore.collection(DB_PETNER).document(DOC_SWITCH).set(
                    mapOf("runPump" to true),
                    SetOptions.merge()
                )
            }.onFailure {
                it.printStackTrace()
                Result.failure<Unit>(it)
            }
        }
}