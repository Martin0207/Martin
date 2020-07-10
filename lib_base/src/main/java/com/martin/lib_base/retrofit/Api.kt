package com.martin.lib_base.retrofit

import com.martin.lib_base.basic.BaseResponse
import com.martin.lib_base.pojo.bean.TypeBean
import com.martin.lib_base.pojo.bean.UploadBean
import kotlinx.coroutines.Deferred
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.http.*

/**
 * @author ：Martin
 * @data : 2018/6/21 20:44
 */
interface Api {

    companion object {
        //正式服务器
        val BASE_URL_RELEASE = "http://app.grass10.com/"

        //测试服务器
        val BASE_URL_TEST = "https://dir1.bnzyll.com/api/"

        val BASE_URL = BASE_URL_RELEASE
    }

    //================================= 上传/下载 ==============================================

    /**
     * 下载文件
     * @param url 下载地址
     */
    @Streaming
    @GET
    fun downloadFile(@Url url: String): Deferred<ResponseBody>

    /**
     * 上传单个文件
     */
    @Multipart
    @POST("api/upload/android")
    fun uploadFile(@Part("file") file: @JvmSuppressWildcards RequestBody): Deferred<BaseResponse<ArrayList<UploadBean>>>

    @Multipart
    @POST("api/upload/android")
    fun uploadFile(@Part file: MultipartBody.Part): Deferred<BaseResponse<ArrayList<UploadBean>>>

    /**
     * 上传多个文件
     */
    @Multipart
    @POST("api/upload/android")
    fun uploadFiles(@PartMap maps: Map<String, @JvmSuppressWildcards RequestBody>)
            : @JvmSuppressWildcards Deferred<BaseResponse<ArrayList<UploadBean>>>

    //================================= 业务接口 ==============================================

    /**
     * 获取推荐数据
     */
    @POST("api/index/get_data")
    @FormUrlEncoded
//    fun getRecommend(@Field("type") type: Int): Deferred<BaseResponse<ArrayList<TypeBean>>>
    suspend fun getRecommend(@Field("type") type: Int): BaseResponse<ArrayList<TypeBean>>

}