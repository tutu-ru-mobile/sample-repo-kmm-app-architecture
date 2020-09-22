import lib.renderReactStateFlowComponent
import view.ApplicationComponent
import kotlinx.css.*
import kotlin.browser.document

fun main() {
    val styles = CSSBuilder(allowClasses = false).apply {
        body {
            margin(100.px)
            padding(100.px)
            alignContent = Align.center
            alignItems = Align.center
            alignSelf = Align.center
        }
    }
    document.getElementById("react-app")?.renderReactStateFlowComponent<ApplicationComponent>()
//    store.dispatch(Intent.LoadDeployTime)
}
