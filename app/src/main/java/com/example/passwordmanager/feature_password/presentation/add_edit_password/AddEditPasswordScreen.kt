package com.example.passwordmanager.feature_password.presentation.add_edit_password

import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.FloatingActionButton
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun AddEditPasswordScreen(
    navController: NavController,
    viewModel: AddEditViewModel = hiltViewModel(),
) {
    val scaffoldState = rememberScaffoldState()
    val serviceState = viewModel.serviceState.value
    val passwordState = viewModel.passwordState.value
    val userNameState = viewModel.userNameState.value
    val noteState = viewModel.notesState.value

    Scaffold (
        scaffoldState =  scaffoldState,
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.onEvent(AddEditPasswordEvent.SavePassword)
            }) {
                Icon(imageVector = Icons.Filled.Send, contentDescription = "Save Password" )
            }
        }
    ) {

    }
    
}