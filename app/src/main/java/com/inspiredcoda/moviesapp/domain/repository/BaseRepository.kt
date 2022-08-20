package com.inspiredcoda.moviesapp.domain.repository

import com.inspiredcoda.moviesapp.domain.remote.BaseWrapper
import org.json.JSONObject
import retrofit2.Response

abstract class BaseRepository {


    suspend fun <T> makeApiRequest(work: suspend () -> Response<T>): BaseWrapper<T?> {
        return try {
            val response = work.invoke()
            if (response.isSuccessful && response.body() != null) {
                BaseWrapper(
                    true,
                    "Successful",
                    response.body()!!
                )
            } else {

                val errorJson = response.errorBody()?.string()
                val jsonObject = JSONObject(errorJson)
                val status = jsonObject.getString("status")
                val message = jsonObject.getString("message")

                BaseWrapper(false, message, null)

            }
        } catch (ex: Exception) {
            BaseWrapper(false, ex.message ?: "Something went wrong", null)
        }
    }

}