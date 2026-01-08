package net.crystopia.crystalshard.paper.shared.util

import sun.misc.Unsafe

object ClassUtil {


    fun createUnsafeInstance(
    ): Unsafe {
        return Unsafe::class.java.getDeclaredConstructor().apply { isAccessible = true }.newInstance()
    }

    fun <T : Class<*>> initFakeInstance(
        clazz: T, callback: CustomClazz<T>.() -> Unit = {}
    ): Any {
        val obj = createUnsafeInstance().allocateInstance(
            clazz
        )
        val instance = CustomClazz(obj, clazz)
        instance.classInstance = clazz
        instance.classObject = obj
        callback(instance)

        return obj
    }


    class CustomClazz<T : Class<*>>(
        val obj: Any, val clazz: T
    ) {
        var classObject: Any? = null
        var classInstance: T? = null

        fun setField(name: String, value: Any) {
            val field = classInstance!!.getDeclaredField(name)
            field.isAccessible = true
            field.set(classObject, value)
        }

        fun setFinalField(name: String, value: Any) {
            val field = classInstance!!.getDeclaredField(name)

            // (c) https://github.com/FancyInnovations/FancyPlugins/blob/main/libraries/packets/packets-api/src/main/java/de/oliver/fancysitula/api/utils/reflections/ReflectionUtils.java#L43
            val unsafe = createUnsafeInstance()
            val offset: Long = unsafe.objectFieldOffset(field)
            if (field.type === Int::class.javaPrimitiveType) {
                unsafe.putInt(classObject, offset, value as Int)
            } else if (field.type === Long::class.javaPrimitiveType) {
                unsafe.putLong(classObject, offset, value as Long)
            } else if (field.type === Double::class.javaPrimitiveType) {
                unsafe.putDouble(classObject, offset, value as Double)
            } else if (field.type === Float::class.javaPrimitiveType) {
                unsafe.putFloat(classObject, offset, value as Float)
            } else if (field.type === Boolean::class.javaPrimitiveType) {
                unsafe.putBoolean(classObject, offset, value as Boolean)
            } else if (field.type === Byte::class.javaPrimitiveType) {
                unsafe.putByte(classObject, offset, value as Byte)
            } else if (field.type === Short::class.javaPrimitiveType) {
                unsafe.putShort(classObject, offset, value as Short)
            } else if (field.type === Char::class.javaPrimitiveType) {
                unsafe.putChar(classObject, offset, value as Char)
            } else {
                unsafe.putObject(classObject, offset, value)
            }
        }
    }
}