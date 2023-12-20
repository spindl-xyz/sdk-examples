//
//  ContentView.swift
//  Spindl SDK Demo
//
//  Created by מאיר רדנוביץ׳ on 21/11/2023.
//

import SwiftUI

struct ContentView: View {
    private enum FocusField : Hashable {
        case eventNameField
        case propertyKeyField
        case propertyValueField
    }
    
    @EnvironmentObject private var vm: DemoViewModel
    @State private var eventName: String = ""
    @State private var propertyKey: String = ""
    @State private var propertyValue: String = ""
    @FocusState private var focusedField: FocusField?
    
    var body: some View {
        VStack {
            Button {
                vm.trackRandomEvent()
            } label: {
                Text("Random Event")
            }
            .buttonStyle(.borderedProminent)
            
        }
        .padding()
        
        VStack {
            
            TextField(
                "New Event Name:",
                text: $eventName
            )
            .textFieldStyle(.roundedBorder)
            .focused($focusedField, equals: .eventNameField)
            
            TextField(
                "Property Key:",
                text: $propertyKey
            )
            .textFieldStyle(.roundedBorder)
            .focused($focusedField, equals: .propertyKeyField)
            
            TextField(
                "Value:",
                text: $propertyValue
            )
            .textFieldStyle(.roundedBorder)
            .focused($focusedField, equals: .propertyValueField)
            
            Button {
                vm.trackEvent(name: eventName, properties: [propertyKey:propertyValue])
                eventName = ""
                propertyKey = ""
                propertyValue = ""
                focusedField = .eventNameField
            } label: {
                Text("Track Event")
            }
            .buttonStyle(.borderedProminent)
            .disabled(eventName.isEmpty)
        
        }
        .padding()
        
        Label(vm.errorMessage, systemImage: "lightning.fill")
    }
}

#Preview {
    ContentView()
}