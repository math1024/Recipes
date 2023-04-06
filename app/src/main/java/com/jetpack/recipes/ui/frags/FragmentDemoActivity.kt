package com.jetpack.recipes.ui.frags

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.jetpack.recipes.R

class FragmentDemoActivity : AppCompatActivity(R.layout.activity_fragment_demo){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            val bundle = bundleOf("some_int" to 1)
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<FragmentDemo>(R.id.fragment_container_view, FragmentDemo::class.simpleName, bundle)
            }
        }
    }
}