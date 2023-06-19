package com.jaino.petner.data.dto

import com.jaino.petner.domain.model.Weight

data class WeightDto(
    val water: Int
){
    constructor() : this(
        water = 0
    )

    fun toWeight(): Weight = Weight(
        water = water
    )
}