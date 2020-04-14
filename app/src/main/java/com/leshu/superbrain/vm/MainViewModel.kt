package com.leshu.superbrain.vm

import androidx.lifecycle.MutableLiveData
import com.leshu.superbrain.data.bean.Article
import com.leshu.superbrain.data.bean.BannerResponse
import com.leshu.superbrain.data.bean.User
import com.leshu.superbrain.data.bean.base.ResultData
import com.leshu.superbrain.data.repository.MainRepository
import com.leshu.superbrain.vm.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 *@创建者wwy
 *@创建时间 2020/1/13 14:24
 *@描述
 */
class MainViewModel : BaseViewModel() {
    private val mainRepository by lazy { MainRepository() }
    val homeArticles: MutableLiveData<List<Article>> = MutableLiveData()
    fun loadBanner() = launchUI {
        val result = withContext(Dispatchers.IO) { mainRepository.getBanners() }
        if (result is ResultData.Success) {
            //mBannerResponse.value = result.data
        }
    }

    fun loadHomeArticles(page: Int) = launchUI {
        val result = withContext(Dispatchers.IO) { mainRepository.getHomeArticles(page) }
        if (result is ResultData.Success) {
            homeArticles.value = result.data
        }
    }
}