# CrystalShard API
<hr />

![GitHub Release](https://img.shields.io/github/v/release/Crystopia/CrystalShardAPI)
![GitHub Actions Workflow Status](https://img.shields.io/github/actions/workflow/status/Crystopia/CrystalShardAPI/release-common.yml?label=crystalshard-common)
![GitHub Actions Workflow Status](https://img.shields.io/github/actions/workflow/status/Crystopia/CrystalShardAPI/release-paper.yml?label=crystalshard-paper)
![GitHub Actions Workflow Status](https://img.shields.io/github/actions/workflow/status/Crystopia/CrystalShardAPI/release-velocity.yml?label=crystalshard-velocity)


<hr />

**CrystalShard** is an open-source Minecraft library that provides packet handling, utilities, and server-side helpers to help you build plugins faster and cleaner.

It supports both **PaperMC** and **Velocity**, offering modular packages for GUIs, NPCs, resource packs, custom elements, and more.

---

## Modules

| Platform  | ID                      | Description                                                                 |
|-----------|-------------------------|-----------------------------------------------------------------------------|
| ALL       | [`common`](https://crystopia.github.io/CrystalShardAPI/common/html)                | Common utils and extensions for crystalshard                                |
| PAPER     | [`paper-core`](https://crystopia.github.io/CrystalShardAPI/paper/paper-core/html)            | Core methods and extensions for PaperMC                                     |
| PAPER     | [`paper-box`](https://crystopia.github.io/CrystalShardAPI/paper/paper-box/html)             | Package-based GUI library for PaperMC *(WIP)*                               |
| PAPER     | [`paper-custom`](https://crystopia.github.io/CrystalShardAPI/paper/paper-custom/html)          | Utility library for custom elements and enhancements for Paper & Bukkit     |
| PAPER     | [`paper-dhl`](https://crystopia.github.io/CrystalShardAPI/paper/paper-dhl/html)             | Package library with factories and helpers for PaperMC *(BETA)*             |
| PAPER     | [`paper-pack`](https://crystopia.github.io/CrystalShardAPI/paper/paper-pack/html)            | Resource pack utilities and helpers for PaperMC                             |
| PAPER     | [`paper-panic`](https://crystopia.github.io/CrystalShardAPI/paper/paper-panic/html)           | Experimental library for unsafe and unstable PaperMC/Bukkit features        |
| PAPER     | [`paper-simulacrum`](https://crystopia.github.io/CrystalShardAPI/paper/paper-simulacrum/html)      | Fancy NPCs, displays, and visual entities                                   |
| VELOCITY  | [`velocity`](https://crystopia.github.io/CrystalShardAPI/velocity/html)              | Core utilities and extensions for Velocity                                  |

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
// Common
implementation("net.crystopia.crystalshard:paper-common:<version>")

// Paper
implementation("net.crystopia.crystalshard:paper-core:<version>")
implementation("net.crystopia.crystalshard:paper-box:<version>")       // 🚧 NOT DONE!
implementation("net.crystopia.crystalshard:paper-custom:<version>")
implementation("net.crystopia.crystalshard:paper-dhl:<version>")       // 🧪 BETA!
implementation("net.crystopia.crystalshard:paper-pack:<version>")
implementation("net.crystopia.crystalshard:paper-panic:<version>")
implementation("net.crystopia.crystalshard:paper-simulacrum:<version>")

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
