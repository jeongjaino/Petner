package com.jaino.petner.presentation.widget

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.jaino.petner.databinding.DialogProvideWaterBinding


class WaterProvideDialog(
    context: Context
) : Dialog(context) {

    private val binding by lazy{ DialogProvideWaterBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}