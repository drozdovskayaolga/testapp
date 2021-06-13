package com.example.testapplication.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.testapplication.model.ValidationStatus
import com.example.testapplication.utils.SingleLiveEvent

class LoginViewModel : ViewModel() {

    private val _validationStatus = SingleLiveEvent<ValidationStatus>()
    val validationStatus: LiveData<ValidationStatus> get() = _validationStatus

    fun validate(email: String, password: String) {
        _validationStatus.value = when {
            email.isEmpty() -> ValidationStatus.EMAIL_EMPTY

            !android.util.Patterns.EMAIL_ADDRESS.matcher(email)
                .matches() -> ValidationStatus.EMAIL_NOT_VALID

            password.isEmpty() -> ValidationStatus.PASSWORD_EMPTY

            password.length < 6 -> ValidationStatus.PASSWORD_TOO_SHORT

            else -> ValidationStatus.SUCCESS
        }
    }
}