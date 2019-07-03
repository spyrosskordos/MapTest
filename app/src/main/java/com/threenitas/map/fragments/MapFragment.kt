package com.threenitas.map.fragments


import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.threenitas.map.R
import android.widget.AutoCompleteTextView
import android.support.v7.app.AppCompatActivity;
import com.threenitas.map.models.Prediction
import com.threenitas.map.models.Predictions
import kotlinx.android.synthetic.main.fragment_map.*
import android.widget.Toast
import android.util.AttributeSet
import com.threenitas.map.adapters.PlacesAutoCompleteAdapter

class MapFragment : Fragment(),OnMapReadyCallback {
  private var googleMap: GoogleMap?=null

    lateinit var autoCompleteTextViewPlace: AutoCompleteTextView



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
    override fun onMapReady(googleMap: GoogleMap?) {
        this.googleMap = googleMap!!

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        autoCompleteTextViewPlace = view!!.findViewById(R.id.autoCompleteTextViewPlace) as AutoCompleteTextView
        loadData()
    }

    private fun loadData() {
        val placesAutoCompleteAdapter: PlacesAutoCompleteAdapter
        val predictions: MutableList<Prediction> = ArrayList()
        placesAutoCompleteAdapter = PlacesAutoCompleteAdapter(activity.applicationContext, predictions)

        autoCompleteTextViewPlace.threshold = 1
        autoCompleteTextViewPlace.setAdapter(placesAutoCompleteAdapter)
    }
}// Required empty public constructor