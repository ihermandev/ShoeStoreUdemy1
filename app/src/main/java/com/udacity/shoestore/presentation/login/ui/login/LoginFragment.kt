package com.udacity.shoestore.presentation.login.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.udacity.shoestore.BuildConfig
import com.udacity.shoestore.R
import com.udacity.shoestore.data.models.Shoe
import com.udacity.shoestore.data.models.User
import com.udacity.shoestore.databinding.FragmentLoginBinding
import timber.log.Timber

class LoginFragment : Fragment() {

    private lateinit var loginViewModel: LoginViewModel

    // Instance of Shoe data class.
    private val user: User = User()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentLoginBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_login,
            container,
            false
        )

        if (BuildConfig.DEBUG) {
            populateDefaultUserData(user)
        }

        binding.user = user

        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        binding.btnLogin.setOnClickListener { view: View ->
            Timber.i(
                "User data: email: ${binding.etUsername.text.toString()}, " +
                        "password: ${binding.etPassword.text.toString()}"
            )
            moveForward(view)
        }

        binding.btnRegister.setOnClickListener { view: View ->
            moveForward(view)
        }

        return binding.root
    }

    /**
     *  Pre-populate user data for debug build
     */
    private fun populateDefaultUserData(user: User) {
        user.apply {
            email = "johndoe@example.com"
            password = "q1w2e3r4"
        }
    }

    private fun moveForward(view: View) {
        view.findNavController()
            .navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment())
    }
}
