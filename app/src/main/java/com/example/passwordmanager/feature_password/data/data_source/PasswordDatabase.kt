package com.example.passwordmanager.feature_password.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.passwordmanager.feature_password.domain.model.Password


@Database(entities = [Password::class], version = 1)
public abstract class PasswordDatabase : RoomDatabase() {

    abstract val passwordDao : PasswordDao
}