//
//  LanguageSetting.swift
//  MindArt
//
//  Created by BELDIN Yaroslav on 30.10.2024.
//

import Foundation

final class LanguageSetting: ObservableObject {
    @Published private(set) var locale: Locale

    init() {
        if Locale.autoupdatingCurrent.identifier == "ru_RU" {
            self.locale = Locale.autoupdatingCurrent
        } else {
            self.locale = Locale(identifier: "en_EN")
        }
    }

    func setLanguage(_ identifier: String) {
        locale = Locale(identifier: identifier)
    }
}
