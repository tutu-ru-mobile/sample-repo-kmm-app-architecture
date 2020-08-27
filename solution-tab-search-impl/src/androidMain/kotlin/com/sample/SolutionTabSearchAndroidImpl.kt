package com.sample

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.unit.dp
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import com.sample.compose.Central
import com.sample.compose.CheckBoxWithLabel
import com.sample.compose.WrapColorBox

class SolutionTabSearchAndroidImpl(
    val common: SolutionTabSearchImpl,
    val searchForm: SolutionSearchFormAndroidApi,
    val searchStart: SolutionSearchStartAndroidApi,
    val searchResult: SolutionSearchResultAndroidApi,
    val solutionBuy: SolutionBuyAndroidApi
) : SolutionTabSearchAndroidApi {

    @Composable
    override fun renderMainScreen() {
        when (common.store.state.screen) {
            is SolutionTabSearchImpl.Screen.SearchForm -> {
                searchForm.renderSearchForm()
            }
            is SolutionTabSearchImpl.Screen.SearchStart -> {
                searchStart.renderSearching()
            }
            is SolutionTabSearchImpl.Screen.SearchResult -> {
                searchResult.renderSearchResult()
            }
            is SolutionTabSearchImpl.Screen.Buy -> {
                solutionBuy.renderBuy()
            }
        }
    }

}
