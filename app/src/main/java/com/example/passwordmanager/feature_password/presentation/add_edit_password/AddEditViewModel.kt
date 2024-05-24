package com.example.passwordmanager.feature_password.presentation.add_edit_password

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.passwordmanager.feature_password.domain.model.Password
import com.example.passwordmanager.feature_password.domain.use_cases.UseCases
import com.example.passwordmanager.feature_password.presentation.passwords.PasswordState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddEditViewModel @Inject constructor(
    private val useCases: UseCases,
    saveStateHandle : SavedStateHandle
) : ViewModel() {

    private val _serviceState = mutableStateOf(PasswordTextFieldState(hint = "Service"))
    val serviceState : State<PasswordTextFieldState> = _serviceState

    private val _passwordState = mutableStateOf(PasswordTextFieldState(hint = "Password"))
    val passwordState : State<PasswordTextFieldState> = _passwordState

    private val _userNameState  = mutableStateOf(PasswordTextFieldState(hint = "User Name"))
    val userNameState : State<PasswordTextFieldState> = _userNameState

    private val _noteState = mutableStateOf(PasswordTextFieldState(hint = "Notes.."))
    val notesState : State<PasswordTextFieldState>  = _noteState

    private var _uiEvents = MutableSharedFlow<UIEvents>()
    val uiEvents : SharedFlow<UIEvents> = _uiEvents

    private var pressedPasswrodId : Int? = null

    init {
        saveStateHandle.get<Int>("passwordId")?.let { passwordId ->
            if(passwordId != -1){
                pressedPasswrodId = passwordId
                viewModelScope.launch (Dispatchers.IO){
                    delay(100)
                    useCases.getPasswordById(passwordId).let { password ->
                        _serviceState.value = _serviceState.value.copy(
                            text = password.service
                        )

                        _passwordState.value = _passwordState.value.copy(
                            text = password.password
                        )

                        password.userName?.let { userName ->
                            _userNameState.value = _userNameState.value.copy(
                                text = userName
                            )
                        }

                        password.note?.let { note ->
                            _noteState.value = _noteState.value.copy(
                                text = note
                            )
                        }
                    }
                }
            }

        }
    }


    fun onEvent  (event: AddEditPasswordEvent){
        when(event){

            is AddEditPasswordEvent.SavePassword -> {
                viewModelScope.launch {
                    useCases.addPassword(
                        Password(
                            service = _serviceState.value.text,
                            password = _passwordState.value.text,
                            userName = _userNameState.value.text,
                            note = _noteState.value.text
                        )
                    )

                    _uiEvents.emit(AddEditViewModel.UIEvents.savePassword)
                }
            }


            is AddEditPasswordEvent.EnteringServies -> {
                _serviceState.value = _serviceState.value.copy(
                    text = event.value
                )
            }

            is AddEditPasswordEvent.EnteringPassword -> {
                _passwordState.value = _passwordState.value.copy(
                    text = event.value
                )
            }

            is AddEditPasswordEvent.EnteringUserName -> {
                _userNameState.value = _userNameState.value.copy(
                    text = event.value
                )
            }

            is AddEditPasswordEvent.EnteringNotes -> {
                _noteState.value = _noteState.value.copy(
                    text = event.value
                )
            }
        }
    }
    sealed class UIEvents{
        object savePassword : UIEvents()
    }
}