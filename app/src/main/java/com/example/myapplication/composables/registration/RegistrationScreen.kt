package com.example.myapplication.composables.registration

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.R
import com.example.myapplication.composables.drop_down_edit_text.DropDownEditText
import com.example.myapplication.composables.login.Prefix

@Preview(showBackground = true)
@Composable
fun RegistrationPreview() {
    RegistrationScreen(prefix = Prefix.Russia, phone = "")
}

@Composable
fun RegistrationScreen(
    viewModel: RegistrationViewModel = viewModel(),
    navController: NavHostController = rememberNavController(),
    prefix: Prefix,
    phone: String
) {
    val sideEffects by viewModel.sideEffectFlow.collectAsState(null)

    val context = LocalContext.current

    LaunchedEffect(sideEffects) {
        val sideEffect = sideEffects
        when (sideEffect) {
            RegistrationViewModel.SideEffect.NavigateToContent -> navController.navigate("content") {
                popUpTo("loginScreen") { inclusive = true }
            }

            is RegistrationViewModel.SideEffect.ShowError -> {
                Toast.makeText(context, sideEffect.throwable.message, Toast.LENGTH_LONG).show()
            }

            null -> Unit
        }
    }

    var username by rememberSaveable { mutableStateOf("") }
    var name by rememberSaveable { mutableStateOf("") }
    Column {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 32.dp, end = 32.dp, top = 40.dp, bottom = 16.dp),
            fontSize = 20.sp,
            text = stringResource(R.string.screen_registration_title)
        )
        DropDownEditText(
            modifier = Modifier
                .padding(horizontal = 32.dp, vertical = 8.dp)
                .fillMaxWidth(),
            value = phone,
            prefix = prefix,
            enabled = false,
            onValueChange = { phonePrefix, text -> },
            items = Prefix.entries,
            label = { Text(text = stringResource(R.string.field_phone)) }
        )

        OutlinedTextField(
            modifier = Modifier
                .padding(horizontal = 32.dp, vertical = 8.dp)
                .fillMaxWidth(),
            value = name,
            onValueChange = {
                name = it
            },
            singleLine = true,
            label = { Text(text = stringResource(R.string.field_name)) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            )
        )

        OutlinedTextField(
            modifier = Modifier
                .padding(horizontal = 32.dp, vertical = 8.dp)
                .fillMaxWidth(),
            value = username,
            onValueChange = {
                username = it
            },
            singleLine = true,
            label = { Text(text = stringResource(R.string.field_username)) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            )
        )

        Button(
            modifier = Modifier
                .padding(horizontal = 32.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(4.dp),
            onClick = {
                val formattedPhone = "${prefix.prefix}$phone"
                viewModel.register(
                    phone = formattedPhone,
                    username = username,
                    name = name
                )
            }
        ) {
            Text(text = stringResource(R.string.screen_registration_register))
        }
    }
}