package com.sample

import react.RBuilder
import react.dom.div
import react.dom.h2

class SolutionAuthBrowserImpl(val common: SolutionAuthImpl) : SolutionAuthBrowserApi {

    override fun renderLoginForm(react: RBuilder) {
        react.apply {
            val state = common.store.state
            if (common.isAuthorized()) {
                div {
                    h2 {
                        +"Вы авторизованы: ${state.login}"
                    }
                    btn("Выйти") {
                        common.store.send(SolutionAuthImpl.Action.LogOut)
                    }
                }
            } else if (state.enterLogin) {
                div {
                    inp("Enter Your Login", state.login) {
                        common.store.send(SolutionAuthImpl.Action.EditLogin(it))
                    }
                    inp("Enter password", state.pass) {
                        common.store.send(SolutionAuthImpl.Action.EditPassword(it))
                    }
                    btn("Войти") {
                        common.store.send(SolutionAuthImpl.Action.SubmitLogin)
                    }
                }
            } else {
                div {
                    btn("Авторизоваться") {
                        common.store.send(SolutionAuthImpl.Action.ShowLogin)
                    }
                }
            }

        }

    }

}
