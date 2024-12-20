package com.example.moviehelloween.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.moviehelloween.R
import com.example.moviehelloween.data.Cache
import com.example.moviehelloween.databinding.FragmentSearchBinding


class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private lateinit var file: Cache
    private val listHistory = mutableListOf<String>()


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
        file = Cache(requireContext())
        val listData = file.readHistory("history_of_search.txt")
        val adapter = ArrayAdapter(requireContext(), R.layout.text_linear, listData.reversed());
        binding.listView.adapter = adapter
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    listHistory.add(query.toString())
                    startResultFragment()
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
        file.saveHistory(listHistory.toList(), filename = "history_of_search.txt")
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