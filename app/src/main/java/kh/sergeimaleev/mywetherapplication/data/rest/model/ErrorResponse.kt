package kh.sergeimaleev.mywetherapplication.data.rest.model

import com.google.gson.annotations.SerializedName


data class ErrorResponse(@SerializedName("error") val error: Error)

data class Error(@SerializedName("errorText") val errorText: String)