package com.example.dashboard.ui.login.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginModel(
    @SerializedName("http_code" ) var httpCode : Int?    = null,
    @SerializedName("message"   ) var message  : String? = null,
    @SerializedName("data"      ) var data     : LoginData?   = LoginData()
): Parcelable

@Parcelize
data class LoginData (
    @SerializedName("token" ) var token : String? = null,
    @SerializedName("user"  ) var user  : UserData?   = UserData(),
): Parcelable
@Parcelize
data class UserData (
    @SerializedName("id"                ) var id              : Int?    = null,
    @SerializedName("name"              ) var name            : String? = null,
    @SerializedName("email"             ) var email           : String? = null,
    @SerializedName("username"          ) var username        : String? = null,
    @SerializedName("email_verified_at" ) var emailVerifiedAt : String? = null,
    @SerializedName("status"            ) var status          : String? = null,
    @SerializedName("created_at"        ) var createdAt       : String? = null,
    @SerializedName("updated_at"        ) var updatedAt       : String? = null,
    @SerializedName("role"              ) var role            : String? = null,
    @SerializedName("leave_balance"     ) var leaveBalance    : Int?     = null,
    //forgot data
    @SerializedName("avatar_directory"   ) var avatarDirectory  : String? = null,
    @SerializedName("avatar_filename"    ) var avatarFilename   : String? = null,
    @SerializedName("otp"                ) var otp              : Int?    = null,
    @SerializedName("otp_verifiy_status" ) var otpVerifiyStatus : Int?    = null,
    @SerializedName("otp_renewal_time"   ) var otpRenewalTime   : String? = null,

): Parcelable
