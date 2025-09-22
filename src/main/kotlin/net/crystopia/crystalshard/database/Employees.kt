package net.crystopia.crystalshard.database

import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object Employees : Table<Nothing>("test") {
    val id = int("id").primaryKey()
    val name = varchar("name")
    val location = varchar("location")
}