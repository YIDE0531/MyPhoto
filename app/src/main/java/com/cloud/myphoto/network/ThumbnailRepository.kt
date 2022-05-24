package com.cloud.myphoto.network

import com.cloud.myphoto.model.ThumbnailInfo
import okhttp3.OkHttpClient
import retrofit2.HttpException

class ThumbnailRepository(var apiService: ApiService): BaseRepository {
    lateinit var httpClient: OkHttpClient

    override suspend fun getThumbnail(): ApiResponseState<Array<ThumbnailInfo>> {
        return try {
            val response = apiService.callThumbnail()
            val body = response.body()
            if (response.isSuccessful && body != null) {
                body?.let {
                    ApiResponseState.Success(it)
                } ?: let {
                    ApiResponseState.Error(response.code(), "null")
                }
            } else {
                ApiResponseState.Error(response.code(), response.message())
            }
        } catch (e: HttpException) {
            ApiResponseState.Error(e.code(), e.message())
        } catch (e: Throwable) {
            ApiResponseState.ApiException(e)
        }
    }

    override fun cancelAllApi() {
        httpClient.dispatcher.cancelAll()
    }
}