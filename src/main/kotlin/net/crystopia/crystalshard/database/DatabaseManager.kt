package net.crystopia.crystalshard.database

import org.ktorm.database.Database

class DatabaseManager(
    url: String = "jdbc:mysql://localhost:3306/ktorm",
    username: String,
    password: String,
) {

    val database = Database.connect(
        url = url,
        driver = "com.mysql.jdbc.Driver",
        user = username,
        password = password
    )
    
    
    fun init(command: String) {
        database.useConnection { conn ->
            conn.createStatement().use { statement ->
                statement.executeUpdate(command)
            }
        }
    }
    
    
}