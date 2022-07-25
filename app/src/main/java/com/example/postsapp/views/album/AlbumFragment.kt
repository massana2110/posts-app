package com.example.postsapp.views.album

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.postsapp.R
import com.example.postsapp.databinding.FragmentAlbumBinding
import com.example.postsapp.repositories.MainRepository
import com.example.postsapp.views.album.adapters.AlbumAdapter

class AlbumFragment : Fragment() {

    private val args: AlbumFragmentArgs by navArgs()
    private lateinit var _binding: FragmentAlbumBinding
    private lateinit var _albumViewModel: AlbumViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_album,
            container,
            false
        )

        val factory = AlbumViewModelFactory(MainRepository())
        _albumViewModel = ViewModelProvider(requireActivity(), factory)[AlbumViewModel::class.java]

        // get photos
        _albumViewModel.getPhotosAction(args.postId)

        imagesObserver()

        return _binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _albumViewModel.clear()
    }

    private fun imagesObserver() {
        _albumViewModel.images.observe(viewLifecycleOwner) { images ->
            if (!images.isNullOrEmpty()) {
                Log.d("Album", "$images")
                _binding.apply {
                     albumRecyclerView.also { rv ->
                         rv.layoutManager = GridLayoutManager(requireContext(), 2)
                         rv.setHasFixedSize(true)
                         rv.adapter = AlbumAdapter(images.subList(0,10))
                     }
                }
            }
        }
    }
}