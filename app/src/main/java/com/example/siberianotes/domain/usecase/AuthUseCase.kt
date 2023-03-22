package com.example.siberianotes.domain.usecase

import android.provider.ContactsContract.CommonDataKinds.Email
import com.example.siberianotes.domain.model.UserModel
import com.example.siberianotes.domain.repository.AuthRepository
import javax.inject.Inject

class AuthUseCase @Inject constructor(
private val repository: AuthRepository
) {

    suspend fun registerNewUser(userModel: UserModel) =
        repository.firebaseSignUp(user = userModel)

    suspend fun loginUser(email: String, password: String) =
        repository.firebaseLoginIn(email, password)
}