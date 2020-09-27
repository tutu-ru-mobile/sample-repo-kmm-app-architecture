package com.sample

class SolutionSearchStartTelegramImpl(
    val common: SolutionSearchStartImpl
) : SolutionSearchStartTelegramApi {

    override fun renderSearching():TelegramView {
        return TelegramView.Message("Searching ${common.store.state.searchQuery}...")
    }

}
