package com.sample

import kotlinx.coroutines.flow.Flow

class SolutionAuthImpl() : SolutionAuthApi {
    data class State(
            val login: String = "",
            val pass: String = "",
            val enterLogin: Boolean = false,
            val token: String? = null
    )

    sealed class Action {
        object ShowLogin : Action()
        class EditLogin(val str: String) : Action()
        class EditPassword(val str: String) : Action()
        object SubmitLogin : Action()
        object LogOut : Action()
    }

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
        }
    }

    override fun isAuthorized(): Boolean = store.state.token != null
    override val login: String? get() = if (isAuthorized()) store.state.login else null

    val update: Flow<*> = store.stateFlow

    //iOS:
    fun getState() = store.state
    fun send(action: Action) = store.send(action)
    fun getActionShowLogin() = Action.ShowLogin
    fun getActionEditLogin(str: String) = Action.EditLogin(str)
    fun getActionEditPassword(str: String) = Action.EditPassword(str)
    fun getActionSubmitLogin() = Action.SubmitLogin
    fun getActionLogOut() = Action.LogOut

}
