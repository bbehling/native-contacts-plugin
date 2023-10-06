import Foundation

@objc public class ContactsPlugins: NSObject {
    @objc public func echo(_ value: String) -> String {
        print(value)
        return value
    }
}
