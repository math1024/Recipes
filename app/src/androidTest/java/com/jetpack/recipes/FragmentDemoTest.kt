package com.jetpack.recipes

import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jetpack.recipes.ui.frags.FragmentDemo
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FragmentDemoTest {

    @Test
    fun runFragment() {
        // The "fragmentArgs" argument is optional.
        val fragmentArgs = bundleOf("some_int" to 0)
        val scenario = launchFragmentInContainer<FragmentDemo>(fragmentArgs)
        scenario.moveToState(Lifecycle.State.RESUMED)
    }

    @Test
    fun testFragmentNotAttachedToViewDuringLifecycle() {
        val scenario = launchFragmentInContainer<FragmentDemo>()

        scenario.onFragment { fragment ->
            // Check if the fragment is not attached to a view
            assertFalse(fragment.isAdded)

            // Simulate attaching the fragment to a view
            val view = View(fragment.requireContext())
            fragment.onViewCreated(view, null)

            // Check if the fragment is attached to a view
            assertTrue(fragment.isAdded)

            // Simulate detaching the fragment from a view
            fragment.onDestroyView()

            // Check if the fragment is not attached to a view again
            assertFalse(fragment.isAdded)
        }
    }
}