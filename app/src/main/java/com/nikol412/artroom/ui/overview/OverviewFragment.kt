package com.nikol412.artroom.ui.overview

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.nikol412.artroom.R
import com.nikol412.artroom.data.ArtAPI
import com.nikol412.artroom.data.GlideApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class OverviewFragment : Fragment() {

    private lateinit var viewModel: OverviewViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_overview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(OverviewViewModel::class.java)
        lifecycleScope.launch {
            val result = withContext(Dispatchers.IO) {
                ArtAPI.getApi().getArtworks(129884)
            }

            view.findViewById<TextView>(R.id.text_view_data)?.text = result.data.title

            val url = "${result.imageConfig.imageUrl}/${result.data.imageId}/full/843,/0/default.jpg"
            GlideApp.with(requireContext())
                .load(url)
                .into(view.findViewById(R.id.image_artwork))
        }
    }

}