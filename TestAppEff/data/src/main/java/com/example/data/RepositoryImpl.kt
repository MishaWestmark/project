package com.example.data

import android.util.Log
import com.example.data.network.DetailItem
import com.example.data.network.FlashSaleItems
import com.example.data.network.LatestItems
import com.example.data.network.ShopApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val db: ProfileDao,
    private val api: ShopApi
) : Repository {

    override suspend fun loginProfile(
        firstName: String,
        password: String
    ): Flow<ProfileInfoEntity?> {
        return withContext(Dispatchers.IO) {
            db.logIn(firstName, password)
        }

    }

    override suspend fun getLatestItems(result: (LatestItems?) -> Unit) {
        withContext(Dispatchers.IO) {
            api.getLatest().enqueue(object : Callback<LatestItems> {
                override fun onResponse(
                    call: Call<LatestItems>,
                    response: Response<LatestItems>
                ) {
                    result(response.body())
                    Log.e("reposLatestItems", response.body().toString())
                }

                override fun onFailure(call: Call<LatestItems>, t: Throwable) {

                }

            })
        }
    }

    override suspend fun getFlashSaleItems(result: (FlashSaleItems?) -> Unit) {
        withContext(Dispatchers.IO) {
            api.getFlashSale().enqueue(object : Callback<FlashSaleItems> {
                override fun onResponse(
                    call: Call<FlashSaleItems>,
                    response: Response<FlashSaleItems>
                ) {
                    result(response.body())
                    Log.e("reposFlashSaleItems", response.body().toString())
                }

                override fun onFailure(call: Call<FlashSaleItems>, t: Throwable) {

                }

            })
        }
    }

    override suspend fun getDetailInfo(result: (DetailItem?) -> Unit) {
        withContext(Dispatchers.IO) {
            api.getDetailInfo().enqueue(object : Callback<DetailItem> {
                override fun onResponse(call: Call<DetailItem>, response: Response<DetailItem>) {
                    result(response.body())
                    Log.e("reposDetailItem", response.body().toString())
                }

                override fun onFailure(call: Call<DetailItem>, t: Throwable) {

                }


            })
        }
    }
}