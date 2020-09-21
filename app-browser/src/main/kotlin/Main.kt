import lib.renderReactMviComponent
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
    document.getElementById("react-app")?.renderReactMviComponent<ApplicationComponent>()
//    store.dispatch(Intent.LoadDeployTime)
}
