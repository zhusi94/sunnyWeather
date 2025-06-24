package com.sunnyweather.android.ui.place

import androidx.lifecycle.*
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sunnyweather.android.logic.Repository
import com.sunnyweather.android.logic.model.Geocodes

class PlaceViewModel : ViewModel() {

    private val searchLiveData = MutableLiveData<String>()

    val placeList = ArrayList<Geocodes>()

    val placeLiveData = searchLiveData.switchMap() { query ->
        Repository.searchPlaces(query)
    }

    fun searchPlaces(query: String) {
        searchLiveData.value = query
    }

    fun  savePlace(place: Geocodes) = Repository.savePlace(place)

    fun  getSavePlace() = Repository.getSavePlace()

    fun isPlaceSaved() = Repository.isPlaceSaved()

}