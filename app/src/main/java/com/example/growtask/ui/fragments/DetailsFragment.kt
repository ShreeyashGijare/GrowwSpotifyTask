package com.example.growtask.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.growtask.data.data_models.artistModel.ArtistModel
import com.example.growtask.data.data_models.searchModel.ItemXXX
import com.example.growtask.databinding.FragmentDetailsBinding
import com.example.growtask.ui.activity.MainActivity
import com.example.growtask.ui.adapter.ArtistAlbumsAdapter
import com.example.growtask.ui.adapter.ArtistRelatedAdapter
import com.example.growtask.ui.adapter.ArtistTopTrackAdapter
import com.example.growtask.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private lateinit var mBinding: FragmentDetailsBinding
    private val mViewModel by viewModels<MainViewModel>()
    val args: DetailsFragmentArgs by navArgs()
    private lateinit var searchedData: ItemXXX

    private lateinit var artistTopTracksAdapter: ArtistTopTrackAdapter
    private lateinit var artistAlbumsAdapter: ArtistAlbumsAdapter
    private lateinit var artistRelatedAdapter: ArtistRelatedAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentDetailsBinding.inflate(layoutInflater)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).setSupportActionBar(mBinding.toolbar)

        setUpAdapter()
        getArgs()
        mViewModel.getArtistData(searchedData.artists!![0].id!!)
        mViewModel.getArtistTopTracks(searchedData.artists!![0].id!!)
        mViewModel.getArtistAlbums(searchedData.artists!![0].id!!)
        mViewModel.getArtistRelated(searchedData.artists!![0].id!!)
        setObserver()
    }

    private fun setObserver() {
        mViewModel.artistData.observe(viewLifecycleOwner) {
            val artistData = it
            setUpUI(artistData)
        }

        mViewModel.artistTopTracks.observe(viewLifecycleOwner) {
            val artistTopTracks = it.tracks
            artistTopTracksAdapter.updateList(artistTopTracks)
        }

        mViewModel.artistAlbums.observe(viewLifecycleOwner) {
            val artistAlbums = it.items
            artistAlbumsAdapter.updateList(artistAlbums)
        }

        mViewModel.artistRelated.observe(viewLifecycleOwner) {
            val artistRelated = it.artists
            artistRelatedAdapter.updateList(artistRelated)
        }
    }

    private fun getArgs() {
        searchedData = args.itemXXX
    }

    private fun setUpUI(artistData: ArtistModel) {
        Glide.with(requireActivity()).load(artistData.images[0].url).into(mBinding.artistPoster)
        mBinding.collapsingToolbar.title = artistData.name
    }

    private fun setUpAdapter() {
        val linearLayoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)

        val linearLayoutManagerHorizontal =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)

        val linearLayoutManagerHorizontalA =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)


        artistTopTracksAdapter = ArtistTopTrackAdapter(requireActivity())
        mBinding.rvPopularTracks.layoutManager = linearLayoutManager
        mBinding.rvPopularTracks.adapter = artistTopTracksAdapter

        artistAlbumsAdapter = ArtistAlbumsAdapter(requireActivity())
        mBinding.rvAlbums.layoutManager = linearLayoutManagerHorizontal
        mBinding.rvAlbums.adapter = artistAlbumsAdapter

        artistRelatedAdapter = ArtistRelatedAdapter(requireActivity())
        mBinding.rvArtists.layoutManager = linearLayoutManagerHorizontalA
        mBinding.rvArtists.adapter = artistRelatedAdapter
    }
}
