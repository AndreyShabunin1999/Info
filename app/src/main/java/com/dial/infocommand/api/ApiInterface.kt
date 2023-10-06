package com.dial.infocommand.api

import com.dial.infocommand.model.InfoModel
import com.dial.infocommand.model.InfoUrl
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {

    @FormUrlEncoded
    @POST("/splash.php")
    suspend fun setInfoUser(@Field("phone_name") phoneName: String, @Field("locale") locale: String,
                             @Field("unique") unique: String) : Response<InfoUrl>

    @GET("/1/infoCommands.json")
    suspend fun getCommands() : Response<ArrayList<InfoModel>>

}
