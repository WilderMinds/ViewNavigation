package com.samdev.viewnavdemo.navigator

import android.view.View
import com.samdev.viewnavdemo.ViewNavigation
import java.lang.IllegalStateException


/**
 * Use this class instead of the CustomNavigator class if you want to navigate through
 * views sequentially, viewPager style
 *
 * Init class with list of views the user will navigate through, kind of
 * like a view pager
 *
 * Considered making this class a singleton but we could need multiple Navigator
 * instances for multiple different fragments
 */
class SequentialNavigator(private val views: List<View>) {

    private var currentIndex = 0

    init {
        validate()
    }


    /**
     * Method to emulate navigation
     * @param viewNavigation a view navigation object that contains the current view
     *                       and the view you intend to navigate to.
     * @param forward `true` if navigating forward, `false` if navigating backwards
     */
    private fun navigate(viewNavigation: ViewNavigation, forward: Boolean) {

        // outgoing view
        viewNavigation.from.visibility = View.GONE

        // incoming view
        viewNavigation.to.visibility = View.VISIBLE

        // update currentIndex
        currentIndex = views.indexOf(viewNavigation.to)
    }


    /**
     * Move to next
     */
    fun navigateForward() {
        // check if there a next view to navigate to
        val nextIndex = currentIndex + 1
        if (nextIndex >= views.size) {
            return
        }

        // create viewNav instance
        val currentView = views[currentIndex]
        val nextView = views[nextIndex]
        val viewNavigation = ViewNavigation(from = currentView, to = nextView)

        // navigate
        navigate(viewNavigation, true)
    }


    /**
     * Go back
     */
    fun navigateBackwards() : Boolean {
        val prevIndex = currentIndex - 1
        if (prevIndex < 0) {
            return false
        }

        val currentView = views[currentIndex]
        val prevView = views[prevIndex]

        val viewNavigation = ViewNavigation(from = currentView, to = prevView)
        navigate(viewNavigation, false)

        return true
    }


    /**
     * Ensure we have valid data
     */
    private fun validate() : Boolean {
        if (views == null) {
            throw IllegalStateException("Views have not been set. Please call `initViews(...)` before attempting navigation")
        }

        // check if empty
        if (views.isEmpty()) {
            throw IllegalStateException("Views count 0. Please call `initViews(...)` with a non-empty list")
        }

        return true
    }

}