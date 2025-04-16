package com.elkfrawy.pixaby.data.remote.video

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.elkfrawy.pixaby.data.remote.Api
import com.elkfrawy.pixaby.data.utils.STARTING_PAGE
import com.elkfrawy.pixaby.domain.model.VideoHit
import okio.IOException
import retrofit2.HttpException

class VideoPagingSource(val text: String, val api: Api): PagingSource<Int, VideoHit>() {


    override fun getRefreshKey(state: PagingState<Int, VideoHit>): Int? {
        val idx = state.anchorPosition
        return idx?.let {
            val page = state.closestPageToPosition(it)
            page?.prevKey?.plus(1) ?: page?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, VideoHit> {

        val page = params.key ?: STARTING_PAGE
        return try {
            val response = api.getVideos(text, page).body()?.hits ?: ArrayList<VideoHit>()
            LoadResult.Page(
                response,
                prevKey = if (page == STARTING_PAGE) null else page - 1,
                nextKey = if (response.isEmpty()) null else page + 1
            )
        }catch (exception: IOException){
            LoadResult.Error(exception)
        }catch (exception: HttpException){
            LoadResult.Error(exception)
        }


    }
}