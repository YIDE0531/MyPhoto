package com.cloud.myphoto.thumbnail
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cloud.myphoto.model.ThumbnailInfo
import com.cloud.myphoto.network.ApiManager
import com.cloud.myphoto.network.ApiResponseState
import com.cloud.myphoto.network.ApiService
import com.cloud.myphoto.network.ThumbnailRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ThumbnailViewModel: ViewModel(){
    private val _thumbInfoMutableLiveData = MutableLiveData<Array<ThumbnailInfo>>()
    val thumbInfoLiveData: LiveData<Array<ThumbnailInfo>> get() = _thumbInfoMutableLiveData

    private val _errorMessageMutableLiveData = MutableLiveData<String>()
    val errorMessageLiveData: LiveData<String> get() = _errorMessageMutableLiveData

    private val thumbnailRepository = ThumbnailRepository(ApiManager.client.create(ApiService::class.java))

    init {
        viewModelScope.launch {
            getPhotosInfo()
        }
    }

    private suspend fun getPhotosInfo() = withContext(Dispatchers.IO) {
        val response = thumbnailRepository.getThumbnail()
        when (response) {
            is ApiResponseState.Success -> {
                response.item?.let {
                    _thumbInfoMutableLiveData.postValue(it)
                }
            }
            is ApiResponseState.Error -> _errorMessageMutableLiveData.postValue(response.message ?: "")
            is ApiResponseState.ApiException -> _errorMessageMutableLiveData.postValue(response.e.message ?: "")
        }
    }
}