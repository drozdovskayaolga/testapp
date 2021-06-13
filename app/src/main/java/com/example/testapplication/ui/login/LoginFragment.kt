package com.example.testapplication.ui.login

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.example.testapplication.R
import com.example.testapplication.model.ValidationStatus

class LoginFragment : Fragment(R.layout.fragment_login) {

    private val viewModel: LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val button = view.findViewById<Button>(R.id.button)
        val etEmail = view.findViewById<EditText>(R.id.et_email)
        val etPassword = view.findViewById<EditText>(R.id.et_pass)

        viewModel.validationStatus.observe(viewLifecycleOwner) {
            when (it) {
                ValidationStatus.EMAIL_EMPTY -> {
                    etEmail.error = "Введите email!"
                }
                ValidationStatus.PASSWORD_EMPTY -> {
                    etPassword.error = "Введите пароль!"
                }
                ValidationStatus.EMAIL_NOT_VALID -> {
                    etEmail.error = "Email некорректный."
                }
                ValidationStatus.PASSWORD_TOO_SHORT -> {
                    etPassword.error = "Пароль должен состоять минимум из 6-ти символов."
                }
                ValidationStatus.SUCCESS -> {
                    val bundle = bundleOf("email" to etEmail.text.toString())
                    findNavController().navigate(R.id.mapFragment, bundle)
                }
            }
        }

        button.setOnClickListener {
            viewModel.validate(etEmail.text.toString(), etPassword.text.toString())
        }
    }
}