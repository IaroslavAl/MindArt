//
//  BackgroundView.swift
//  MindArt
//
//  Created by BELDIN Yaroslav on 30.10.2024.
//

import SwiftUI

struct BackgroundView: View {
    let image: Image

    var body: some View {
        image
            .resizable()
            .scaledToFill()
            .ignoresSafeArea()
    }
}

extension View {
    func customBG(_ imageResource: ImageResource) -> some View {
        self.background(BackgroundView(image: .init(imageResource)))
    }
}
