package com.android.paysafe.ui.stores

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.paysafe.databinding.StoreListFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class StoreListFragment : Fragment() {

    companion object {
        fun newInstance() = StoreListFragment()
    }

    private val viewModel: StoreListViewModel by viewModel()
    private lateinit var binding: StoreListFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = StoreListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.stores.observe(viewLifecycleOwner) {

        }
    }
}