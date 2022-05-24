package com.cloud.myphoto.network
import com.cloud.myphoto.model.ThumbnailInfo
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("/photos")
    suspend fun callThumbnail(): Response<Array<ThumbnailInfo>>

//    @GET("/photos")
//    suspend fun callThumbnail(): ApiResponseState<Array<ThumbnailInfo>>

//    @GET("/api/v1/dataset/f18de02f-b6c9-47c0-8cda-50efad621c14?scope=resourceAquire")
//    fun getPlantApi(@QueryMap params: Map<String, String>): Call<PlantItem>
}