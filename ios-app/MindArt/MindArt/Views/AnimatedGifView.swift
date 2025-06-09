//
//  AnimatedGifView.swift
//  MindArt
//
//  Created by BELDIN Yaroslav on 08.06.2025.
//

import SwiftyGif
import SwiftUI

struct AnimatedGifView: UIViewRepresentable {
    let url =  Bundle.main.url(forResource: "loading", withExtension: ".gif")!

    func makeUIView(context: Context) -> UIImageView {
        let imageView = UIImageView(gifURL: url)
        imageView.contentMode = .scaleAspectFit
        imageView.frame.size = CGSize(width: 100, height: 100)
        return imageView
    }

    func updateUIView(_ uiView: UIImageView, context: Context) {
        uiView.setGifFromURL(url)
    }
}
