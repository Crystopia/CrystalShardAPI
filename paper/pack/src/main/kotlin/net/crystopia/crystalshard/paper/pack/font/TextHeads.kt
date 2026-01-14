package net.crystopia.crystalshard.paper.pack.font

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import net.kyori.adventure.key.Key
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import java.awt.image.BufferedImage
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.util.*
import javax.imageio.ImageIO
import kotlin.collections.get

/**
 * Makes custom Chat Heads from Player Skin.
 * And generate a Adventure Component
 * 
 * > **This requires a resource pack**
 */

object TextHeads {

    private val json = Json {
        prettyPrint = true
        ignoreUnknownKeys = true
        encodeDefaults = true
    }

    @Serializable
    private data class MojangProfile(
        val id: String? = null, val name: String? = null, val properties: MutableList<MojangProfileProperties>
    )

    @Serializable
    private data class MojangProfileProperties(
        val name: String? = null, val value: String? = null
    )

    @Serializable
    private data class MojangTexture(
        //     val timestamp: Int? = null,
        val profileId: String? = null,
        val profileName: String? = null,
        val textures: MojangTextureTextures? = null,
    )

    @Serializable
    private data class MojangTextureTextures(
        val SKIN: MojangTextureTexturesSKIN? = null,
        // CAPE...
    )

    @Serializable
    private data class MojangTextureTexturesSKIN(
        val url: String? = null
    )


    fun generateHead(
        uuid: UUID, overlay: Boolean, unicodeCharDefault: Char = '\uF000', pixelSize: Int = 8, font: String? = null
    ): Component {
        val url = URL("https://sessionserver.mojang.com/session/minecraft/profile/$uuid")
        val connection = url.openConnection() as HttpURLConnection
        connection.setRequestMethod("GET")

        BufferedReader(InputStreamReader(connection.getInputStream())).use { reader ->
            val response = StringBuilder()
            var line: String?
            while ((reader.readLine().also { line = it }) != null) {
                response.append(line)
            }
            reader.close()

            val jsonObject = json.decodeFromString<MojangProfile>(response.toString())
            val data = jsonObject.properties.map { (name, value) ->
                if (name == "textures") value
                else null
            }[0]

            val decodedBytes: ByteArray = Base64.getDecoder().decode(data)
            val decodedValue = String(decodedBytes)
            val textureJson = json.decodeFromString<MojangTexture>(decodedValue)

            return toComponent(
                pixelColorsFromSkin(textureJson.textures!!.SKIN!!.url, overlay), unicodeCharDefault, pixelSize, font
            )
        }
    }

    fun pixelColorsFromSkin(playerSkinUrl: String?, overlay: Boolean): Array<String?> {
        var overlay = overlay
        val colors = arrayOfNulls<String>(64)
        try {
            val skinImage: BufferedImage = ImageIO.read(URL(playerSkinUrl))

            if (skinImage.height < 64) {
                overlay = false
            }

            val faceStartX = 8
            val faceStartY = 8
            val faceWidth = 8
            val faceHeight = 8
            val overlayStartX = 40
            val overlayStartY = 8

            val faceImage = skinImage.getSubimage(faceStartX, faceStartY, faceWidth, faceHeight)

            var overlayImage: BufferedImage? = null
            if (overlay) {
                overlayImage = skinImage.getSubimage(overlayStartX, overlayStartY, faceWidth, faceHeight)
            }

            var index = 0
            for (x in 0..<faceHeight) {
                for (y in 0..<faceWidth) {
                    val rgbFace = faceImage.getRGB(x, y)

                    if (overlay && overlayImage != null) {
                        val rgbOverlay = overlayImage.getRGB(x, y)

                        if ((rgbOverlay shr 24) != 0x00) {
                            colors[index++] = String.format("#%06X", (rgbOverlay and 0xFFFFFF))
                            continue
                        }
                    }
                    colors[index++] = String.format("#%06X", (rgbFace and 0xFFFFFF))
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return colors
    }


    fun toComponent(
        hexColors: Array<String?>, unicodeCharDefault: Char = '\uF000', pixelSize: Int = 8, font: String? = null
    ): Component {
        require(hexColors.size >= 64) { "Hex colors must have at least 64 elements." }

        val mm = java.lang.StringBuilder()

        for (i in 0..63) {
            val unicodeChar = (unicodeCharDefault.code + (i % pixelSize) + 1).toChar()
            val pixel = when (i) {
                7, 15, 23, 31, 39, 47, 55 -> {
                    unicodeChar.toString() + "\uF101"
                }

                63 -> {
                    unicodeChar.toString()
                }

                else -> {
                    unicodeChar.toString() + "\uF102"
                }
            }

            mm.append("<color:").append(hexColors[i]).append(">").append(pixel).append("</color>")
        }

        return Component.text().append(
            MiniMessage.miniMessage().deserialize(mm.toString()).font(Key.key(font ?: "crystalshard:fontheads"))
        ).build()
    }


}