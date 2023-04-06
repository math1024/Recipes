package com.jetpack.recipes.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.jetpack.recipes.databinding.FragmentDashboardBinding
import com.jetpack.recipes.ui.frags.FragmentDemoActivity
import com.jetpack.recipes.ui.frags.FragmentNavDemoActivity

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this)[DashboardViewModel::class.java]

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textDashboard
        dashboardViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        binding.fragsBtn.setOnClickListener(View.OnClickListener {
            startActivity(Intent(context, FragmentNavDemoActivity::class.java))
//            startActivity(Intent(context, FragmentDemoActivity::class.java))
        })
        println("DashboardFragment onCreateView")
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}