package com.example.myapplication.composables.check_code

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
import com.example.myapplication.composables.login.Prefix
import com.example.myapplication.composables.login.makePhone
import com.example.myapplication.utils.FormatterConst

@Preview(showBackground = true)
@Composable
fun CheckCodePreview() {
    CheckCodeScreen(navController = rememberNavController(), phone = "2345678901", prefix = Prefix.Russia)
}

@Composable
fun CheckCodeScreen(
    viewModel: CheckCodeViewModel = viewModel(),
    navController: NavHostController,
    prefix: Prefix,
    phone: String
) {
    val sideEffects by viewModel.sideEffectFlow.collectAsState(null)

    val context = LocalContext.current

    LaunchedEffect(sideEffects) {
        val sideEffect = sideEffects
        when(sideEffect) {
            CheckCodeViewModel.SideEffect.NavigateToRegistration -> navController.navigate("registrationScreen/${prefix.name}&$phone") {
                popUpTo("loginScreen") { inclusive = true }
            }
            CheckCodeViewModel.SideEffect.NavigateToContent -> navController.navigate("content") {
                popUpTo("loginScreen") { inclusive = true }
            }
            is CheckCodeViewModel.SideEffect.ShowError -> {
                Toast.makeText(context, sideEffect.throwable.message, Toast.LENGTH_LONG).show()
            }
            null -> Unit
        }
    }

    var code by rememberSaveable { mutableStateOf("") }
    Column {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 32.dp, end = 32.dp, top = 40.dp, bottom = 16.dp),
            fontSize = 20.sp,
            text = stringResource(R.string.screen_check_code_title)
        )

        OutlinedTextField(
            modifier = Modifier
                .padding(horizontal = 32.dp, vertical = 8.dp)
                .fillMaxWidth(),
            value = code,
            onValueChange = {
                code = if (FormatterConst.codeRegex.matches(it)) {
                    it
                } else {
                    code
                }
            },
            label = { Text(text = stringResource(R.string.field_code)) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            singleLine = true
        )

        Button(
            modifier = Modifier
                .padding(horizontal = 32.dp, vertical = 8.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(4.dp),
            onClick = {
                viewModel.checkCode(prefix.makePhone(phone), code)
            }
        ) {
            Text(text = stringResource(R.string.screen_check_code_check))
        }
    }
}