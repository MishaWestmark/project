package com.example.apptest

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CardInformation(
    @Json(name = "number")
    val number: Number? = null,
    @Json(name = "scheme")
    val scheme: String? = null,
    @Json(name = "type")
    val type: String? = null,
    @Json(name = "brand")
    val brand: String? = null,
    @Json(name = "prepaid")
    val prepaid: Boolean? = null,
    @Json(name = "country")
    val country: Country? = null,
    @Json(name = "bank")
    val bank: Bank? = null
) {
    @JsonClass(generateAdapter = true)
    data class Number(
        @Json(name = "length")
        val length: String? = null
    )

    @JsonClass(generateAdapter = true)
    data class Country(
        @Json(name = "name")
        val name: String? = null,
        @Json(name = "latitude")
        val latitude: Int? = null,
        @Json(name = "longitude")
        val longitude: Int? = null
    )

    @JsonClass(generateAdapter = true)
    data class Bank(
        @Json(name = "name")
        val name: String? = null,
        @Json(name = "url")
        val url: String? = null,
        @Json(name = "phone")
        val phone: String? = null
    )
}
