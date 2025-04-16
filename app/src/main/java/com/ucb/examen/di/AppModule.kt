package com.ucb.examen.di

import android.content.Context
import com.ucb.data.BookRepository
import com.ucb.data.book.IBookLocalDataSource
import com.ucb.data.book.IBookRemoteDataSource
import com.ucb.framework.book.BookLocalDataSource
import com.ucb.framework.book.BookRemoteDataSource
import com.ucb.framework.service.RetrofitBuilder
import com.ucb.usecases.GetFavoriteBooks
import com.ucb.usecases.SaveBook
import com.ucb.usecases.SearchBooks
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofitBuilder(@ApplicationContext context: Context): RetrofitBuilder {
        return RetrofitBuilder(context)
    }

    @Provides
    @Singleton
    fun provideBookRemoteDataSource(retrofitBuilder: RetrofitBuilder): IBookRemoteDataSource {
        return BookRemoteDataSource(retrofitBuilder)
    }

    @Provides
    @Singleton
    fun provideBookLocalDataSource(@ApplicationContext context: Context): IBookLocalDataSource {
        return BookLocalDataSource(context)
    }

    @Provides
    @Singleton
    fun provideBookRepository(
        remote: IBookRemoteDataSource,
        local: IBookLocalDataSource
    ): BookRepository {
        return BookRepository(remote, local)
    }

    @Provides
    @Singleton
    fun provideSearchBooks(repository: BookRepository): SearchBooks {
        return SearchBooks(repository)
    }

    @Provides
    @Singleton
    fun provideSaveBook(repository: BookRepository): SaveBook {
        return SaveBook(repository)
    }

    @Provides
    @Singleton
    fun provideGetFavoriteBooks(repository: BookRepository): GetFavoriteBooks {
        return GetFavoriteBooks(repository)
    }
}
