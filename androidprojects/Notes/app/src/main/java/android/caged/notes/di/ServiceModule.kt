package android.caged.notes.di

import android.app.Application
import android.caged.notes.data.services.AccountServiceImpl
import android.caged.notes.data.services.LogServiceImpl
import android.caged.notes.domain.services.AccountService
import android.caged.notes.domain.services.LogService
import android.caged.notes.utils.Constants.GOOGLE_WEB_CLIENT_ID
import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Provides
    @Singleton
    fun provideAccountService(auth: FirebaseAuth, googleSignInClient: GoogleSignInClient): AccountService {
        return AccountServiceImpl(auth, googleSignInClient)
    }

    @Provides
    @Singleton
    fun provideLogService(): LogService {
        return LogServiceImpl()
    }
}