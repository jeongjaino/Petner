package com.jaino.petner.data.source

import com.jaino.petner.data.dto.ScheduleDto

interface TimerDataSource {
    suspend fun setFeedTime(schedule: ScheduleDto): Result<Unit>

    suspend fun getFeedTime(): Result<List<ScheduleDto>>
}