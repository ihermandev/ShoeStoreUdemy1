package com.udacity.shoestore.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.data.MockData
import com.udacity.shoestore.data.models.Shoe

class SharedViewModel : ViewModel() {

    private val _isUserLoggedIn = MutableLiveData<Boolean>()
    val isUserLoggedIn: LiveData<Boolean>
        get() = _isUserLoggedIn

    private val _shoeList = MutableLiveData<List<Shoe>>()
    val shoeList: LiveData<List<Shoe>>
        get() = _shoeList

    init {
        _isUserLoggedIn.postValue(false)
    }

    fun onUserLoggedIn() {
        _isUserLoggedIn.postValue(true)
        populateShoeList(MockData.listOfShoe)
    }

    fun onUserLogOut() {
        _isUserLoggedIn.postValue(false)
    }

    fun populateShoeList(data: List<Shoe>) {
        _shoeList.postValue(data)
    }

}
