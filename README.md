# CrystalShard API
<hr />

![GitHub Tag](https://img.shields.io/github/v/tag/Crystopia/CrystalShardAPI)
[![CodeFactor](https://www.codefactor.io/repository/github/crystopia/crystalshardapi/badge/1.2.7)](https://www.codefactor.io/repository/github/crystopia/crystalshardapi/overview/1.2.7)


<hr />

**CrystalShard** is an open-source Minecraft library that provides packet handling, utilities, and server-side helpers to help you build plugins faster and cleaner.
It supports both **PaperMC** and **Velocity**, offering modular packages for GUIs, NPCs, resource packs, custom
elements, and more. <br />
Crystalshard used no Reflection. 100% Minecraft driven. Builds a Platform for Paper, Velocity, Folia and *(
Modded)*. <br />
Use the packages you need as a Shade-in Library. Crystalshard is builded for Kotlin.

---

## Modules

| Platform | ID                                                                                            | Description                                                             |
|----------|-----------------------------------------------------------------------------------------------|-------------------------------------------------------------------------|
| ALL      | [`traveler`](https://crystopia.github.io/CrystalShardAPI/traveler/html)                       | Extensions API for advanture, etc. and Minecraft+                       |
| PAPER    | [`paper:core`](https://crystopia.github.io/CrystalShardAPI/paper/paper-core/html)             | Core methods and extensions for PaperMC                                 |
| PAPER    | [`paper:folia`](https://crystopia.github.io/CrystalShardAPI/paper/paper-folia/html)           | Folia Addon for CrystalShard Paper.                                     |
| PAPER    | [`paper:box`](https://crystopia.github.io/CrystalShardAPI/paper/paper-box/html)               | Package-based GUI library for PaperMC *(WIP)*                           |
| PAPER    | [`paper:util`](https://crystopia.github.io/CrystalShardAPI/paper/paper-util/html)             | Utility library for custom elements and enhancements for Paper & Bukkit |
| PAPER    | [`paper:dhl`](https://crystopia.github.io/CrystalShardAPI/paper/paper-dhl/html)               | Package library with factories and helpers for Minecraft. *(BETA)*      |
| PAPER    | [`paper:panic`](https://crystopia.github.io/CrystalShardAPI/paper/paper-panic/html)           | Experimental library for unsafe and unstable PaperMC/Bukkit features    |
| PAPER    | [`paper:simulacrum`](https://crystopia.github.io/CrystalShardAPI/paper/paper-simulacrum/html) | Fancy NPCs, displays, and visual entities                               |
| VELOCITY | [`velocity`](https://crystopia.github.io/CrystalShardAPI/velocity/html)                       | Core utilities and extensions for Velocity                              |

---

## Installation

CrystalShard is available via the public Maven repository:

**Repository:**  
https://repo.jespersen.zip/#/releases/net/crystopia/crystalshard

Add it to your `build.gradle.kts`:

```kotlin
maven("https://repo.jespersen.zip/releases")
```

## Dependencies

![GitHub Tag](https://img.shields.io/github/v/tag/Crystopia/CrystalShardAPI)
```kotlin
// Traveler
implementation("net.crystopia.crystalshard:traveler:<version>")

// Paper
implementation("net.crystopia.crystalshard.paper:core:<version>")
implementation("net.crystopia.crystalshard.paper:box:<version>")      
implementation("net.crystopia.crystalshard.paper:util:<version>")
implementation("net.crystopia.crystalshard.paper:dhl:<version>")    
implementation("net.crystopia.crystalshard.paper:panic:<version>")
implementation("net.crystopia.crystalshard.paper:simulacrum:<version>")

// Velocity
implementation("net.crystopia.crystalshard:velocity:<version>")
```

<details>
  <summary>Dhl Releases</summary>

  ```kotlin
  // Dhl
implementation("net.crystopia.crystalshard:dhl:<version>")
implementation("net.crystopia.crystalshard.dhl:shared:<version>")
// mcVersion: The implementation of the Minecraft Version.
implementation("net.crystopia.crystalshard.dhl.versions:<mcVersion>:<version>")
  ```

</details>

## License

CrystalShard is licensed under the [MIT License](LICENSE), meaning it’s free to use, modify, and distribute.
See the LICENSE
file for more details.

## Credits

Special thanks to the following projects and developers for inspiration and ideas:
- FancyInnovations/FancyPlugins
- PaperMC
- Adventure Platform
- flytegg/twilight


> **Made with ❤️ for the Minecraft developer community.**
