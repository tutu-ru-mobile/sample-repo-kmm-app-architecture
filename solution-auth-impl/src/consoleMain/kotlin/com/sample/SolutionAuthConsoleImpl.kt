package com.sample

class SolutionAuthConsoleImpl(
    val common: SolutionAuthImpl
) : SolutionAuthConsoleApi{

    override fun renderLoginForm(builder: ConsolePanelBuilder) {
        builder.apply {
            val state = common.store.state
            if (common.isAuthorized()) {
                label("Вы авторизованы: ${state.login}")
                button("Выйти") {
                    common.store.send(SolutionAuthImpl.Action.LogOut)
                }
            } else if (state.enterLogin) {
                row {
                    textInput("Enter Your Login", state.login) {
                        common.store.send(SolutionAuthImpl.Action.EditLogin(it))
                    }
                }
                row {
                    passwordInput("Enter password", state.login) {
                        common.store.send(SolutionAuthImpl.Action.EditPassword(it))
                    }
                }
                button("Войти") {
                    common.store.send(SolutionAuthImpl.Action.SubmitLogin)
                }
            } else {
                button("Авторизоваться") {
                    common.store.send(SolutionAuthImpl.Action.ShowLogin)
                }
            }

        }
    }

}
