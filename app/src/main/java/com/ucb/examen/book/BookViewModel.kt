package com.ucb.examen.book

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucb.data.NetworkResult
import com.ucb.domain.Book
import com.ucb.usecases.GetFavoriteBooks
import com.ucb.usecases.SaveBook
import com.ucb.usecases.SearchBooks
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookViewModel @Inject constructor(
    private val searchBooks: SearchBooks,
    private val saveBook: SaveBook,
    private val getFavoriteBooks: GetFavoriteBooks
) : ViewModel() {

    sealed class BookUIState {
        object Init : BookUIState()
        object Loading : BookUIState()
        data class Success(val books: List<Book>) : BookUIState()
        data class Error(val message: String) : BookUIState()
    }

    private val _state = MutableStateFlow<BookUIState>(BookUIState.Init)
    val state: StateFlow<BookUIState> = _state

    private val _favorites = MutableStateFlow<List<Book>>(emptyList())
    val favorites: StateFlow<List<Book>> = _favorites

    fun search(title: String) {
        _state.value = BookUIState.Loading
        viewModelScope.launch {
            when (val result = searchBooks.invoke(title)) {
                is NetworkResult.Success -> _state.value = BookUIState.Success(result.data)
                is NetworkResult.Error -> _state.value = BookUIState.Error(result.error)
            }
        }
    }

    fun save(book: Book) {
        viewModelScope.launch {
            saveBook.invoke(book)
            loadFavorites()
        }
    }

    fun loadFavorites() {
        viewModelScope.launch {
            _favorites.value = getFavoriteBooks.invoke()
        }
    }
}
