package com.lt.load_the_image.loader

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.toComposeImageBitmap
import androidx.compose.ui.res.ClassLoaderResourceLoader
import androidx.compose.ui.res.loadImageBitmap
import com.lt.load_the_image.LoadTheImageManager
import com.lt.load_the_image.painter.HttpImagePainter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.skia.Image

/**
 * creator: lt  2022/4/8  lt.dygzs@qq.com
 * effect : Load the image from network
 * warning:
 */
open class HttpLoadTheImage : LoadTheImage {

    @OptIn(ExperimentalComposeUiApi::class)
    @Composable
    override fun load(url: String): Painter? {
        val painter = HttpImagePainter()
        LaunchedEffect(url, painter) {
            withContext(Dispatchers.IO) {
                val byteArray =
                    LoadTheImageManager.memoryCache.getCache(url)
                        ?: LoadTheImageManager.fileCache.getCache(url)
                        ?: LoadTheImageManager.httpLoader.load(url)
                        ?: kotlin.run {
                            //Handling exceptions
                            val errorImagePath = LoadTheImageManager.defaultErrorImagePath
                            if (errorImagePath.isNotEmpty())
                                painter.imageBitmap.value =
// TODO by lt 2022/5/31 18:22 这里似乎放大了,不是自适应的,可能要该游戏啊httpimagepainter 
                                    loadImageBitmap(ClassLoaderResourceLoader().load(errorImagePath))
                            return@withContext
                        }
                LoadTheImageManager.memoryCache.saveCache(url, byteArray)
                LoadTheImageManager.fileCache.saveCache(url, byteArray)
                val imageBitmap = Image.makeFromEncoded(byteArray).toComposeImageBitmap()
                painter.imageBitmap.value = imageBitmap
            }
        }
        return painter
    }

    override fun canLoad(url: String): Boolean {
        if (url.startsWith("http://") || url.startsWith("https://"))
            return true
        return false
    }
}