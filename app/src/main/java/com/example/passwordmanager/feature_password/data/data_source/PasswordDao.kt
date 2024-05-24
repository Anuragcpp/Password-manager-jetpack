package com.example.passwordmanager.feature_password.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.passwordmanager.feature_password.domain.model.Password
import java.util.concurrent.Flow

@Dao
interface PasswordDao {

    @Query("select * from password")
    fun getAllPassword() : kotlinx.coroutines.flow.Flow<List<Password>>

    @Query("select * from password where id=:id")
    suspend fun getPasswordById(id : Int) : Password

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPassword(password: Password)

    @Delete
    suspend fun deletePassword(password: Password)
}