//
//  Spindl_SDK_DemoApp.swift
//  Spindl SDK Demo
//
//  Created by מאיר רדנוביץ׳ on 21/11/2023.
//

import SwiftUI
import SpindlSDK

@main
struct Spindl_SDK_DemoApp: App {
    
    @StateObject var demoViewModel = DemoViewModel()
    
    init() {
        Spindl.initialize(apiKey: "<your api key>")
        
    }
    
    var body: some Scene {
        WindowGroup {
            ContentView()
                .environmentObject(demoViewModel)
        }
    }
}
