package com.jaino.petner.presentation.widget

import android.animation.Animator
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
        initAnim()
    }

    private fun initAnim(){
        binding.animationView.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(p0: Animator) { }

            override fun onAnimationEnd(p0: Animator) {
                dismiss()
            }

            override fun onAnimationCancel(p0: Animator) { }

            override fun onAnimationRepeat(p0: Animator) { }
        })
    }
}