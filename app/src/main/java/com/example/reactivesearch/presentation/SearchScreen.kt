package com.example.reactivesearch.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.reactivesearch.data.User

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = hiltViewModel(),

) {
    val uiState by viewModel.uiState.collectAsState()
    val listState = rememberLazyListState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.events.collect { event ->
            when (event) {
                is UiEvent.ShowToast ->
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    Column(modifier = modifier) {
        UserSearchBar(onQueryChanged = viewModel::onSearchQueryChanged)

        if (uiState.isLoading) {
            LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
        }

        UserList(uiState = uiState, listState = listState)

    }
}

@Composable
fun UserSearchBar(
    onQueryChanged: (String) -> Unit,
){
    var query by rememberSaveable { mutableStateOf("") }

    TextField(
        value = query,
        onValueChange = {
            query = it
            onQueryChanged(it)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        placeholder = { Text("Search users...") }
    )

}

@Composable
fun UserList(
    uiState: SearchUiState,
    listState: LazyListState,
    modifier: Modifier = Modifier,
){
    LazyColumn(
        state = listState,
        modifier = modifier.fillMaxSize(),
    ) {
        items(uiState.results) { user ->
            UserListItem(user = user)
        }
    }
}

@Composable
fun UserListItem(
    user: User,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier.padding(8.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = user.name,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
    }
}
