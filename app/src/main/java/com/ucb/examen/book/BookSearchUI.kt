// app/book/BookSearchUI.kt
package com.ucb.examen.book

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ucb.domain.Book

@Composable
fun BookSearchUI(viewModel: BookViewModel = hiltViewModel(), onGoToFavorites: () -> Unit) {
    var query by remember { mutableStateOf("") }
    val state by viewModel.state.collectAsState()

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        OutlinedTextField(
            value = query,
            onValueChange = { query = it },
            label = { Text("Buscar libro") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { viewModel.search(query) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Buscar")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = onGoToFavorites,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ver Favoritos")
        }

        Spacer(modifier = Modifier.height(16.dp))

        when (val ui = state) {
            is BookViewModel.BookUIState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            }

            is BookViewModel.BookUIState.Success -> {
                LazyColumn {
                    items(ui.books) { book ->
                        BookItem(book = book, onSave = { viewModel.save(it) })
                    }
                }
            }

            is BookViewModel.BookUIState.Error -> {
                Text("Error: ${ui.message}")
            }

            else -> Unit
        }
    }
}

@Composable
fun BookItem(book: Book, onSave: (Book) -> Unit) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Título: ${book.title}")
            Text("Autor(es): ${book.authors.joinToString()}")
            Text("Año: ${book.publishYear}")
            IconButton(onClick = { onSave(book) }) {
                Icon(imageVector = Icons.Default.Favorite, contentDescription = "Guardar")
            }
        }
    }
}
