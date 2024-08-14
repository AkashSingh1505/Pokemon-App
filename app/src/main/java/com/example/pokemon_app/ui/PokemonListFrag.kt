package com.example.pokemon_app.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokemon_app.databinding.FragmentPokemonListBinding
import com.example.pokemon_app.factory.GenericVMFactory
import com.example.pokemon_app.viewmodels.MainViewModel
import com.finhaat.aarogyahaat.adapter.PokemonListRVAdapter
import kotlinx.coroutines.launch
import javax.inject.Inject

class PokemonListFrag : Fragment() {
    lateinit var binding: FragmentPokemonListBinding

    @Inject
    lateinit var genericVMFactory: GenericVMFactory
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (this.requireContext().applicationContext as PokemonApp).applicationComponent.injectPokemonListFrag(
            this
        )
        mainViewModel = ViewModelProvider(
            this.requireActivity(),
            genericVMFactory
        ).get(MainViewModel::class.java)

        mainViewModel.getPokemonList()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPokemonListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.pokemonListRv.layoutManager = LinearLayoutManager(context)

        lifecycleScope.launch {
            mainViewModel.pokemonListResponse.collect { response ->
                Log.d("PokemonListFrag", "Response: $response")
                val results = response?.results ?: emptyList()
                if (results.isNotEmpty()) {
                    binding.pokemonListRv.adapter = PokemonListRVAdapter(
                        requireContext(),
                        results,
                        mainViewModel
                    )
                }
            }

        }
    }
}
