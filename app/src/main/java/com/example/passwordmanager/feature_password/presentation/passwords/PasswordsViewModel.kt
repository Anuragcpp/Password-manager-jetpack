package com.example.passwordmanager.feature_password.presentation.passwords

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Insert
import com.example.passwordmanager.feature_password.domain.model.Password
import com.example.passwordmanager.feature_password.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PasswordsViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel(){

    private val _passwordsState  = mutableStateOf(PasswordState())
    val passwordState : State<PasswordState> = _passwordsState

    private var recentlyDeletedPassword : Password? = null
    private var job : Job? = null

    init {
        getPasswords()
    }

    private fun getPasswords() {
        job?.cancel()
        job = useCases.getAllPasswords().onEach {
            _passwordsState.value = _passwordsState.value.copy(
                passswords = it
            )
        }.launchIn(viewModelScope)
    }

    fun onEvent(event : PasswordsEvents){
        when (event) {
            is PasswordsEvents.DeletePassword -> {
                viewModelScope.launch {
                    useCases.deletePassword(event.password)
                }

                //saving the deleted password int this to restore it afterwards
                recentlyDeletedPassword = event.password
            }

            is PasswordsEvents.RestorePassword -> {
                viewModelScope.launch {
                    // if the recently deleted password is null then just return out from the function and add nathing
                    useCases.addPassword(recentlyDeletedPassword ?: return@launch)
                }
            }
        }
    }
}