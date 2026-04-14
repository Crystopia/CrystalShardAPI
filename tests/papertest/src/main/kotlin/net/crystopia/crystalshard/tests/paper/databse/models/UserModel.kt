package net.crystopia.crystalshard.tests.paper.databse.models

import net.crystopia.crystalshard.tests.paper.databse.entities.UserEntity
import org.ktorm.database.Database
import org.ktorm.entity.sequenceOf
import org.ktorm.schema.Table
import org.ktorm.schema.boolean
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object UserModel : Table<UserEntity>("users") {
    val id = int("id").primaryKey().bindTo { it.id }
    val name = varchar("name").bindTo { it.name }
    val password = varchar("password").bindTo { it.password }
    val enabled = boolean("enabled").bindTo { it.enabled }
}

val Database.users get() = this.sequenceOf(UserModel)