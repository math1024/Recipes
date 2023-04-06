package com.jetpack.recipes.ui.frags

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.jetpack.recipes.R

class FragmentDemo : Fragment(R.layout.fragment_demo){
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val someInt = requireArguments().getInt("some_int")
        Toast.makeText(context, (someInt).toString(), Toast.LENGTH_SHORT).show()
    }
}