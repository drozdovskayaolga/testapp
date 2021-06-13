package com.example.testapplication.ui.map

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testapplication.R
import com.example.testapplication.model.Places
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions

class MapFragment : Fragment(R.layout.fragment_map), OnMapReadyCallback {

    private val viewModel: MapViewModel by viewModels()

    private val adapter = PlaceListAdapter()

    private var googleMap: GoogleMap? = null
    private val mapView by lazy {
        requireView().findViewById<MapView>(R.id.map_view)
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map
        viewModel.loadPlacesList()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.places.observe(viewLifecycleOwner) {
            adapter.submitList(it)
            addPlaceMarks(it)
        }

        val rvPlaces = view.findViewById<RecyclerView>(R.id.rv_place_list)
        val toolbarTitle = view.findViewById<TextView>(R.id.toolbar_title)

        rvPlaces.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )

        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)

        toolbarTitle.text = requireArguments().getString("email")

        val layoutManager = LinearLayoutManager(requireContext())
        rvPlaces.layoutManager = layoutManager
        rvPlaces.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    private fun addPlaceMarks(items: List<Places>) {

        val latLngBounds = LatLngBounds.Builder()

        items.forEach {
            val latLng = LatLng(it.lat, it.lng)
            latLngBounds.include(latLng)
            googleMap?.addMarker(MarkerOptions().position(latLng).title(it.name))
        }

        googleMap?.moveCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds.build(), 70))
    }
}