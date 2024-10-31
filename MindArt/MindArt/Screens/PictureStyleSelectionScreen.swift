//
//  PictureStyleSelectionScreen.swift
//  MindArt
//
//  Created by BELDIN Yaroslav on 30.10.2024.
//

import SwiftUI

struct PictureStyleSelectionScreen: View {
    @ObservedObject
    var viewModel: MindArtViewModel

    @ObservedObject
    var languageSetting: LanguageSetting

    var body: some View {
        VStack(spacing: .zero) {
            Spacer()
            TextView(
                .init("PictureStyleSelectionScreen.Question"),
                font: .largeTitle
            )
            Spacer()
            buttons
            Spacer()
        }
        .padding(.horizontal, 32)
        .customBG(.bg6)
    }
}

private extension PictureStyleSelectionScreen {
    var buttons: some View {
        VStack(spacing: 16) {
            ForEach(EventType.allCases) { eventType in
                Button {
                    viewModel.send(.selectEventType(eventType))
                    viewModel.send(.setScreenState(.resultScreen))
                } label: {
                    ButtonLabel(buttonText(eventType))
                }
            }
        }
    }

    func buttonText(_ eventType: EventType) -> LocalizedStringKey {
        switch eventType {
        case .light:
            return LocalizedStringKey("PictureStyleSelectionScreen.Light")
        case .significant:
            return LocalizedStringKey("PictureStyleSelectionScreen.Significant")
        case .dynamic:
            return LocalizedStringKey("PictureStyleSelectionScreen.Dynamic")
        case .tender:
            return LocalizedStringKey("PictureStyleSelectionScreen.Tender")
        }
    }
}

#Preview {
    PictureStyleSelectionScreen(
        viewModel: .init(),
        languageSetting: .init()
    )
}
