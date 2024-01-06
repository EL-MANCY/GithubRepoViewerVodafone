package com.example.githubrepoviewervodafone.baseViewModel


import android.app.Application

import androidx.lifecycle.*
import com.example.githubrepoviewervodafone.data.remote.httpStatus.ApiResult
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.flow
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Response
import java.lang.Exception

open class BaseViewModel(application: Application) : AndroidViewModel(application) {

    fun <T> handleFlowResponse(apiRequest: suspend () -> Response<T>) =
        flow<ApiResult<T>> {
            try {
                emit(ApiResult.Loading(null, true))

                val response = withContext(Dispatchers.IO) {
                    try {
                        apiRequest()
                    } catch (e: Exception) {
                        Response.error(
                            403,
                            ResponseBody.create(
                                "application/json".toMediaTypeOrNull(),
                                "{\"message\":\"Unauthorized User!!! \"}"
                            )
                        )
                    }
                }

                if (response.isSuccessful) {
                    emit(ApiResult.Success(response.body()))
                } else {
                    when (response.code()) {
                        401 -> emit(ApiResult.Error("Unauthenticated User!!!"))
                        in 400 until 500 -> {
                            val errorBodyString = response.errorBody()?.string().orEmpty()
                            val responseObject = JSONObject(errorBodyString)

                            when {
                                responseObject.has("errors") -> {
                                    val errors = responseObject.opt("errors")

                                    when (errors) {
                                        is String -> emit(ApiResult.Error(errors))
                                        is JSONObject -> {
                                            errors.keys().forEach { key ->
                                                emit(ApiResult.Error(errors.getString(key)))
                                            }
                                        }
                                        else -> emit(ApiResult.Error("Unknown error"))
                                    }
                                }

                                responseObject.has("message") -> {
                                    emit(ApiResult.Error(responseObject.getString("message")))
                                    response.errorBody()?.close()
                                }

                                else -> {
                                    if (responseObject.has("documentation_url")) {
                                        val documentationUrl = responseObject.getString("documentation_url")
                                        emit(ApiResult.Error("GitHub API Error: $documentationUrl"))
                                    } else {
                                        emit(ApiResult.Error("Unknown GitHub API error"))
                                    }
                                }
                            }
                        }

                        else -> emit(ApiResult.Error("Server Error!!!"))
                    }
                }
            } catch (ex: Exception) {
                emit(ApiResult.Error("No internet"))
            }
        }.asLiveData()

}