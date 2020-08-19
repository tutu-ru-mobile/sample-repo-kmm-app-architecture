package com.sample

interface SolutionAbApi {
    fun registerBooleanToggle(key: String, defaultValue: Boolean)
    fun getBooleanToggleState(key: String): Boolean
}
