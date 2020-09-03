package com.sample

import kotlinx.coroutines.flow.Flow

class SolutionAuthImpl() : SolutionAuthApi, SolutionWithState {

    /**
     * Цвет обводки для простоты понимая архитектуры и разбиения по Solution-ам.
     */
    fun getColor() = MyColors.SOLUTION_AUTH

    data class State(
        val login: String = "some@mail.com",
        val pass: String = "123456789",
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
    override fun getLogin(): String? = if (isAuthorized()) store.state.login else null

    override fun onStateUpdate(): Flow<*> = store.stateFlow

    // Для iOS проще пользоваться не State-ом, а специальной прослойкой из helper-функий
    fun getState() = store.state
    fun send(action: Action) = store.send(action)
    fun getActionShowLogin() = Action.ShowLogin
    fun getActionEditLogin(str: String) = Action.EditLogin(str)
    fun getActionEditPassword(str: String) = Action.EditPassword(str)
    fun getActionSubmitLogin() = Action.SubmitLogin
    fun getActionLogOut() = Action.LogOut

}
