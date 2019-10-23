package com.mancel.yann.randomgame.views.activities

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.graphics.drawable.Animatable
import android.support.constraint.ConstraintLayout
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.View
import android.view.ViewTreeObserver
import android.widget.Toast
import com.mancel.yann.randomgame.R
import com.mancel.yann.randomgame.models.Dice
import com.mancel.yann.randomgame.views.adapters.DiceAdapter
import com.mancel.yann.randomgame.views.bases.BaseActivity
import kotlinx.android.synthetic.main.activity_dice.*
import kotlinx.android.synthetic.main.activity_main.mToolbar
import kotlinx.android.synthetic.main.layout_fabs.*

/**
 * Created by Yann MANCEL on 02/10/2019.
 * Name of the project: RandomGame
 * Name of the package: com.mancel.yann.randomgame.views.activities
 *
 * A [BaseActivity] subclass which implements [View.OnClickListener].
 */
class DiceActivity : BaseActivity(), View.OnClickListener {

    // FIELDS --------------------------------------------------------------------------------------

    private lateinit var mLayoutContainer: ConstraintLayout
    private lateinit var mFab0: FloatingActionButton
    private lateinit var mFab1: FloatingActionButton
    private lateinit var mFab2: FloatingActionButton
    private lateinit var mFab3: FloatingActionButton
    private lateinit var mRecyclerView: RecyclerView

    private lateinit var mAdapter: DiceAdapter
    private var isExpanded = false
    private var mOffset1: Float = 0F
    private var mOffset2: Float = 0F
    private var mOffset3: Float = 0F

    // METHODS -------------------------------------------------------------------------------------

    // -- BASE ACTIVITY --

    override fun getActivityLayout(): Int = R.layout.activity_dice

    override fun getToolBar(): Toolbar = this.mToolbar

    override fun configureFields() {
        this.mLayoutContainer = this.layout_fabs_constraint_layout
        this.mFab0 = this.layout_fabs_fab_0
        this.mFab1 = this.layout_fabs_fab_1
        this.mFab2 = this.layout_fabs_fab_2
        this.mFab3 = this.layout_fabs_fab_3
        this.mRecyclerView = this.activity_dice_RecyclerView
    }

    override fun configureDesign() {
        this.addUpButtonOfToolBar()
        this.configureRecyclerView()
        this.configureListenerOfFABs()
    }

    // -- ACTIONS [View.OnClickListener INTERFACE] --

    override fun onClick(v: View?) {

        if (v != null) {
            when(v.id){
                // FAB 0: Always displayed
                R.id.layout_fabs_fab_0 -> {
                    this.isExpanded = !this.isExpanded
                    if (isExpanded) this.configureExpandableFABs() else this.configureCollapseFABs()
                }

                // FAB 1: Displaying in Expandable mode
                R.id.layout_fabs_fab_1 -> Toast.makeText(this, "FAB 1", Toast.LENGTH_SHORT).show()

                // FAB 2: Displaying in Expandable mode
                R.id.layout_fabs_fab_2 -> Toast.makeText(this, "FAB 2", Toast.LENGTH_SHORT).show()

                // FAB 3: Displaying in Expandable mode
                R.id.layout_fabs_fab_3 -> Toast.makeText(this, "FAB 3", Toast.LENGTH_SHORT).show()

                // Error
                else -> throw Exception("Impossible action: None of buttons is selected.")
            }
        }
    }

    // -- RECYCLER VIEW --

