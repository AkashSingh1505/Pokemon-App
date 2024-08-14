package com.example.pokemon_app.ui

import android.app.Application
import android.util.Log
import com.example.pokemon_app.ApplicationComponent.ApplicationComponent
import com.example.pokemon_app.ApplicationComponent.DaggerApplicationComponent

import javax.inject.Inject

class PokemonApp:Application() {
    lateinit var applicationComponent: ApplicationComponent
    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.builder().build()

    }
}