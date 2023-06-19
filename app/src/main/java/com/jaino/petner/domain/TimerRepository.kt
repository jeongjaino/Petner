package com.jaino.petner.domain

import com.jaino.petner.domain.model.Schedule

interface TimerRepository {
    suspend fun setFeedTime(time : String, count: Int): Result<Unit>

    suspend fun getFeedTime(): Result<List<Schedule>>
}