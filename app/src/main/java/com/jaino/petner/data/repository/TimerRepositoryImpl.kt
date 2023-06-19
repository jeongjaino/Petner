package com.jaino.petner.data.repository

import com.jaino.petner.data.dto.ScheduleDto
import com.jaino.petner.data.source.TimerDataSource
import com.jaino.petner.domain.TimerRepository
import com.jaino.petner.domain.model.Schedule
import javax.inject.Inject

class TimerRepositoryImpl @Inject constructor(
    private val dataSource: TimerDataSource
) : TimerRepository{
    override suspend fun setFeedTime(time : String, count: Int): Result<Unit> =
        dataSource.setFeedTime(ScheduleDto(time, count))

    override suspend fun getFeedTime(): Result<List<Schedule>> =
        dataSource.getFeedTime().mapCatching { list -> list.map{ it.toSchedule() } }
}