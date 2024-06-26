package com.example.passwordmanager.feature_password.domain.repository

import com.example.passwordmanager.feature_password.domain.model.Password
import kotlinx.coroutines.flow.Flow

interface PasswordRepository {

    fun getAllPasswords() : Flow<List<Password>>

    suspend fun getPasswordById(id : Int) : Password

    suspend fun addPassword(password: Password)

    suspend fun deletePassWord(password: Password)
}