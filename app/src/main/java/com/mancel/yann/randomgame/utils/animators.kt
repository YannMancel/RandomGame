package com.mancel.yann.randomgame.utils

import android.animation.ObjectAnimator
import android.util.Property
import android.view.View

/**
 * Created by Yann MANCEL on 26/10/2019.
 * Name of the project: RandomGame
 * Name of the package: com.mancel.yann.randomgame.utils
 */

// -- ANIMATOR --

    /**
     * Returns a [ObjectAnimator] which allows to animate
     * @param view      the [View] which will be animated
     * @param property  the [Property<View,Float>] that is the type of animation
     * @param alphaIn   the [Float] that contains the initial value
     * @param alphaOut  the [Float] that contains the final value
     * @param time  the [Long] that contains the duration value
     * @return the [ObjectAnimator] which allows to animate
     */
    fun createObjectAnimator(view: View,
                             property: Property<View, Float>,
                             valueIn: Float,
                             valueOut: Float,
                             time: Long): ObjectAnimator {
        return ObjectAnimator.ofFloat(view, property, valueIn, valueOut)
                             .setDuration(time)
    }
 