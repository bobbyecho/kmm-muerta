import SwiftUI
import MultiPlatformLibrary

@main
struct iOSApp: App {
    init() {
        KoinShared().initalize_iOS()
    }
	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
