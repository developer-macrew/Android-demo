package com.example.dashboard.ui.fragments.home.model

import com.google.gson.annotations.SerializedName

data class AvailableLeadsModel (
    @SerializedName("status" ) var status : Boolean?        = null,
    @SerializedName("msg"    ) var msg    : String?         = null,
    @SerializedName("data"   ) var data   : ArrayList<Data> = arrayListOf()
)
data class Data (

    @SerializedName("leadId"                ) var leadId               : String? = null,
    @SerializedName("product_id"            ) var productId            : String? = null,
    @SerializedName("questionnaire_answers" ) var questionnaireAnswers : String? = null,
    @SerializedName("order4"                ) var order4               : String? = null,
    @SerializedName("price"                 ) var price                : String? = null,
    @SerializedName("city"                  ) var city                 : String? = null,
    @SerializedName("state"                 ) var state                : String? = null,
    @SerializedName("country"               ) var country              : String? = null,
    @SerializedName("available_on"          ) var availableOn          : String? = null,
    @SerializedName("productName"           ) var productName          : String? = null,
    @SerializedName("icon"                  ) var icon                 : String? = null,
    @SerializedName("description"           ) var description          : String? = null,
    @SerializedName("modelid"               ) var modelid              : String? = null,
    @SerializedName("ram"                   ) var ram                  : String? = null,
    @SerializedName("internal"              ) var internal             : String? = null,
    @SerializedName("postedOn"              ) var postedOn             : String? = null,
    @SerializedName("pincode"               ) var pincode              : String? = null,
    @SerializedName("fromTime"              ) var fromTime             : String? = null,
    @SerializedName("toTime"                ) var toTime               : String? = null,
    @SerializedName("boughtBy"              ) var boughtBy             : String? = null,
    @SerializedName("latitude"              ) var latitude             : String? = null,
    @SerializedName("longitude"             ) var longitude            : String? = null,
    @SerializedName("lead_price"            ) var leadPrice            : Int?    = null

)