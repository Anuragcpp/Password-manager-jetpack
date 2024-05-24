package com.example.passwordmanager.feature_password.presentation.passwords.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.passwordmanager.feature_password.domain.model.Password

@Composable
fun PasswordItem(
    modifier: Modifier = Modifier,
    password: Password,
    onItemClicked : () -> Unit,
    onItemDelete : () -> Unit
) {
    
    Card(
        modifier = Modifier
            .fillMaxSize()
            .clickable {
                onItemClicked()
            },
        elevation = CardDefaults.cardElevation(10.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            Text(text = password.service , fontWeight = FontWeight.Bold, style = MaterialTheme.typography.h5)
            
            Spacer(modifier = Modifier.height(5.dp))
            
            Text(text = password.password, style = MaterialTheme.typography.h6, modifier = Modifier.padding(start = 10.dp))
            
            IconButton(onClick = { onItemDelete}) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete password")
            }
        }
    }
    
}