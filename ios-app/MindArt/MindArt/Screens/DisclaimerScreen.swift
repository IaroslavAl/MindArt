//
//  DisclaimerScreen.swift
//  MindArt
//
//  Created by BELDIN Yaroslav on 30.10.2024.
//

import SwiftUI

struct DisclaimerScreen: View {
    @EnvironmentObject
    private var viewModel: MindArtViewModel

    @EnvironmentObject
    private var languageSetting: LanguageSetting

    var body: some View {
        VStack(spacing: .zero) {
            Spacer()
            TextView(
                .init("DisclaimerScreen.Info"),
                font: .largeTitle
            )
            Spacer()
            button
            Spacer()
        }
        .padding(.horizontal, 32)
        .customBG(.bg2)
    }
}

private extension DisclaimerScreen {
    var button: some View {
        Button {
            viewModel.send(.setScreenState(.colorSelectionScreenFirst))
        } label: {
            ButtonLabel(.init("DisclaimerScreen.Continue"))
        }
    }
}

#Preview {
    DisclaimerScreen()
        .environmentObject(MindArtViewModel())
        .environmentObject(LanguageSetting())
}
