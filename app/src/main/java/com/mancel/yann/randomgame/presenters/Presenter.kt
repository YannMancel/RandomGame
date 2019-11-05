package com.mancel.yann.randomgame.presenters

import com.mancel.yann.randomgame.models.Dice

/**
 * Created by Yann MANCEL on 04/11/2019.
 * Name of the project: RandomGame
 * Name of the package: com.mancel.yann.randomgame.presenters
 */
interface Presenter {

    /**
     * Returns an [Int] in random way
     * @return an [Int]
     */
    fun getRandomNumber(): Int

    /**
     * Returns a [List] of [Dice] in random way
     * @return a [List] of [Dice]
     */
    fun getRandomDices(size: Int): List<Dice>
}