package com.example.passwordmanager.feature_password.data.repository

import com.example.passwordmanager.feature_password.data.data_source.PasswordDao
import com.example.passwordmanager.feature_password.domain.model.Password
import com.example.passwordmanager.feature_password.domain.repository.PasswordRepository
import kotlinx.coroutines.flow.Flow

class PasswordRopImpl(
    private val passwordDao: PasswordDao
) : PasswordRepository {
    override fun getAllPasswords(): Flow<List<Password>> {
        return passwordDao.getAllPassword()
    }

    override suspend fun getPasswordById(id: Int): Password {
        return passwordDao.getPasswordById(id)
    }

    override suspend fun addPassword(password: Password) {
        return passwordDao.addPassword(password)
    }

    override suspend fun deletePassWord(password: Password) {
        return passwordDao.deletePassword(password)
    }
}