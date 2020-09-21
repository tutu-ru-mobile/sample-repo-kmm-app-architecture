package lib

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

object Mvi {
    interface Store<State, Intent> {
        fun dispatch(intent: Intent)
        fun subscribeToState(subscription: (State) -> Unit)
        val state: State
    }

    sealed class Reduce<State, SideEffect> {
        class DoNothing<State, SideEffect> : Reduce<State, SideEffect>()
        class ChangeState<State, SideEffect>(val state: State) : Reduce<State, SideEffect>()
        class Effect<State, SideEffect>(val effect: SideEffect) : Reduce<State, SideEffect>()
        class Both<State, SideEffect>(val state: State, val effect: SideEffect) : Reduce<State, SideEffect>()
    }

    interface ReduceContext<State, SideEffect> {
        fun onlyState(state: State): Reduce<State, SideEffect> = Reduce.ChangeState(state)
        fun State.onlyState(): Reduce<State, SideEffect> = Reduce.ChangeState(this)
        fun onlySideEffect(effect: SideEffect): Reduce<State, SideEffect> = Reduce.Effect(effect)
        fun SideEffect.onlySideEffect(): Reduce<State, SideEffect> = Reduce.Effect(this)
        infix fun State.andEffect(effect: SideEffect): Reduce<State, SideEffect> =
            Reduce.Both(this, effect)
        infix fun SideEffect.andState(state: State): Reduce<State, SideEffect> =
            Reduce.Both(state, this)
        fun both(state: State, effect:SideEffect): Reduce<State, SideEffect> =
            Reduce.Both(state, effect)
        val doNothing get():Reduce<State, SideEffect> = Reduce.DoNothing()
    }

    fun <State, Intent, SideEffect> store(
        initialState: State,
        sideEffectHandler: suspend (Store<State, Intent>, SideEffect) -> Unit,
        reducer: ReduceContext<State, SideEffect>.(State, Intent) -> Reduce<State, SideEffect>
    ): Store<State, Intent> {
        var state: State = initialState
        val subscriptions: MutableList<(State) -> Unit> = mutableListOf()

        return object : Store<State, Intent> {
            override fun dispatch(intent: Intent) {

                fun updateState(st: State) {
                    state = st
                    subscriptions.forEach {
                        it.invoke(state)
                    }
                }

                fun applySideEffect(effect: SideEffect) {
                    val store = this
                    GlobalScope.launch {
                        sideEffectHandler(store, effect)
                    }
                }

                val context = object : ReduceContext<State, SideEffect> {}
                when (val reduce = context.reducer(state, intent)) {
                    is Reduce.ChangeState<State, SideEffect> -> {
                        updateState(reduce.state)
                    }
                    is Reduce.Effect<State, SideEffect> -> {
                        applySideEffect(reduce.effect)
                    }
                    is Reduce.Both<State, SideEffect> -> {
                        updateState(reduce.state)
                        applySideEffect(reduce.effect)
                    }
                }

            }

            override fun subscribeToState(subscription: (State) -> Unit) {
                subscriptions.add(subscription)
                subscription(state)
            }

            override val state: State get() = state
        }
    }
}