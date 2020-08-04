package com.sample

interface SolutionSearchStartApi {
    fun startSearch(query: String)
    fun getSearchQuery(): String
}
