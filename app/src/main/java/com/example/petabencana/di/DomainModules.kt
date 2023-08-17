package com.example.petabencana.di

import com.example.petabencana.domain.usecase.GetReportsImpl
import com.example.petabencana.domain.usecase.GetReportsUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface DomainModules {

    @Binds
    fun provideGetReports(useCaseImpl: GetReportsImpl): GetReportsUseCase
}