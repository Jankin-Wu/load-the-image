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

package com.lt.load_the_image.loader.image

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.painter.Painter
import com.lt.load_the_image.LoadTheImageManager
import com.lt.load_the_image.loader.DataToBeLoaded
import com.lt.load_the_image.loader.LoadTheImage

/**
 * creator: lt  2022/6/1  lt.dygzs@qq.com
 * effect : Load the image from [ByteArray] of image
 * warning:
 */
class ByteArrayLoadTheImage : LoadTheImage {
    @Composable
    override fun load(data: DataToBeLoaded): Painter? {
        val byteArray = data.data as? ByteArray ?: return null
        return remember(byteArray) { LoadTheImageManager.painterCreator.create(byteArray) }
    }

    override fun canLoad(data: DataToBeLoaded): Boolean {
        return data.data is ByteArray
    }
}