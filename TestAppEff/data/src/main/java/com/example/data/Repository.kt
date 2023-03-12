package com.example.data

import com.example.data.network.DetailItem
import com.example.data.network.FlashSaleItems
import com.example.data.network.LatestItems
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun loginProfile(firstName: String, password: String): Flow<ProfileInfoEntity?>

    suspend fun getLatestItems(result: (LatestItems?) -> Unit)

    suspend fun getFlashSaleItems(result: (FlashSaleItems?) -> Unit)

    suspend fun getDetailInfo(result: (DetailItem?) -> Unit)
}