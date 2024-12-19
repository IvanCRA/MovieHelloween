package com.example.moviehelloween.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.moviehelloween.Movie
import com.example.moviehelloween.MovieRepository
import com.example.moviehelloween.R
import com.example.moviehelloween.adapter.ResultAdapter
import com.example.moviehelloween.databinding.FragmentResultBinding
import com.example.moviehelloween.viewmodel.ResultViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ResultFragment : Fragment(R.layout.fragment_result) {

    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: ResultAdapter
    private lateinit var viewModel: ResultViewModel
    private lateinit var repository: MovieRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentResultBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var searchQuery = ""
        arguments?.let {
            searchQuery = it.getString("searchQuery") ?: ""
        }
        setUpRecycle()
        val repository = MovieRepository(requireContext())
        viewModel = ResultViewModel(repository, searchQuery)
        observeMovies()
        Log.i("Fragg", "я родился")

    }

    private fun observeMovies() {
        binding.progressBar.visibility = View.VISIBLE
        binding.catView.visibility = View.INVISIBLE
        binding.notFount.visibility = View.INVISIBLE
        viewModel.movieList.observe(viewLifecycleOwner) { movies ->
            adapter.setItems(movies.toMutableList())
        }
        binding.progressBar.visibility = View.INVISIBLE
        if (adapter.itemCount == 0) {
            binding.catView.visibility = View.VISIBLE
            binding.notFount.visibility = View.VISIBLE
        } else {
            binding.catView.visibility = View.INVISIBLE
            binding.notFount.visibility = View.INVISIBLE
        }
        binding.progressBar.visibility = View.INVISIBLE
        Log.d("Fragg", "Количество элементов в адаптере: ${adapter.itemCount}")
    }

    private fun setUpRecycle() {
        adapter = ResultAdapter(this, mutableListOf<Movie>())
        binding.recyclerView.adapter = adapter
    }
}