package com.example.myapplication.composables.drop_down_edit_text

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import com.example.myapplication.composables.login.Prefix
import com.example.myapplication.utils.FormatterConst.PHONE_DIGITS
import kotlin.enums.EnumEntries

@Composable
fun DropDownEditText(
    value: String,
    prefix: Prefix,
    onValueChange: (Prefix, String) -> Unit,
    items: EnumEntries<Prefix>,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isError: Boolean = false,
    label: @Composable (() -> Unit)? = null
) {
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = value,
            enabled = enabled,
            onValueChange = {
                val text = it.take(PHONE_DIGITS)
               onValueChange(prefix, text)
            },
            isError = isError,
            label = label,
            leadingIcon = {
                IconButton(onClick = { expanded = !expanded }) {
                    Image(
                        painter = painterResource(id = prefix.flag),
                        contentDescription = "More"
                    )
                }
            },
            prefix = {
                Text(text = "+${prefix.prefix}")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            singleLine = true
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            items.forEach {
                DropdownMenuItem(
                    text = {
                        Text(
                            text = it.prefix
                        )
                    },
                    onClick = {
                        onValueChange(it, value)
                        expanded = false
                    },
                    leadingIcon = {
                        Image(
                            painter = painterResource(id = it.flag),
                            contentDescription = "More"
                        )
                    }
                )
            }
        }
    }
}