//
//  Untitled.swift
//  MindArt
//
//  Created by BELDIN Yaroslav on 30.10.2024.
//

import SwiftUI

struct TextView: View {
    let text: Text
    let font: Font

    init(_ text: String, font: Font) {
        self.text = .init(text)
        self.font = font
    }

    init(_ localizedStringKey: LocalizedStringKey, font: Font) {
        self.text = .init(localizedStringKey)
        self.font = font
    }

    var body: some View {
        text
            .font(font)
            .foregroundStyle(.color)
            .frame(maxWidth: .infinity)
            .multilineTextAlignment(.center)
    }
}
