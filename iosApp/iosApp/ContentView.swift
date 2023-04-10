import SwiftUI
import MultiPlatformLibrary
import mokoMvvmFlowSwiftUI

struct ContentView: View {
    @ObservedObject var vm: CounterViewModel = iOS_ViewModels().getCounterViewModel()
    
	var body: some View {
        Group {
            Text("\(vm.state(\.state))")
            Button(action: vm.increment) {
                Text("The increment")
            }
        }
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
