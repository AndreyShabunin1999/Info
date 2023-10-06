package com.dial.infocommand.model

import com.google.gson.annotations.SerializedName


data class CompoundModel (

    @SerializedName("number") var number: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("position") var position : String? = null,
    @SerializedName("old") var old: Int? = null,
    @SerializedName("img") var img: String? = null

)
