//
//  ColorSelectionScreen.swift
//  MindArt
//
//  Created by BELDIN Yaroslav on 30.10.2024.
//

import SwiftUI

struct ColorSelectionScreen: View {
    @EnvironmentObject
    private var viewModel: MindArtViewModel

    @EnvironmentObject
    private var languageSetting: LanguageSetting

    var body: some View {
        VStack(spacing: .zero) {
            Spacer()
            if viewModel.questions.indices.contains(viewModel.questionIndex) {
                TextView(
                    viewModel.questions[viewModel.questionIndex],
                    font: .largeTitle
                )
                .minimumScaleFactor(0.5)
                .padding(.top)
            }
            Spacer(minLength: 16)
            LazyVGrid(
                columns: .init(
                    repeating: .init(),
                    count: 5
                ), spacing: 16
            ) {
                ForEach(CustomColor.allCases, id: \.color) { customColor in
                    Button {
                        if viewModel.questionIndex <= viewModel.questions.count - 1 {
                            viewModel.send(.selectColor(customColor))
                            switch viewModel.screenState {
                            case .colorSelectionScreenFirst:
                                viewModel.send(.setScreenState(.colorSelectionScreenSecond))
                            case .colorSelectionScreenSecond:
                                viewModel.send(.setScreenState(.colorSelectionScreenThird))
                            default:
                                break
                            }
                            if viewModel.questionIndex == 3 {
                                viewModel.send(.setScreenState(.pictureStyleSelectionScreen))
                            }
                        }
                    } label: {
                        label(customColor)
                    }
                }
            }
            .padding(.bottom)
        }
        .padding(.horizontal)
        .customBG(backgroundImage)
    }
}

private extension ColorSelectionScreen {
    var backgroundImage: ImageResource {
        switch viewModel.screenState {
        case .colorSelectionScreenFirst: .bg3
        case .colorSelectionScreenSecond: .bg4
        case .colorSelectionScreenThird: .bg5
        default: .bg3
        }
    }

    func label(_ customColor: CustomColor) -> some View {
        Group {
            if customColor == .silver
                || customColor == .gold
                || customColor == .bronze {
                LinearGradient(
                    gradient: Gradient(colors: customColor.colors),
                    startPoint: .topLeading,
                    endPoint: .bottomTrailing
                )
            } else {
                customColor.color
            }
        }
        .frame(width: 55, height: 55)
        .background(.white)
        .cornerRadius(3)
        .overlay(
            RoundedRectangle(cornerRadius: 3)
                .stroke(Color.color, lineWidth: 0.5)
        )
        .shadow(color: Color.black.opacity(0.2), radius: 10, x: 10, y: 10)
        .shadow(color: Color.white.opacity(0.7), radius: 10, x: -5, y: -5)
    }
}

#Preview {
    ColorSelectionScreen()
        .environmentObject(MindArtViewModel())
        .environmentObject(LanguageSetting())
}
