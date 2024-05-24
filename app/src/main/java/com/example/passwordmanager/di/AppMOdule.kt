package com.example.passwordmanager.di

import android.app.Application
import androidx.room.Room
import com.example.passwordmanager.feature_password.data.data_source.PasswordDatabase
import com.example.passwordmanager.feature_password.data.repository.PasswordRopImpl
import com.example.passwordmanager.feature_password.domain.repository.PasswordRepository
import com.example.passwordmanager.feature_password.domain.use_cases.AddPassword
import com.example.passwordmanager.feature_password.domain.use_cases.DeletePassword
import com.example.passwordmanager.feature_password.domain.use_cases.GetAllPasswords
import com.example.passwordmanager.feature_password.domain.use_cases.GetPasswordById
import com.example.passwordmanager.feature_password.domain.use_cases.UseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppMOdule {

    @Provides
    @Singleton
    fun providesDatabase(application: Application) : PasswordDatabase {
        return Room.databaseBuilder(application,PasswordDatabase::class.java,"DB").build()
    }

    @Provides
    @Singleton
    fun providesPasswordRepo(db : PasswordDatabase) : PasswordRepository {
        return PasswordRopImpl(db.passwordDao)
    }

    @Provides
    @Singleton
    fun providesUseCases (passwordRepository: PasswordRepository) : UseCases{
        return UseCases(
            getAllPasswords = GetAllPasswords(passwordRepository),
            getPasswordById = GetPasswordById(passwordRepository),
            addPassword = AddPassword(passwordRepository),
            deletePassword = DeletePassword(passwordRepository)
        )
    }

}