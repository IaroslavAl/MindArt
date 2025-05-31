//
//  MindArtView.swift
//  MindArt
//
//  Created by BELDIN Yaroslav on 30.10.2024.
//

import SwiftUI

struct MindArtView: View {
    @ObservedObject
    var viewModel: MindArtViewModel

    @ObservedObject
    var languageSetting: LanguageSetting

    var body: some View {
        screens
            .environmentObject(viewModel)
            .environmentObject(languageSetting)
            .environment(\.locale, languageSetting.locale)
            .overlay(alignment: .top) {
                progressBar
            }
    }
}

private extension MindArtView {
    var progressBar: some View {
        HStack(spacing: 4) {
            ForEach(MindArtViewModel.ScreenState.allCases, id: \.self) { index in
                Rectangle()
                    .frame(height: 4)
                    .cornerRadius(4)
                    .foregroundStyle(
                        index.rawValue <= viewModel.screenState.rawValue ? .color : .color.opacity(0.5)
                    )
                    .transaction {
                        $0.animation = nil
                        $0.disablesAnimations = true
                    }
            }
        }
        .frame(maxWidth: .infinity)
        .padding(.horizontal, 16)
        .padding(.top, 12)
    }

    @ViewBuilder
    var screens: some View {
        switch viewModel.screenState {
        case .createdContentSelectionScreen:
            CreatedContentSelectionScreen()
        case .disclaimerScreen:
            DisclaimerScreen()
        case .colorSelectionScreenFirst:
            ColorSelectionScreen()
        case .colorSelectionScreenSecond:
            ColorSelectionScreen()
        case .colorSelectionScreenThird:
            ColorSelectionScreen()
        case .pictureStyleSelectionScreen:
            PictureStyleSelectionScreen()
        case .resultScreen:
            ResultScreen()
        }
    }
}
