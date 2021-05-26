package net.franzka.courses.fragments.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import net.franzka.courses.R
import net.franzka.courses.adapters.HomeAdapter
import net.franzka.courses.adapters.HomeClickListener
import net.franzka.courses.databinding.FragmentHomeListBinding
import net.franzka.courses.repository.HomeRepository
import net.franzka.courses.utils.Utils
import net.franzka.courses.viewmodels.HomeViewModel
import net.franzka.courses.viewmodels.HomeViewModelFactory

class HomeListFragment : Fragment() {

    companion object {
        private const val TAG = "HomeListFragment"
    }

    private lateinit var binding: FragmentHomeListBinding

    private val homeViewModel: HomeViewModel by activityViewModels {
        HomeViewModelFactory(HomeRepository(), requireNotNull(this.activity).application )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_list, container, false)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = homeViewModel
            homeListFragment = this@HomeListFragment
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val homeAdapter = HomeAdapter(HomeClickListener { /*home -> openHome(home)*/ })
        homeAdapter.setHasStableIds(true)

        homeViewModel.myHomes.observe(viewLifecycleOwner, {
            homeAdapter.submitList(it)
        })

        binding.recyclerviewHomes.apply {
            adapter = homeAdapter
            animation = null
        }

        homeViewModel.getMyHomes(Utils.getAppToken())
    }

    fun create() {
        findNavController().navigate(R.id.homeCreateFragment)
    }

}