package com.threenitas.map.interfaces



import com.threenitas.map.models.Predictions
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GoogleMapAPI_Interface {

    @GET("place/autocomplete/json")
    fun getPlacesAutoComplete(
        @Query("input") input: String,
        @Query("types") types: String,
        @Query("language") language: String,
        @Query("key") key: String
    ): Call<Predictions>

}