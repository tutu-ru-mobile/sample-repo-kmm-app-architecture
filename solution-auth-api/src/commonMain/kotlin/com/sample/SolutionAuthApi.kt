package com.sample

interface SolutionAuthApi {
    fun isAuthorized(): Boolean
    fun getLogin(): String?
}
