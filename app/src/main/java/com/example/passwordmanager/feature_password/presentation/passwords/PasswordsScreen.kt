package com.example.passwordmanager.feature_password.presentation.passwords

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.passwordmanager.feature_password.presentation.passwords.components.PasswordItem
import com.example.passwordmanager.feature_password.presentation.util.Screens
import java.security.Key


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PasswordsScreen(
    navController: NavController,
    viewModel: PasswordsViewModel = hiltViewModel(),
) {
    val scaffoldState = rememberScaffoldState()

    //for the floating add button for add passwords
    Scaffold (
        scaffoldState = scaffoldState,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screens.AddEditPasswordScreen.route)
                }
            ) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Add Passwords")
            }
        }
    ) { padding ->
        val passwords = viewModel.passwordState.value.passswords

        //TODO need to apply the lazy colum there
        LazyColumn {
            itemsIndexed(
                passwords,
                key = {_,password ->
                    password.hashCode()
                }
//                Key = { _,password ->
//                    password.hashCode()
//                }
            ){ index, password ->
                
                PasswordItem(
                    modifier = Modifier.animateItemPlacement(),
                    password = password,
                    onItemClicked = {
                        navController.navigate(Screens.AddEditPasswordScreen.route + "?passwordId=${password.id}")
                    },
                    onItemDelete = {
                        viewModel.onEvent(PasswordsEvents.DeletePassword(password))
                    }
                    )
            }
        }


    }
}