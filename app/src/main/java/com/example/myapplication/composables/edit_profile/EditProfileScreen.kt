package com.example.myapplication.composables.edit_profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.myapplication.R

@Preview(showBackground = true)
@Composable
fun EditProfilePreview() {
    EditProfileScreen()
}

@Composable
fun EditProfileScreen(
    viewModel: EditProfileViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
    ) {
        when (val state = uiState) {
            is EditProfileViewModel.UiState.EditProfile -> EditProfileState(viewModel, state)
            is EditProfileViewModel.UiState.Error -> ErrorState(viewModel, state)
            EditProfileViewModel.UiState.Loading -> LoadingState()
        }
    }
}

@Composable
fun ErrorState(viewModel: EditProfileViewModel, uiState: EditProfileViewModel.UiState.Error) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Text(text = uiState.throwable.message ?: stringResource(R.string.screen_edit_profile_error))
            Button(onClick = {
                viewModel.loadData()
            }) {
                Text(text = stringResource(R.string.screen_edit_profile_error_reload))
            }
        }
    }
}

@Composable
fun LoadingState() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(100.dp)
        )
    }
}

@Composable
fun EditProfileState(
    viewModel: EditProfileViewModel,
    uiState: EditProfileViewModel.UiState.EditProfile
) {
    var isInEditMode by rememberSaveable { mutableStateOf(false) }

    var name by rememberSaveable { mutableStateOf(uiState.userResponse.name) }
    var city by rememberSaveable { mutableStateOf(uiState.userResponse.city) }
    LaunchedEffect(uiState) {
        isInEditMode = false
    }

    Column {

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 32.dp, end = 32.dp, top = 40.dp, bottom = 16.dp),
            fontSize = 20.sp,
            text = stringResource(R.string.screen_profile_title)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 32.dp),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape),
                model = "https://sun1-83.userapi.com/impg/ii-28ZZ0nFCSN9Ma_hnABd7tinfoWB4Q_hOVHA/1awi9kzTs90.jpg?size=1280x849&quality=96&sign=f10dfbfbea743038c95ac967de05b662",
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }

        OutlinedTextField(
            value = uiState.userResponse.phone,
            onValueChange = { },
            modifier = Modifier
                .padding(horizontal = 32.dp, vertical = 8.dp)
                .fillMaxWidth(),
            enabled = false,
            label = {
                Text(text = stringResource(R.string.field_phone))
            }
        )

        OutlinedTextField(
            modifier = Modifier
                .padding(horizontal = 32.dp, vertical = 8.dp)
                .fillMaxWidth(),
            value = uiState.userResponse.username,
            onValueChange = { },
            enabled = false,
            label = { Text(text = stringResource(R.string.field_username)) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal
            ),
            singleLine = true
        )

        OutlinedTextField(
            modifier = Modifier
                .padding(horizontal = 32.dp, vertical = 8.dp)
                .fillMaxWidth(),
            value = city ?: "",
            onValueChange = {
                city = it
            },
            enabled = isInEditMode,
            label = { Text(text = stringResource(R.string.field_city)) },
            singleLine = true
        )

        OutlinedTextField(
            modifier = Modifier
                .padding(horizontal = 32.dp, vertical = 8.dp)
                .fillMaxWidth(),
            value = name,
            onValueChange = {
                name = it
            },
            enabled = isInEditMode,
            label = { Text(text = stringResource(R.string.field_name)) },
            singleLine = true
        )


        if (isInEditMode) {
            Button(
                modifier = Modifier
                    .padding(horizontal = 32.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(4.dp),
                onClick = {
                    viewModel.updateProfile(name, city ?: "", uiState.userResponse.username)
                }
            ) {
                Text(text = stringResource(R.string.screen_profile_edit_save))
            }
            OutlinedButton(
                modifier = Modifier
                    .padding(horizontal = 32.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(4.dp),
                onClick = {
                    isInEditMode = false
                    name = uiState.userResponse.name
                    city = uiState.userResponse.city
                }
            ) {
                Text(text = stringResource(R.string.screen_profile_cancel))
            }
        } else {
            Button(
                modifier = Modifier
                    .padding(horizontal = 32.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(4.dp),
                onClick = {
                    isInEditMode = !isInEditMode
                }
            ) {
                Text(text = stringResource(R.string.screen_profile_edit))
            }
        }
        Box(modifier = Modifier.height(40.dp))
    }
}
