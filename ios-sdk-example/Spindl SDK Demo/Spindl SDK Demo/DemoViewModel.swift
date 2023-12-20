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
