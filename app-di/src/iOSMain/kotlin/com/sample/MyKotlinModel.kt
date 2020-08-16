package com.sample

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlin.random.Random

class MyKotlinModel {

    fun createRandomState() = MyState(
        list = List(5) {
            MyItem("id_$it", "rnd: ${Random.nextInt()}")
        }
    )

    val stateFlow = MutableStateFlow(createRandomState())
    fun getLastState() = stateFlow.value

    init {
        todoScope {
            while (true) {
                delay(2000)
                doAction(MyAction.Update())
            }
        }
    }

    fun reducer(state: MyState, action: MyAction): MyState {
        return when (action) {
            is MyAction.Update -> {
                state.copy(
                    list = state.list.map {
                        it.copy(
                            counter1 = it.counter1 + 1
                        )
                    }
                )
            }
            is MyAction.Click -> {
                state.copy(
                    list = state.list.map {
                        if (it == action.item) {
                            it.copy(
                                counter2 = it.counter2 + 1
                            )
                        } else {
                            it
                        }
                    }
                )
            }
        }
    }

    fun doAction(action: MyAction) {
        stateFlow.value = reducer(stateFlow.value, action)
    }

    fun addListener(listener: (MyState) -> Unit) {
        println("my addListener")
        todoScope {
            stateFlow.collectLatest {
                listener(it)
            }
        }
    }

}

sealed class MyAction {
    class Update : MyAction()
    class Click(val item: MyItem) : MyAction()
}
