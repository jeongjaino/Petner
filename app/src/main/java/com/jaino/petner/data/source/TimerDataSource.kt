package com.jaino.petner.data.source

interface TimerDataSource {
    suspend fun setFeedTime(time: String): Result<Unit>
}