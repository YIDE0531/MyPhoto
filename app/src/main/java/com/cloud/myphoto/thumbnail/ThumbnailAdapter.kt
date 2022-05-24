package com.cloud.myphoto.thumbnail

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cloud.myphoto.databinding.AdapterThumbnailBinding
import com.cloud.myphoto.model.ThumbnailInfo
import java.util.*

class ThumbnailAdapter(private val viewModel: ThumbnailViewModel, private val context: Context, private val onItemClickListener: OnItemClickListener): RecyclerView.Adapter<ThumbnailAdapter.ThumbnailHolder>() {
    private var thumbInfoArray: Array<ThumbnailInfo>? = null
    private var thumbInfoAllArray: Array<ThumbnailInfo>? = null
    private var nowCount = 2000

    fun updateThumbInfoAllArray(list: Array<ThumbnailInfo>){
        thumbInfoAllArray = list
        thumbInfoArray = (Arrays.copyOfRange(thumbInfoAllArray,0,nowCount))
        nowCount = thumbInfoArray!!.size
        notifyDataSetChanged()
    }

    fun updateList(){
        if (thumbInfoAllArray!!.size > nowCount + 1000) {
            nowCount += 1000
        }else {
            nowCount = thumbInfoAllArray!!.size
        }
        thumbInfoArray = (Arrays.copyOfRange(thumbInfoAllArray,0,nowCount))
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ThumbnailHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = AdapterThumbnailBinding.inflate(layoutInflater, parent, false)
        return ThumbnailHolder(binding)
    }

    override fun onBindViewHolder(holder: ThumbnailHolder, position: Int) {
        thumbInfoArray?.let {
            holder.bind(it[position])
        }
    }

    override fun getItemCount(): Int {
        return thumbInfoArray?.size ?: 0
    }

    inner class ThumbnailHolder(private val binding: AdapterThumbnailBinding): RecyclerView.ViewHolder(
        binding.root
    ) {
        fun bind(thumbnailInfo: ThumbnailInfo){
            binding.tvThumbnailId.text = thumbnailInfo.id.toString()
            binding.tvThumbnailTitle.text = thumbnailInfo.title
            binding.root.setOnClickListener {
                onItemClickListener.onItemClick(thumbnailInfo)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(thumbnailInfo: ThumbnailInfo)
    }
}
