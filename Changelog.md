-------------------------
CHANGELOG - 10/1/2024 | PUSHED 10/1/2024
* Fixed improper distance formula (Swapped to built-in function)
* Changed Proxy priority. It will now happen before any other items.
-------------------------
CHANGELOG - 9/30/2024 | PUSHED 9/30/2024
* Fixed improper queue calls (never called onChatRecieve,
    improper message being edited)
* Fixed Proxies not disabling each other when activating multiple
* Fixed accidential call of ModItemProperties on the Server side
* Fixed LocalChatMessages not transferring tags when copying
* Fixed RadioItem.java not properly reacting to recieved messages
* Added Recipient (Player.class) to onChatRecieve() in IChatModifierObject
    to allow objects to access the recipient player
* * Source player can still be accessed via message.getOrigin()