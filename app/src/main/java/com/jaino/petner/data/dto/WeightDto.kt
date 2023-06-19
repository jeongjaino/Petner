package com.jaino.petner.data.dto

import com.jaino.petner.domain.model.Weight

data class WeightDto(
    val weight: Int
){
    constructor() : this(
        weight = 0
    )

    fun toWeight(): Weight = Weight(
        weight = weight
    )
}