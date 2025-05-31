//
//  Untitled.swift
//  MindArt
//
//  Created by BELDIN Yaroslav on 30.10.2024.
//

import SwiftUI

enum CustomColor: String, CaseIterable {
    case red
    case coral
    case pink
    case lightPink
    case burgundy
    case plum
    case purple
    case lilac
    case indigo
    case blue
    case darkBlue
    case lightBlue
    case turquoise
    case mint
    case green
    case lime
    case limeGreen
    case deepGreen
    case olive
    case yellow
    case caramel
    case gold
    case silver
    case bronze
    case brown
    case beige
    case terracotta
    case gray
    case white
    case black
}

// MARK: - Color
extension CustomColor {
    var color: Color {
        switch self {
        case .red: return Color(red: 1, green: 0, blue: 0)
        case .coral: return Color(red: 1, green: 127/255, blue: 80/255)
        case .pink: return Color(red: 1, green: 192/255, blue: 203/255)
        case .lightPink: return Color(red: 1, green: 126/255, blue: 147/255)
        case .burgundy: return Color(red: 128/255, green: 0, blue: 32/255)
        case .plum: return Color(red: 142/255, green: 69/255, blue: 133/255)
        case .purple: return Color(red: 128/255, green: 0, blue: 128/255)
        case .lilac: return Color(red: 200/255, green: 162/255, blue: 200/255)
        case .indigo: return Color(red: 75/255, green: 0, blue: 130/255)
        case .blue: return Color(red: 0, green: 0, blue: 1)
        case .darkBlue: return Color(red: 0, green: 165/255, blue: 139/255)
        case .lightBlue: return Color(red: 173/255, green: 216/255, blue: 230/255)
        case .turquoise: return Color(red: 64/255, green: 224/255, blue: 208/255)
        case .mint: return Color(red: 189/255, green: 252/255, blue: 201/255)
        case .green: return Color(red: 0, green: 128/255, blue: 0)
        case .lime: return Color(red: 0, green: 1, blue: 0)
        case .limeGreen: return Color(red: 209/255, green: 226/255, blue: 49/255)
        case .deepGreen: return Color(red: 0, green: 56/255, blue: 43/255)
        case .olive: return Color(red: 128/255, green: 128/255, blue: 0)
        case .yellow: return Color(red: 1, green: 1, blue: 0)
        case .caramel: return Color(red: 1, green: 213/255, blue: 154/255)
        case .gold: return Color(red: 1, green: 215/255, blue: 0)
        case .silver: return Color(red: 192/255, green: 192/255, blue: 192/255)
        case .bronze: return Color(red: 205/255, green: 127/255, blue: 50/255)
        case .brown: return Color(red: 165/255, green: 42/255, blue: 42/255)
        case .beige: return Color(red: 245/255, green: 245/255, blue: 220/255)
        case .terracotta: return Color(red: 204/255, green: 78/255, blue: 92/255)
        case .gray: return Color(red: 128/255, green: 128/255, blue: 128/255)
        case .white: return Color(red: 1, green: 1, blue: 1)
        case .black: return Color(red: 0, green: 0, blue: 0)
        }
    }

    var colors: [Color] {
        switch self {
        case .silver: return [
            Color(red: 192/255, green: 192/255, blue: 192/255),
            Color(red: 224/255, green: 224/255, blue: 224/255),
            Color(red: 245/255, green: 245/255, blue: 245/255),
            Color(red: 200/255, green: 200/255, blue: 200/255),
            Color(red: 169/255, green: 169/255, blue: 169/255),
            Color(red: 224/255, green: 224/255, blue: 224/255),
        ]
        case .gold: return [
            Color(red: 212/255, green: 175/255, blue: 55/255),
            Color(red: 247/255, green: 215/255, blue: 91/255),
            Color(red: 1, green: 239/255, blue: 130/255),
            Color(red: 211/255, green: 175/255, blue: 55/255),
            Color(red: 184/255, green: 134/255, blue: 11/255),
            Color(red: 247/255, green: 215/255, blue: 91/255),
        ]
        case .bronze: return [
            Color(red: 205/255, green: 127/255, blue: 50/255),
            Color(red: 216/255, green: 139/255, blue: 75/255),
            Color(red: 196/255, green: 98/255, blue: 16/255),
            Color(red: 139/255, green: 69/255, blue: 19/255),
            Color(red: 205/255, green: 127/255, blue: 50/255),
            Color(red: 196/255, green: 98/255, blue: 16/255),
        ]
        default:
            return [Color.clear]
        }
    }
}
