package lib

import com.sample.APP_SCOPE
import kotlinext.js.jsObject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.w3c.dom.Element
import react.*
import react.dom.render

class WrapState<St>(val state:St):RState

abstract class StateFlowComponent<St>(
    stateFlow:StateFlow<St>,
    val render2: RBuilder.(St) -> Unit
) : RComponent<RProps, WrapState<St>>() {

    init {
        APP_SCOPE.launch {
            stateFlow.collect { newState ->
                setState(transformState = { WrapState(newState) })
            }
        }
        state = WrapState(stateFlow.value)
    }

    override fun componentDidMount() {
    }

    override fun RBuilder.render() {
        render2(state.state)
    }
}

inline fun <reified Component> Element.renderReactStateFlowComponent()
        where Component : RComponent<out RProps, out RState> {
    render(
        buildElement {
            childList.add(createElement(Component::class.js, jsObject<RProps> { }))
        },
        this
    )
}
