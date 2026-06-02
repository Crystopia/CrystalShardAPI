package net.crystopia.crystalshard.dhl.versions.v1_21_1.converter.data.nbt

import com.google.gson.JsonElement
import com.mojang.serialization.JsonOps
import net.minecraft.nbt.CompoundTag
import net.minecraft.nbt.NbtIo
import net.minecraft.nbt.NbtOps
import net.minecraft.nbt.TagParser
import java.io.ByteArrayOutputStream
import java.io.IOException


fun CompoundTag.deserialize(nbt: String): CompoundTag {
    return TagParser.parseTag(nbt)
}

fun CompoundTag.serialize(data: JsonElement.() -> Unit) {
    // this.putInt("DataVersion", this.getDataVersion())
    val outputStream = ByteArrayOutputStream()

    try {
        NbtIo.writeCompressed(this, outputStream)
    } catch (ex: IOException) {
        throw RuntimeException(ex)
    }

    val jsonElement = NbtOps.INSTANCE.convertTo(JsonOps.INSTANCE, this)
    data.invoke(jsonElement)
}