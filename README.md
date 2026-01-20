# CrystalShard API

**CrystalShard** is an open-source Minecraft library that provides packet handling, utilities, and server-side helpers to help you build plugins faster and cleaner.

It supports both **PaperMC** and **Velocity**, offering modular packages for GUIs, NPCs, resource packs, custom elements, and more.

---

## Modules

| Platform  | ID                 | Description                                                                 |
|-----------|--------------------|-----------------------------------------------------------------------------|
| PAPER     | `paper-core`       | Core methods and extensions for PaperMC                                     |
| PAPER     | `paper-box`        | Package-based GUI library for PaperMC *(WIP)*                                |
| PAPER     | `paper-custom`     | Utility library for custom elements and enhancements for Paper & Bukkit     |
| PAPER     | `paper-dhl`        | Package library with factories and helpers for PaperMC *(BETA)*              |
| PAPER     | `paper-pack`       | Resource pack utilities and helpers for PaperMC                              |
| PAPER     | `paper-panic`      | Experimental library for unsafe and unstable PaperMC/Bukkit features         |
| PAPER     | `paper-simulacrum` | Fancy NPCs, displays, and visual entities                                     |
| VELOCITY  | `velocity`         | Core utilities and extensions for Velocity                                   |

---

## Installation

CrystalShard is available via the public Maven repository:

**Repository:**  
https://repo.xyzhub.link/#/releases/net/crystopia/crystalshard

Add it to your `build.gradle.kts`:

```kotlin
maven {
    name = "xyzReleases"
    url = uri("https://repo.xyzhub.link/releases")
}
```

## Dependencies

```kotlin
// Paper
implementation("net.crystopia.crystalshard:paper-core:0.3.1")
implementation("net.crystopia.crystalshard:paper-box:0.3.1")       // 🚧 NOT DONE!
implementation("net.crystopia.crystalshard:paper-custom:0.3.1")
implementation("net.crystopia.crystalshard:paper-dhl:0.3.1")       // 🧪 BETA!
implementation("net.crystopia.crystalshard:paper-pack:0.3.1")
implementation("net.crystopia.crystalshard:paper-panic:0.3.1")
implementation("net.crystopia.crystalshard:paper-simulacrum:0.3.1")

// Velocity
implementation("net.crystopia.crystalshard:velocity:0.3.1")
```

## License

CrystalShard is licensed under the [MIT License](LICENSE), meaning it’s free to use, modify, and distribute.
See the LICENSE
file for more details.

## Credits

Special thanks to the following projects and developers for inspiration and ideas:
- FancyInnovations/FancyPlugins
- flytegg/twilight


> **Made with ❤️ for the Minecraft developer community.**
