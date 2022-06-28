package com.hanshow.myapplication.api

import com.hanshow.myapplication.bean.Music
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by yikai on 2022/6/1.
 * Describe:
 */
interface Api {
    @GET("/music")
    suspend fun getMusicList(): MusicStyleResponse<List<Music>>

    @GET("/{id}}")
    suspend fun getMusicById(@Path("id") id: Int): MusicStyleResponse<Music>

    /**
     * 分页获取音乐
     */
    @GET("")
    suspend fun getMusicPage(
        @Query("page") page: Int,
        @Query("size") size: Int
    ): MusicStyleResponse<List<Music>>




}