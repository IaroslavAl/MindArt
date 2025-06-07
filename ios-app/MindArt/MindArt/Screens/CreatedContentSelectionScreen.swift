//
//  CreatedContentSelectionScreen.swift
//  MindArt
//
//  Created by BELDIN Yaroslav on 30.10.2024.
//

import SwiftUI

struct CreatedContentSelectionScreen: View {
    @EnvironmentObject
    private var viewModel: MindArtViewModel

    @EnvironmentObject
    private var languageSetting: LanguageSetting

    var body: some View {
        VStack(spacing: .zero) {
            Spacer()
            TextView(
                .init("CreatedContentSelectionScreen.Message"),
                font: .largeTitle
            )
            Spacer()
            buttons
            Spacer()
        }
        .padding(.horizontal, 32)
        .customBG(.bg1)
        .overlay(alignment: .bottomTrailing) {
            toolbarItem()
        }
    }
}

private extension CreatedContentSelectionScreen {
    func toolbarItem() -> some View {
        Menu {
            Button {
                languageSetting.setLanguage("en_EN")
                Analytics.log(name: "SetLanguage", parameters: ["language": "EN"])
            } label: {
                Text(LocalizedStringKey("English"))
            }
            Button {
                languageSetting.setLanguage("ru_RU")
                Analytics.log(name: "SetLanguage", parameters: ["language": "RU"])
            } label: {
                Text(LocalizedStringKey("Russian"))
            }
        } label: {
            Text(languageSetting.locale.identifier == "ru_RU" ? "RU" : "EN")
                .font(.title)
                .foregroundStyle(.color)
                .fontWeight(.medium)
        }
        .padding(.trailing, 32)
    }

    var buttons: some View {
        VStack(spacing: 20) {
            ForEach(TaskType.allCases) { taskType in
                button(taskType)
            }
        }
    }

    func button(_ taskType: TaskType) -> some View {
        Button {
            viewModel.send(.selectTaskType(taskType))
            viewModel.send(.setScreenState(.disclaimerScreen))
        } label: {
            ButtonLabel(buttonText(taskType))
        }
    }

    func buttonText(_ taskType: TaskType) -> LocalizedStringKey {
        switch taskType {
        case .goal:
            return LocalizedStringKey("CreatedContentSelectionScreen.Goal")
        case .memories:
            return LocalizedStringKey("CreatedContentSelectionScreen.Memories")
        }
    }
}

#Preview {
    CreatedContentSelectionScreen()
        .environmentObject(MindArtViewModel())
        .environmentObject(LanguageSetting())
}
