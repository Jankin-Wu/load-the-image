package com.lt.load_the_image.loader.http

import com.lt.load_the_image.util.println
import okhttp3.OkHttpClient
import okhttp3.Request

/**
 * creator: lt  2022/4/8  lt.dygzs@qq.com
 * effect : Use OkHttp load url
 * warning:
 */
open class OkHttpLoader(
    private val okHttpClient: OkHttpClient = OkHttpClient()
) : HttpLoader {

    override fun load(url: String): ByteArray? {
        return try {
            val build = Request.Builder()
                .url(url)
                .build()
            okHttpClient.newCall(build).execute().body?.bytes()
        } catch (e: Exception) {
            e.println()
            null
        }
    }
}