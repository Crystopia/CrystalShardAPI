package net.crystopia.crystalshard.common.database.custom

import org.ktorm.database.Database

class SQLDatabase {

    private var url: String = "jdbc:mysql://localhost:3306/ktorm"
    private var username: String? = null
    private var password: String? = null

    fun init(
        url: String = "jdbc:mysql://localhost:3306/ktorm",
        username: String,
        password: String,
    ): SQLDatabase {
        this.url = url
        this.username = username
        this.password = password
        return this
    }

    var database = Database.connect(
        url = url,
        driver = "com.mysql.jdbc.Driver",
        user = username,
        password = password
    )

    /**
     *
     * Setup Method to execute SQL Queries
     *
     */
    fun init(command: String): SQLDatabase {
        try {
            database.useConnection { conn ->
                conn.createStatement().use { statement ->
                    statement.executeUpdate(command)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return this
    }

    fun preload(callback: Database.() -> Unit): SQLDatabase {
        callback(database)
        return this
    }

}