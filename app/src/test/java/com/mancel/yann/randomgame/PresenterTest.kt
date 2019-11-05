package com.mancel.yann.randomgame

import com.mancel.yann.randomgame.presenters.Presenter
import com.mancel.yann.randomgame.presenters.PresenterImpl
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Test

/**
 * Created by Yann MANCEL on 04/11/2019.
 * Name of the project: RandomGame
 * Name of the package: com.mancel.yann.randomgame
 */
class PresenterTest {

    // FIELDS --------------------------------------------------------------------------------------

    private lateinit var mPresenter: Presenter

    // METHODS -------------------------------------------------------------------------------------

    @Before
    fun setUp() {
        this.mPresenter = PresenterImpl()
    }

    @Test
    fun getRandomNumber_ShouldBeSuccess() {
        var randomValue: Int

        for (i in 1..50) {
            randomValue = this.mPresenter.getRandomNumber()

            // TEST: The result of each dice is in range [1 - 6]
            assertTrue( (randomValue >= 1) && (randomValue <= 6) )
        }
    }

    @Test
    fun getRandomDices_shouldBeSuccess() {
        val size = 2
        val dices = this.mPresenter.getRandomDices(size)

        // TEST: Size of List
        assertEquals(size, dices.size)

        // TEST: The result of each dice is in range [1 - 6]
        dices.forEach{assertTrue( (it.result >= 1) && (it.result <= 6) )}
    }
}