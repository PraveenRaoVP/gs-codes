package android.caged.videoapplicationapp.di

import android.app.Application
import android.caged.videoapplicationapp.firebase.FirebaseClient
import android.caged.videoapplicationapp.repository.MainRepository
import android.caged.videoapplicationapp.services.MainServiceRepository
import android.content.Context
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideContext(@ApplicationContext context: Context) : Context {
        return context.applicationContext
    }

    @Provides
    fun providesGson() : Gson {
        return Gson()
    }

    @Provides
    fun provideDatabaseInstance() : FirebaseDatabase {
        return FirebaseDatabase.getInstance()
    }

    @Provides
    fun providesDatabaseReference(database : FirebaseDatabase) : DatabaseReference = database.reference

    @Provides
    @Singleton
    fun providesFirebaseClient(databaseReference: DatabaseReference, gson : Gson) : FirebaseClient {
        return FirebaseClient(databaseReference, gson)
    }

    @Provides
    @Singleton
    fun provideMainRepository(firebaseClient: FirebaseClient) : MainRepository {
        return MainRepository(firebaseClient)
    }

    @Provides
    @Singleton
    fun providesMainServiceRepository(context: Context) : MainServiceRepository {
        return MainServiceRepository(context)
    }
}