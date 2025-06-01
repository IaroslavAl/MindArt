//
//  ButtonLabel.swift
//  MindArt
//
//  Created by BELDIN Yaroslav on 30.10.2024.
//

import SwiftUI

struct ButtonLabel: View {
    let text: Text

    init(_ text: String) {
        self.text = .init(text)
    }

    init(_ localizedStringKey: LocalizedStringKey) {
        self.text = .init(localizedStringKey)
    }

    var body: some View {
        text
            .textCase(.lowercase)
            .font(.largeTitle)
            .foregroundStyle(.color)
            .frame(maxWidth: .infinity)
            .padding(20)
            .background(.white)
            .cornerRadius(9)
            .shadow(color: Color.black.opacity(0.2), radius: 10, x: 10, y: 10)
            .shadow(color: Color.white.opacity(0.7), radius: 10, x: -5, y: -5)
            .tracking(3)
    }
}

#Preview {
    ButtonLabel("ButtonText")
        .padding()
}
