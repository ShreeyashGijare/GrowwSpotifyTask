package com.example.growtask.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.growtask.R
import com.example.growtask.data.data_models.searchModel.ItemXXX
import com.example.growtask.databinding.FragmentDashBoardBinding
import com.example.growtask.ui.activity.MainActivity
import com.example.growtask.ui.adapter.DashBoardAdapter
import com.example.growtask.ui.clickInterfaces.SearchClickInterface
import com.example.growtask.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashBoardFragment : Fragment(), SearchClickInterface {

    private lateinit var mBinding: FragmentDashBoardBinding
    private val mViewModel by viewModels<MainViewModel>()
    private lateinit var dashBoardAdapter: DashBoardAdapter

    private var offset = 0
    private var limit = 20
    private var isLoading = false
    private var isLastPage = false

    private lateinit var linearLayoutManager: LinearLayoutManager

    private var songList: MutableList<ItemXXX> = ArrayList()
    private lateinit var rotateAnimation: Animation

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentDashBoardBinding.inflate(layoutInflater)
        return mBinding.root
    }

    override fun onResume() {
        super.onResume()
        mBinding.etSearch.setTextChangedAction {
            val query = mBinding.etSearch.getData()
            if (query.isBlank() || query.isEmpty()) {
                mBinding.loader.visibility = View.VISIBLE
                mBinding.loader.startAnimation(rotateAnimation)
                songList.clear()
                dashBoardAdapter.setUpData(songList)
            } else {
                mViewModel.getSearchData(mBinding.etSearch.getData(), offset, limit)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        setUpObserver()
        setUpAdapter()

        rotateAnimation = AnimationUtils.loadAnimation(requireActivity(), R.anim.rotate)
        mBinding.loader.startAnimation(rotateAnimation)

        mBinding.rvSongs.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount = linearLayoutManager.childCount
                val totalItemCount = linearLayoutManager.itemCount
                val firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition()
                if (!isLoading && !isLastPage) {
                    if (visibleItemCount + firstVisibleItemPosition >= totalItemCount
                        && firstVisibleItemPosition >= 0
                        && totalItemCount >= limit
                    ) {
                        offset++
                        mViewModel.getSearchData(mBinding.etSearch.getData(), offset, limit)
                    }
                }
            }
        })
    }

    private fun setUpObserver() {
        mViewModel.searchData.observe(viewLifecycleOwner, Observer {
            val searchModel = it
            if (searchModel != null) {
                mBinding.loader.clearAnimation()
                mBinding.loader.visibility = View.GONE
                mBinding.rvSongs.visibility = View.VISIBLE
                songList.addAll(searchModel.tracks.items)
                dashBoardAdapter.setUpData(songList)
            } else {
                mBinding.loader.startAnimation(rotateAnimation)
                mBinding.loader.visibility = View.VISIBLE
            }
        })
    }

    private fun setUpAdapter() {
        dashBoardAdapter = DashBoardAdapter(requireActivity(), this)
        linearLayoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        mBinding.rvSongs.layoutManager = linearLayoutManager
        mBinding.rvSongs.adapter = dashBoardAdapter
    }

    override fun searchClickData(data: ItemXXX) {
        val action = DashBoardFragmentDirections.actionDashBoardFragmentToDetailsFragment(data)
        findNavController().navigate(action)
    }
}