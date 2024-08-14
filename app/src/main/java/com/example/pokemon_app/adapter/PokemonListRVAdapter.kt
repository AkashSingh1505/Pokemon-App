package com.finhaat.aarogyahaat.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.example.pokemon_app.R
import com.example.pokemon_app.viewmodels.MainViewModel
import com.example.pokemon_assignment.model.PokemonListResponse
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.temporal.ChronoUnit

class PokemonListRVAdapter(
    private val context: Context,
    private val pokemonList: List<com.example.pokemon_assignment.model.Result?>,
    private val viewModel: MainViewModel
) : RecyclerView.Adapter<PokemonListRVAdapter.PokemonListRVAdapterViewHolder>() {

    class PokemonListRVAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageIV: ImageView
        var nameTv: TextView
        var detailsBtn: TextView

        init {
            imageIV = itemView.findViewById(R.id.image_iv)
            nameTv = itemView.findViewById(R.id.name_tv)
            detailsBtn = itemView.findViewById(R.id.details_btn)
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PokemonListRVAdapterViewHolder {
        return PokemonListRVAdapterViewHolder(
            LayoutInflater.from(context).inflate(R.layout.pokemon_list_item_layout, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    override fun onBindViewHolder(holder: PokemonListRVAdapterViewHolder, position: Int) {
        val pokemon = pokemonList.get(position)
        holder.nameTv.text = pokemon!!.name!!.toUpperCase()
        var id = extractIdFromUrl(pokemon.url!!)!!.toInt()

        holder.imageIV.loadUrl("https://unpkg.com/pokeapi-sprites@2.0.2/sprites/pokemon/other/dream-world/${id}.svg")
        val navController = (context as AppCompatActivity).findNavController(R.id.nav_host_fragment_container) // or other way to obtain NavController


        holder.detailsBtn.setOnClickListener {
            viewModel.getPokemonDetails(id)
            navController.navigate(R.id.action_pokemonListFrag_to_pokemonDetailsFrag)

        }

    }

    fun extractIdFromUrl(url: String): Int? {
        val regex = """\/pokemon\/(\d+)\/""".toRegex()
        val matchResult = regex.find(url)
        return matchResult?.groupValues?.get(1)?.toInt()
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
