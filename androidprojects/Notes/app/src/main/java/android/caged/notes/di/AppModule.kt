package android.caged.notes.di

import android.app.Application
import android.caged.notes.data.manager.LocalUserManagerImpl
import android.caged.notes.domain.manager.LocalUserManager
import android.caged.notes.domain.usecases.appentry.AppEntryUsecases
import android.caged.notes.domain.usecases.appentry.ReadAppEntry
import android.caged.notes.domain.usecases.appentry.SaveAppEntry
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideLocalUserManager(application: Application): LocalUserManager =
        LocalUserManagerImpl(context = application)

    @Provides
    @Singleton
    fun provideAppEntryUsecases(
        localUserManager: LocalUserManager
    ) = AppEntryUsecases(
        saveAppEntry = SaveAppEntry(localUserManager),
        readAppEntry = ReadAppEntry(localUserManager)
    )
}