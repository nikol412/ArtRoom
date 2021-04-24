package com.nikol412.artroom.ui.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.nikol412.artroom.R
import com.nikol412.artroom.data.ArtAPI
import com.nikol412.artroom.data.response.toArtworkObjectList
import com.nikol412.artroom.ui.overview.adapter.ArtworksAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class OverviewFragment : Fragment() {

    private lateinit var viewModel: OverviewViewModel

    private val adapter by lazy {
        ArtworksAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_overview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(OverviewViewModel::class.java)

        view.findViewById<RecyclerView>(R.id.recycler_view_artworks).adapter = adapter

        lifecycleScope.launch {
            val artworksPage = withContext(Dispatchers.IO) {
                ArtAPI.getApi().getArtsPage(1)
            }
            adapter.setItems(artworksPage.toArtworkObjectList().filter { it.imageId.isNullOrBlank().not() })
        }


    }

}