package com.example.summership2023cluj.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.summership2023cluj.R
import com.example.summership2023cluj.data.dto.User
import com.example.summership2023cluj.databinding.FragmentLoginBinding
import com.example.summership2023cluj.ui.Navigator
import com.example.summership2023cluj.ui.login.viewmodel.LoginViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {
    private val viewModel: LoginViewModel by viewModels()

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root
        setListeners()
        return view
    }

    private fun setListeners() {
        binding.signUpButton.setOnClickListener {
            if (areFieldsEmpty()) {
                showSnackbar(requireView(), getString(R.string.message_username_password_empty))
            } else {
                val username = binding.usernameInputField.text.toString()
                val password = binding.passwordInputField.text.toString()
                viewModel.authenticate(User(username, password))
                observeLoginState()
            }
        }
    }

    private fun showSnackbar(view: View, message: String) {
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()
    }

    private fun areFieldsEmpty(): Boolean {
        val usernameInputField = binding.usernameInputField
        val passwordInputField = binding.passwordInputField

        if (usernameInputField.text.toString().isEmpty()) {
            usernameInputField.error = getString(R.string.invalid_username)
            return true
        }

        if (passwordInputField.text.toString().isEmpty()) {
            passwordInputField.error = getString(R.string.invalid_password)
            return true
        }
        return false
    }

    private fun observeLoginState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.viewModelState.collect { state ->
                    checkStateStatus(state)
                }
            }

        }
    }

    private fun checkStateStatus(state: LoginViewModel.LoginState) {
        state.isLogin?.let { isSuccess ->
            if (isSuccess) {
                context?.let {
                    Navigator.getInstance().openProfileFragment()
                }
            } else {
                showSnackbar(requireView(), getString(R.string.authentication_failed))
            }
        }
    }
}