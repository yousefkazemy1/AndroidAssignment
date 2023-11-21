package ir.miare.androidcodechallenge.data.remote

import ir.logicbase.mockfit.Mock
import ir.miare.androidcodechallenge.data.remote.response.SportResponse
import retrofit2.Call
import retrofit2.http.GET

interface SportApi {
    @Mock("data.json")
    @GET("list")
    fun getData(): Call<List<SportResponse>>
}