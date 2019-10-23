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

const val TRANSLATION_Y = "translationY"

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

    private fun configureListenerOfFABs() {
        // FAB 0
        this.mFab0.setOnClickListener(this)

        // FAB 1
        this.mFab1.setOnClickListener(this)
        // this.mFab1.hide()

        // FAB 2
        this.mFab2.setOnClickListener(this)
        // this.mFab2.hide()

        // FAB 3
        this.mFab3.setOnClickListener(this)
        // this.mFab3.hide()

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

    private fun configureExpandableFABs() {
        Toast.makeText(this, "Expanded", Toast.LENGTH_SHORT).show()

        // FAB 0
        this.mFab0.apply {
            setImageResource(R.drawable.animated_add)
            (drawable as Animatable).start()
        }

        // FAB 1
        //this.mFab1.show()
        this.mFab1.translationY = - this.mOffset1

        // FAB 2
        //this.mFab2.show()
        this.mFab2.translationY = - this.mOffset2

        // FAB 3
        //this.mFab3.show()
        this.mFab3.translationY = - this.mOffset3

//        val animatorSet = AnimatorSet().apply {
//            playTogether(createExpandAnimator(mFab1, mOffset1),
//                         createExpandAnimator(mFab2, mOffset2),
//                         createExpandAnimator(mFab3, mOffset3))
//            start()
//        }
    }

    private fun configureCollapseFABs() {
        Toast.makeText(this, "Collapsed", Toast.LENGTH_SHORT).show()

        // FAB 0
        this.mFab0.apply {
            setImageResource(R.drawable.animated_remove)
            (drawable as Animatable).start()
        }

        // FAB 1
        //this.mFab1.hide()
        this.mFab1.translationY = this.mOffset1

        // FAB 2
        //this.mFab2.hide()
        this.mFab2.translationY = this.mOffset2

        // FAB 3
        //this.mFab3.hide()
        this.mFab3.translationY = this.mOffset3

//        val animatorSet = AnimatorSet().apply {
//            playTogether(createCollapseAnimator(mFab1, mOffset1),
//                         createCollapseAnimator(mFab2, mOffset2),
//                         createCollapseAnimator(mFab3, mOffset3))
//            start()
//        }
    }

    // -- ANIMATOR --

//    private fun createCollapseAnimator(view: View, offset: Float) = ObjectAnimator.ofFloat(view, TRANSLATION_Y, 0,offset)
//                                                                                  .duration(resources.getInteger(R.integer.duration_animation))
//
//    private fun createExpandAnimator(view: View, offset: Float) = ObjectAnimator.ofFloat(view, TRANSLATION_Y, offset, 0)
//                                                                                .duration(resources.getInteger(R.integer.duration_animation))
}
