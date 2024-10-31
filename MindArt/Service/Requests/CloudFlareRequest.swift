//
//  CloudFlareRequest.swift
//  MindArt
//
//  Created by BELDIN Yaroslav on 30.10.2024.
//

import Foundation
import Networking

struct CloudFlareRequest: Request {
    typealias Response = Data
    typealias Error = NetworkError

    let color1: String
    let color2: String
    let color3: String
    let style: String

    var method: HTTPMethod { .get }
    var headers: HTTPHeaders { [] }

    var url: String {
        Path.cloudFlare + "/?color1=\(color1)&color2=\(color2)&color3=\(color3)&style=\(style)"
    }
}
