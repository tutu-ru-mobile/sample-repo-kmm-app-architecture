package lib

import com.sample.APP_SCOPE
import com.sample.Store
import kotlinext.js.jsObject
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.w3c.dom.Element
import react.*
import react.dom.render

abstract class MviComponent<St, In>(
    store: Store<St, In>,
    val render2: RBuilder.(St) -> Unit
) : RComponent<RProps, St>()
        where St : RState {

    init {
        APP_SCOPE.launch {
            store.stateFlow.collect { newState ->
                setState(transformState = { newState })
            }
        }
        state = store.state
    }

    override fun componentDidMount() {
    }

    override fun RBuilder.render() {
        render2(state)
    }
}

inline fun <reified Component> Element.renderReactMviComponent()
        where Component : RComponent<out RProps, out RState> {
    render(
        buildElement {
            childList.add(createElement(Component::class.js, jsObject<RProps> { }))
        },
        this
    )
}
