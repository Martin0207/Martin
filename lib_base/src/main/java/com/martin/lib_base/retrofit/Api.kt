package com.martin.lib_base.retrofit

import com.martin.lib_base.base.BaseResponse
import com.martin.lib_base.pojo.bean.UploadBean
import io.reactivex.Observable
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
        val BASE_URL_RELEASE = "https://driver.lxzy888.com/api/"
        //测试服务器
        val BASE_URL_TEST = "https://dir1.bnzyll.com/api/"

        val BASE_URL = BASE_URL_RELEASE
    }

    /**
     * 下载文件
     * @param url 下载地址
     */
    @Streaming
    @GET
    fun downloadFile(@Url url: String): Observable<ResponseBody>

    //================================= 上传文件 ==============================================

    /**
     * 上传单个文件
     */
    @Multipart
    @POST("api/upload/android")
    fun uploadFile(@Part("file") file: @JvmSuppressWildcards RequestBody): Observable<BaseResponse<ArrayList<UploadBean>>>

    @Multipart
    @POST("api/upload/android")
    fun uploadFile(@Part file: MultipartBody.Part): Observable<BaseResponse<ArrayList<UploadBean>>>

    /**
     * 上传多个文件
     */
    @Multipart
    @POST("api/upload/android")
    fun uploadFiles(@PartMap maps: Map<String, @JvmSuppressWildcards RequestBody>)
            : @JvmSuppressWildcards Observable<BaseResponse<ArrayList<UploadBean>>>

    //================================= 业务接口 ==============================================
}