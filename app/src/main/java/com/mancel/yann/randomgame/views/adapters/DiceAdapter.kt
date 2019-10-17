package com.mancel.yann.randomgame.views.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.mancel.yann.randomgame.models.Dice

/**
 * Created by Yann MANCEL on 08/10/2019.
 * Name of the project: RandomGame
 * Name of the package: com.mancel.yann.randomgame.views.adapters
 *
 * A [RecyclerView.Adapter] subclass.
 */
class DiceAdapter : RecyclerView.Adapter<DiceViewHolder>(){ // DiceAdapter(private val mCallback: DiceAdapter.DiceAdapterListener)

    // INTERFACES ----------------------------------------------------------------------------------

    interface DiceAdapterListener {
        /**
         * Returns the [Dice] at the i position
         * @param position an [Int] that corresponds to the i position of the [List] of [Dice]
         */
        fun onClickDeleteButton(position: Int)
    }

    // FIELDS --------------------------------------------------------------------------------------

    private var mDices: List<Dice>

    // CONSTRUCTORS --------------------------------------------------------------------------------

    init {
        this.mDices = mutableListOf()
    }

    // METHODS -------------------------------------------------------------------------------------

    // -- ADAPTER --

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): DiceViewHolder {
        // Creates a Context to the LayoutInflater
        val context = parent.context
        val inflater = LayoutInflater.from(context)

        // Creates the View thanks to the inflater
        val view = inflater.inflate(DiceViewHolder.getItemLayout(), parent, false)

        return DiceViewHolder(view)
    }

    override fun onBindViewHolder(diceViewHolder: DiceViewHolder, position: Int) {
        diceViewHolder.updateItem(this.mDices[position])
    }

    override fun getItemCount(): Int = this.mDices.size

    // -- DICES --

    /**
     * Returns the [Dice] at the i position
     * @param position an [Int] that corresponds to the i position of the [List] of [Dice]
     * @return a [Dice] at the i position of the [List] of [Dice]
     */
    fun getDice(position: Int) = this.mDices[position]

    // -- DATA --

    /**
     * Updates the [List] of [Dice] and displays it
     * @param newDices a [List] of [Dice] that corresponds to the new data
     */
    fun updateData(newDices: List<Dice>) {
        this.mDices = newDices

        // Refreshes the RecyclerView
        notifyDataSetChanged()
    }
}