package com.cloud.myphoto.network

import com.cloud.myphoto.model.ThumbnailInfo

interface BaseRepository {
    suspend fun getThumbnail(): ApiResponseState<Array<ThumbnailInfo>>
    fun cancelAllApi()
}