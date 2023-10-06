package com.dial.infocommand.model

import com.google.gson.annotations.SerializedName

data class InfoModel (
    @SerializedName("country") var country  : String? = null,
    @SerializedName("commands") var commands : ArrayList<CommandsModel> = arrayListOf()
)
