package mvi

import Dictionary

sealed class Intent {
    object LoadDeployTime : Intent()
    class SetDeployTime(val deployTime: String) : Intent()
    class ChooseDictionary(val dictionary: Dictionary) : Intent()
    object StartWordScreen : Intent()
    class MarkWord(val success: Boolean) : Intent()
    object OpenWord : Intent()
    object NextWord : Intent()
}

sealed class SideEffect {
    class StoreWord(val key: String, val success: Boolean) : SideEffect()
    object LoadDeployTime : SideEffect()
}
