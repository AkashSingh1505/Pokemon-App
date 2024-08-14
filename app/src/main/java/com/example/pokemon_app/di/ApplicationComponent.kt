package com.example.pokemon_app.ApplicationComponent

import androidx.lifecycle.ViewModel
import com.example.pokemon_app.MainActivity
import com.example.pokemon_app.ui.PokemonDetailsFrag
import com.example.pokemon_app.ui.PokemonListFrag
import com.example.pokemon_app.di.NetworkModule
import com.example.pokemon_app.di.ViewModelModule
import dagger.Component
import javax.inject.Singleton



@Singleton
@Component(modules = [ViewModelModule::class, NetworkModule::class])
interface ApplicationComponent {

    fun injectMainActivity(target: MainActivity)
    fun injectPokemonListFrag(target: PokemonListFrag)
    fun injectPokemonDetailsFrag(target: PokemonDetailsFrag)
    fun getMap():Map<Class<*>, ViewModel>

}
