package com.mancel.yann.randomgame.views.activities

import android.support.v7.widget.Toolbar
import com.mancel.yann.randomgame.R
import com.mancel.yann.randomgame.views.bases.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by Yann MANCEL on 03/10/2019.
 * Name of the project: RandomGame
 * Name of the package: com.mancel.yann.randomgame.views.activities
 *
 * A [BaseActivity] subclass.
 */
class RandomDrawActivity : BaseActivity() {

    // FIELDS --------------------------------------------------------------------------------------

    // METHODS -------------------------------------------------------------------------------------

    // -- BASE ACTIVITY --

    override fun getActivityLayout(): Int = R.layout.activity_random_draw

    override fun getToolBar(): Toolbar = this.mToolbar

    override fun configureFields() {}

    override fun configureDesign() {
        this.addUpButtonOfToolBar()
    }
}
