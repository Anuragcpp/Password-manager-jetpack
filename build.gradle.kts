import org.jetbrains.kotlin.gradle.internal.kapt.incremental.UnknownSnapshot



// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
//    id("com.google.dagger.hilt.android") version "2.44" apply false
    id("com.google.dagger.hilt.android") version "2.48" apply false


}
//dependencies for the dagger hilt to execute smoothly
buildscript{
    dependencies {
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.38.1")
    }
}