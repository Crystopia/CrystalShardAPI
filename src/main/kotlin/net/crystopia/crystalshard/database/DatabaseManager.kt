package net.crystopia.crystalshard.database

import org.ktorm.database.Database

/**
 *
 * Small Database Manager to create Database connections and use of ktorm.
 *
 */
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


    /**
     *
     * Setup Method to create Tables in your Database.
     *
     */
    fun init(command: String) {
        try {
        database.useConnection { conn ->
            conn.createStatement().use { statement ->
                statement.executeUpdate(command)
            }
        }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    
    
}