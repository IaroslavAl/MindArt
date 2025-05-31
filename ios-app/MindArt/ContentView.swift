//
//  ContentView.swift
//  MindArt
//
//  Created by BELDIN Yaroslav on 30.10.2024.
//

import SwiftUI

struct ContentView: View {
    @StateObject
    var mindArtViewModel = MindArtViewModel()

    @StateObject
    var languageSetting = LanguageSetting()

    var body: some View {
        MindArtView(
            viewModel: mindArtViewModel,
            languageSetting: languageSetting
        )
        .environment(\.locale, languageSetting.locale)
    }
}

#Preview {
    ContentView()
}
