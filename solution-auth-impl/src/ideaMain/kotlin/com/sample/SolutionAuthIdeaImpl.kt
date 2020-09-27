package com.sample

import kotlinx.coroutines.flow.Flow

class SolutionAuthIdeaImpl(
    val getGithubMail: ((String) -> Unit) -> Unit
) : SolutionAuthIdeaApi, SolutionAuthApi, SolutionWithState {

    data class State(
        val login: String = "some@mail.com",
        val pass: String = "123456789",
        val enterLogin: Boolean = false,
        val token: String? = null
    )

    sealed class Action {
        object GithubAuth : Action()
        object ShowLogin : Action()
        class EditLogin(val str: String) : Action()
        class EditPassword(val str: String) : Action()
        object SubmitLogin : Action()
        object LogOut : Action()
    }

    override fun isAuthorized(): Boolean = store.state.token != null
    override fun getLogin(): String? = if (isAuthorized()) store.state.login else null
    override fun onStateUpdate(): Flow<*> = store.stateFlow

    val store = createStore(State()) { s, a: Action ->
        when (a) {
            is Action.ShowLogin -> {
                s.copy(
                    enterLogin = true
                )
            }
            is Action.EditLogin -> {
                s.copy(
                    login = a.str
                )
            }
            is Action.EditPassword -> {
                s.copy(
                    pass = a.str
                )
            }
            is Action.SubmitLogin -> {
                s.copy(
                    token = "bearer_secret_token_..."
                )
            }
            is Action.LogOut -> {
                s.copy(
                    token = null,
                    pass = "",
                    enterLogin = false
                )
            }
            is Action.GithubAuth -> {
                s.copy()
            }
        }
    }

    override fun renderLoginForm(builder: IdeaPanelBuilder) {
        builder.apply {
            val state = store.state
            if (isAuthorized()) {
                label("Вы авторизованы: ${state.login}")
                button("Выйти") {
                    store.send(Action.LogOut)
                }
            } else if (state.enterLogin) {
                row {
                    label("Enter Your Login")
                    textInput(state.login) {
                        store.send(Action.EditLogin(it))
                    }
                }
                row {
                    label("Enter password")
                    passwordInput(state.login) {
                        store.send(Action.EditPassword(it))
                    }
                }
                button("Войти") {
                    store.send(Action.SubmitLogin)
                }
            } else {
                button("Авторизоваться по почте") {
                    store.send(Action.ShowLogin)
                }
                button("Авторизовать через Github") {
                    store.send(Action.GithubAuth)
                }
            }

        }
    }

}
