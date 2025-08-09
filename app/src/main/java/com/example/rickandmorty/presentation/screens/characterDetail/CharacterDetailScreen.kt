package com.example.rickandmorty.presentation.screens.characterDetail


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.rickandmorty.ui.components.HeaderRowWithBackBtn
import com.example.rickandmorty.ui.components.ScreenPreloader
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailScreen(
    navController: NavController,
    characterId: Int,
    viewModel: CharacterDetailViewModel = koinViewModel(
        parameters = { parametersOf(characterId) }
    )
){
    val state by viewModel.state.collectAsState()
    val refreshState = rememberPullToRefreshState()

    if(state.isLoading){
        ScreenPreloader()
    }
    else {
        PullToRefreshBox(
            state = refreshState,
            isRefreshing = state.isRefreshing,
            onRefresh = {
                viewModel.handleIntent(
                    CharacterDetailIntent.LoadCharacterDetail(
                        characterId = characterId,
                        isRefresh = true
                    )
                )
            }
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                HeaderRowWithBackBtn(title = "Character detail") {
                    navController.popBackStack()
                }

                Card(
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth().height(350.dp).padding(bottom = 8.dp)
                ) {
                    AsyncImage(
                        model = state.character?.image,
                        contentDescription = "Character Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                CharacterField("Name", state.character?.name)
                CharacterField("Type", state.character?.type)
                CharacterField("Status", state.character?.status)
                CharacterField("Gender", state.character?.gender)
                CharacterField("Location", state.character?.location?.name)
                CharacterField("Origin", state.character?.origin?.name)
                CharacterField("Url", state.character?.url)
                CharacterField("Created", state.character?.created)
                CharacterField("Episode", state.character?.episode.toString())
            }
        }
    }
}

@Composable
fun CharacterField(
    name: String,
    value: String?
){
    Column(
        modifier = Modifier.padding(top = 6.dp)
    ) {
        Text(
            text = "$name: $value",
            fontSize = 16.sp,
            color = Color.DarkGray
        )
        Divider(
            color = Color.DarkGray,
            thickness = 1.dp,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}