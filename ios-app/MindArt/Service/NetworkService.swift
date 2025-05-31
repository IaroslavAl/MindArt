//
//  NetworkService.swift
//  MindArt
//
//  Created by BELDIN Yaroslav on 30.10.2024.
//

import Foundation
import Networking

struct Service {
    let networkManager = NetworkManager()

    enum NetworkError: Error {
        case loadImage
    }

    func loadImage(
        color1: String,
        color2: String,
        color3: String,
        style: String
    ) async throws -> CloudFlareRequest.Response {
        let maxAttempts = 3
        var attempt = 0
        while attempt < maxAttempts {
            do {
                let request = CloudFlareRequest(
                    color1: color1,
                    color2: color2,
                    color3: color3,
                    style: style
                )
                let response = try await networkManager.execute(request: request)
                return response
            } catch {
                try await Task.sleep(nanoseconds: NSEC_PER_SEC)
                attempt += 1
                if attempt == maxAttempts {
                    throw error
                }
            }
        }
        throw NetworkError.loadImage
    }
}
