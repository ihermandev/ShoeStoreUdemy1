package com.udacity.shoestore.presentation.login.ui.login

import androidx.lifecycle.ViewModel
import com.udacity.shoestore.data.models.User
import timber.log.Timber

class LoginViewModel : ViewModel() {

    //TODO implement mock login
    fun login(user: User) {}
    //TODO implement mock logout
    fun logOut() {}

    // A placeholder email validation check
    private fun isEmailValid(username: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(username).matches()
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 6
    }

    override fun onCleared() {
        super.onCleared()
        Timber.d("onCleared called")
    }
}
