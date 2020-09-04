import SwiftUI
import Foundation
import solution_bonus_api_swift
import solution_bonus_impl_swift
import app_di

public class SampleDiIos {
    public let common = SampleDi()
    lazy public var solutionBonusIos = SolutionBonusIosImpl(
            common: common.solutionBonus
    )

    public init() {

    }
}
