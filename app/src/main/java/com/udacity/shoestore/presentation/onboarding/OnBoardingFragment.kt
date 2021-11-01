package com.udacity.shoestore.presentation.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentOnBoardingBinding

class OnBoardingFragment : Fragment() {
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

        return binding.root
    }
}
