package com.elkfrawy.pixaby.data.remote.photo

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.elkfrawy.pixaby.data.exception.ClientException
import com.elkfrawy.pixaby.data.exception.DataNotAvailableException
import com.elkfrawy.pixaby.data.exception.ServerException
import com.elkfrawy.pixaby.data.model.PhotoResponse
import com.elkfrawy.pixaby.data.remote.Api
import com.elkfrawy.pixaby.domain.model.Photo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class PhotoRemoteSourceImpl @Inject constructor (val api: Api) : PhotoRemoteSource {


    override suspend fun getPhotoById(id: Int): Result<Photo> {

        return try {
            withContext(Dispatchers.IO){
                val response = api.getPhotoById(id)
                if (response.isSuccessful){
                    response.body()?.let {
                        Result.success(it.hits[0])
                    } ?: Result.failure(DataNotAvailableException())
                }else if (response.code() > 500) Result.failure(ServerException())
                else Result.failure(ClientException())
            }
        }catch (e: Exception){
            Result.failure(ClientException())
        }

    }
}