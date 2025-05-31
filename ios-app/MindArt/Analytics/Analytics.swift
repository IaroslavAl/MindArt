//
//  Analytics.swift
//  MindArt
//
//  Created by BELDIN Yaroslav on 25.11.2024.
//

import AppMetricaCore
import Foundation

struct Analytics {
    static func log(name: String, parameters: [AnyHashable: Any]? = nil) {
        #if !DEBUG
            AppMetrica.reportEvent(name: name, parameters: parameters)
        #endif
    }
}
