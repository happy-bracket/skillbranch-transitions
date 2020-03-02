package ru.hbracket.skillbranch_transitions

import android.animation.Animator
import android.animation.ValueAnimator
import android.view.ViewGroup
import androidx.transition.Transition
import androidx.transition.TransitionValues
import kotlin.math.abs

class Rotate : Transition() {

    override fun captureEndValues(transitionValues: TransitionValues) {
        captureValues(transitionValues)
    }

    override fun captureStartValues(transitionValues: TransitionValues) {
        captureValues(transitionValues)
    }

    private fun captureValues(transitionValues: TransitionValues) {
        transitionValues.values[Tag] = transitionValues.view.rotation
    }

    override fun createAnimator(
        sceneRoot: ViewGroup,
        startValues: TransitionValues?,
        endValues: TransitionValues?
    ): Animator? {
        return nullable {
            val sv = startValues.coerce()
            val start: Float = sv.values.coerce()[Tag].coerce()
                .typeFilter<Float>().coerce()
            val end: Float = endValues.coerce()
                .values.coerce()[Tag].coerce()
                .typeFilter<Float>().coerce()

            val view = sv.view.coerce()

            if (abs(start - end) <= 0.0001) {
                null
            } else {
                val va = ValueAnimator.ofFloat(start, end)
                va.addUpdateListener { ani ->
                    val currentRotation = ani.animatedValue as Float
                    view.rotation = currentRotation
                }
                va
            }
        }
    }

    private companion object {

        const val Tag = "sb:transition:rotation"

    }

}