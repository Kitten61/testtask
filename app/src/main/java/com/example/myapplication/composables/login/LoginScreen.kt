package com.example.myapplication.composables.login

import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.R
import com.example.myapplication.composables.drop_down_edit_text.DropDownEditText
import com.example.myapplication.utils.FormatterConst.PHONE_DIGITS
import com.example.myapplication.utils.FormatterConst.phoneRegex
import kotlinx.coroutines.flow.onEach

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    LoginScreen()
}

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = viewModel(),
    navController: NavController = rememberNavController()
) {
    val context = LocalContext.current

    var number by rememberSaveable { mutableStateOf("") }
    var prefix by rememberSaveable { mutableStateOf(Prefix.Russia) }
    var phoneErrorState by rememberSaveable { mutableStateOf(false) }

    val sideEffects by viewModel.sideEffectFlow.collectAsState(null)

    LaunchedEffect(sideEffects) {
        when (sideEffects) {
            LoginViewModel.SideEffect.NavigateToCheckCode -> navController.navigate("checkCodeScreen/$prefix&${number}")
            LoginViewModel.SideEffect.ShowError -> Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
            null -> Unit
        }
    }
    Column {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 32.dp, end = 32.dp, top = 40.dp, bottom = 16.dp),
            fontSize = 20.sp,
            text = stringResource(R.string.screen_login_title)
        )
        DropDownEditText(
            modifier = Modifier
                .padding(horizontal = 32.dp, vertical = 16.dp)
                .fillMaxWidth(),
            value = number,
            prefix = prefix,
            isError = phoneErrorState,
            onValueChange = { phonePrefix, text ->
                prefix = phonePrefix
                number = if (phoneRegex.matches(text)) {
                    text
                } else {
                    number
                }
            },
            items = Prefix.entries,
            label = { Text(text = stringResource(R.string.field_phone)) }
        )

        Button(
            modifier = Modifier
                .padding(horizontal = 32.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(4.dp),
            onClick = {
                phoneErrorState = if (number.length == PHONE_DIGITS) {
                    viewModel.sendCode(phone = "${prefix.prefix}$number")
                    false
                } else {
                    true
                }
            }
        ) {
            Text(text = stringResource(R.string.screen_login_send_code))
        }
    }
}

enum class Prefix(
    val prefix: String,
    @DrawableRes val flag: Int,
) {
    USA("1", R.drawable.flag_us),
    Russia("7", R.drawable.flag_ru)
}

fun Prefix.makePhone(phone: String): String {
    return "$prefix$phone"
}