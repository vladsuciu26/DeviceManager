package com.example.summership2023cluj.ui.profile

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.summership2023cluj.common.utils.DateHelper
import com.example.summership2023cluj.databinding.FragmentProfileBinding
import com.example.summership2023cluj.ui.Navigator
import com.example.summership2023cluj.ui.profile.viewmodel.ProfileViewModel
import kotlinx.coroutines.launch

class ProfileFragment : Fragment() {
    private val viewModel: ProfileViewModel by viewModels()
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val view = binding.root
        collectState()

        setupLogoutButton()

        return view
    }

    private fun setupLogoutButton() {
        binding.logOutButton.setOnClickListener {
            lifecycleScope.launch {
                viewModel.logout()
                viewModel.uiLogoutState.collect { state ->
                    if (state?.logoutState != null) {
                        if (state.logoutState.success != null) {
                            Navigator.getInstance().openLoginFragment()
                        }
                    }
                }
            }
        }
    }

    private fun collectState() {
        lifecycleScope.launch {
            viewModel.loadProfile()
            viewModel.uiProfileState.collect { state ->
                if (state?.profileState != null) {
                    val firstname = state.profileState.firstname
                    val lastname = state.profileState.lastname
                    val mail = state.profileState.mail
                    val startDate = state.profileState.startDate
                    val admin = state.profileState.admin

                    binding.usernameInputField.text = "$firstname $lastname"
                    binding.emailInputField.text = "$mail"
                    binding.startDateInputField.text = DateHelper.convertFromDateToDate(
                        startDate,
                        DateHelper.FULL_DATE_FORMAT, DateHelper.YEAR_MONTH_DATE_FORMAT
                    )

                    Navigator.getInstance().setNavigationBarVisible()
                }
            }
        }
    }
}