package com.programmer270487.dansandroid.presentation.googlesignin

data class SignInState(
    val isSignInSuccessful: Boolean = false,
    val signInError: String? = null
)