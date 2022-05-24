package com.cloud.myphoto.thumbnail.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.cloud.myphoto.databinding.FragmentThumbnailDetailBinding

class ThumbnailDetailFragment : Fragment() {
    private var _binding: FragmentThumbnailDetailBinding? = null
    private val binding get() = _binding!!
    val args: ThumbnailDetailFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        _binding = FragmentThumbnailDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()
    }

    private fun initData() {
        binding.tvThumbnailId.text = args.thumbnailInfo.id.toString()
        binding.tvThumbnailTitle.text = args.thumbnailInfo.title
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}