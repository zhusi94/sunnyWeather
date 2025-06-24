package com.sunnyweather.android.ui.place

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.sunnyweather.android.MainActivity
import com.sunnyweather.android.R
import com.sunnyweather.android.logic.model.Geocodes
import com.sunnyweather.android.ui.weather.WeatherActivity

class PlaceAdapter(private val fragment: PlaceFragment, private val placeList: List<Geocodes>) :
    RecyclerView.Adapter<PlaceAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val placeName: TextView = view.findViewById(R.id.placeName)
        val placeAddress: TextView = view.findViewById(R.id.placeAddress)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.palce_item, parent, false)
        val holder = ViewHolder(view)
        holder.itemView.setOnClickListener {
            val position = holder.adapterPosition
            val place = placeList[position]
            val activity = fragment.activity
            if (activity is WeatherActivity) {
                Log.d(Companion.TAG, "onCreateViewHolder: weatherActivity ")
                activity.findViewById<DrawerLayout>(R.id.drawerLayout).closeDrawers()
                activity.viewModel.locationLng = place.location.split(",")[0]
                activity.viewModel.locationLng = place.location.split(",")[1]
                activity.viewModel.placeName = place.city
                activity.refreshWeather()
            } else {
                Log.d(Companion.TAG, "onCreateViewHolder: NoWeatherActivity ")
                val intent = Intent(parent.context, WeatherActivity::class.java).apply {
                    putExtra("location_lng", place.location.split(",")[0])
                    putExtra("location_lat", place.location.split(",")[1])
                    putExtra("place_name", place.city)
                }
                fragment.startActivity(intent)
                fragment.activity?.finish()
            }
            fragment.viewModel.savePlace(place)
        }
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val place = placeList[position]
        holder.placeName.text = place.city
        holder.placeAddress.text = place.address
    }

    override fun getItemCount(): Int = placeList.size

    companion object {
        private const val TAG = "PlaceAdapter"
    }

}