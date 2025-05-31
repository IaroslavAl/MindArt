//
//  Requests.swift
//  MindArt
//
//  Created by BELDIN Yaroslav on 30.10.2024.
//

import Foundation

enum Requests {
    case generateImageRequest(color1: String, color2: String, color3: String, style: String)

    var request: URLRequest {
        switch self {
        case .generateImageRequest(color1: let color1, color2: let color2, color3: let color3, style: let style):
            var components = URLComponents()
            components.scheme = "https"
            components.host = Host.cloudFlareImageGenerator
            components.path = ""
            components.queryItems = [
                URLQueryItem(name: "color1", value: color1),
                URLQueryItem(name: "color2", value: color2),
                URLQueryItem(name: "color3", value: color3),
                URLQueryItem(name: "style", value: style),
            ]
            print(components.url)
            var request = URLRequest(url: components.url!)
            request.httpMethod = "GET"

            return request
        }
    }
}
