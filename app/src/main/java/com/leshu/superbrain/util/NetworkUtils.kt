package com.leshu.superbrain.util

import com.leshu.superbrain.data.bean.base.ResultData

/**
 *@创建者wwy
 *@创建时间 2019/10/25 16:09
 *@描述 网络请求类
 */
suspend fun <T : Any> safeApiCall(call: suspend () -> ResultData<T>): ResultData<T> {
    return call()
}
