package com.mancel.yann.randomgame.views.activities

import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.Button
import com.mancel.yann.randomgame.R
import com.mancel.yann.randomgame.views.bases.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by Yann MANCEL on 02/10/2019.
 * Name of the project: RandomGame
 * Name of the package: com.mancel.yann.randomgame.views.activities
 *
 * A [BaseActivity] subclass which implements [View.OnClickListener].
 */
class MainActivity : BaseActivity(), View.OnClickListener {

    // FIELDS --------------------------------------------------------------------------------------

    private lateinit var mDiceButton: Button
    private lateinit var mRandomDrawButton: Button

    // METHODS -------------------------------------------------------------------------------------

    // -- BASE ACTIVITY --

    override fun getActivityLayout(): Int = R.layout.activity_main

    override fun getToolBar(): Toolbar = this.mToolbar

    override fun configureFields() {
        this.mDiceButton = this.activity_main_Button_dice
        this.mRandomDrawButton = this.activity_main_Button_random_draw
    }

    override fun configureDesign() = this.configureListenerOfButton()

    // -- ACTIONS [View.OnClickListener INTERFACE] --

    override fun onClick(v: View?) {
        if (v != null) {
            when(v.id){
                // Starts DiceActivity class
                R.id.activity_main_Button_dice -> this.startAnotherActivity(this, DiceActivity::class.java)

                // Starts RandomDrawActivity class
                R.id.activity_main_Button_random_draw -> this.startAnotherActivity(this, RandomDrawActivity::class.java)

                // Error
                else -> throw Exception("Impossible action: None of buttons is selected.")
            }
        }
    }

    // -- LISTENER OF BUTTONS --

    /**
     * Configures the listener of buttons
     */
    private fun configureListenerOfButton() {
        this.mDiceButton.setOnClickListener(this)
        this.mRandomDrawButton.setOnClickListener(this)
    }
}
