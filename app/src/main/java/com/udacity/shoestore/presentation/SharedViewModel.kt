package com.udacity.shoestore.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.data.MockData
import com.udacity.shoestore.data.models.Shoe
import timber.log.Timber

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
        //Check if shoe list is empty and populate it with data if needed
        if (_shoeList.value.isNullOrEmpty()) {
            populateShoeList(MockData.listOfShoe)
        }
    }

    fun onUserLogOut() {
        _isUserLoggedIn.postValue(false)
    }

    private fun populateShoeList(data: List<Shoe>) {
        _shoeList.postValue(data)
    }

    fun addShoeToList(shoe: Shoe) {
        val oldList = _shoeList.value
        val newList = oldList?.toMutableList()
        newList?.let { list ->
            //Add item at top of the list
            list.add(0, shoe)
            populateShoeList(list)
        }
    }

    override fun onCleared() {
        super.onCleared()
        Timber.d("onCleared called")
    }
}
