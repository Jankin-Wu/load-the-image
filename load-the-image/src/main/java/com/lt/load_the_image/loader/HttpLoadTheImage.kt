/*
 * Copyright lt 2023
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.lt.load_the_image.loader

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.toComposeImageBitmap
import com.lt.load_the_image.LoadTheImageManager
import com.lt.load_the_image.painter.AsyncImagePainter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.skia.Image

/**
 * creator: lt  2022/4/8  lt.dygzs@qq.com
 * effect : Load the image from network
 * warning:
 */
open class HttpLoadTheImage : LoadTheImage {

    @Composable
    override fun load(data: DataToBeLoaded): Painter? {
        val url = data.data as? String ?: return null
        val painter = remember(url) { AsyncImagePainter() }
        LaunchedEffect(url) {
            withContext(Dispatchers.IO) {
                //Use cache
                var byteArray =
                    LoadTheImageManager.memoryCache.getCache(url)
                        ?: LoadTheImageManager.fileCache.getCache(url)
                if (byteArray == null) {
                    val placeholderResource = data.placeholderResource
                    if (placeholderResource.isNotEmpty())
                        painter.imageBitmap.value =
                            LoadTheImageManager.loadResourceImageBitmap(placeholderResource)
                    //Load with http
                    byteArray = LoadTheImageManager.httpLoader.load(url)
                    if (byteArray == null) {
                        //Handling exceptions
                        val errorImagePath = data.errorImagePath
                        if (errorImagePath.isNotEmpty())
                            painter.imageBitmap.value =
                                LoadTheImageManager.loadResourceImageBitmap(errorImagePath)
                        return@withContext
                    }
                }
                LoadTheImageManager.memoryCache.saveCache(url, byteArray)
                LoadTheImageManager.fileCache.saveCache(url, byteArray)
                val imageBitmap = Image.makeFromEncoded(byteArray).toComposeImageBitmap()
                painter.imageBitmap.value = imageBitmap
            }
        }
        return painter
    }

    override fun canLoad(data: DataToBeLoaded): Boolean {
        val url = data.data as? String ?: return false
        if (url.startsWith("http://") || url.startsWith("https://"))
            return true
        return false
    }
}