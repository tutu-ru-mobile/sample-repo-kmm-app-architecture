package mvi

import Dictionary

sealed class Intent {
    class ChooseDictionary(val dictionary: Dictionary) : Intent()
    object StartWordScreen : Intent()
    class MarkWord(val success: Boolean) : Intent()
    object OpenWord : Intent()
    object NextWord : Intent()
}
