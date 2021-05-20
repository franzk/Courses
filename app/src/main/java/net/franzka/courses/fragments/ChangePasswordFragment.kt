package net.franzka.courses.fragments

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
import net.franzka.courses.databinding.FragmentChangePasswordBinding
import net.franzka.courses.repository.Repository

class ChangePasswordFragment : Fragment() {

    companion object {
        private const val TAG = "ChangePasswordFragment"
    }

    private lateinit var binding: FragmentChangePasswordBinding

    private val coursesViewModel: CoursesViewModel by activityViewModels {
        CoursesViewModelFactory(Repository(), requireNotNull(this.activity).application )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_change_password, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = coursesViewModel
            changePasswordFragment = this@ChangePasswordFragment
        }
    }

    fun submit() {
        coursesViewModel.changePassword(binding.edChangePassword.text.toString())
    }

    fun back() {
        findNavController().navigateUp()
    }

}