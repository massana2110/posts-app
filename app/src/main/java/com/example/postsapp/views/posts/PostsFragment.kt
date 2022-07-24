package com.example.postsapp.views.posts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.postsapp.R
import com.example.postsapp.databinding.FragmentPostsBinding

class PostsFragment : Fragment() {

    private lateinit var _binding: FragmentPostsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_posts,
            container,
            false
        )

        setupButtonListeners()

        return _binding.root
    }

    private fun setupButtonListeners() {
        _binding.commentsBtn.setOnClickListener {
            findNavController().navigate(
                PostsFragmentDirections.actionPostsFragmentToCommentsFragment()
            )
        }

        _binding.albumBtn.setOnClickListener {
            findNavController().navigate(
                PostsFragmentDirections.actionPostsFragmentToAlbumFragment()
            )
        }
    }



}