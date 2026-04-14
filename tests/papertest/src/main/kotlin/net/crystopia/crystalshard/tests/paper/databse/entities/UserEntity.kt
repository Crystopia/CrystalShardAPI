package net.crystopia.crystalshard.tests.paper.databse.entities

import org.ktorm.entity.Entity

interface UserEntity : Entity<UserEntity> {
    companion object : Entity.Factory<UserEntity>()

    val id: Int
    var name: String
    var password: String
    var enabled: Boolean
}