    /**
     * Configures the [RecyclerView]
     */
    private fun configureRecyclerView() {
        // DATA
        val dices: MutableList<Dice> = mutableListOf()
        for (i in 1..5) dices.add(Dice(i,4))

        // ADAPTER
        this.mAdapter = DiceAdapter().apply { updateData(dices) }

        // RECYCLER VIEW
        this.mRecyclerView.adapter = this.mAdapter
        this.mRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    // -- FLOATING ACTION BUTTON --

    /**
     * Configures the set of [FloatingActionButton]
     */
    private fun configureListenerOfFABs() {
        // FABs
        this.mFab0.setOnClickListener(this)
        this.mFab1.setOnClickListener(this)
        this.mFab2.setOnClickListener(this)
        this.mFab3.setOnClickListener(this)

        // CONSTRAINT LAYOUT
        this.mLayoutContainer.viewTreeObserver.addOnPreDrawListener(
            object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    // FAB 1
                    mOffset1 = mFab0.y - mFab1.y
                    mFab1.translationY = mOffset1

                    // FAB 2
                    mOffset2 = mFab0.y - mFab2.y
                    mFab2.translationY = mOffset2

                    // FAB 3
                    mOffset3 = mFab0.y - mFab3.y
                    mFab3.translationY = mOffset3

                    mLayoutContainer.viewTreeObserver.removeOnPreDrawListener(this)

                    return true
                }
            }
        )
    }

    /**
     * Configures the behavior of [FloatingActionButton] when it is expanded
     */
    private fun configureExpandableFABs() {
        Toast.makeText(this, "Expanded", Toast.LENGTH_SHORT).show()

        // FAB 0
        this.mFab0.apply {
            setImageResource(R.drawable.animated_add)
            (drawable as Animatable).start()
        }

        // ANIMATOR SET
        AnimatorSet().apply {
            playTogether(createTranslateYAnimator(mFab1, mOffset1, 0F),
                         createVisibilityAnimator(mFab1, 0F, 1F, 1500),
                         createTranslateYAnimator(mFab2, mOffset2, 0F),
                         createVisibilityAnimator(mFab2, 0F, 1F, 1500),
                         createTranslateYAnimator(mFab3, mOffset3, 0F),
                         createVisibilityAnimator(mFab3, 0F, 1F, 1500))
            start()
        }
    }

    /**
     * Configures the behavior of [FloatingActionButton] when it is collapsed
     */
    private fun configureCollapseFABs() {
        Toast.makeText(this, "Collapsed", Toast.LENGTH_SHORT).show()

        // FAB 0
        this.mFab0.apply {
            setImageResource(R.drawable.animated_remove)
            (drawable as Animatable).start()
        }

        // ANIMATOR SET
        AnimatorSet().apply {
            playTogether(createTranslateYAnimator(mFab1, 0F, mOffset1),
                         createVisibilityAnimator(mFab1, 1F, 0F, 200),
                         createTranslateYAnimator(mFab2, 0F, mOffset2),
                         createVisibilityAnimator(mFab2, 1F, 0F, 200),
                         createTranslateYAnimator(mFab3, 0F, mOffset3),
                         createVisibilityAnimator(mFab3, 1F, 0F, 200))
            start()
        }
    }

    // -- ANIMATOR --

    /**
     * Returns a [ObjectAnimator] which allows to translate in Y
     * @param view      the [View] which will be animated
     * @param valueFrom the [Float] that contains the initial value of translation in Y
     * @param valueTo   the [Float] that contains the final value of translation in Y
     * @return the [ObjectAnimator] which allows to translate in Y
     */
    private fun createTranslateYAnimator(view: View, valueFrom: Float, valueTo: Float): ObjectAnimator {
        return ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, valueFrom,valueTo)
                             .setDuration(resources.getInteger(R.integer.duration_animation).toLong())
    }

    /**
     * Returns a [ObjectAnimator] which allows to animate in alpha
     * @param view      the [View] which will be animated
     * @param alphaIn   the [Float] that contains the initial value of alpha
     * @param alphaOut  the [Float] that contains the final value of alpha
     * @param time  the [Long] that contains the duration value
     * @return the [ObjectAnimator] which allows to animate in alpha
     */
    private fun createVisibilityAnimator(view: View, alphaIn: Float, alphaOut: Float, time: Long): ObjectAnimator {
        return ObjectAnimator.ofFloat(view, View.ALPHA, alphaIn, alphaOut)
                             .setDuration(time)
    }
}
