package com.ydhnwb.workshopmvpkotlin.models

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id") var id : Int?,
    @SerializedName("name") var name : String?,
    @SerializedName("email") var email : String?
) {
    constructor() : this(null, null, null)
}