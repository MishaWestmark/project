package ru.westmark.app42.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import ru.westmark.app42.data.Employers
import ru.westmark.app42.data.ServerItemsWrapper

interface HHApi {
    @GET("/employers")
    fun getEmployers(
        @Query(value = "page") page: Int = 0,
        @Query(value = "per_page") perPage: Int = 99,
        @Query(value = "text") name: String? = null,
        @Query(value = "type") type: String? = "company",
        @Query(value = "only_with_vacancies") onlyWithVacancies: Boolean? = null
    ): Call<ServerItemsWrapper<Employers>>

}