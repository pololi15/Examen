package com.ucb.usecases

import com.ucb.data.BookRepository

class SearchBooks(private val repository: BookRepository) {
    suspend fun invoke(title: String) = repository.search(title)
}
