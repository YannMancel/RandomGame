package com.mancel.yann.randomgame.views.activities

import android.graphics.drawable.Animatable
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

    private lateinit var mFab0: FloatingActionButton
    private lateinit var mFab1: FloatingActionButton
    private lateinit var mRecyclerView: RecyclerView

    private lateinit var mAdapter: DiceAdapter
    private var isExpanded = false
    private var mOffset1: Float = 0F

    // METHODS -------------------------------------------------------------------------------------

    // -- BASE ACTIVITY --

    override fun getActivityLayout(): Int = R.layout.activity_dice

    override fun getToolBar(): Toolbar = this.mToolbar

    override fun configureFields() {
        this.mFab0 = this.layout_fabs_fab_0
        this.mFab1 = this.layout_fabs_fab_1
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
        this.layout_fabs_fab_0.setOnClickListener(this)

        // FAB 1
        this.layout_fabs_fab_1.setOnClickListener(this)
        this.layout_fabs_fab_1.hide()

//        this.layout_fabs_constraint_layout.viewTreeObserver.addOnPreDrawListener {
//            this.layout_fabs_constraint_layout.viewTreeObserver.removeOnPreDrawListener(this);
////            this.layout_fabs_constraint_layout.viewTreeObserver.removeOnPreDrawListener { this }
//            this.mOffset1 = this.layout_fabs_fab_0.y - this.layout_fabs_fab_1.y
//            this.layout_fabs_fab_1.translationY = this.mOffset1
//
//            true
//        }

//        fabContainer.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
//            @Override
//            public boolean onPreDraw() {
//                fabContainer.getViewTreeObserver().removeOnPreDrawListener(this);
//                offset1 = fab.getY() - fabAction1.getY();
//                fabAction1.setTranslationY(offset1);
//                return true;
//            }
    }

    private fun configureExpandableFABs() {
        Toast.makeText(this, "Expanded", Toast.LENGTH_SHORT).show()

        // FAB 0
        this.mFab0.apply {
            setImageResource(R.drawable.animated_add)
            (drawable as Animatable).start()
        }

        // FAB 1
        this.layout_fabs_fab_1.show()

//        AnimatorSet animatorSet = new AnimatorSet();
//        animatorSet.playTogether(createExpandAnimator(fabAction1, offset1),
//            createExpandAnimator(fabAction2, offset2),
//            createExpandAnimator(fabAction3, offset3));
//        animatorSet.start();
//        animateFab();
    }

    private fun configureCollapseFABs() {
        Toast.makeText(this, "Collapsed", Toast.LENGTH_SHORT).show()

        // FAB 0
        this.mFab0.apply {
            setImageResource(R.drawable.animated_remove)
            (drawable as Animatable).start()
        }

        // FAB 1
        this.layout_fabs_fab_1.hide()

//        AnimatorSet animatorSet = new AnimatorSet();
//        animatorSet.playTogether(createCollapseAnimator(fabAction1, offset1),
//            createCollapseAnimator(fabAction2, offset2),
//            createCollapseAnimator(fabAction3, offset3));
//        animatorSet.start();
//        animateFab();
    }

//    private Animator createCollapseAnimator(View view, float offset) {
//        return ObjectAnimator.ofFloat(view, TRANSLATION_Y, 0, offset)
//            .setDuration(getResources().getInteger(android.R.integer.config_mediumAnimTime));
//    }
//
//    private Animator createExpandAnimator(View view, float offset) {
//        return ObjectAnimator.ofFloat(view, TRANSLATION_Y, offset, 0)
//            .setDuration(getResources().getInteger(android.R.integer.config_mediumAnimTime));
//    }
//
//    private void animateFab() {
//        Drawable drawable = fab.getDrawable();
//        if (drawable instanceof Animatable) {
//            ((Animatable) drawable).start();
//        }
//    }
//
//    public void fabAction1(View view) {
//        Log.d(TAG, "Action 1");
//        Toast.makeText(this, "Go shopping!", Toast.LENGTH_SHORT).show();
//    }
//
//    public void fabAction2(View view) {
//        Log.d(TAG, "Action 2");
//        Toast.makeText(this, "Gimme money!", Toast.LENGTH_SHORT).show();
//    }
//
//    public void fabAction3(View view) {
//        Log.d(TAG, "Action 3");
//        Toast.makeText(this, "Turn it up!", Toast.LENGTH_SHORT).show();
//    }
}
