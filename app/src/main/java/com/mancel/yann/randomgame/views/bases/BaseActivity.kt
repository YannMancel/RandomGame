package com.mancel.yann.randomgame.views.bases

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import com.mancel.yann.randomgame.R

/**
 * Created by Yann MANCEL on 03/10/2019.
 * Name of the project: RandomGame
 * Name of the package: com.mancel.yann.randomgame.views.bases
 *
 * A [AppCompatActivity] subclass.
 */
abstract class BaseActivity : AppCompatActivity() {

    // FIELDS --------------------------------------------------------------------------------------

    protected var mDuration: Long = 0L

    // METHODS -------------------------------------------------------------------------------------

    // -- ABSTRACT METHODS --

    protected abstract fun getActivityLayout(): Int
    protected abstract fun getToolBar(): Toolbar?
    protected abstract fun configureFields()
    protected abstract fun configureDesign()

    // -- ACTIVITY --

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Associates the layout file to this class
        setContentView(this.getActivityLayout())

        // Resources
        this.mDuration = resources.getInteger(R.integer.duration_animation).toLong()

        // Configures the fields
        this.configureFields()

        // Configures the design
        this.configureDesign()
    }

    override fun finish() {
        super.finish()

        // Animation [left -> right]
        this.addAnimationFromLeftToRight()
    }

    // -- TOOL BAR --

    override fun onSupportNavigateUp(): Boolean {
        this.finish()
        return true
    }

    /**
     * Adds the Up button of the [Toolbar]
     */
    protected fun addUpButtonOfToolBar() {
        // If ToolBar exists
        if (this.getToolBar() != null) {
            setSupportActionBar(this.getToolBar())
        }
        else {
            return
        }

        // Enables the Up Button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    // -- LAUNCHERS OF ACTIVITY --

    /**
     * Starts another activity
     * @param context the [Context]
     * @param cls the [Class] of the activity to launch
     * @param T a type parameter which extends to [Activity]
     */
    protected fun <T: Activity> startAnotherActivity(context: Context, cls: Class<T>) {
        val intent = Intent(context, cls)
        startActivity(intent)

        // Animation [right -> left]
        this.addAnimationFromRightToLeft()
    }

    // -- ANIMATIONS --

    /**
     * Adds animation [left -> right]
     */
    private fun addAnimationFromRightToLeft() = overridePendingTransition(R.anim.slide_in_right,
                                                                          R.anim.slide_out_left)

    /**
     * Adds animation [right -> left]
     */
    private fun addAnimationFromLeftToRight() = overridePendingTransition(R.anim.slide_in_left,
                                                                          R.anim.slide_out_right)
}
