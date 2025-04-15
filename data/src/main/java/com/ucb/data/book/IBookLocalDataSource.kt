package com.ucb.data.book

import com.ucb.domain.Book

interface IBookLocalDataSource {
    suspend fun save(book: Book): Boolean
    suspend fun getFavorites(): List<Book>
}