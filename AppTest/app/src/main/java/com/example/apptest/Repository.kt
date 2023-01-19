package com.example.apptest

import android.util.Log
import com.example.apptest.network.Network
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Repository {
    fun getInfoCard(
        number: String,
        onComplete: (CardInformation?) -> Unit
    ) {
        Log.e("cardInfo", "fuckRep")
        Network.BinApi.getInfoCard(number).enqueue(object : Callback<CardInformation> {
            override fun onResponse(
                call: Call<CardInformation>,
                response: Response<CardInformation>
            ) {
                onComplete(response.body())
                Log.e("cardInfo", response.body().toString())
            }

            override fun onFailure(call: Call<CardInformation>, t: Throwable) {
                Log.e("cardInfo", t.toString())
            }
        })
    }
}