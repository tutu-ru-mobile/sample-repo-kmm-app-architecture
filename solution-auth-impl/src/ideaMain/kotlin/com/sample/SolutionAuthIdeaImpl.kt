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
        class ReceivedGithubMail(val mail: String) : Action()
    }

    sealed class SideEffect {
        object GithubAuth : SideEffect()
    }

    override fun isAuthorized(): Boolean = store.state.token != null
    override fun getLogin(): String? = if (isAuthorized()) store.state.login else null
    override fun onStateUpdate(): Flow<*> = store.stateFlow

    val store = createStoreWithSideEffect(
        State(),
        { store, sideEffect: SideEffect ->
            when (sideEffect) {
                is SideEffect.GithubAuth -> {
                    getGithubMail { mail ->
                        store.send(
                            Action.ReceivedGithubMail(mail)
                        )
                    }
                }
            }
        }
    ) { s, a: Action ->
        when (a) {
            is Action.ShowLogin -> {
                ReducerResult(
                    s.copy(
                        enterLogin = true
                    )
                )
            }
            is Action.EditLogin -> {
                ReducerResult(
                    s.copy(
                        login = a.str
                    )
                )
            }
            is Action.EditPassword -> {
                ReducerResult(
                    s.copy(
                        pass = a.str
                    )
                )
            }
            is Action.SubmitLogin -> {
                ReducerResult(
                    s.copy(
                        token = "bearer_secret_token_..."
                    )
                )
            }
            is Action.LogOut -> {
                ReducerResult(
                    s.copy(
                        token = null,
                        pass = "",
                        enterLogin = false
                    )
                )
            }
            is Action.GithubAuth -> {
                ReducerResult(
                    s.copy(),
                    listOf(
                        SideEffect.GithubAuth
                    )
                )
            }
            is Action.ReceivedGithubMail -> {
                ReducerResult(
                    s.copy(
                        login = a.mail,
                        token = "some_token_generated_with_github"
                    )
                )
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
