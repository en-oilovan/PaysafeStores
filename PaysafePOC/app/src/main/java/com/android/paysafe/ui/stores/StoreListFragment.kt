package com.android.paysafe.ui.stores

import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.android.paysafe.databinding.StoreListFragmentBinding
import com.android.paysafe.ui.stores.model.ScreenState
import com.android.paysafe.utils.isPermissionGranted
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber
import java.lang.Error

class StoreListFragment : Fragment() {

    companion object {
        fun newInstance() = StoreListFragment()
    }

    private lateinit var binding: StoreListFragmentBinding
    private val viewModel: StoreListViewModel by viewModel()
    private val adapter = StoreListAdapter()
    private val requestMultiplePermissions =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
            checkPermissions()
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = StoreListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.storesList.adapter = adapter

        requestMultiplePermissions.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )

        viewModel.loadingState.observe(viewLifecycleOwner) {
            when(it) {
                ScreenState.Loading -> binding.progressBar.visibility = View.VISIBLE
                ScreenState.Normal -> binding.progressBar.visibility = View.GONE
                is ScreenState.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), it.msg, Toast.LENGTH_LONG).show()
                }
            }
        }

        viewModel.stores.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    @SuppressLint("MissingPermission")
    private fun checkPermissions() {
        if (requireContext().isPermissionGranted(Manifest.permission.ACCESS_FINE_LOCATION) ||
            requireContext().isPermissionGranted(Manifest.permission.ACCESS_COARSE_LOCATION)
        ) {
            viewModel.registerLocationListener()
            viewModel.getStoresForCurrentLocation()
        }
    }
}