package com.example.passwordmanager.feature_password.domain.model

import android.net.wifi.WifiManager.SubsystemRestartTrackingCallback
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Password")
data class Password(
    @PrimaryKey val id : Int? = null,
    val service : String,
    val userName : String,
    val password: String,
    val note : String
)
