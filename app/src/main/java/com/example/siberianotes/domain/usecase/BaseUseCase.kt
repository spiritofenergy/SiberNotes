package com.example.siberianotes.domain.usecase

abstract class BaseUseCase <T> {
    abstract suspend fun invoke(): T
}