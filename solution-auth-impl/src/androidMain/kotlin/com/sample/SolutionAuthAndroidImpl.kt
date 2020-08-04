package com.sample

import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.foundation.Text
import androidx.ui.input.PasswordVisualTransformation
import androidx.ui.layout.Column
import androidx.ui.layout.fillMaxWidth
import androidx.ui.layout.padding
import androidx.ui.material.Button
import androidx.ui.material.FilledTextField
import androidx.ui.unit.dp


class SolutionAuthAndroidImpl(val common: SolutionAuthImpl) : SolutionAuthAndroidApi {

    @Composable
    override fun renderLoginForm() {
        val state = common.store.state
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
                FilledTextField(
                    value = state.login,
                    onValueChange = {
                        common.store.send(SolutionAuthImpl.Action.EditLogin(it))
                    },
                    label = { Text("Enter Your Login") },
                    placeholder = { Text(text = "your@mail.com") },
                    modifier = Modifier.padding(16.dp) + Modifier.fillMaxWidth()
                )
                FilledTextField(
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
