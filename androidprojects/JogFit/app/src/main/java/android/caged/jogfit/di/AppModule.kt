package android.caged.jogfit.di

import android.app.Application
import android.content.Context
import androidx.credentials.CredentialManager
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
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
    fun provideContext(application: Application) : Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun provideFirebase() : Firebase = Firebase

    @Provides
    @Singleton
    fun provideFirebaseAuth() : FirebaseAuth = Firebase.auth

    @Provides
    @Singleton
    fun provideCredentialManager(context: Application) : CredentialManager {
        return CredentialManager.create(context = context)
    }

    @Provides
    @Singleton
    fun provideFirebaseFirestore() : FirebaseFirestore = FirebaseFirestore.getInstance()
}