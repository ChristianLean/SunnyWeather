package com.example.sunnyweather.ui.place

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.sunnyweather.logic.Repository
import com.example.sunnyweather.logic.model.Place

/**
 * Created by ChristianL on 4/7/21
 */
class PlaceViewModel : ViewModel() {
    private val searchLiveData = MutableLiveData<String>()

    //in order to cache city data,for Example rotation the phone
    val placeList = ArrayList<Place>()

    //observe the object from repository model
    // now we can initiate the network response ,
    // only Need To transfer the searchPlaces() form repository,
    // and at the same time,we get a liveData OBJECT ${placeLiveData} which can observe by activity
    val placeLiveData = Transformations.switchMap(searchLiveData) { query ->
        Repository.searchPlaces(query)
    }

    fun searchPlaces(query: String) {
        searchLiveData.value = query
    }
}