import Foundation
import Capacitor
import Contacts

@objc(ContactsPluginPlugin)
public class ContactsPluginPlugin: CAPPlugin {
    
    @objc func echo(_ call: CAPPluginCall) {
        let value = call.getString("value") ?? ""
        call.resolve([
            "value": value
        ])
    }
    
    private func requestContactsPermission() {
        CNContactStore().requestAccess(for: .contacts, completionHandler: { (access, accessError) -> Void in})
    }
   
    private func isContactsPermissionGranted() -> Bool {
        switch CNContactStore.authorizationStatus(for: .contacts) {
        case .notDetermined, .restricted, .denied:
            return false
        case .authorized:
            return true
        @unknown default:
            return false
        }
    }
    
    @objc func getContacts(_ call: CAPPluginCall) {
        
        if !isContactsPermissionGranted() {
            requestContactsPermission()
        }
        
       let value = call.getString("filter") ?? ""
       // You could filter based on the value passed to the function!

        let contactStore = CNContactStore()
        
       
       var contacts = [Any]()
       let keys = [
               CNContactFormatter.descriptorForRequiredKeys(for: .fullName),
                   CNContactPhoneNumbersKey,
                   CNContactEmailAddressesKey
               ] as [Any]
       let request = CNContactFetchRequest(keysToFetch: keys as! [CNKeyDescriptor])

       contactStore.requestAccess(for: .contacts) { (granted, error) in
           if let error = error {
               print("failed to request access", error)
               call.reject("access denied")
               return
           }
           if granted {
              do {
                  try contactStore.enumerateContacts(with: request){
                        (contact, stop) in
                   contacts.append([
                        "firstName": contact.givenName,
                        "lastName": contact.familyName,
                        "telephone": contact.phoneNumbers.first?.value.stringValue ?? ""
                   ])
                  }
                  print(contacts)
                  call.resolve([
                      "results": contacts
                  ])
              } catch {
                  print("unable to fetch contacts")
                  call.reject("Unable to fetch contacts")
              }
           } else {
               print("access denied")
               call.reject("access denied")
           }
       }
   }
}
