package com.elkfrawy.pixaby.data.remote.video

import com.elkfrawy.pixaby.data.exception.ClientException
import com.elkfrawy.pixaby.data.exception.DataNotAvailableException
import com.elkfrawy.pixaby.data.exception.ServerException
import com.elkfrawy.pixaby.data.remote.Api
import com.elkfrawy.pixaby.domain.model.Photo
import com.elkfrawy.pixaby.domain.model.VideoHit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class VideoRemoteSourceImpl @Inject constructor (val api: Api): VideoRemoteSource {

    override suspend fun getVideoById(id: Int): Result<VideoHit> {
        return try {
            withContext(Dispatchers.IO){
                val response = api.getVideoById(id)
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