package com.sample
//todo rename file
import kotlinx.coroutines.flow.StateFlow

sealed class Intent {
    object ChangeChecked : Intent()
    object OpenGithubAuth : Intent()
    class SuccessGithubAuth(val mail: String, val token: String) : Intent()
    class EditSearchText(val searchText: String) : Intent()
}

sealed class SideEffect {
    object OpenGithubAuth : SideEffect()
}

data class UniWindowState(
    val checked: Boolean = true,
    val searchText:String = "Москва",
    val githubMail: String? = null
)

@Suppress("unused")
fun getStateFlowAndRender(
    getGithubMail: ((String) -> Unit) -> Unit
): StateFlowAndRender<UniWindowState> {

    val store = createStoreWithSideEffect(
        UniWindowState(),
        effectHandler = { store, effect ->
            getGithubMail { mail ->
                store.send(Intent.SuccessGithubAuth(mail, "some_token"))
            }
        }
    ) { state, action: Intent ->
        when (action) {
            is Intent.ChangeChecked -> {
                ReducerResult(
                    state.copy(
                        checked = !state.checked
                    )
                )
            }
            is Intent.OpenGithubAuth -> {
                ReducerResult(
                    state.copy(),
                    listOf(
                        SideEffect.OpenGithubAuth
                    )
                )
            }
            is Intent.SuccessGithubAuth -> {
                ReducerResult(
                    state.copy(
                        githubMail = action.mail
                    )
                )
            }
            is Intent.EditSearchText -> {
                ReducerResult(
                    state.copy(
                        searchText = action.searchText
                    )
                )
            }
        }
    }

    return object : StateFlowAndRender<UniWindowState> {
        override val stateFlow: StateFlow<UniWindowState> = store.stateFlow


        override fun render(state: UniWindowState): IdeaPanel =
            panelView {
                title("Заголовок")
                checkBox("бонусы", state.checked) {
                    store.send(Intent.ChangeChecked)
                }
                row {
                    label("Куда:")
                    textInput(state.searchText) {
                        store.send(Intent.EditSearchText(it))
                    }
                }
                row {
                    label("пароль")
                    passwordInput(state.searchText) {
                        store.send(Intent.EditSearchText(it))
                    }
                }
                label("Длинный текст ыва ыва ыфва\n вфыа фывафвыа фыва фыва фыва фыва")
                row {
                    button("Поиск") {
                        store.send(Intent.ChangeChecked)
                    }
                    button("Билеты") {
                        store.send(Intent.ChangeChecked)
                    }
                    button("Авторизация") {
                        store.send(Intent.OpenGithubAuth)
                    }
                }
                if(state.githubMail != null) {
                    label("mail: ${state.githubMail}")
                }
            }

    }
}
