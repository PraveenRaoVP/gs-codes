// Copyright 2020 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.codelabs.buildyourfirstmap

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.Circle
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.codelabs.buildyourfirstmap.place.Place
import com.google.codelabs.buildyourfirstmap.place.PlaceRenderer
import com.google.codelabs.buildyourfirstmap.place.PlacesReader
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.ktx.addCircle
import com.google.maps.android.ktx.addMarker
import com.google.maps.android.ktx.awaitMap
import com.google.maps.android.ktx.awaitMapLoad
import kotlinx.coroutines.launch
import kotlin.math.cos
import kotlin.math.sin

class MainActivity : AppCompatActivity() {
    private val places: List<Place> by lazy {
        PlacesReader(this).read()
    }

    private var circle: Circle? = null

    private val bicycleIcon: BitmapDescriptor by lazy {
        val color = ContextCompat.getColor(this, R.color.colorPrimary)
        BitmapHelper.vectorToBitmap(this, R.drawable.ic_directions_bike_black_24dp, color)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mapFragment = supportFragmentManager.findFragmentById(
            R.id.map_fragment
        ) as SupportMapFragment
//        mapFragment?.getMapAsync { googleMap ->
////            addMarkers(googleMap)
//            addClusteredMarkers(googleMap)
//            googleMap.setInfoWindowAdapter(MarkerInfoWindowAdapter(this))
//            googleMap.setOnMapLoadedCallback {
//                val bounds = LatLngBounds.builder()
//                places.forEach { place ->
//                    bounds.include(place.latLng)
//                }
//                googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds.build(), 20))
//            }
//        }
        lifecycleScope.launchWhenCreated {
            val googleMap = mapFragment.awaitMap()
            googleMap.awaitMapLoad()
            val bounds = LatLngBounds.builder()
            places.forEach{ place ->
                bounds.include(place.latLng)
            }
            googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds.build(), 20))
            addClusteredMarkers(googleMap)
        }

    }

    private fun addClusteredMarkers(googleMap: GoogleMap) {
        val clusterManager = ClusterManager<Place>(this, googleMap) // Create a ClusterManager
        clusterManager.renderer = PlaceRenderer(
            this,
            googleMap,
            clusterManager
        )

        clusterManager.markerCollection.setInfoWindowAdapter(MarkerInfoWindowAdapter(this)) // Set custom info window adapter

        clusterManager.addItems(places) // Add the places to the ClusterManager

        clusterManager.cluster()

        googleMap.setOnCameraIdleListener {
            // When the camera stops moving, change the alpha value back to opaque.
            clusterManager.markerCollection.markers.forEach { it.alpha = 1.0f }
            clusterManager.clusterMarkerCollection.markers.forEach { it.alpha = 1.0f }

            // Call clusterManager.onCameraIdle() when the camera stops moving so that reclustering
            // can be performed when the camera stops moving.
            clusterManager.onCameraIdle()
        }

        clusterManager.setOnClusterItemClickListener { item ->
            addCircle(googleMap, item)
            return@setOnClusterItemClickListener false
        }

        googleMap.setOnCameraMoveListener {
            clusterManager.markerCollection.markers.forEach { it.alpha = 0.3f }
            clusterManager.clusterMarkerCollection.markers.forEach { it.alpha = 0.3f }
        }
    }

    fun calculateCirclePoints(center: LatLng, radius: Double, numPoints: Int = 360): List<LatLng> {
        val points = mutableListOf<LatLng>()
        val earthRadius = 6371000.0 // Radius of the Earth in meters

        for (i in 0 until numPoints) {
            val angle = 2 * Math.PI * i / numPoints
            val dx = radius * cos(angle)
            val dy = radius * sin(angle)

            // Convert to latitude and longitude
            val deltaLat = dy / earthRadius
            val deltaLon = dx / (earthRadius * cos(Math.toRadians(center.latitude)))

            val pointLat = center.latitude + Math.toDegrees(deltaLat)
            val pointLon = center.longitude + Math.toDegrees(deltaLon)

            points.add(LatLng(pointLat, pointLon))
        }

        return points
    }

    fun addCircle(googleMap: GoogleMap, item: Place) {
        circle?.remove()
        circle = googleMap.addCircle {
            center(item.latLng)
            radius(1000.0)
            fillColor(ContextCompat.getColor(this@MainActivity, R.color.colorPrimaryTranslucent))
            strokeColor(ContextCompat.getColor(this@MainActivity, R.color.colorPrimary))
        }
        Log.i("MainActivity", calculateCirclePoints(item.latLng, 1000.0).toString())
    }

    private fun addMarkers(googleMap: GoogleMap) {
        places.forEach { place ->
            val marker = googleMap.addMarker {
                title(place.name)
                position(place.latLng)
                icon(bicycleIcon)
            }
            marker?.tag = place
        }
    }
}
