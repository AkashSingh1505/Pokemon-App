package com.example.pokemon_assignment.model

data class PokemonListResponse(
    val count: Int?,
    val next: String?,
    val previous: Any?,
    val results: List<Result?>?
)

data class Result(
    val name: String?,
    val url: String?
)
