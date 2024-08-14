package com.example.pokemon_app.retrofit

import com.example.pokemon_assignment.model.PokemonDetailsResponse
import com.example.pokemon_assignment.model.PokemonListResponse

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("pokemon")
    suspend fun getPokemonList(): Response<PokemonListResponse>

    @GET("pokemon/{id}")
    suspend fun getPokemonDetails(@Path("id") id: Int): Response<PokemonDetailsResponse>

}