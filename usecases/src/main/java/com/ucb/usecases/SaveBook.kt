package com.ucb.usecases

import com.ucb.data.BookRepository
import com.ucb.domain.Book

class SaveBook(private val repository: BookRepository) {
    suspend fun invoke(book: Book) = repository.save(book)
}