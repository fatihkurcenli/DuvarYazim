package com.autumnsun.duvaryazim.di

import android.content.Context
import androidx.room.Room
import com.autumnsun.duvaryazim.data.local.WallStreetDatabase
import com.autumnsun.duvaryazim.utils.Constants.DB_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


/*
 Created by Fatih Kurcenli on 12/7/2021
*/


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Singleton
    @Provides
    fun provideNoteDatabase(
        @ApplicationContext context: Context
    ): WallStreetDatabase = Room.databaseBuilder(
        context,
        WallStreetDatabase::class.java,
        DB_NAME
    ).build()


    @Singleton
    @Provides
    fun provideWallStreetDao(
        wallStreetDatabase: WallStreetDatabase
    ) = wallStreetDatabase.getNoteDao()


/*    @Singleton
    @Provides
    fun provideGson() = Gson()

    @Singleton
    @Provides
    fun provideWallWriters(@ApplicationContext context: Context): WallWriters {
        val builder = OkHttpClient.Builder()
        builder.addInterceptor(HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        })
        builder.addInterceptor(
            ChuckerInterceptor.Builder(context)
                .collector(ChuckerCollector(context).apply {
                    showNotification = false
                })
                .maxContentLength(250000L)
                .redactHeaders(emptySet())
                .alwaysReadResponseBody(false)
                .build()
        )
        builder.build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(builder.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WallWriters::class.java)
    }*/
}