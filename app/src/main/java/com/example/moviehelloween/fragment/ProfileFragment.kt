package com.example.moviehelloween.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.moviehelloween.R
import com.example.moviehelloween.databinding.FragmentFavouritesBinding
import com.example.moviehelloween.databinding.FragmentProfileBinding
import com.example.moviehelloween.viewmodel.CivitaiViewModel
import com.bumptech.glide.request.RequestOptions
import kotlinx.coroutines.launch
import kotlin.random.Random


class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: CivitaiViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(CivitaiViewModel::class.java)


        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.civitaiList.collect { list ->
                if (list.isNotEmpty()) {
                    val r = Random.nextInt(0, 49)
                    Glide.with(requireContext())
                        .load(list[r].url)
                        .into(binding.imageProfile)
                }
            }
        }

        viewModel.fetchCivitaiList(viewModel.currentPage)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}