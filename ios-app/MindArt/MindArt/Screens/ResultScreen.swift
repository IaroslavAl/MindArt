//
//  ResultScreen.swift
//  MindArt
//
//  Created by BELDIN Yaroslav on 30.10.2024.
//

import SwiftUI
import Lottie
import SwiftUIGIF

struct ResultScreen: View {
    @EnvironmentObject
    private var viewModel: MindArtViewModel

    @EnvironmentObject
    private var languageSetting: LanguageSetting

    var body: some View {
        Group {
            switch viewModel.resultScreenState {
            case .loading:
                loadingView
            case .loaded:
                loadedView
            case .error:
                loadedView
            }
        }
        .transition(.opacity)
        .animation(.easeInOut(duration: 0.25), value: viewModel.resultScreenState)
        .task {
            viewModel.send(.loadImageData)
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity)
    }
}

private extension ResultScreen {
    var loadingView: some View {
        VStack(spacing: .zero) {
            Spacer()
            GIFImage(name: "loading")
            Spacer()
            TextView(
                .init("ResultScreen.ImageCreating"),
                font: .largeTitle
            )
            .padding(.horizontal, 32)
            .padding(.bottom, 32)
        }
    }

    var loadedView: some View {
        VStack(spacing: .zero) {
            Spacer()
            Button {
                saveImage()
            } label: {
                ButtonLabel(.init("ResultScreen.Download"))
            }
            .padding(.bottom)
            Button {
                viewModel.send(.refresh)
                viewModel.send(.setScreenState(.createdContentSelectionScreen))
                Analytics.log(name: "Repeat")
            } label: {
                ButtonLabel(.init("ResultScreen.Repeat"))
            }
        }
        .padding(32)
        .background(background.ignoresSafeArea())
        .onAppear {
            Analytics.log(name: "ImageShown")
        }
    }

    func saveImage() {
        Analytics.log(name: "SaveImage")
        guard let url = viewModel.url else { return }
        let activity = UIActivityViewController(
            activityItems: [url],
            applicationActivities: nil
        )
        if let windowScene = UIApplication.shared.connectedScenes.first as? UIWindowScene,
           let keyWindow = windowScene.windows.first(where: \.isKeyWindow) {
            keyWindow.rootViewController?.present(
                activity,
                animated: true,
                completion: nil
            )
        }
    }

    @ViewBuilder
    var background: some View {
        if let data = viewModel.imageData,
           let image = UIImage(data: data){
            Image(uiImage: image)
                .resizable()
                .scaledToFill()
                .ignoresSafeArea()
        }
    }
}

#Preview {
    ResultScreen()
        .environmentObject(MindArtViewModel())
        .environmentObject(LanguageSetting())
}
