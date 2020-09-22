package view

import com.sample.AppDi
import com.sample.AppDiBrowser
import kotlinx.css.*
import react.dom.div
import styled.css
import styled.styledDiv
import lib.StateFlowComponent

val appDiBrowser = AppDiBrowser(AppDi())

class ApplicationComponent : StateFlowComponent<AppDi.GlobalState>(
    appDiBrowser.common.globalStateFlow,
    { state ->
        styledDiv {
            css {
                padding(32.px, 16.px)
            }

            div {
                styledDiv {
                    css {
                        marginBottom = 32.px
                        textAlign = TextAlign.center
                        alignContent = Align.center
                        alignItems = Align.center
                        alignSelf = Align.center
                        fontSize = 16.pt
                    }
                    appDiBrowser.solutionTabsBrowser.renderScaffold(this)
                }
            }
        }
    }
)

