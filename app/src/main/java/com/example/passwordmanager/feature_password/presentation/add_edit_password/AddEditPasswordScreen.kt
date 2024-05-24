package com.example.passwordmanager.feature_password.presentation.add_edit_password

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.FloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.passwordmanager.feature_password.presentation.add_edit_password.compoents.CustomTextField
import kotlinx.coroutines.flow.collectLatest

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

    LaunchedEffect(key1 = true, block = {
        viewModel.uiEvents.collectLatest { event ->
            when(event) {
                is AddEditViewModel.UIEvents.savePassword -> {
                    navController.navigateUp()
                }
            }
        }
    })

    Scaffold (
        scaffoldState =  scaffoldState,
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.onEvent(AddEditPasswordEvent.SavePassword)
            }) {
                Icon(imageVector = Icons.Filled.Send, contentDescription = "Save Password" )
            }
        }
    ) { padding ->
        Box ( modifier = Modifier
            .fillMaxSize()
            .padding(padding),
            contentAlignment = Alignment.Center
            ) {

            Column (){
                CustomTextField(
                    text = serviceState.text,
                    hint =  serviceState.hint,
                    onChangeText = {
                        viewModel.onEvent(AddEditPasswordEvent.EnteringServies(it))
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Home,
                            contentDescription = "Service"
                        )
                    }
                )
                
                Spacer(modifier = Modifier.height(20.dp))

                CustomTextField(
                    text = userNameState.text,
                    hint =  userNameState.hint,
                    onChangeText = {
                        viewModel.onEvent(AddEditPasswordEvent.EnteringUserName(it))
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Person,
                            contentDescription = "Person"
                        )
                    }
                )

                Spacer(modifier = Modifier.height(20.dp))

                CustomTextField(
                    text = passwordState.text,
                    hint =  passwordState.hint,
                    onChangeText = {
                        viewModel.onEvent(AddEditPasswordEvent.EnteringPassword(it))
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.AccountBox,
                            contentDescription = "Password"
                        )
                    }
                )

                Spacer(modifier = Modifier.height(20.dp))

                CustomTextField(
                    text = noteState.text,
                    hint =  noteState.hint,
                    isNote = true,
                    onChangeText = {
                        viewModel.onEvent(AddEditPasswordEvent.EnteringNotes(it))
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.DateRange,
                            contentDescription = "Notes"
                        )
                    }
                )

                Spacer(modifier = Modifier.height(20.dp))



            }

        }
    }
    
}