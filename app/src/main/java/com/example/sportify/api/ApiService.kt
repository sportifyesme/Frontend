package com.example.sportify.api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

data class User(val nom_utilisateur: String, val email: String, val mot_de_passe: String, val sport: String, val niveau: String)
data class Event(val titre: String, val description: String, val date: String, val lieu: String, val id_organisateur: Int)
data class Activity(val type_activite: String, val duree: Int, val id_utilisateur: Int, val date: String)
data class ApiResponse(val message: String, val user_id: Int? = null)

interface ApiService {
    @POST("api/register")
    fun registerUser(@Body user: User): Call<ApiResponse>

    @POST("/api/login")
    fun loginUser(@Body user: User): Call<ApiResponse>

    @GET("/api/users")
    fun getUsers(): Call<List<User>>

    @GET("/api/events")
    fun getEvents(): Call<List<Event>>

    @POST("/api/events")
    fun createEvent(@Body event: Event): Call<ApiResponse>

    @POST("/api/activities")
    fun createActivity(@Body activity: Activity): Call<ApiResponse>

    @GET("/api/matchmaking")
    fun matchmaking(@Query("sport") sport: String, @Query("niveau") niveau: String, @Query("localisation") localisation: String): Call<List<User>>

    @POST("/api/location")
    fun updateLocation(@Body location: Map<String, Any>): Call<ApiResponse>
}
