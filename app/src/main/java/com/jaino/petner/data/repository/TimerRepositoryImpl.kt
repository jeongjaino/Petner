package com.jaino.petner.data.repository

import com.jaino.petner.data.dto.ScheduleDto
import com.jaino.petner.data.source.TimerDataSource
import com.jaino.petner.domain.TimerRepository
import javax.inject.Inject

class TimerRepositoryImpl @Inject constructor(
    private val dataSource: TimerDataSource
) : TimerRepository{
    override suspend fun setFeedTime(time : String, count: Int): Result<Unit> =
        dataSource.setFeedTime(ScheduleDto(time, count))
}