package com.example.pokemon_app.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.load
import coil.request.ImageRequest
import com.example.pokemon_app.R
import com.example.pokemon_app.databinding.FragmentPokemonDetailsBinding
import com.example.pokemon_app.factory.GenericVMFactory
import com.example.pokemon_app.viewmodels.MainViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class PokemonDetailsFrag : Fragment() {
    lateinit var binding: FragmentPokemonDetailsBinding

    @Inject
    lateinit var genericVMFactory: GenericVMFactory
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (this.requireContext().applicationContext as PokemonApp).applicationComponent.injectPokemonDetailsFrag(
            this
        )
        mainViewModel = ViewModelProvider(
            this.requireActivity(),
            genericVMFactory
        ).get(MainViewModel::class.java)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPokemonDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            mainViewModel.pokemonDetailsResponse.collect { response ->
                Log.d("PokemonDetailsFrag", "Response: $response")
                val results = response?: null
                if (results!=null) {
                    binding.run {
                        pokemonName.text = results.name
                        pokemonStats.text = results.stats?.get(0).toString()
                        pokemonHeightWeight.text = "Height : ${results.height} and Weight : ${results.weight}"
                        pokemonBaseExperience.text= " Base Exp : ${results.base_experience}"
                        pokemonAbilities.text="Abilitie: ${results.abilities?.get(0)?.ability?.name}"
                        pokemonImage.load(results.sprites!!.front_default)
                    }
                }
            }

        }
    }

    fun ImageView.loadUrl(url: String) {
        val imageLoader = ImageLoader.Builder(context)
            .components { add(SvgDecoder.Factory()) }
            .build()

        val request = ImageRequest.Builder(context)
            .crossfade(true)
            .crossfade(500)
            .placeholder(R.drawable.img)
            .data(url)
            .target(this)
            .build()

        imageLoader.enqueue(request)
    }

}