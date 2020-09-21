package view

import kotlinx.css.FontWeight
import kotlinx.css.fontSize
import kotlinx.css.fontWeight
import kotlinx.css.pt
import kotlinx.html.DIV
import kotlinx.html.InputType
import kotlinx.html.js.onChangeFunction
import kotlinx.html.js.onClickFunction
import org.w3c.dom.HTMLSelectElement
import react.dom.*
import styled.css
import styled.styledDiv

fun RDOMBuilder<DIV>.checkBox(
    label: String,
    value: Boolean,
    onClick: () -> Unit
) {
//    button {
//        attrs {
//            onClickFunction = {
//                onClick()
//            }
//        }
//        styledDiv {
//            css {
//                fontSize = 30.pt
//                if (value) {
//                    fontWeight = FontWeight.bold
//                }
//            }
//            +((if (value) "+" else "-") + label)
//        }
//    }
    input(type = InputType.checkBox) {
        attrs.checked = value
        attrs.defaultChecked
        attrs.onChangeFunction = {
            onClick()
        }
    }
    span {
        +label
        attrs.onClickFunction = {
            onClick()
        }
    }
}

fun RDOMBuilder<DIV>.selectCollection(
    selectionTitle: String,
    currentValue: String?,
    values: List<String>,
    onSelected: (String) -> Unit
) {
    +"$selectionTitle: "
    select {
        val NOT_SELECTED = "not selected"
        //https://github.com/JetBrains/kotlin-wrappers/issues/92
        attrs.value = currentValue ?: NOT_SELECTED
        attrs.onChangeFunction = {
            val value = (it.target as? HTMLSelectElement)?.value
            if (value != null) {
                onSelected(value)
            }
        }
        option {
            attrs.disabled = true
            attrs.value = NOT_SELECTED
            +NOT_SELECTED
        }
        values.forEach {
            option {
                attrs.value = it
                +it
            }
        }
    }
}
