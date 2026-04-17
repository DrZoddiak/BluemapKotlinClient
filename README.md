# BluemapKotlinClient
```kotlin
// Method should be suspended to make requests
suspend fun main() {
    RealtyClient().request { response : BluemapSerializableData.BlueBridge ->

        // Get a list of Markers to work with
        val markers : List<Marker> = response.worldguardBridge.markers
    }
}
```

## What does this do?
Basically just a client that provides serialized data for you to work with that provides data from Bluemap.

Given that the bluemap data presents itself as a key for the object, this is set at compile-time via annotation. If
you find this library and believe you wish to use it for personal purposes, you will likely have to change the UUID and
recompile with `gradle build`

## Dependencies
There aren't any dependencies — you will want to use Kotlin for this library, though it's potentially consumable in Java
it would be highly suggested against.

