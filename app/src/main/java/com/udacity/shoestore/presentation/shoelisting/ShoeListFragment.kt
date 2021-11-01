package com.udacity.shoestore.presentation.shoelisting

import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.setPadding
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.data.models.Shoe
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.presentation.SharedViewModel
import timber.log.Timber


class ShoeListFragment : Fragment() {

    private val viewModel: SharedViewModel by lazy {
        ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentShoeListBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_shoe_list,
            container,
            false
        )

        viewModel.isUserLoggedIn.observe(viewLifecycleOwner, { isLoggedIn ->
            if (!isLoggedIn) {
                findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToLoginFragment())
            }
        })

        viewModel.shoeList.observe(viewLifecycleOwner, Observer { list ->
            Timber.i("Shoe list -> $list")
            list.forEach { shoe ->
                addViewToList(shoe, binding)
            }
        })


        binding.fabAddShoe.setOnClickListener { view: View ->
            view.findNavController()
                .navigate(ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment())
        }

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.logout -> viewModel.onUserLogOut()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun addViewToList(item: Shoe, binding: FragmentShoeListBinding) {
        val layout = binding.shoeContainer
        val context = layout.context

        val childLayout = LinearLayout(context)

        childLayout.apply {
            orientation = LinearLayout.VERTICAL
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            ).apply {
                setMargins(0, 0, 0, 16)
            }

            background = ContextCompat.getDrawable(context, R.drawable.child_view_border)
            setPadding(8)
        }

        val name = TextView(context)
        name.apply {
            textSize = 16f
            text = getString(R.string.shoe_item_name, item.name)
        }

        val desc = TextView(context)
        desc.apply {
            textSize = 16f
            text = getString(R.string.shoe_item_desc, item.description)
        }

        val size = TextView(context)
        size.apply {
            textSize = 14f
            text = getString(R.string.shoe_item_size, item.size.toString())

        }

        val company = TextView(context)
        company.apply {
            textSize = 14f
            text = getString(R.string.shoe_item_company, item.company)
        }

        childLayout.apply {
            addView(name)
            addView(desc)
            addView(size)
            addView(company)
        }

        layout.addView(childLayout)
    }


}
