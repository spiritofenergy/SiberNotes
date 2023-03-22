package com.example.siberianotes.di.modules

import com.example.siberianotes.data.firebase.AuthRepositoryImpl
import com.example.siberianotes.domain.repository.AuthRepository
import com.example.siberianotes.domain.usecase.AuthUseCase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Singleton
    @Provides
    fun provideFirebaseAuth() = FirebaseAuth.getInstance()

    @Singleton
    @Provides
    fun provideFirebaseFirestore() = FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun provideAuthRepository(firebaseAuth: FirebaseAuth, firebaseFirestore: FirebaseFirestore):AuthRepository =
        AuthRepositoryImpl(firebaseAuth = firebaseAuth, firebaseFirestore = firebaseFirestore)

    @Singleton
    @Provides
    fun provideAuthUseCase(authRepository: AuthRepository)= AuthUseCase(authRepository)
}