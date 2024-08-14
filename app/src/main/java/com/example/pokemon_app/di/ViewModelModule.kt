package com.example.pokemon_app.di

import androidx.lifecycle.ViewModel
import com.example.pokemon_app.viewmodels.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @ClassKey(MainViewModel::class)
    @IntoMap
    abstract fun provideMainViewModel(mainViewModel: MainViewModel): ViewModel



}