package net.crystopia.crystalshard.paper.core.messaging

enum class MessageType(val channel: String) {
    /**
     * Arguments: server name
     * Response: N/A
     */
    CONNECT("Connect"),

    /**
     * Arguments: server name
     * Response: N/A
     */
    CONNECT_OTHER("ConnectOther"),

    /**
     * Arguments: N/A
     * Response: IP, port
     */
    IP("IP"),

    /**
     * Arguments: player name
     * Response: player name, IP, port
     */
    IP_OTHER("IPOther"),

    /**
     * Arguments: server name
     * Response: server name, player count
     */
    PLAYER_COUNT("PlayerCount"),

    /**
     * Arguments: server name
     * Response: server name, CSV player names
     */
    PLAYER_LIST("PlayerList"),

    /**
     * Arguments: N/A
     * Response: CSV server names
     */
    GET_SERVERS("GetServers"),

    /**
     * Arguments: player name, message
     * Response: 	N/A
     */
    MESSAGE("Message"),

    /**
     * Arguments: player name, JSON chat component
     * Response: N/A
     */
    MESSAGE_RAW("MessageRaw"),

    /**
     * Arguments: N/A
     * Response: server name
     */
    GET_SERVER("GetServer"),

    /**
     * Arguments: player name
     * Response: player name, server name
     */
    GET_PLAYER_SERVER("GetPlayerServer"),

    /**
     * Arguments: server name
     * Response: server name, IP, port
     */
    SERVER_IP("ServerIp"),

    /**
     * Arguments: player name, reason
     * Response: N/A
     */
    KICK_PLAYER("KickPlayer"),

    /**
     * Arguments: player name, JSON chat component
     * Response: N/A
     */
    KICK_PLAYER_RAW("KickPlayerRaw"),

    /**
     * Arguments: server, subchannel, size of plugin message, message
     * Response: subchannel, size of plugin message, message
     */
    FORWARD("Forward"),

    /**
     * Arguments: player name, subchannel, size of plugin message, message
     * Response: subchannel, size of plugin message, message
     */
    FORWARD_TO_PLAYER("ForwardToPlayer"),
}