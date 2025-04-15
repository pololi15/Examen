package com.ucb.framework.book

import android.content.Context
import com.ucb.data.book.IBookLocalDataSource
import com.ucb.domain.Book
import com.ucb.framework.mappers.toEntity
import com.ucb.framework.mappers.toModel
import com.ucb.framework.persistence.AppRoomDatabase

class BookLocalDataSource(context: Context): IBookLocalDataSource {
    val dao = AppRoomDatabase.getDatabase(context).bookDao()

    override suspend fun save(book: Book): Boolean {
        dao.insert(book.toEntity())
        return true
    }

    override suspend fun getFavorites(): List<Book> {
        return dao.getAll().map { it.toModel() }
    }
}
