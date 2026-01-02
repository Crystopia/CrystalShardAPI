package net.crystopia.crystalshard.shared.enums.server

enum class ServerVersion(val version: String, val protocol: Int) {
    v1_21_11("1.21.11", 774), v1_21_10("1.21.10", 773), v1_21_9("1.21.9", 773), v1_21_8(
        "1.21.8", 772
    ),
    v1_21_7("1.21.7", 772), v1_21_6("1.21.6", 771), v1_21_5("1.21.5", 770), v1_21_4("1.21.4", 769), v1_21_3(
        "1.21.3", 768
    ),
    v1_21_1("1.21.1", 767), v1_21("1.21", 767), UNKNOWN("?.?.?", 0);

    companion object {
        fun getServerVersion(version: String, protocol: Int): ServerVersion? {
            for (serverVersion in entries) {
                if (serverVersion.version == version && serverVersion.protocol == protocol) {
                    return serverVersion
                }
            }

            return null
        }

        fun getServerVersionByProtocol(protocol: Int): ServerVersion? {
            for (serverVersion in entries) {
                if (serverVersion.protocol == protocol) {
                    return serverVersion
                }
            }

            return null
        }

        fun getServerVersionByVersion(version: String): ServerVersion? {
            for (serverVersion in entries) {
                if (serverVersion.version == version) {
                    return serverVersion
                }
            }

            return null
        }

        fun getVersions(): MutableList<ServerVersion> {
            return entries.toMutableList()
        }

    }
}