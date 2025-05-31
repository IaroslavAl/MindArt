//
//  NetworkService.swift
//  MindArt
//
//  Created by BELDIN Yaroslav on 30.10.2024.
//

import Foundation

struct NetworkService {
    enum Error: Swift.Error {
        case loadingFailed
    }

    private func performData(_ request: URLRequest) async throws -> Data {
        let (data, response) = try await URLSession.shared.data(for: request)

        print(#function, "status code:", (response as? HTTPURLResponse)?.statusCode ?? 0)
        guard let httpResponse = response as? HTTPURLResponse,
              (200...299).contains(httpResponse.statusCode) else {
            throw URLError(.badServerResponse)
        }

        return data
    }

    func generateImage(
        color1: String,
        color2: String,
        color3: String,
        style: String
    ) async throws -> Data {
        let maxAttempts = 3

        print(#function, "started \(color1), \(color2), \(color3), \(style)")
        for attempt in 0...maxAttempts {
            do {
                print(#function, "attempt: \(attempt)")
                let request = Requests.generateImageRequest(
                    color1: color1,
                    color2: color2,
                    color3: color3,
                    style: style
                ).request

                let imageData = try await performData(request)

                print(#function, "success")
                return imageData
            } catch {
                if attempt == maxAttempts {
                    print(#function, "failed")
                    throw Error.loadingFailed
                } else {
                    print(#function, "failed, retrying...")
                    try await Task.sleep(for: .seconds(1))
                }
            }
        }

        print(#function, "failed, max attempts reached")
        throw Error.loadingFailed
    }
}
