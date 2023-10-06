package com.dial.infocommand.model

import com.google.gson.annotations.SerializedName


data class CommandsModel (

    @SerializedName("name") var name: String? = null,
    @SerializedName("image") var image: String? = null,
    @SerializedName("description") var description : String? = null,
    @SerializedName("compound") var compound: ArrayList<CompoundModel> = arrayListOf()

)
