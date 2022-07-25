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

        val factory = PostsViewModelFactory(MainRepository())
        _postsViewModel = ViewModelProvider(requireActivity(), factory)[PostsViewModel::class.java]

        postsObserver()

        return _binding.root
    }

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

    override fun onPostItemClick(view: View, id: Int) {
        Log.d("PostFragment", "Id: $id")
    }

}