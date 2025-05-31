//
//  TaskType.swift
//  MindArt
//
//  Created by BELDIN Yaroslav on 30.10.2024.
//

import Foundation

enum TaskType: String, CaseIterable, Identifiable {
    case goal
    case memories

    var id: String { self.rawValue }
}
