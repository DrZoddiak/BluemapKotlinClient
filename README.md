# BluemapKotlinClient
## Usage
### Setting up the client

```kotlin
// Direct the client to the URL for the data, should include the full url 
val client = RealtyClient("https://map.example.com/maps/world/live")

// You'll likely want to create your own version of the BlueBridgeSerializable Class
@Serializable
data class MyBridge(
    // You'll need to use the @SerialName annotation and provide the UUID found in the json
    @SerialName("BlueBridgeWG-0652e60a-dd04-4ec6-a803-be0c6caae1c8")
    val bridge: BridgeData,
)
```

### Making a request

```kotlin
// Method should be suspended to make requests
suspend fun main() {
    // Uses the client to request the player data from the map
    // the closure allows you to manipulate the data from the request
    val players : List<PlayerSerializable.Player> = client.requestPlayerData { data : PlayerSerializable.Players -> data.players }
    
    // We can make multiple requests with the same client
    // Here we specify the base request (MyBridge), and the type that is expected to be returned from the closure
    val regions : Collection<RegionSerializable.RegionMarker> = client.requestRegionData<MyBridge, Collection<RegionSerializable.RegionMarker>> { it : MyBridge ->
        it.bridge.markers.values
    }
    
    // Once we're done with the client, we should close it out!
    client.closeClient()
}
```

## What does this do?
Basically just a client that provides serialized data for you to work with that provides data from Bluemap.

## Dependencies
There aren't any dependencies — you will want to use Kotlin for this library, though it's potentially consumable in Java
it would be highly suggested against.

