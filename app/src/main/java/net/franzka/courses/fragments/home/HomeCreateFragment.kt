package net.franzka.courses.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import net.franzka.courses.R
import net.franzka.courses.databinding.FragmentHomeCreateBinding
import net.franzka.courses.repository.HomeRepository
import net.franzka.courses.viewmodels.HomeViewModel
import net.franzka.courses.viewmodels.HomeViewModelFactory

class HomeCreateFragment : Fragment() {

    companion object {
        private const val TAG = "HomeCreateFragment"
    }

    private lateinit var binding: FragmentHomeCreateBinding

    private val homeViewModel: HomeViewModel by activityViewModels {
        HomeViewModelFactory(HomeRepository(), requireNotNull(this.activity).application )
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_create, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = homeViewModel
            homeCreateFragment = this@HomeCreateFragment
        }
    }

    fun submit() {
        homeViewModel.create(binding.edHomeName.text.toString())
    }

    fun back() {
        findNavController().navigateUp()
    }

}