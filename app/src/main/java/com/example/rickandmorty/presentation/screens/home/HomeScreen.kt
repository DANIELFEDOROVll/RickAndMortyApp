package com.example.rickandmorty.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.rickandmorty.R
import com.example.rickandmorty.domain.Character
import com.example.rickandmorty.presentation.screens.characterDetail.CharacterDetailIntent
import com.example.rickandmorty.ui.components.MiniPreloader
import com.example.rickandmorty.ui.components.ScreenPreloader
import org.koin.compose.viewmodel.koinViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = koinViewModel()
){
    val state by viewModel.state.collectAsState()

    Column {
        SearchCard(viewModel, state.searchValue){ newValue ->
            viewModel.handleIntent(HomeIntent.NewSearchValue(newValue))
        }

        when {
            state.isLoading -> ScreenPreloader()
            else -> {
                PullToRefreshBox(
                    isRefreshing = state.isRefreshing,
                    onRefresh = {
                        viewModel.handleIntent(
                            HomeIntent.LoadStartPage
                        )
                    }
                ) {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        content = {
                            items(state.characters.results) { character ->
                                CharacterCard(character, navController)
                            }

                            if (state.isLoadingNextPage) {
                                item(span = { GridItemSpan(maxLineSpan) }) {
                                    MiniPreloader()
                                }
                            } else if (!state.error.isNullOrEmpty()) {
                                item(span = { GridItemSpan(maxLineSpan) }) {
                                    ErrorMessage(state.error.toString())
                                }
                            }
                        },
                        state = rememberLazyGridState().apply {
                            LaunchedEffect(this) {
                                snapshotFlow { layoutInfo.visibleItemsInfo.lastOrNull()?.index }
                                    .collect { lastVisibleItemIndex ->
                                        if (lastVisibleItemIndex == (state.characters.results.size) - 1
                                        ) {
                                            viewModel.handleIntent(HomeIntent.LoadNextPage)
                                        }
                                    }
                            }
                        }
                    )
                }
            }
        }
    }

    Box(
        contentAlignment = Alignment.BottomEnd,
        modifier = Modifier.fillMaxSize()
    ){
        IconFilter {
            navController.navigate("filter_screen")
        }
    }
}

@Composable
fun SearchCard(
    viewModel: HomeViewModel,
    searchValue: String,
    onValueChange: (String) -> Unit,
){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(Color.DarkGray)
            .padding(16.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        BasicTextField(
            value = searchValue,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 40.dp),
            textStyle = TextStyle(
                color = Color.White,
                fontSize = 16.sp
            ),
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.CenterStart
                ) {
                    if (searchValue.isEmpty()) {
                        Text(
                            text = "Search characters",
                            color = Color.White,
                            fontSize = 16.sp
                        )
                    }
                    innerTextField()
                }
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    viewModel.handleIntent(HomeIntent.LoadStartPage)
                }
            )
        )

        IconButton(
            onClick = {
                viewModel.handleIntent(HomeIntent.LoadStartPage)
            },
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .size(24.dp),
        ) {
            Icon(
                painter = painterResource(R.drawable.search_icon),
                contentDescription = "Search Icon",
                tint = Color.White,
                modifier = Modifier
                    .size(24.dp),
            )
        }
    }
}

@Composable
fun CharacterCard(
    character: Character,
    navController: NavController
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(260.dp)
            .padding(7.dp)
            .clickable { navController.navigate("detail_screen/${character.id}") },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.DarkGray
        )
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
            ){
                AsyncImage(
                    model = character.image,
                    contentDescription = "Character image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                )

                val color = if(character.status == "Alive") Color.Green else Color.Red
                Box(
                    modifier = Modifier
                        .size(12.dp)
                        .align(Alignment.BottomEnd)
                        .clip(CircleShape)
                        .background(color)
                        .padding(10.dp),
                )

            }
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = character.name,
                    fontSize = 18.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
                Row(
                    modifier = Modifier
                        .padding(top = 4.dp)
                ) {
                    val type = if(character.type == "") "Unknown" else character.type
                    Text(
                        text = character.gender + " | " + type,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Composable
fun IconFilter(onClick: () -> Unit){
    IconButton(
        onClick = onClick,
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = Color.Black,
            contentColor = Color.White
        ),
        modifier = Modifier
            .size(86.dp)
            .padding(15.dp),
    ) {
        Icon(
            painter = painterResource(R.drawable.filter_icon),
            contentDescription = "Filter Icon",
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
fun ErrorMessage(error: String){
    Box(
        modifier = Modifier.fillMaxWidth().height(300.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = error,
            fontSize = 18.sp,
            color = Color.Red,
            textAlign = TextAlign.Center
        )
    }
}