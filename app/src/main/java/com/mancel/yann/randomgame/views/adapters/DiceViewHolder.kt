package com.mancel.yann.randomgame.views.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import com.mancel.yann.randomgame.R
import com.mancel.yann.randomgame.models.Dice
import kotlinx.android.synthetic.main.item_dice.view.*

/**
 * Created by Yann MANCEL on 08/10/2019.
 * Name of the project: RandomGame
 * Name of the package: com.mancel.yann.randomgame.views.adapters
 *
 * A [RecyclerView.ViewHolder] subclass.
 */
class DiceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    // FIELDS --------------------------------------------------------------------------------------

    private val mImage = itemView.item_dice_ImageView
    private val mIdText = itemView.item_dice_id
    private val mResultText = itemView.item_dice_result

    // METHODS -------------------------------------------------------------------------------------

    companion object {
        fun getItemLayout() = R.layout.item_dice
    }

    fun updateItem(dice: Dice) {
        // IMAGE
        this.mImage.background = when (dice.result) {
            // Dice value
            1 -> itemView.context.getDrawable(R.drawable.dice1)
            2 -> itemView.context.getDrawable(R.drawable.dice2)
            3 -> itemView.context.getDrawable(R.drawable.dice3)
            4 -> itemView.context.getDrawable(R.drawable.dice4)
            5 -> itemView.context.getDrawable(R.drawable.dice5)
            6 -> itemView.context.getDrawable(R.drawable.dice6)

            // Error
            else -> throw Exception("Impossible action: Dice value is out of boundary")
        }

        // TEXT VIEW
        this.mIdText.text = itemView.context.getString(R.string.dice_id, dice.id)
        this.mResultText.text = itemView.context.getString(R.string.dice_result, dice.result)
    }
}