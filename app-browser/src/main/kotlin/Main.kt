//todo simplify import
import lib.renderReactMviComponent
import lib.renderReactStateFlowComponent
import mvi.Intent
import mvi.store
import view.ApplicationComponent
import kotlinx.browser.document

fun main() {
//    val styles = CSSBuilder(allowClasses = false).apply {
//        body {
//            margin(0.px)
//            padding(0.px)
//        }
//    }
    document.getElementById("react-app")?.renderReactStateFlowComponent<ApplicationComponent>()
//    store.dispatch(Intent.LoadDeployTime)
}
