package com.sample

class SolutionSearchStartTelegramImpl(
    val common: SolutionSearchStartImpl
) : SolutionSearchStartTelegramApi {

    override fun renderSearching():Content {
        return Content.Message("Searching ${common.store.state.searchQuery}...")
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
