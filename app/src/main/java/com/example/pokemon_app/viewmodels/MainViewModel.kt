package com.example.pokemon_app.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemon_app.repository.Repository
import com.example.pokemon_assignment.model.PokemonDetailsResponse
import com.example.pokemon_assignment.model.PokemonListResponse
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel@Inject constructor(private val repository: Repository) : ViewModel() {
    val pokemonListResponse: StateFlow<PokemonListResponse?>
        get() = repository.pokemonListResponse

    val pokemonDetailsResponse: StateFlow<PokemonDetailsResponse?>
        get() = repository.pokemonDetailsResponse

    fun getPokemonDetails(id: Int){
        viewModelScope.launch {
            repository.getPokemonDetails(id)
        }
    }

    fun getPokemonList(){
        viewModelScope.launch {
            repository.getPokemonList()
        }
    }

}