package com.mancel.yann.randomgame.views.activities

import android.animation.AnimatorSet
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
import com.mancel.yann.randomgame.utils.createObjectAnimator
import com.mancel.yann.randomgame.views.adapters.DiceAdapter
import com.mancel.yann.randomgame.views.bases.BaseActivity
import kotlinx.android.synthetic.main.activity_dice.*
import kotlinx.android.synthetic.main.activity_main.mToolbar
import kotlinx.android.synthetic.main.layout_linear_fabs.*

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
    private var mOffsetXY1 = FloatArray(2)
    private var mOffsetXY2 = FloatArray(2)
    private var mOffsetXY3 = FloatArray(2)

    // METHODS -------------------------------------------------------------------------------------

    // -- BASE ACTIVITY --

    override fun getActivityLayout(): Int = R.layout.activity_dice

    override fun getToolBar(): Toolbar = this.mToolbar

    override fun configureFields() {
        this.mLayoutContainer = this.layout_linear_fabs_constraint_layout
        this.mFab0 = this.layout_linear_fabs_fab_0
        this.mFab1 = this.layout_linear_fabs_fab_1
        this.mFab2 = this.layout_linear_fabs_fab_2
        this.mFab3 = this.layout_linear_fabs_fab_3
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
                R.id.layout_linear_fabs_fab_0 -> {
                    this.isExpanded = !this.isExpanded
                    if (isExpanded) this.configureExpandableFABs() else this.configureCollapseFABs()
                }

                // FAB 1: Displaying in Expandable mode
                R.id.layout_linear_fabs_fab_1 -> Toast.makeText(this, "FAB 1", Toast.LENGTH_SHORT).show()

                // FAB 2: Displaying in Expandable mode
                R.id.layout_linear_fabs_fab_2 -> Toast.makeText(this, "FAB 2", Toast.LENGTH_SHORT).show()

                // FAB 3: Displaying in Expandable mode
                R.id.layout_linear_fabs_fab_3 -> Toast.makeText(this, "FAB 3", Toast.LENGTH_SHORT).show()

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
        for (i in 1..6) dices.add(Dice(i,i))

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
                    mOffsetXY1[0] = (mFab0.x + mFab0.width/2.0F) - (mFab1.x + mFab1.width/2.0F)
                    mOffsetXY1[1] = (mFab0.y + mFab0.height/2.0F) - (mFab1.y + mFab1.height/2.0F)

                    mFab1.translationX = mOffsetXY1[0]
                    mFab1.translationY = mOffsetXY1[1]

                    // FAB 2
                    mOffsetXY2[0] = (mFab0.x + mFab0.width/2.0F) - (mFab2.x + mFab2.width/2.0F)
                    mOffsetXY2[1] = (mFab0.y + mFab0.height/2.0F) - (mFab2.y + mFab2.height/2.0F)

                    mFab2.translationX = mOffsetXY2[0]
                    mFab2.translationY = mOffsetXY2[1]

                    // FAB 3
                    mOffsetXY3[0] = (mFab0.x + mFab0.width/2.0F) - (mFab3.x + mFab3.width/2.0F)
                    mOffsetXY3[1] = (mFab0.y + mFab0.height/2.0F) - (mFab3.y + mFab3.height/2.0F)

                    mFab3.translationX = mOffsetXY3[0]
                    mFab3.translationY = mOffsetXY3[1]

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
        // FAB 0
        this.mFab0.apply {
            setImageResource(R.drawable.animated_add)
            (drawable as Animatable).start()
        }

        // ANIMATOR SET
        AnimatorSet().apply {
            playTogether(
                createObjectAnimator(mFab1, View.TRANSLATION_X, mOffsetXY1[0], 0F, mDuration),
                createObjectAnimator(mFab1, View.TRANSLATION_Y, mOffsetXY1[1], 0F, mDuration),
                createObjectAnimator(mFab1, View.ALPHA, 0F, 1F, 1500),

                createObjectAnimator(mFab2, View.TRANSLATION_X, mOffsetXY2[0], 0F, mDuration),
                createObjectAnimator(mFab2, View.TRANSLATION_Y, mOffsetXY2[1], 0F, mDuration),
                createObjectAnimator(mFab2, View.ALPHA, 0F, 1F, 1500),

                createObjectAnimator(mFab3, View.TRANSLATION_X, mOffsetXY3[0], 0F, mDuration),
                createObjectAnimator(mFab3, View.TRANSLATION_Y, mOffsetXY3[1], 0F, mDuration),
                createObjectAnimator(mFab3, View.ALPHA, 0F, 1F, 1500)
            )
            start()
        }
    }

    /**
     * Configures the behavior of [FloatingActionButton] when it is collapsed
     */
    private fun configureCollapseFABs() {
        // FAB 0
        this.mFab0.apply {
            setImageResource(R.drawable.animated_remove)
            (drawable as Animatable).start()
        }

        // ANIMATOR SET
        AnimatorSet().apply {
            playTogether(createObjectAnimator(mFab1, View.TRANSLATION_X, 0F, mOffsetXY1[0], mDuration),
                createObjectAnimator(mFab1, View.TRANSLATION_Y, 0F, mOffsetXY1[1], mDuration),
                createObjectAnimator(mFab1, View.ALPHA, 1F, 0F, 800),

                createObjectAnimator(mFab2, View.TRANSLATION_X, 0F, mOffsetXY2[0], mDuration),
                createObjectAnimator(mFab2, View.TRANSLATION_Y, 0F, mOffsetXY2[1], mDuration),
                createObjectAnimator(mFab2, View.ALPHA, 1F, 0F, 800),

                createObjectAnimator(mFab3, View.TRANSLATION_X, 0F, mOffsetXY3[0], mDuration),
                createObjectAnimator(mFab3, View.TRANSLATION_Y, 0F, mOffsetXY3[1], mDuration),
                createObjectAnimator(mFab3, View.ALPHA, 1F, 0F, 800))
            start()
        }
    }
}
