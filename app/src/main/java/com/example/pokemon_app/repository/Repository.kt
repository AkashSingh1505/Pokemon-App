package com.example.pokemon_app.repository

import com.example.pokemon_assignment.model.PokemonDetailsResponse
import com.example.pokemon_assignment.model.PokemonListResponse

import com.example.pokemon_app.retrofit.ApiService

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class Repository @Inject constructor(
    private val api: ApiService
) {
    private val _pokemonListResponse = MutableStateFlow<PokemonListResponse?>(null)
    val pokemonListResponse: StateFlow<PokemonListResponse?>
        get() = _pokemonListResponse

    private val _pokemonDetailsResponse = MutableStateFlow<PokemonDetailsResponse?>(null)
    val pokemonDetailsResponse: StateFlow<PokemonDetailsResponse?>
        get() = _pokemonDetailsResponse


    suspend fun getPokemonList() {
        val result = api.getPokemonList()
        if (result.isSuccessful && result.body() != null) {
            _pokemonListResponse.emit(result.body()!!)
        }
    }

    suspend fun getPokemonDetails(id: Int) {
        val result = api.getPokemonDetails(id)
        if (result.isSuccessful && result.body() != null) {
            _pokemonDetailsResponse.emit(result.body()!!)
        }
    }

}
