package com.hanshow.myapplication.api

import com.google.gson.annotations.SerializedName

/**
 * Created by yikai on 2022/6/1.
 * Describe:
 */
data class MusicStyleResponse<T>(
    val code: Int,
    val data: T,
    val meta: Meta? = null,
    val message: String
) {
    fun isSuccessful(): Boolean {
        return code == 1000
    }
}

data class Meta(
    @SerializedName("has_next") val hasNext: Boolean = true,
    @SerializedName("next_offset") val nextOffset: Int = 0,
    val limit: Int = 20,
    val offset: Int = 0,
    @SerializedName("total_count") val total: Long = 0
)