package com.mancel.yann.randomgame.presenters

import com.mancel.yann.randomgame.models.Dice
import kotlin.random.Random

/**
 * Created by Yann MANCEL on 04/11/2019.
 * Name of the project: RandomGame
 * Name of the package: com.mancel.yann.randomgame.presenters
 *
 * A class which implements [Presenter]
 */
class PresenterImpl : Presenter {

    // METHODS -------------------------------------------------------------------------------------

    override fun getRandomNumber(): Int = Random.Default.nextInt(5) + 1

    override fun getRandomDices(size: Int): List<Dice> {
        val dices = mutableListOf<Dice>()

        for (i in 1..size) {
            dices.add(Dice(i, this.getRandomNumber()))
        }

        return dices
    }
}