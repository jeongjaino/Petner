package com.jaino.petner.domain

import com.jaino.petner.domain.model.Schedule
import com.jaino.petner.domain.model.Weight

interface TimerRepository {
    suspend fun setFeedTime(time : String, count: Int): Result<Unit>

    suspend fun getFeedTime(): Result<List<Schedule>>

    suspend fun getWeight(): Result<Weight>

    suspend fun setRunPump(): Result<Unit>
}