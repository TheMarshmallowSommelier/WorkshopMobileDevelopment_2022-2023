package fr.epsi.workshopmobiledevelopment

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.activity.result.contract.ActivityResultContracts
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import org.json.JSONObject

class StoreMapsFragment : Fragment() {

    private lateinit var googleMap: GoogleMap

    private val callback = OnMapReadyCallback { googleMap ->
        this.googleMap = googleMap
        loadStoresFromApi()

        googleMap.setOnInfoWindowClickListener { marker ->
            val storeDetailsIntent = Intent(requireContext(), StoreDetailsActivity::class.java)
            val selectedStore = marker.tag as JSONObject
            storeDetailsIntent.putExtra("selectedStore", selectedStore.toString())
            startActivity(storeDetailsIntent)
        }
    }

    @SuppressLint("MissingPermission")
    val locationPermissionRequest = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        when {
            permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                // Precise location access granted.
                googleMap.isMyLocationEnabled = true
            }
            permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                // Only approximate location access granted.
                googleMap.isMyLocationEnabled = true
            }
            else -> {
                // No location access granted.
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_store_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    private fun loadStoresFromApi() {
        val url = "https://www.ugarit.online/epsi/stores.json"
        val queue = Volley.newRequestQueue(requireContext())

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                val stores = response.getJSONArray("stores")
                for (i in 0 until stores.length()) {
                    val store = stores.getJSONObject(i)
                    val position = LatLng(
                        store.getDouble("latitude"),
                        store.getDouble("longitude")
                    )
                    val markerOptions = MarkerOptions()
                        .position(position)
                        .title(store.getString("name"))
                    val marker = googleMap.addMarker(markerOptions)
                    if (marker != null) {
                        marker.tag = store
                    }
                }
                // Zoom on France
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(46.227638, 2.213749), 5f))
                Log.d("StoreMapsFragment", "Stores loaded successfully")
            },
            { error ->
                error.printStackTrace()
            }
        )
        queue.add(jsonObjectRequest)
    }
}
