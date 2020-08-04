package com.sample

interface SolutionAuthApi {
    fun isAuthorized(): Boolean
    val login: String?
}
