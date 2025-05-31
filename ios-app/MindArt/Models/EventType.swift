//
//  Untitled.swift
//  MindArt
//
//  Created by BELDIN Yaroslav on 30.10.2024.
//

import Foundation

enum EventType: String, CaseIterable, Identifiable {
    case light
    case significant
    case dynamic
    case tender

    var id: String { self.rawValue }
}
