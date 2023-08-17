package com.example.petabencana.di

import com.example.petabencana.data.repository.ReportsRepositoryImpl
import com.example.petabencana.domain.repository.ReportRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModules {

    @Binds
    abstract fun bindReportsRepository(repositoryImpl: ReportsRepositoryImpl) : ReportRepository
}