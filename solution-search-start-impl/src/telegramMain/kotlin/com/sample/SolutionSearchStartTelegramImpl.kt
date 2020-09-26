package com.sample

class SolutionSearchStartTelegramImpl(
    val common: SolutionSearchStartImpl
) : SolutionSearchStartTelegramApi {

    override fun renderSearching():TelegramView {
        return TelegramView.Message("Searching ${common.store.state.searchQuery}...")
//        Column {
//            Text("Searching ${common.store.state.searchQuery}...")
//            Button(onClick = {
//                common.completeSearch()
//            }) {
//                Text("Complete search")
//            }
//        }
    }

}
