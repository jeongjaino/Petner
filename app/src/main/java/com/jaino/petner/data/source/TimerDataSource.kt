package com.jaino.petner.data.source

import com.jaino.petner.data.dto.ScheduleDto
import com.jaino.petner.data.dto.WeightDto

interface TimerDataSource {
    suspend fun setFeedTime(schedule: ScheduleDto): Result<Unit>

    suspend fun getFeedTime(): Result<List<ScheduleDto>>

    suspend fun getWeight(): Result<WeightDto>

    suspend fun setRunPump(): Result<Unit>
}