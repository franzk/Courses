package net.franzka.courses.fragments.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import net.franzka.courses.viewmodels.LoginViewModel
import net.franzka.courses.viewmodels.LoginViewModelFactory
import net.franzka.courses.R
import net.franzka.courses.databinding.FragmentSignInBinding
import net.franzka.courses.repository.LoginRepository

class SignInFragment : Fragment() {

    companion object {
        private const val TAG = "SignInFragment"
    }

    private lateinit var binding: FragmentSignInBinding

    private val loginViewModel: LoginViewModel by activityViewModels {
        LoginViewModelFactory(LoginRepository(), requireNotNull(this.activity).application )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_in, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = loginViewModel
            signInFragment = this@SignInFragment
        }

        loginViewModel.authenticated.observe(viewLifecycleOwner,  {
            it?.let {
                if (it) findNavController().navigate(R.id.mainFragment)
            }
        })

    }

    fun signIn() {
        loginViewModel.signIn(binding.edLogin.text.toString(), binding.edPassword.text.toString())
    }

    fun signUp() {
        findNavController().navigate(R.id.signUpFragment)
    }

    fun forgottenPassword() {
        findNavController().navigate(R.id.forgottenPasswordFragment)
    }

}