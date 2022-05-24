package com.cloud.myphoto.thumbnail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.cloud.myphoto.databinding.FragmentThumbnailBinding
import com.cloud.myphoto.model.ThumbnailInfo
import com.google.android.material.snackbar.Snackbar

class ThumbnailFragment : Fragment(), ThumbnailAdapter.OnItemClickListener {

    private var _binding: FragmentThumbnailBinding? = null
    private val binding get() = _binding!!
    private lateinit var thumbnailAdapter: ThumbnailAdapter
    private val thumbnailViewModel: ThumbnailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentThumbnailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initObserve()
    }

    private fun initView() {
        thumbnailAdapter = ThumbnailAdapter(thumbnailViewModel, requireContext(), this)
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(context, 4)
            adapter = thumbnailAdapter
            setHasFixedSize(true)
        }
    }

    private fun initObserve() {
        thumbnailViewModel.thumbInfoLiveData.observe(viewLifecycleOwner) {
            thumbnailAdapter.updateThumbInfoAllArray(it)
        }

        thumbnailViewModel.errorMessageLiveData.observe(viewLifecycleOwner) {
            Snackbar.make(binding.root, it, Snackbar.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(thumbnailInfo: ThumbnailInfo) {
        ThumbnailFragmentDirections.actionThumbnailFragmentToThumbnailDetailFragment(thumbnailInfo).let { action ->
            findNavController().navigate(action)
        }
    }
}