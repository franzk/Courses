package net.franzka.courses.fragments

import android.content.*
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import net.franzka.courses.CoursesViewModel
import net.franzka.courses.CoursesViewModelFactory
import net.franzka.courses.R
import net.franzka.courses.databinding.FragmentMainBinding
import net.franzka.courses.repository.Repository

class MainFragment : Fragment() {

    companion object {
        private const val TAG = "MainFragment"
    }

    private val coursesViewModel: CoursesViewModel by activityViewModels {
        CoursesViewModelFactory(Repository(), requireNotNull(this.activity).application )
    }

    private lateinit var binding: FragmentMainBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        // si la connexion de l'tilisateur n'est pas enregistrée, on affiche l'écran de sign in
        coursesViewModel.loadShardPrefs()
        if (coursesViewModel.token.value.isNullOrEmpty()) { findNavController().navigate(R.id.signInFragment) }


        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = coursesViewModel
            fragmentMain = this@MainFragment
        }
        coursesViewModel.authenticated.observe(viewLifecycleOwner,  {
            if ((it == null) || (!it))
                findNavController().navigate(R.id.signInFragment)
        })

    }

    fun changePassword() {
        findNavController().navigate(R.id.changePasswordFragment)
    }

    fun signOut() {
        coursesViewModel.signOut()
        findNavController().navigate(R.id.signInFragment)
    }

}