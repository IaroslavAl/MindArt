//
//  MindArtViewModel.swift
//  MindArt
//
//  Created by BELDIN Yaroslav on 30.10.2024.
//

import SwiftUI

@MainActor
final class MindArtViewModel: ObservableObject {
    @Published
    private(set) var screenState = ScreenState.createdContentSelectionScreen

    @Published
    private(set) var resultScreenState = ResultScreenState.loading

    @Published
    private(set) var questionIndex = 0

    @Published
    private(set) var imageData: Data?

    var url: URL?
    var questions: [LocalizedStringKey] = []

    private var color1: String?
    private var color2: String?
    private var color3: String?
    private var style: String?

    enum ScreenState: Int, CaseIterable {
        case createdContentSelectionScreen = 0
        case disclaimerScreen
        case colorSelectionScreenFirst
        case colorSelectionScreenSecond
        case colorSelectionScreenThird
        case pictureStyleSelectionScreen
        case resultScreen
    }

    enum ResultScreenState {
        case loading
        case loaded
        case error
    }

    enum Action {
        case setScreenState(ScreenState)
        case selectTaskType(TaskType)
        case selectColor(CustomColor)
        case selectEventType(EventType)
        case loadImageData
        case refresh
    }

    func send(_ action: Action) {
        switch action {
        case let .setScreenState(screenState):
            self.screenState = screenState
        case let .selectTaskType(taskType):
            selectTaskType(taskType)
            Analytics.log(name: "ContentType", parameters: ["type": taskType.rawValue])
        case let .selectColor(color):
            switch screenState {
            case .colorSelectionScreenFirst:
                color1 = color.rawValue
                Analytics.log(name: "Color1", parameters: ["color": color.rawValue])
            case .colorSelectionScreenSecond:
                color2 = color.rawValue
                Analytics.log(name: "Color2", parameters: ["color": color.rawValue])
            case .colorSelectionScreenThird:
                color3 = color.rawValue
                Analytics.log(name: "Color3", parameters: ["color": color.rawValue])
            case .createdContentSelectionScreen, .disclaimerScreen, .pictureStyleSelectionScreen, .resultScreen:
                break
            }
            questionIndex += 1
        case let .selectEventType(eventType):
            style = eventType.rawValue
            Analytics.log(name: "EventType", parameters: ["type": eventType.rawValue])
        case .loadImageData:
            loadImageData()
            Analytics.log(name: "LoadImage")
        case .refresh:
            color1 = nil
            color2 = nil
            color3 = nil
            style = nil
            imageData = nil
            questions = .init()
            questionIndex = .zero
            resultScreenState = .loading
        }
    }
}

private extension MindArtViewModel {
    func selectTaskType(_ taskType: TaskType) {
        questions = switch taskType {
        case .goal:
            [
                LocalizedStringKey("ColorSelectionScreen.GoalFirstQuestion"),
                LocalizedStringKey("ColorSelectionScreen.GoalSecondQuestion"),
                LocalizedStringKey("ColorSelectionScreen.GoalThirdQuestion"),
            ]
        case .memories:
            [
                LocalizedStringKey("ColorSelectionScreen.MemoriesFirstQuestion"),
                LocalizedStringKey("ColorSelectionScreen.MemoriesSecondQuestion"),
                LocalizedStringKey("ColorSelectionScreen.MemoriesThirdQuestion"),
            ]
        }
    }

    func loadImageData() {
        Task {
            do {
                let service = NetworkService()
                let imageData = try await service.generateImage(
                    color1: color1 ?? "",
                    color2: color2 ?? "",
                    color3: color3 ?? "",
                    style: style ?? ""
                )
                writeData(imageData)
                resultScreenState = .loaded
            } catch {
                let randomNumber = Int.random(in: 0...9)
                if let data = UIImage(named: "image\(randomNumber)")?.pngData() {
                    writeData(data)
                    resultScreenState = .error
                }
                Analytics.log(name: "LoadImageError")
            }
        }
    }

    func writeData(_ data: Data) {
        imageData = data
        let tempDirectory = URL(fileURLWithPath: NSTemporaryDirectory())
        let fileURL = tempDirectory.appendingPathComponent("wallpaper.png")
        do {
            try data.write(to: fileURL)
            self.url = fileURL
        } catch {}
    }
}
