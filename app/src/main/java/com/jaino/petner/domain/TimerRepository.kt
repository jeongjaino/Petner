package com.jaino.petner.domain

interface TimerRepository {
    suspend fun setFeedTime(time : String): Result<Unit>
}