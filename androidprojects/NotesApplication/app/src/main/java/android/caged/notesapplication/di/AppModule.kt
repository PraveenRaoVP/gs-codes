package android.caged.notesapplication.di

import android.app.Application
import android.caged.notesapplication.data.LocalUserManagerImpl
import android.caged.notesapplication.data.local.NotesDatabase
import android.caged.notesapplication.data.services.AccountServiceImpl
import android.caged.notesapplication.data.services.LogServiceImpl
import android.caged.notesapplication.domain.manager.LocalUserManager
import android.caged.notesapplication.domain.services.AccountService
import android.caged.notesapplication.domain.services.LogService
import android.caged.notesapplication.domain.usecases.AppEntryUseCases
import android.caged.notesapplication.domain.usecases.ReadAppEntry
import android.caged.notesapplication.domain.usecases.SaveAppEntry
import androidx.compose.ui.platform.LocalContext
import androidx.credentials.CredentialManager
import androidx.room.Room
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /***** LOCAL PROVIDERS ******/
    @Provides
    @Singleton
    fun provideLocalUserManager(application : Application) : LocalUserManager {
        return LocalUserManagerImpl(application)
    }

    @Provides
    @Singleton
    fun provideAppEntryUseCases(localUserManager: LocalUserManager) : AppEntryUseCases {
        return AppEntryUseCases(
            readAppEntry = ReadAppEntry(localUserManager),
            saveAppEntry = SaveAppEntry(localUserManager)
        )
    }

    /*** APP MODULE PROVIDERS ***/

    @Provides
    @Singleton
    fun provideCoroutineScope(): CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    /***** FIREBASE PROVIDERS ******/


    @Provides
    @Singleton
    fun provideFirebaseAuth() : FirebaseAuth {
        return Firebase.auth
    }

    @Provides
    @Singleton
    fun provideCredentialManager(context: Application) : CredentialManager {
        return CredentialManager.create(context = context)
    }

    /***** SERVICE PROVIDERS ******/

    @Provides
    @Singleton
    fun provideAccountService(
        firebaseAuth: FirebaseAuth,
        credentialManager: CredentialManager,
        coroutineScope: CoroutineScope
    ) : AccountService {
        return AccountServiceImpl(
            credentialManager = credentialManager,
            auth = firebaseAuth,
            scope = coroutineScope
        )
    }

    @Provides
    @Singleton
    fun provideLogService() : LogService {
        return LogServiceImpl()
    }

    /***** ROOM PROVIDERS ******/
    @Provides
    @Singleton
    fun provideDatabase(application: Application) : NotesDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = NotesDatabase::class.java,
            name = "notes_database"
        ).fallbackToDestructiveMigration()
            .build()
    }
}