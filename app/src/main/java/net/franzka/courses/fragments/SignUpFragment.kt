package net.franzka.courses.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import net.franzka.courses.CoursesViewModel
import net.franzka.courses.CoursesViewModelFactory
import net.franzka.courses.R
import net.franzka.courses.databinding.FragmentSignUpBinding
import net.franzka.courses.repository.Repository

class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding

    private val coursesViewModel: CoursesViewModel by viewModels {
        CoursesViewModelFactory(Repository(), requireNotNull(this.activity).application )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = coursesViewModel
            signUpFragment = this@SignUpFragment
        }

    }

    fun signUp() {
        coursesViewModel.signUp(binding.edUsername.text.toString(), binding.edEmail.text.toString(), binding.edPassword.text.toString())
    }

    fun backToLogin() {
        findNavController().navigate(R.id.signInFragment)
    }

}