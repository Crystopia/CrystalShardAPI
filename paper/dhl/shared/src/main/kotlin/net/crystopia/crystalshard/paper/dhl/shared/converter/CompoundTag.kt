package net.crystopia.crystalshard.paper.dhl.shared.converter

import net.minecraft.nbt.CompoundTag
import net.minecraft.nbt.ListTag
import net.minecraft.nbt.Tag

fun CompoundTag.deserialize(data: MutableMap<String, Any>): CompoundTag {
    data.forEach { (key, data) ->
        when (data) {
            is String -> this.putString(key, data)
            is Int -> this.putInt(key, data)
            is IntArray -> this.putIntArray(key, data)
            is Long -> this.putLong(key, data)
            is LongArray -> this.putLongArray(key, data)
            is Float -> this.putFloat(key, data)
            is Double -> this.putDouble(key, data)
            is Boolean -> this.putBoolean(key, data)
            is ByteArray -> this.putByteArray(key, data)
            is Byte -> this.putByte(key, data)
            is Short -> this.putShort(key, data)
            is Map<*, *> -> deserialize(data.toMutableMap() as MutableMap<String, Any>)
            is List<*> -> {
                data as List<MutableMap<*, *>>
                data.forEach { mutableMap ->
                    deserialize(mutableMap as MutableMap<String, Any>)
                }

            }
        }
    }
    return this
}

fun CompoundTag.serialize(
    tag: Tag, data: MutableMap<String, Any> = mutableMapOf()
) {

    when (tag.id) {
        Tag.TAG_COMPOUND -> {
            if (!tag.asCompound().isEmpty) {
                val tag = tag.asCompound().get()
                tag.forEach { string, tag ->
                    if (tag.type.name == CompoundTag.TYPE.name) {
                        val compound: MutableMap<String, Any> = mutableMapOf()
                        data[string] = compound
                        serialize(tag, compound)
                    } else {
                        data[string] = tag
                    }

                }
            }
        }

        Tag.TAG_FLOAT -> {
            if (!tag.asFloat().isEmpty) data["FLOAT"] = tag.asFloat().get().toString()
        }

        Tag.TAG_SHORT -> {
            if (!tag.asShort().isEmpty) data["SHORT"] = tag.asShort().get().toString()
        }

        Tag.TAG_DOUBLE -> {
            if (!tag.asDouble().isEmpty) data["DOUBLE"] = tag.asDouble().get().toString()
        }

        Tag.TAG_STRING -> {
            if (!tag.asString().isEmpty) data["STRING"] = tag.asString().get()
        }

        Tag.TAG_BYTE -> {
            if (!tag.asByte().isEmpty) data["STRING"] = tag.asByte().get().toString()
        }

        Tag.TAG_BYTE_ARRAY -> {
            if (!tag.asByteArray().isEmpty) data["BYTE_ARRAY"] = tag.asByteArray().get().toString()
        }

        Tag.TAG_END -> {
            data["END"] = "null"
        }

        Tag.TAG_INT -> {
            if (!tag.asInt().isEmpty) data["INT"] = tag.asInt().get().toString()
        }

        Tag.TAG_INT_ARRAY -> {
            if (!tag.asIntArray().isEmpty) data["INT_ARRAY"] = tag.asIntArray().get().toString()
        }

        Tag.TAG_LONG -> {
            if (!tag.asLong().isEmpty) data["LONG"] = tag.asLong().get().toString()
        }

        Tag.TAG_LONG_ARRAY -> {
            if (!tag.asLongArray().isEmpty) data["LONG_ARRAY"] = tag.asLongArray().get().toString()
        }
    }
}