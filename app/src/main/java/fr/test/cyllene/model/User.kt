package fr.test.cyllene.model

import com.google.gson.annotations.SerializedName

class User (

    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("name")
    val name: String

)