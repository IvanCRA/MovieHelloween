package com.example.moviehelloween.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.lifecycleScope
import com.example.moviehelloween.Movie
import com.example.moviehelloween.MovieRepository
import com.example.moviehelloween.adapter.HomeAdapter
import com.example.moviehelloween.databinding.FragmentHomeBinding
import com.example.moviehelloween.viewmodel.HomeViewModel
import com.example.moviehelloween.viewmodelfactory.HomeViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: HomeViewModel
    private lateinit var adapter: HomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        val repository = MovieRepository(requireContext())
        viewModel = ViewModelProvider(this, HomeViewModelFactory(repository)).get(HomeViewModel::class.java)

        adapter = HomeAdapter(requireContext(), mutableListOf())
        binding.listHome.adapter = adapter

        observeMovies()

        return view
    }

    private fun observeMovies() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.movie.collectLatest { movies ->
                adapter.updateMovies(movies)
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}