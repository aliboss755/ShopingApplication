package com.example.shopingapplication.di

import com.google.firebase.auth.FirebaseAuth
import dagger.*
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object APPModule {
    @Provides
    @Singleton
    fun provideFirebaseAuth()= FirebaseAuth.getInstance()
}