package com.xenia.practice.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @SerializedName("avatar") var avatar: String? = null,
    @SerializedName("email") var email: String? = null,
    @SerializedName("first_name") var first_name: String? = null,
    @SerializedName("id") var id: Int? = null,
    @SerializedName("last_name") var last_name: String? = null,
    @SerializedName("pass_id") var pass_id: String? = "22"
)