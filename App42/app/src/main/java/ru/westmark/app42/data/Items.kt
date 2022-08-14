package ru.westmark.app42.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class Employers(
    @Json(name = "alternate_url")
    val alternate_url: String? = null,
    @Json(name = "id")
    val id: String? = null,
    @Json(name = "logo_urls")
    val logo_urls: LogoUrls? = null,
    @Json(name = "name")
    val name: String? = null,
    @Json(name = "open_vacancies")
    val openVacancies: Int? = null,
    @Json(name = "url")
    val url: String? = null,
    @Json(name = "vacancies_url")
    val vacanciesUrl: String? = null
) {
    @JsonClass(generateAdapter = true)
    data class LogoUrls(
        @Json(name = "90")
        val small: String? = null,
        @Json(name = "240")
        val middle: String? = null,
        @Json(name = "original")
        val original: String? = null
    )
}

