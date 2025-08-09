package com.example.rickandmorty.presentation.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.rickandmorty.presentation.screens.home.HomeIntent
import com.example.rickandmorty.presentation.screens.home.HomeViewModel
import com.example.rickandmorty.ui.components.HeaderRowWithBackBtn
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun FilterScreen(
    navController: NavController,
    viewModel: HomeViewModel = koinViewModel()
){
    val state = viewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .background(Color.White)
            .padding(16.dp)
    ) {
        HeaderRowWithBackBtn(title = "Set character filter") {
            navController.popBackStack()
        }

        FilterInputField(state.value.status, "status") { newValue ->
            viewModel.handleIntent(HomeIntent.ChangeFilter(status = newValue))
        }
        FilterInputField(state.value.species, "species") { newValue ->
            viewModel.handleIntent(HomeIntent.ChangeFilter(species = newValue))
        }
        FilterInputField(state.value.type, "type") { newValue ->
            viewModel.handleIntent(HomeIntent.ChangeFilter(type = newValue))
        }
        FilterInputField(state.value.gender, "gender") { newValue ->
            viewModel.handleIntent(HomeIntent.ChangeFilter(gender = newValue))
        }

        Button(
            modifier = Modifier
                .padding(top = 10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.DarkGray,
                contentColor = Color.White
            ),
            onClick = {
                viewModel.handleIntent(HomeIntent.LoadStartPage)
                navController.popBackStack()
            }
        ) {
            Text(
                text = "Save filter",
                fontSize = 16.sp
            )
        }
    }
}

@Composable
fun FilterInputField(
    value: String?,
    placeholder: String,
    onValueChange: (String?) -> Unit,
){
    Spacer(modifier = Modifier.padding(top = 8.dp))
    OutlinedTextField(
        value = value ?: "",
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            ,
        textStyle = TextStyle(
            fontSize = 16.sp
        ),
        placeholder = {
            Text(
                text = placeholder,
                fontSize = 16.sp,
                modifier = Modifier.padding(vertical = 1.dp),
                color = Color.LightGray
            )
        },
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = Color.DarkGray,
            focusedBorderColor = Color.DarkGray
        )
    )
}