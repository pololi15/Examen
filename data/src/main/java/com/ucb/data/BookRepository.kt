package com.ucb.data

import com.ucb.data.book.IBookLocalDataSource
import com.ucb.data.book.IBookRemoteDataSource
import com.ucb.domain.Book

class BookRepository(
    val remote: IBookRemoteDataSource,
    val local: IBookLocalDataSource
) {
    suspend fun search(title: String) = remote.searchBooks(title)
    suspend fun save(book: Book) = local.save(book)
    suspend fun getFavorites() = local.getFavorites()
}