//
//  MindArtApp.swift
//  MindArt
//
//  Created by BELDIN Yaroslav on 30.10.2024.
//

import SwiftUI
import AppMetricaCore

@main
struct MindArtApp: App {
    @UIApplicationDelegateAdaptor(AppDelegate.self) private var delegate

    var body: some Scene {
        WindowGroup {
            ContentView()
                .preferredColorScheme(.light)
        }
    }
}

final class AppDelegate: NSObject, UIApplicationDelegate {
    func application(
        _ application: UIApplication,
        didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]? = nil
    ) -> Bool {
        #if !DEBUG
        let apiKey = "e3533dfe-ca91-4b48-8571-7d37e99969db"
        if let configuration = AppMetricaConfiguration(apiKey: apiKey) {
            AppMetrica.activate(with: configuration)
        }
        #endif
        return true
    }
}
