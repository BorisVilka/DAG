package com.advertisment.advertisementboard.di

import android.content.Context
import com.advertisment.advertisementboard.data.remote.AdvBoardService
import com.advertisment.advertisementboard.data.remote.repository.AdvBoardRepositoryImpl
import com.advertisment.advertisementboard.domain.repository.AdvBoardRepository
import com.advertisment.advertisementboard.domain.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideAdvBoardService(@ApplicationContext context: Context) = AdvBoardService(context)

    @Singleton
    @Provides
    fun provideAdvBoardRepository(service: AdvBoardService): AdvBoardRepository =
        AdvBoardRepositoryImpl(service)

    @Singleton
    @Provides
    fun provideAdvBoardUseCases(repository: AdvBoardRepository) =
        AdvBoardUseCases(
            validateAdminPassword = ValidateAdminPassword(repository),
            validateClientAdminPassword = ValidateClientAdminPassword(repository),
            addProperty = AddProperty(repository),
            getProperty = GetProperty(repository),
            filterByPrice = FilterByPrice(repository),
            uploadImage = UploadImage(repository),
            downloadImage = DownloadImage(repository),
            getLastPropertyId = GetLastPropertyId(repository),
            deleteProperty = DeleteProperty(repository)
        )
}