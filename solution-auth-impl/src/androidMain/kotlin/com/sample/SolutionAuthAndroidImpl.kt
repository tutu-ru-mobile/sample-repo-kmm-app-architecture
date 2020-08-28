package com.sample

import androidx.compose.foundation.*
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.PasswordVisualTransformation
import com.sample.compose.CheckBoxWithLabel
import com.sample.compose.WrapColorBox


class SolutionAuthAndroidImpl(val common: SolutionAuthImpl) : SolutionAuthAndroidApi {

    @Composable
    override fun renderLoginForm() {
        val state = common.store.state
        WrapColorBox(color = common.getColor()) {
            if (common.isAuthorized()) {
                Column() {
                    Text("Вы авторизованы: ${state.login}")
                    Button(onClick = {
                        common.store.send(SolutionAuthImpl.Action.LogOut)
                    }) {
                        Text("Выйти")
                    }
                }
            } else if (state.enterLogin) {
                Column() {
                    TextField(
                        value = state.login,
                        onValueChange = {
                            common.store.send(SolutionAuthImpl.Action.EditLogin(it))
                        },
                        label = {
                            Text("Enter Your Login")
                        },
                        placeholder = { Text(text = "your@mail.com") },
                        modifier = Modifier.padding(16.dp) + Modifier.fillMaxWidth()
                    )

                    TextField(
                        value = state.pass,
                        onValueChange = {
                            common.store.send(SolutionAuthImpl.Action.EditPassword(it))
                        },
                        label = { Text("Enter password") },
                        visualTransformation = PasswordVisualTransformation(),
                        modifier = Modifier.padding(16.dp) + Modifier.fillMaxWidth()
                    )

                    Button(onClick = {
                        common.store.send(SolutionAuthImpl.Action.SubmitLogin)
                    }) {
                        Text("Войти")
                    }
                }
            } else {
                Column() {
//                Text("Вы не авторизованы")
                    Button(onClick = {
                        common.store.send(SolutionAuthImpl.Action.ShowLogin)
                    }) {
                        Text("Авторизоваться")
                    }
                }
            }
        }

    }

}
