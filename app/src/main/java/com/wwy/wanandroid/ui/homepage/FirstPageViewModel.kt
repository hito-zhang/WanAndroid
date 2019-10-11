package com.wwy.wanandroid.ui.homepage

import androidx.lifecycle.MutableLiveData
import com.wwy.wanandroid.bean.Banner
import com.wwy.wanandroid.repository.HomeRepository
import com.wwy.wanandroid.ui.base.BaseViewModel

/**
 *@创建者wwy
 *@创建时间 2019/10/9 9:51
 *@描述
 */
class FirstPageViewModel : BaseViewModel() {

    private val repository by lazy { HomeRepository() }
    val mBanner: MutableLiveData<List<Banner>> by lazy { MutableLiveData<List<Banner>>().also { loadBanner() } }

    private fun loadBanner() = launchUI({
        val result = repository.getBanners()
        mBanner.value = result.data
    }, true)

}