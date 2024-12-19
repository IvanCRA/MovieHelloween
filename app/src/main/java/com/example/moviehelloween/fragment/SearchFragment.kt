package com.example.moviehelloween.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.moviehelloween.R
import com.example.moviehelloween.databinding.FragmentProfileBinding
import com.example.moviehelloween.databinding.FragmentSearchBinding
import kotlin.concurrent.fixedRateTimer

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    startResultFragment();
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                }
                return true
            }
        })
    }

    fun startResultFragment() {
        //binding.searchView.query.toString()
        parentFragmentManager.apply {
            arguments = Bundle().apply {
                putString("searchQuery", binding.searchView.query.toString())
            }
        }
        val bundle = Bundle()
        bundle.putString("searchQuery", binding.searchView.query.toString())
        findNavController().navigate(R.id.action_searchFragment_to_resultFragment, bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}