package com.sample

class SolutionAuthIdeaImpl(val common: SolutionAuthImpl) : SolutionAuthIdeaApi {

    override fun renderLoginForm(builder: IdeaPanelBuilder) {
        builder.apply {
            val state = common.store.state
            if (common.isAuthorized()) {
                label("Вы авторизованы: ${state.login}")
                button("Выйти") {
                    common.store.send(SolutionAuthImpl.Action.LogOut)
                }
            } else if (state.enterLogin) {
                row {
                    label("Enter Your Login")
                    textInput(state.login) {
                        common.store.send(SolutionAuthImpl.Action.EditLogin(it))
                    }
                }
                row {
                    label("Enter password")
                    passwordInput(state.login) {
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
