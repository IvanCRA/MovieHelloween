package com.example.moviehelloween.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.moviehelloween.Favourite
import com.example.moviehelloween.FavouriteRepository
import com.example.moviehelloween.MovieRepository
import com.example.moviehelloween.R
import com.example.moviehelloween.adapter.FavouriteAdapter
import com.example.moviehelloween.adapter.HomeAdapter
import com.example.moviehelloween.databinding.FragmentFavouritesBinding
import com.example.moviehelloween.databinding.FragmentHomeBinding
import com.example.moviehelloween.viewmodel.FavouriteViewModel
import com.example.moviehelloween.viewmodel.HomeViewModel
import com.example.moviehelloween.viewmodelfactory.FavouriteViewModelFactory
import com.example.moviehelloween.viewmodelfactory.HomeViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class FavouritesFragment : Fragment() {

    private var _binding: FragmentFavouritesBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: FavouriteViewModel
    private lateinit var adapter: FavouriteAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentFavouritesBinding.inflate(inflater, container, false)
        val view = binding.root

        val repository = FavouriteRepository(requireContext())
        viewModel = ViewModelProvider(this, FavouriteViewModelFactory(repository)).get(FavouriteViewModel::class.java)

        adapter = FavouriteAdapter(requireContext(), mutableListOf())
        binding.listFavourite.adapter = adapter

        observeFavourites()

        return view
    }

    private fun observeFavourites() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.favourite.collectLatest { favourites ->
                adapter.updateFavourites(favourites)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}