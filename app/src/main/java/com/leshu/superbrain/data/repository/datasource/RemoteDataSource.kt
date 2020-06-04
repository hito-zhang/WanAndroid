package com.leshu.superbrain.data.repository.datasource

import com.leshu.superbrain.data.api.RetrofitClient
import com.leshu.superbrain.data.api.WAN_ANDROID
import com.leshu.superbrain.data.bean.*
import com.leshu.superbrain.data.bean.base.ResultData
import com.leshu.superbrain.util.safeApiCall
import java.io.IOException

/**
 *@创建者wwy
 *@创建时间 2020/6/3 15:19
 *@描述
 */
class RemoteDataSource {
    /**
     * 首页数据源
     * @param getHomeArticles 首页文章数据
     * @param getBanners 首页Banner数据
     * @param getStickArticles 首页置顶文章数据
     */
    suspend fun getHomeArticles(page: Int) = safeApiCall(
        call = { requestHomeArticles(page) }
    )

    suspend fun getBanners() = safeApiCall(
        call = { requestBanners() }
    )

    suspend fun getStickArticles() = safeApiCall(
        call = { requestStickArticles() }
    )

    private suspend fun requestHomeArticles(page: Int): ResultData<WanListResponse<List<Article>>> {
        val homeArticles = RetrofitClient(WAN_ANDROID).service.getHomeArticles(page)
        if (homeArticles.errorCode == 0) {
            return ResultData.Success(homeArticles.data)
        }
        return ResultData.Error(IOException("Failed to get homeArticles${homeArticles.errorMsg}"))
    }

    private suspend fun requestBanners(): ResultData<List<Banner>> {
        val banner = RetrofitClient(WAN_ANDROID).service.getBanner()
        if (banner.errorCode == 0) {
            return ResultData.Success(banner.data)
        }
        return ResultData.Error(IOException("Failed to get banners${banner.errorMsg}"))
    }

    private suspend fun requestStickArticles(): ResultData<List<Article>> {
        val stickArticles = RetrofitClient(WAN_ANDROID).service.getStickArticles()
        if (stickArticles.errorCode == 0) {
            return ResultData.Success(stickArticles.data)
        }
        return ResultData.Error(IOException("Failed to get stickArticles${stickArticles.errorMsg}"))
    }

    /**
     * 广场数据源
     *  @param getSquareArticleList 广场列表数据
     *  @param getBlogType 公众号分类
     *  @param getProjectTypeDetailList 项目tab下数据
     */
    suspend fun getSquareArticleList(page: Int) = safeApiCall(
        call = { requestSquareArticleList(page) }
    )

    suspend fun getBlogType() = safeApiCall(
        call = { requestBlogType() }
    )

    private suspend fun requestSquareArticleList(page: Int): ResultData<WanListResponse<MutableList<Article>>> {
        val squareArticleList = RetrofitClient(WAN_ANDROID).service.getSquareArticleList(page)
        if (squareArticleList.errorCode == 0) {
            return ResultData.Success(squareArticleList.data)
        }
        return ResultData.Error(IOException("Failed to get squareArticleList${squareArticleList.errorMsg}"))
    }

    private suspend fun requestBlogType(): ResultData<MutableList<ClassifyResponse>> {
        val blogType = RetrofitClient(WAN_ANDROID).service.getBlogType()
        if (blogType.errorCode == 0) {
            return ResultData.Success(blogType.data)
        }
        return ResultData.Error(IOException("Failed to get blogType${blogType.errorMsg}"))
    }

    /**
     * 项目数据源
     *  @param getProjectClassify 项目tab
     *  @param getLatestProjectList 最新项目列表数据
     *  @param getProjectTypeDetailList 项目tab下数据
     */

    suspend fun getProjectClassify() = safeApiCall(
        call = { requestProjectClassify() }
    )

    suspend fun getLatestProjectList(page: Int) = safeApiCall(
        call = { requestLatestProjectList(page) }
    )

    suspend fun getProjectTypeDetailList(page: Int, cid: Int) = safeApiCall(
        call = { requestProjectTypeDetailList(page, cid) }
    )

    private suspend fun requestProjectClassify(): ResultData<List<ClassifyResponse>> {
        val projectClassify = RetrofitClient(WAN_ANDROID).service.getProjectTypes()
        if (projectClassify.errorCode == 0) {
            return ResultData.Success(projectClassify.data)
        }
        return ResultData.Error(IOException("Failed to get projectClassify${projectClassify.errorMsg}"))
    }

    private suspend fun requestLatestProjectList(page: Int): ResultData<WanListResponse<MutableList<Article>>> {
        val projectNewData = RetrofitClient(WAN_ANDROID).service.getProjectNewData(page)
        if (projectNewData.errorCode == 0) {
            return ResultData.Success(projectNewData.data)
        }
        return ResultData.Error(IOException("Failed to get latestProjectList${projectNewData.errorMsg}"))
    }

    private suspend fun requestProjectTypeDetailList(
        page: Int,
        cid: Int
    ): ResultData<WanListResponse<List<Article>>> {
        val projectDataByType = RetrofitClient(WAN_ANDROID).service.getProjectDataByType(page, cid)
        if (projectDataByType.errorCode == 0) {
            return ResultData.Success(projectDataByType.data)
        }
        return ResultData.Error(IOException("Failed to get projectTypeDetailList${projectDataByType.errorMsg}"))
    }

    /**
     * 我的界面数据源
     * @param login 登录
     */
    suspend fun login(username: String, password: String) = safeApiCall(
        call = { requestLogin(username, password) }
    )

    private suspend fun requestLogin(username: String, password: String): ResultData<User> {
        val login = RetrofitClient(WAN_ANDROID).service.login(username, password)
        if (login.errorCode == 0) {
            return ResultData.Success(login.data)
        }
        return ResultData.Error(IOException("Failed to get login${login.errorMsg}"))
    }

}