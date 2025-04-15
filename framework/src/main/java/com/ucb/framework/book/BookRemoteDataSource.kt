package com.ucb.framework.book

import com.ucb.data.NetworkResult
import com.ucb.data.book.IBookRemoteDataSource
import com.ucb.domain.Book
import com.ucb.framework.mappers.toModel
import com.ucb.framework.service.RetrofitBuilder

class BookRemoteDataSource(
    val retrofitBuilder: RetrofitBuilder
) : IBookRemoteDataSource {
    override suspend fun searchBooks(title: String): NetworkResult<List<Book>> {
        val response = retrofitBuilder.bookService.searchBooks(title)
        return if (response.isSuccessful) {
            NetworkResult.Success(response.body()!!.docs.map { it.toModel() })
        } else {
            NetworkResult.Error("Error: ${response.code()}")
        }
    }
}
