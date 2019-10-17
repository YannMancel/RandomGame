package com.mancel.yann.randomgame.views.bases

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by Yann MANCEL on 03/10/2019.
 * Name of the project: RandomGame
 * Name of the package: com.mancel.yann.randomgame.views.bases
 *
 * A [Fragment] subclass.
 */
abstract class BaseFragment : Fragment() {

    // METHODS -------------------------------------------------------------------------------------

    // -- ABSTRACT METHODS --

    protected abstract fun getFragmentLayout(): Int
    protected abstract fun configureDesign()

    // -- FRAGMENT --

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(this.getFragmentLayout(), container, false)

        // Configures the design
        this.configureDesign()

        return view
    }
}
