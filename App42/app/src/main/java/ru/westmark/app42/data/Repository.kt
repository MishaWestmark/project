package ru.westmark.app42.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.await
import ru.westmark.app42.network.Network

class Repository {
    fun getListEmployers(
        name: String?,
        type: String?,
        isVacancies: Boolean?
    ): Flow<List<Employers>> {
        return flow {
            val listEmployers = Network.hhApi.getEmployers(
                name = name,
                type = type,
                onlyWithVacancies = isVacancies
            ).await()
                .items
            emit(listEmployers)
        }.flowOn(Dispatchers.IO)
    }
}