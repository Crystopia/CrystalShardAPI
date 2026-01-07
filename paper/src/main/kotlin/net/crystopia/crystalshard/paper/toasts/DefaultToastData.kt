package net.crystopia.crystalshard.extras.toasts

import net.crystopia.crystalshard.extras.resourcepacks.go

object DefaultToastData {

    val OFFSET_ADVANCEMENT = net.kyori.adventure.text.Component.text().go(9, '\uF109') {}.build()
    val OFFSET_ADVANCEMENT_TEXT =
        net.kyori.adventure.text.Component.text().go(17, '\uF109') {}.go(1, '\uF103') {}.build()

    
    val OFFSET_DEFAULT = net.kyori.adventure.text.Component.text().go(7, '\uF109') {}.build()
    val OFFSET_DEFAULT_TEXT = net.kyori.adventure.text.Component.text().go(23, '\uF109') {}.go(1, '\uF105') {}.build()

    
    val OFFSET_RECIPE = net.kyori.adventure.text.Component.text().go(9, '\uF109') {}.build()
    val OFFSET_RECIPE_TEXT = net.kyori.adventure.text.Component.text().go(17, '') {}.go(1, '\uF103') {}.build()

    
    val OFFSET_SYSTEM = net.kyori.adventure.text.Component.text().go(7, '\uF109') {}.build()
    val OFFSET_SYSTEM_TEXT =
        net.kyori.adventure.text.Component.text().go(15, '\uF109') {}.go(1, '\uF105') {}.go(1, '\uF101') {}.build()
    
    
    val OFFSET_TUTORIAL = net.kyori.adventure.text.Component.text().go(9, '\uF109') {}.build()
    val OFFSET_TUTORIAL_TEXT = net.kyori.adventure.text.Component.text().go(17, '') {}.go(1, '\uF103') {}.build()
}