//
//  DemoViewModel.swift
//  Spindl SDK Demo
//
//  Created by מאיר רדנוביץ׳ on 21/11/2023.
//

import SpindlSDK
import SwiftUI

class DemoViewModel : ObservableObject {
    @Published var errorMessage = ""
    
//    private func randomEvent() -> Event<[String:String]> {
//        let dataName = String.random(of: 10)
//        let props = [String.random(of: 9):String.random(of: 7)]
//        let eventData = EventData(name: dataName, properties: props)
//        let identity = EventIdentity(address: String.random(of: 22), customerUserId: "\(String.random(of: 6))@\(String.random(of: 9)).\(String.random(of: 3))".lowercased())
//        let metadata = MobileSDKMetadata(persistentId: String.random(of: 12), ts: Date(timeIntervalSinceNow: Double.random(in: -340000000.0 ... -9.0)))
//        return Event(type: .custom, data: eventData, identity: identity, metadata: metadata)
//    }
//    
    func trackRandomEvent() {
        Task {
            let eventProperties = ["property1":Int.random(in: -47...470), "property2":Int.random(in: 200...7777)]
            
            do {
                try await Spindl.shared.track(name: "random event \(Double.random(in: -789.55...1254.777))", properties: eventProperties)
            } catch {
                let msg = "Couldn't initialise Spindl SDK: \(error.localizedDescription)"
                print(msg)
                errorMessage = msg
                
            }
        }
    }
    
    func trackEvent(name: String, properties: [String:String]) {
        Task {
            do {
                try await Spindl.shared.track(name: name, properties: properties)
            } catch {
                let msg = "Couldn't track event [\(name):\(properties)] because \(error.localizedDescription)"
                print(msg)
                errorMessage = msg
            }
        }
    }
}
