package com.jaino.petner.data.dto

import com.jaino.petner.domain.model.Schedule

data class ScheduleDto(
    val time: String,
    val count: Int
){
    constructor(): this(
        "",
        0
    )
    fun toSchedule(): Schedule = Schedule(
        time = time,
        count = count
    )
}