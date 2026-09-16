# Offline Chat

A peer-to-peer chat app that works **without the internet**. Devices on the same local
network discover each other with Android's Network Service Discovery (NSD/mDNS), then
one device hosts a chat room over a raw TCP socket while the others join it. Recent
rooms are remembered locally with Room, so you can find them again quickly.

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://www.apache.org/licenses/LICENSE-2.0)

## Features

- **No server required** — chat runs over your LAN via mDNS discovery + TCP sockets.
- Host a room or join one from the discovered devices list.
- Real-time message feed with a simple send/receive client and server.
- Device list persisted locally with Room.
- MVVM architecture with Hilt dependency injection and Jetpack Navigation.

## Screenshots

|<img src="screenshots/screenshot_01.png" width="280" height="600">|<img src="screenshots/screenshot_02.png" width="280" height="600">|
|:---:|:---:|

## Architecture

```
app/
└── src/main/java/org/turkiye/offlinechat/
    ├── data/
    │   ├── entity/      # Room entities
    │   ├── local/       # Room database, DAOs, type converters
    │   ├── model/       # network/UI models
    │   └── remote/      # Retrofit API (optional REST layer)
    ├── di/              # Hilt modules (Api, App, Database)
    ├── repository/      # UsersRepository
    ├── ui/
    │   ├── activitys/   # MainActivity
    │   ├── adapters/    # RecyclerView / Array adapters
    │   ├── dialogs/     # join dialog
    │   └── fragments/   # main / client / server fragments + view models
    └── util/            # constants, extensions, IP helpers
```

The app follows **MVVM**: fragments observe `LiveData` exposed by `@HiltViewModel`
view models, which delegate to repositories backed by Room and the network layer.

## Tech stack

- **Kotlin** + **Coroutines / Flow**
- **Hilt (Dagger)** for dependency injection
- **Room** for local persistence (schema exported to `app/schemas`)
- **Retrofit + OkHttp** for the optional REST layer
- **Jetpack Navigation** for in-app navigation
- **ViewBinding** for type-safe view access

## Permissions

The app requests WiFi/network permissions so that mDNS discovery and the local socket
server can operate:

`INTERNET`, `ACCESS_NETWORK_STATE`, `ACCESS_WIFI_STATE`, `CHANGE_WIFI_STATE`,
`CHANGE_WIFI_MULTICAST_STATE`, `NEARBY_WIFI_DEVICES`.

All devices must be on the same network.

## Requirements

| Tool | Version |
| --- | --- |
| minSdk | 24 |
| compileSdk / targetSdk | 36 |
| Gradle | 8.14.5 |
| Android Gradle Plugin | 8.13.2 |
| Kotlin | 2.2.21 |
| JDK | 17 |

## Building

```bash
./gradlew assembleDebug      # debug APK
./gradlew assembleRelease    # release APK
```

## Author

**Ferhat OZCELIK**

- GitHub: [@ferhatozcelik](https://github.com/ferhatozcelik)
- LinkedIn: [ferhatozcelik](https://www.linkedin.com/in/ferhatozcelik/)

## License

Apache License 2.0 — see [LICENSE](LICENSE). If this project helped you, give it a ⭐️.
