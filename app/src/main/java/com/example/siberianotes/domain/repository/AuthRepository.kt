package com.example.siberianotes.domain.repository

import com.example.siberianotes.domain.model.NetworkResult
import com.example.siberianotes.domain.model.UserModel
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    suspend fun firebaseSignUp(user: UserModel): Flow<NetworkResult<Boolean>>
    suspend fun firebaseLoginIn(email: String, password:String): Flow<NetworkResult<Boolean>>
}