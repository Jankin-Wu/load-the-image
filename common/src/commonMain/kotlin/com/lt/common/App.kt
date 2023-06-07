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

package com.lt.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun App(
    lazyColumnState: LazyListState = rememberLazyListState()
) {
    val images = listOf(
        //Correct
        "C:\\SpringBootFiles\\imgs\\7633948650d6b30461fce6d13422ec3a.jpeg",
        "https://img.zcool.cn/community/017e625e57415ea801216518c25819.jpg@1280w_1l_2o_100sh.jpg",
        "drawable-xxhdpi/img_a.jpeg",
        "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fbkimg.cdn.bcebos.com%2Fpic%2Ff9198618367adab44aed43580398a41c8701a18b4ed1&refer=http%3A%2F%2Fbkimg.cdn.bcebos.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1651992355&t=0eb27c606fc381fdaaaca828505ac114",
        "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Folimg.3dmgame.com%2Fuploads%2Fimages%2Fraiders%2F20200319%2F1584597196_470838.jpg&refer=http%3A%2F%2Folimg.3dmgame.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1651991879&t=6ff7794b1d539b27df0f55a97004a36e",
        "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.jbzj.com%2Ffile_images%2Fgame%2F201512%2F2015122310510491.jpg&refer=http%3A%2F%2Fimg.jbzj.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1651991926&t=7f0680dcc5189609ead24cdbb5d6d291",
        "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fbkimg.cdn.bcebos.com%2Fpic%2F8326cffc1e178a82b9018131e84f648da97739124247&refer=http%3A%2F%2Fbkimg.cdn.bcebos.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1651992324&t=afbd7342f9b42a0c05f1ddd6e4e7d5f0",
        "https://ss2.baidu.com/-vo3dSag_xI4khGko9WTAnF6hhy/baike/pic/item/e850352ac65c10380e49077eba119313b07e8953.jpg",
        "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fbkimg.cdn.bcebos.com%2Fpic%2F0df431adcbef76094b36910e3091b4cc7cd98d103044&refer=http%3A%2F%2Fbkimg.cdn.bcebos.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1651991945&t=a6c5f8f1fa6a8bbeba11b99ec0a03f94",

        //Three error
        "C:\\SpringBootFiles\\imgs\\45260159a1f8864027389751.jpg",
        "drawable-xxhdpi/image_b.jpg",
        "https://img.zcool.cn/community/017e625e57415ea80121128c25819.jpg@1280w_1l_2o_100sh.jpg",
    )

    LazyColumn(state = lazyColumnState) {
        items(images) {
            Image(rememberImagePainter(it), "", Modifier.size(500.dp))
        }
    }
}
