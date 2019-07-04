package com.threenitas.map.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.threenitas.map.R
import android.widget.AutoCompleteTextView
import android.os.Build
import com.threenitas.map.models.Prediction

import android.util.Log
import com.google.android.gms.maps.*
import com.threenitas.map.adapters.PlacesAutoCompleteAdapter
import kotlinx.android.synthetic.*
import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.support.v4.app.ActivityCompat
import android.widget.Toast
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices


class MapFragment : Fragment(){
   private lateinit var googleMap: GoogleMap
    lateinit var mapView: MapView
    lateinit var autoCompleteTextViewPlace: AutoCompleteTextView
    var mLastKnowLocation:Location?=null
    private var mFusedLocationClient: FusedLocationProviderClient? = null
    var location:Location?=null
    companion object {
        private val MY_PERMISSION_FINE_LOCATION = 101
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater?, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater!!.inflate(R.layout.fragment_map, container, false)

        return rootView
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        autoCompleteTextViewPlace = view!!.findViewById(R.id.autoCompleteTextViewPlace) as AutoCompleteTextView

        mapView=view.findViewById<MapView>(R.id.map_view) as MapView
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(OnMapReadyCallback {
            googleMap=it

            if (ActivityCompat.checkSelfPermission(context,
                    Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                googleMap.isMyLocationEnabled = true

            }
            else {//condition for Marshmello and above
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), MY_PERMISSION_FINE_LOCATION)
                }
            }



            Log.d("GoogleMap", "before isMyLocationEnabled")
            //googleMap.setMyLocationEnabled(true)
            mapView.onResume()




        })

        loadData()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            MY_PERMISSION_FINE_LOCATION -> if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {//permission to access location grant
                if (ActivityCompat.checkSelfPermission(context,
                        Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    googleMap.isMyLocationEnabled = true
                }
            }
            //permission to access location denied
            else {
                Toast.makeText(context, "This app requires location permissions to be granted", Toast.LENGTH_LONG).show()

            }
        }
    }

    private fun loadData() {
        val placesAutoCompleteAdapter: PlacesAutoCompleteAdapter
        val predictions: MutableList<Prediction> = ArrayList()
        placesAutoCompleteAdapter = PlacesAutoCompleteAdapter(activity.applicationContext, predictions)

        autoCompleteTextViewPlace.threshold = 1
        autoCompleteTextViewPlace.setAdapter(placesAutoCompleteAdapter)

    }
}// Required empty public constructor