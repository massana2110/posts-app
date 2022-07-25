package com.example.postsapp.views.posts

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.postsapp.R
import com.example.postsapp.databinding.FragmentPostsBinding
import com.example.postsapp.repositories.MainRepository
import com.example.postsapp.views.posts.adapters.PostAdapter
import com.example.postsapp.views.posts.adapters.PostItemClickListener

/**
 * Author: David Massana [david.massana.10@gmail.com]
 * Creation Date: July 23rd, 2022
 * Description: Post Fragment Screen, this class inflate the post fragment layout, manage the
 * lifecycle of the screen, init the Post ViewModel with its livedata variables.
 */
class PostsFragment : Fragment(), PostItemClickListener {

    private lateinit var _binding: FragmentPostsBinding
    private lateinit var _postsViewModel: PostsViewModel

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

        // init post view model variable
        val factory = PostsViewModelFactory(MainRepository())
        _postsViewModel = ViewModelProvider(requireActivity(), factory)[PostsViewModel::class.java]

        postsObserver()

        return _binding.root
    }

    /**
     * Definition of post list observer.
     * When the post list value change, this observer is in charge of refreshing the UI
     */
    private fun postsObserver() {
        _postsViewModel.posts.observe(viewLifecycleOwner) { postsList ->
            if (!postsList.isNullOrEmpty()) {
                _binding.apply {
                    postsProgressBar.visibility = View.GONE

                    postsRecyclerView.also { rv ->
                        rv.layoutManager = LinearLayoutManager(requireContext())
                        rv.setHasFixedSize(true)
                        rv.adapter = PostAdapter(postsList, this@PostsFragment)
                    }

                    postsRecyclerView.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun onPostCommentItemClick(view: View, id: Int) {
        findNavController().navigate(
            PostsFragmentDirections.actionPostsFragmentToCommentsFragment(postId = id)
        )
    }

    override fun onPostAlbumItemClick(view: View, id: Int) {
        TODO("Not yet implemented")
    }

}