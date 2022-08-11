package com.nextgentest.android.repo

import com.nextgentest.android.models.Country
import com.nextgentest.android.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

object CountriesRepo {

    suspend fun getCountriesList(): Response<List<Country>> {
        return withContext(Dispatchers.IO) {
            return@withContext RetrofitClient.apiInteface.getCountries()
        }
    }
}