package com.sample

class SampleDiAndroid(val common: SampleDi) {
    val solutionBonusAndroid by lazy { SolutionBonusAndroidImpl(common.solutionBonus) }
}
