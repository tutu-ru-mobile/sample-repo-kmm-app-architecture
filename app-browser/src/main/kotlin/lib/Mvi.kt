package lib

object Mvi {
    interface Store<State, Intent> {
        fun dispatch(intent: Intent)
        fun subscribeToState(subscription: (State) -> Unit)
        val state: State
    }

    sealed class Reduce<State> {
        class DoNothing<State> : Reduce<State>()
        class ChangeState<State>(val state: State) : Reduce<State>()
    }

    interface ReduceContext<State> {
        fun onlyState(state: State): Reduce<State> = Reduce.ChangeState(state)
        fun State.onlyState(): Reduce<State> = Reduce.ChangeState(this)
        val doNothing get():Reduce<State> = Reduce.DoNothing()
    }

    fun <State, Intent> store(
        initialState: State,
        reducer: ReduceContext<State>.(State, Intent) -> Reduce<State>
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

                val context = object : ReduceContext<State> {}
                when (val reduce = context.reducer(state, intent)) {
                    is Reduce.ChangeState<State> -> {
                        updateState(reduce.state)
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
