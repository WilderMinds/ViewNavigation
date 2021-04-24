package com.samdev.viewnavdemo.navigator

import android.view.View
import com.samdev.viewnavdemo.ViewNavigation
import java.util.*

class CustomNavigator {

    /**
     * Stack to keep track of the number of navigation transactions
     */
    private var navigationStack: Stack<ViewNavigation> = Stack()


    /**
     * Method to emulate navigation
     * @param viewNavigation a view navigation object that contains the current view
     *                       and the view you intend to navigate to.
     */
    private fun navigate(viewNavigation: ViewNavigation) {

        // TODO: 24/04/2021 direction will control the 'direction' of the navigation animation

        // outgoing view
        viewNavigation.from.visibility = View.GONE

        // incoming view
        viewNavigation.to.visibility = View.VISIBLE
    }


    /**
     * @param from the current visible page/viewGroup
     * @param to the page/viewGroup you want to navigate to
     */
    fun navigateViews(from: View, to: View) {
        val viewNavigation = ViewNavigation(from, to)
        navigationStack.push(viewNavigation)
        navigate(viewNavigation)
    }


    /**
     * Notice that the `from` and `to` params are switched when creating
     * the viewNavigation object.
     *
     * We do this when we want to move in the opposite/reverse direction
     */
    /*private fun navigateBackwards(from: View, to: View) {
        val viewNavigation = ViewNavigation(from, to)
        navigate(viewNavigation, false)
    }*/


    /**
     * Similar to the `onBackPressed()` method in activities
     *
     * Notice that the `from` and `to` params are switched when creating
     * the viewNavigation object.
     *
     * We do this when we want to move in the opposite/reverse direction
     *
     * @return `true` if back navigation was successful and `false` if there's nothing else
     *          the user can trigger the default `onBackPressed()` method if false to hand control
     *          back to the fragment/activity
     */
    fun back() : Boolean {
        if (!navigationStack.empty()) {
            val viewNavigation = navigationStack.pop()
            navigateViews(viewNavigation.to, viewNavigation.from)
            return true
        }
        return false
    }

}