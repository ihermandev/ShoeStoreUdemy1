package com.udacity.shoestore.presentation.shoedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.data.models.Shoe
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.presentation.SharedViewModel
import timber.log.Timber

class ShoeDetailFragment : Fragment() {

    private val viewModel: SharedViewModel by lazy {
        ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
    }

    // Instance of Shoe data class.
    private val userShoeData: Shoe = Shoe()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentShoeDetailBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_shoe_detail,
            container,
            false
        )

        binding.shoeItem = userShoeData

        binding.btnCancel.setOnClickListener { view: View ->
            view.findNavController()
                .navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment())
        }

        binding.btnSave.setOnClickListener { view: View ->
            if (addShoeItem(binding)) {
                view.findNavController()
                    .navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment())
            } else {
                Toast.makeText(requireContext(), "Can't save empty fields", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        return binding.root
    }

    /**
     * Method for adding shoe item to shoeList from SharedViewModel
     * @param binding needed for two way binding observation
     * @return boolean value depending on whether the item was added or not
     */
    private fun addShoeItem(binding: FragmentShoeDetailBinding): Boolean {
        val name = binding.etName.text.toString()
        val company = binding.etCompany.text.toString()
        val size = binding.etSize.text.toString().toDoubleOrNull()
        val desc = binding.etDesc.text.toString()

        if (name.isBlank() || company.isBlank() || desc.isBlank() || size == 0.0 || size == null) {
            return false
        } else {
            userShoeData.apply {
                this.name = name
                this.company = company
                this.size = size
                this.description = desc

            }

            Timber.i("Shoe item added --> $userShoeData")

            viewModel.addShoeToList(userShoeData)

            return true
        }
    }
}
