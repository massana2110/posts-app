package com.example.postsapp.views.comments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.postsapp.R
import com.example.postsapp.databinding.FragmentCommentsBinding
import com.example.postsapp.repositories.MainRepository
import com.example.postsapp.views.comments.adapters.CommentAdapter

/**
 * Author: David Massana [david.massana.10@gmail.com]
 * Creation Date: July 24th, 2022
 * Description: Comments Fragment class with its UI operation and init variables
 */
class CommentsFragment : Fragment() {

    private val _args: CommentsFragmentArgs by navArgs()
    private lateinit var _binding: FragmentCommentsBinding
    private lateinit var _commentViewModel: CommentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_comments,
            container,
            false
        )

        val factory = CommentViewModelFactory(MainRepository())
        _commentViewModel = ViewModelProvider(requireActivity(), factory)[CommentViewModel::class.java]

        // get comments view model
        _commentViewModel.getCommentsAction(_args.postId)

        // observer
        commentsObserver()

        return _binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _commentViewModel.clear()
    }

    private fun commentsObserver() {
        _commentViewModel.comments.observe(viewLifecycleOwner) { comments ->
            if (!comments.isNullOrEmpty()) {
                _binding.apply {
                    commentsProgressBar.visibility = View.GONE

                    commentsRecyclerView.also { rv ->
                        rv.layoutManager = LinearLayoutManager(requireContext())
                        rv.setHasFixedSize(true)
                        rv.adapter = CommentAdapter(comments)
                    }

                    commentsRecyclerView.visibility = View.VISIBLE
                }
            }
        }
    }
}