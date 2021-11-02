package com.udacity.shoestore.presentation.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentOnBoardingBinding
import com.udacity.shoestore.presentation.SharedViewModel

class OnBoardingFragment : Fragment() {

    private val viewModel: SharedViewModel by lazy {
        ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentOnBoardingBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_on_boarding,
            container,
            false
        )

        binding.btnNext.setOnClickListener { view: View ->
            viewModel.onUserLoggedIn()
            view.findNavController()
                .navigate(OnBoardingFragmentDirections.actionOnBoardingFragmentToShoeListFragment())
        }

        return binding.root
    }
}
