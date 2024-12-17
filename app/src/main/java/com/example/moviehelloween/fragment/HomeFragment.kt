package com.example.moviehelloween.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.moviehelloween.MovieRepository
import com.example.moviehelloween.adapter.HomeAdapter
import com.example.moviehelloween.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        val movieRepository = MovieRepository(requireContext())
        val movies = movieRepository.loadMovies()
        Log.i("Debag", movies.toString())

        binding.listHome.adapter = HomeAdapter(requireContext(), movies)

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}